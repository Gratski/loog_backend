package com.dalbot.ai.cmcopilot.service.code;

import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisRequestDTO;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRepository;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
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
import java.nio.file.Files;
import java.util.*;

@Service
public class CodeService {

    @Value("${openai.api.key}")
    private String openAIApiKey;

    private final OpenAIRepository repository;

    public CodeService(OpenAIRepository repository) {
        this.repository = repository;
    }

    public String performCodeAnalysis(CodeAnalysisRequestDTO dto) {
        OpenAIRequestEntities.ChatResponse fetchCompletionResult =
                repository.completeTextV2("Bearer " + openAIApiKey, dto);
        return fetchCompletionResult.getChoices().get(0).getMessage().getContent();
    }

    public void improveCodePerformance(Map<String, Object> githubPayload, String repoUrl, String filename) throws IOException {

        // BASE
        //download project to local system
        Optional<File> project =
                GithubUtils.cloneProject(
                        repoUrl,
                        "/Users/joaorodrigues/Documents/software/projects/ai/cmcopilot/cmcopilot");

        ProjectContext pc = project
                .map(file -> ProjectContext.builder().projectDirectory(file).build())
                .orElseThrow();

        // find build tool to be used
        pc.setBuildTool(extractBuildTool(pc));

        // identify the files changes
        pc.setChangedFiles(extractChangedFiles(githubPayload));

        // check if the changed files are being tested
        // find unit tests for changed files (break if there are no tests found)
        pc.setUnitTestFiles(extractUnitTestFiles(pc, pc.getChangedFiles()));
        if(pc.getChangedFiles().size() != pc.getUnitTestFiles().size()) {
            throw new IllegalArgumentException("" +
                    "The changed files are missing unit tests. " +
                    "Unit tests are required for all files for code performance improvement operations");
        };

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
                new PrintStream(new TeeOutputStream(System.out, Files.newOutputStream(outputLog.toPath())), true, "UTF-8"),
                true);
        InvocationRequest mavenRequest = new DefaultInvocationRequest();
        mavenRequest.setPomFile(new File(String.format("%s/pom.xml", pc.getProjectDirectory())));
        // TODO fix this code to be printed with comma separation
        mavenRequest.setGoals(Collections.singletonList("test -Dtest " + pc.getUnitTestFiles().toString()));
        mavenRequest.setOutputHandler(outputHandler);

        Invoker mavenInvoker = new DefaultInvoker();
        File invokerLog = new File(pc.getProjectDirectory().getAbsolutePath(), "invoker.log");
        PrintStreamLogger logger = new PrintStreamLogger(new PrintStream(new FileOutputStream(invokerLog), false, "UTF-8"),
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

    private List<String> extractUnitTestFiles(ProjectContext pc, List<String> changedFiles) {
        List<String> testFilesPath = Collections.emptyList();

        changedFiles.forEach(fname -> {
            //TODO: Remove the file extension, add "Test" word and add the file extension again
            fname = fname + "Test";
            if(pc.getBuildTool() == ProjectContext.BuildTool.MAVEN) {
                String filePath = filenameExists(
                        new File(pc.getProjectDirectory().getAbsolutePath() + "/src/test"), fname );
                if(filePath != null) {
                    testFilesPath.add(filePath);
                }
            }
        });

        return testFilesPath;
    }

    private String filenameExists(File root, String fname) {
        try {
            boolean recursive = true;
            Collection<File> files = FileUtils.listFiles(root, null, recursive);

            for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().equals(fname))
                    return file.getAbsolutePath();
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    private List<String> extractChangedFiles(Map<String, Object> githubPayload) {
        return null;
    }

    private ProjectContext.BuildTool extractBuildTool(ProjectContext pc) {
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
        if(filesList == null || filesList.length == 0) {
            return false;
        } else {
            return true;
        }
    }

}
