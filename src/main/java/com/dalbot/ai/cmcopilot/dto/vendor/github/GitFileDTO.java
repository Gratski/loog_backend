package com.dalbot.ai.cmcopilot.dto.vendor.github;

import lombok.Builder;

import java.net.URI;
import java.util.Map;

@Builder
public class GitFileDTO {

    private String sha;
    private String filename;
    private GitFileDTO.Status status;
    private Integer additions;
    private Integer deletions;
    private Integer changes;
    private URI blobUrl;
    private URI rawUrl;
    private URI contentsUrl;
    private String patch;
    private String previousFilename;
    private Map<String, Object> additionalProperties;

    public enum Status {
        added,
        removed,
        modified,
        renamed,
        copied,
        changed,
        unchanged;
    }

}
