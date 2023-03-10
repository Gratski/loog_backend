package com.dalbot.ai.cmcopilot.service.webhook;

import com.dalbot.ai.cmcopilot.repository.github.GithubRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GithubWebhookService {

    private final GithubRepository githubRepo;

    public GithubWebhookService(GithubRepository githubRepo) {
        this.githubRepo = githubRepo;
    }

    public void triggerPullRequestFlow(Map<String, Object> payload) {

    }
}
