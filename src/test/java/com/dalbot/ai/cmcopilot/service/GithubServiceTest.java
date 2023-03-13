package com.dalbot.ai.cmcopilot.service;

import com.dalbot.ai.cmcopilot.dto.github.GithubPullRequestPayload;
import com.dalbot.ai.cmcopilot.repository.github.GithubRepository;
import com.dalbot.ai.cmcopilot.service.webhook.GithubWebhookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class GithubServiceTest {

    private GithubRepository githubRepo;

    @Value("classpath: mocks/githubPullRequestPayload.json")
    Resource payloadResource;

    private GithubPullRequestPayload payload;

    @BeforeAll
    public void beforeAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.payload = mapper.readValue(payloadResource.getFile(), GithubPullRequestPayload.class);
        this.githubRepo = Mockito.mock(GithubRepository.class);
    }

    @Test
    public void testCodeImprove() {

        GithubWebhookService service = new GithubWebhookService(this.githubRepo);
        service.triggerPullRequestFlow(payload);

    }

}
