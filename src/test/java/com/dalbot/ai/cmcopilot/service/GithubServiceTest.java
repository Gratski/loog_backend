package com.dalbot.ai.cmcopilot.service;

import com.dalbot.ai.cmcopilot.dto.github.pr.GithubPullRequestPayload;
import com.dalbot.ai.cmcopilot.repository.github.GithubRepository;
import com.dalbot.ai.cmcopilot.service.webhook.GithubWebhookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GithubServiceTest {

    private GithubRepository githubRepo;

    private GithubPullRequestPayload payload;

    @BeforeAll
    public void beforeAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        this.payload = mapper.readValue(new File("/Users/joaorodrigues/Documents/software/projects/ai/" +
                "cmcopilot/cmcopilot/src/test/resources/mocks/githubPullRequestPayload.json"),
                GithubPullRequestPayload.class);
        this.githubRepo = Mockito.mock(GithubRepository.class);
    }

    @Test
    public void testCodeImprove() throws IOException {

        GithubWebhookService service = new GithubWebhookService(this.githubRepo);
        service.triggerPullRequestFlow(payload);

    }

}
