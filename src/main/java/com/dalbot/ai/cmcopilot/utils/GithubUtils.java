package com.dalbot.ai.cmcopilot.utils;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class GithubUtils {

    public static Optional<File> cloneProject(final String repoUrl, String targetDirectory) {
        targetDirectory = String.format("%s/%s", targetDirectory, UUID.randomUUID().toString());
        System.out.println("Cloning "+repoUrl+" into "+ targetDirectory);
        CredentialsProvider credentialProvider = new UsernamePasswordCredentialsProvider("gratski", "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");
        try {
            Git.cloneRepository()
                    .setCredentialsProvider(credentialProvider)
                    .setURI(repoUrl)
                    .setDirectory(Paths.get(targetDirectory).toFile())
                    .call();
            return Optional.of(new File(targetDirectory));
        } catch (GitAPIException e) {
            return Optional.empty();
        }
    }

}
