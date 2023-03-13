package com.dalbot.ai.cmcopilot.dto.github;

import lombok.Builder;

@Builder
public class Head {
    private String label;
    private String ref;
    private String sha;
    User user;
    Repository repo;

}
