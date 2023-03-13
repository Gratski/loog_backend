package com.dalbot.ai.cmcopilot.dto.github.pr;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubPullRequestPayload {
    private String action;
    private float number;
    private PullRequest pull_request;
    private Repository repository;
    private Sender sender;
}













