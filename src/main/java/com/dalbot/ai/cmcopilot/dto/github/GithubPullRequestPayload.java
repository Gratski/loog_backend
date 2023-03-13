package com.dalbot.ai.cmcopilot.dto.github;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GithubPullRequestPayload {
    private String action;
    private float number;
    private PullRequest pull_request;
    private Repository repository;
    private Sender sender;
}













