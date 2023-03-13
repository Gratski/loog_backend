package com.dalbot.ai.cmcopilot.controller;

import com.dalbot.ai.cmcopilot.dto.github.pr.GithubPullRequestPayload;
import com.dalbot.ai.cmcopilot.service.webhook.GithubWebhookService;
import org.kohsuke.github.GHEventPayload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(path = "/webhook")
public class WebhookController {

    private final GithubWebhookService githubWBService;

    public WebhookController(GithubWebhookService githubWBService) {
        this.githubWBService = githubWBService;
    }

    @PostMapping("/github/pr")
    public void captureGithubPullRequest(@RequestBody GithubPullRequestPayload payload) throws IOException {
        githubWBService.triggerPullRequestFlow(payload);
    }

    @PostMapping("/github/push")
    public void captureGithubPush(@RequestBody Map<String, Object> payload) throws IOException {
        System.out.println("The pusher is: " + payload.size());
    }

}
