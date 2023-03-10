package com.dalbot.ai.cmcopilot.dto.vendor.github;

import lombok.Builder;

import java.net.URI;
import java.util.Map;

@Builder
public class GitCommitDTO {
    private URI url;
    private Object author;
    private Object committer;
    private String message;
    private Integer commentCount;
    private GitTreeDTO tree;
    private GitVerificationDTO verification;
    private Map<String, Object> additionalProperties;

}
