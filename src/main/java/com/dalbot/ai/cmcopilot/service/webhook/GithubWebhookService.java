package com.dalbot.ai.cmcopilot.service.webhook;

import com.dalbot.ai.cmcopilot.dto.github.pr.GithubPullRequestPayload;
import com.dalbot.ai.cmcopilot.dto.github.pr.annotations.PerformanceAnalysis;
import com.dalbot.ai.cmcopilot.dto.github.pr.files.ChangedFile;
import com.dalbot.ai.cmcopilot.repository.github.GithubRepository;
import com.dalbot.ai.cmcopilot.service.code.ProjectContext;
import com.dalbot.ai.cmcopilot.utils.CodeUtils;
import com.dalbot.ai.cmcopilot.utils.GithubUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.output.TeeOutputStream;
import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.dalbot.ai.cmcopilot.utils.CodeUtils.hasElegibleCodeExtension;

@Service
public class GithubWebhookService {

    @Value("${github.api.key}")
    private String githubApiKey;
    private final GithubRepository githubRepo;

    public GithubWebhookService(GithubRepository githubRepo) {
        this.githubRepo = githubRepo;
    }

    public void triggerPullRequestFlow(GithubPullRequestPayload payload) throws IOException {
        improveCodePerformance(payload);
    }

    private void improveCodePerformance(GithubPullRequestPayload payload) throws IOException {
        // BASE
        //download project to local system
        ProjectContext pc = ProjectContext.builder()
                .id(UUID.randomUUID())
                .payload(payload)
                .build();

        Optional<File> project = GithubUtils.cloneProject(
                "gratski", githubApiKey, payload.getRepository().getClone_url(), pc.getId());
        pc.setProjectDirectory(project.orElseThrow());
        pc.setBuildTool(identifyBuildTool(pc));
        pc.setChangedFiles(identifyChangedFiles(pc));
        pc.setUnitTestFilesPath(identifyUnitTestFiles(pc, pc.getChangedFiles()));

        // STEP 1 (original)
        // execute unit tests and store metrics
        if(!isPassingTests(pc)) {
            throw new IllegalArgumentException("Tests Execution Error: All tests must be green before passing to Generation Algorithm Step.");
        }

        // STEP 2 (generated code for improvements)
        // genetic algorithm to improve code performance
        //// generate a better code using the same imports (something that should change over time V2 maybe)
        //// run the existing unit tests and store the metrics
        ////    if the genereations limit has ben reached, then stop

        // STEP 3
        // Return the most performant code

    }

    private boolean isPassingTests(ProjectContext pc) throws IOException {
        File outputLog = new File(pc.getProjectDirectory().getAbsolutePath(), "output.log");

        InvocationOutputHandler outputHandler = new PrintStreamHandler(
                new PrintStream(new TeeOutputStream(System.out, Files.newOutputStream(outputLog.toPath())), true, StandardCharsets.UTF_8),
                true);

        InvocationRequest mavenRequest = new DefaultInvocationRequest();
        mavenRequest.setPomFile(new File(String.format("%s/pom.xml", pc.getProjectDirectory())));
        mavenRequest.setGoals(Collections.singletonList("test -Dtest " +
                        pc.getUnitTestFilesPath().stream()
                            .map(path -> path.getFileName().toFile().getName())
                            .collect(Collectors.joining(" ", "", ","))
                        ));
        mavenRequest.setOutputHandler(outputHandler);

        // TODO fix this code to be printed with comma separation
        Invoker mavenInvoker = new DefaultInvoker();
        File invokerLog = new File(pc.getProjectDirectory().getAbsolutePath(), "invoker.log");
        PrintStreamLogger logger = new PrintStreamLogger(new PrintStream(new FileOutputStream(invokerLog), false, StandardCharsets.UTF_8),
                InvokerLogger.DEBUG);
        mavenInvoker.setLogger(logger);


        try {
            InvocationResult invocationResult = mavenInvoker.execute(mavenRequest);
            if(invocationResult.getExitCode() != 0) {
                System.out.println("Maven exited with non zero exit code.");
            }

            // TODO check if the test results log has no errors
            List<String> output = Files.readAllLines(outputLog.toPath());
            return true;

        } catch (MavenInvocationException e) {
            return false;
        }
    }

    @PerformanceAnalysis
    private List<Path> identifyUnitTestFiles(ProjectContext pc, List<ChangedFile> changedFiles) {
        return changedFiles.stream()
                .filter(file -> hasElegibleCodeExtension(file.getFilename()))
                .map(file -> {
                    try {
                        return CodeUtils.identifyTestFilename(pc, file.getFilename());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    private String filenameExists(File root, String fname) {
        try {
            boolean recursive = true;
            Collection<File> files = FileUtils.listFiles(root, null, recursive);

            for (File file : files) {
                if (file.getName().equals(fname))
                    return file.getAbsolutePath();
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    private List<ChangedFile> identifyChangedFiles(ProjectContext pc) {
        Integer pullRequestId = pc.getPayload().getPull_request().getNumber();
        return githubRepo.GetPullRequestAffectedFiles(
                "Bearer "+ githubApiKey,
                pc.getPayload().getRepository().getOwner().getLogin(),
                pc.getPayload().getRepository().getName(),
                pullRequestId);
    }

    private ProjectContext.BuildTool identifyBuildTool(ProjectContext pc) {
        if(isMaven(pc)){
            return ProjectContext.BuildTool.MAVEN;
        }
        return ProjectContext.BuildTool.GRADLE;
    }

    private Boolean isMaven(ProjectContext pc) {
        if(!pc.getProjectDirectory().isDirectory()) {
            return false;
        }
        String[] filesList = pc.getProjectDirectory().list(new NameFileFilter("pom.xml"));
        return filesList != null && filesList.length != 0;
    }

}
