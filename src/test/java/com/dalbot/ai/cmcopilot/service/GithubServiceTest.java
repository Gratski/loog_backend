package com.dalbot.ai.cmcopilot.service;

import com.dalbot.ai.cmcopilot.ContentManagementCoPilotApplication;
import com.dalbot.ai.cmcopilot.dto.github.pr.GithubPullRequestPayload;
import com.dalbot.ai.cmcopilot.repository.github.GithubRepository;
import com.dalbot.ai.cmcopilot.service.webhook.GithubWebhookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ContentManagementCoPilotApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GithubServiceTest {

    @Autowired
    private GithubWebhookService service;
    private GithubRepository githubRepo;
    private GithubPullRequestPayload payload;
    private String githubApiKey;

    @BeforeAll
    public void beforeAll() throws IOException {

        Dotenv dotenv = Dotenv.load();
        githubApiKey = dotenv.get("GITHUB_API_KEY");

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        this.payload = mapper.readValue(new File("/Users/joaorodrigues/Documents/software/projects/ai/" +
                "cmcopilot/cmcopilot/src/test/resources/mocks/githubPullRequestPayload2.json"),
                GithubPullRequestPayload.class);
        this.githubRepo = Mockito.mock(GithubRepository.class);
    }

    @Test
    public void testCodeImprove() throws IOException {
        service.triggerPullRequestFlow(payload);

    }

}
