package com.dalbot.ai.cmcopilot.dto.vendor.github;

import lombok.Builder;

import java.net.URI;
import java.util.Map;

@Builder
public class GitParentDTO {
    private String sha;
    private URI url;
    private URI htmlUrl;
    private Map<String, Object> additionalProperties;
}
