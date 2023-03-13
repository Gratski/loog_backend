package com.dalbot.ai.cmcopilot.utils;

import com.dalbot.ai.cmcopilot.exception.GithubException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class GithubUtils {

    public static Optional<File> cloneProject(final String repoUrl, UUID projectContextId) {
        String targetDirectory = String.format("%s/%s", getWorkingDirectory(), projectContextId.toString());

        CredentialsProvider credentialProvider = new UsernamePasswordCredentialsProvider("gratski", "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");

        try {
            Git.cloneRepository()
                    .setCredentialsProvider(credentialProvider)
                    .setURI(repoUrl)
                    .setDirectory(Paths.get(targetDirectory).toFile())
                    .call();
            return Optional.of(new File(targetDirectory));
        } catch (GitAPIException e) {
            throw new GithubException(String.format("An error occurred while cloning original project from: %s", repoUrl), e);
        }
    }

    public static String getWorkingDirectory() {
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }

}
