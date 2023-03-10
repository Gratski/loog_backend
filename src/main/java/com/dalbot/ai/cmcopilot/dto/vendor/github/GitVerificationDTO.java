package com.dalbot.ai.cmcopilot.dto.vendor.github;

import lombok.Builder;
import java.util.Map;

@Builder
public class GitVerificationDTO {
    private Boolean verified;
    private String reason;
    private String payload;
    private String signature;
    private Map<String, Object> additionalProperties;
}
