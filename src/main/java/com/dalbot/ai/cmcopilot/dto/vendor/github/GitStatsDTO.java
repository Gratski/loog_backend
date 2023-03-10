package com.dalbot.ai.cmcopilot.dto.vendor.github;

import lombok.Builder;

import java.util.Map;

@Builder
public class GitStatsDTO {
    private Integer additions;
    private Integer deletions;
    private Integer total;
    private Map<String, Object> additionalProperties;
}
