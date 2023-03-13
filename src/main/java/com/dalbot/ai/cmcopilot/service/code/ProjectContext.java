package com.dalbot.ai.cmcopilot.service.code;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Builder
public class ProjectContext {

    private File projectDirectory;
    private List<String> changedFiles;
    private List<String> unitTestFiles;
    private BuildTool buildTool;

    public static enum BuildTool {
        MAVEN,
        GRADLE
    }
}
