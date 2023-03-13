package com.dalbot.ai.cmcopilot.dto.github;

import lombok.Builder;

@Builder
public class Base {
    private String label;
    private String ref;
    private String sha;
    private User user;
    private Repository repo;

}