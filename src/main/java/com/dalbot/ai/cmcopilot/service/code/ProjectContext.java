package com.dalbot.ai.cmcopilot.service.code;

import com.dalbot.ai.cmcopilot.dto.github.pr.GithubPullRequestPayload;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ProjectContext {

    private UUID id;
    private GithubPullRequestPayload payload;
    private File projectDirectory;
    private List<String> changedFiles;
    private List<String> unitTestFiles;
    private BuildTool buildTool;

    public static enum BuildTool {
        MAVEN,
        GRADLE
    }
}
