package com.dalbot.ai.cmcopilot.service.article;

import com.dalbot.ai.cmcopilot.dto.article.ArticleDTO;
import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleRequestDTO;
import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleResponseDTO;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRepository;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final String PROMPT_TEMPLATE = "Generate me a very comic article based on what is found in this link " +
            "%s and demonstrate a " +
            "%s opinion";

    @Value("Bearer: ${openai.api.key}")
    private String openAIApiKey;
    private final OpenAIRepository openAIRepository;

    public ArticleServiceImpl(OpenAIRepository openAIRepository) {
        this.openAIRepository = openAIRepository;
    }

    @Override
    public GenerateArticleResponseDTO generateArticle(GenerateArticleRequestDTO dto) {
        if(Objects.isNull(dto) | Objects.isNull(dto.getLink())) {
            throw new IllegalArgumentException("Article must contain the minimum required details");
        }

        String prompt = String.format(PROMPT_TEMPLATE, dto.getLink(), dto.getAtitude());

        OpenAIRequestEntities.TextCompletionRequest request = OpenAIRequestEntities
                .TextCompletionRequest.createStandard(prompt);
        if(Objects.nonNull(dto.getSuffix())) {
            request.setSuffix(dto.getSuffix());
        }

        OpenAIRequestEntities.GeneralResponse fetchCompletionResult =
                openAIRepository.completeText(openAIApiKey, request);

        String generatedText = (fetchCompletionResult.getChoices() != null && !fetchCompletionResult.getChoices().isEmpty())
                ? fetchCompletionResult.getChoices().get(0).getText() : "The algorithm failed to generate an article. " +
                "This operation won't be charged. Go ahead and try again!";

        GenerateArticleResponseDTO result = GenerateArticleResponseDTO.builder()
                .body(generatedText)
                .build();

        // call image generation api
        if( Objects.nonNull(generatedText) && dto.getIncludeImage() != null && dto.getIncludeImage() ) {
            String t = generatedText.replace("\n", "");
            OpenAIRequestEntities.TextCompletionRequest summaryRequest = OpenAIRequestEntities
                    .TextCompletionRequest.createStandard("Create a summary of this text for a 6 years old kid: " + generatedText);
            summaryRequest.setMax_tokens(50);
            OpenAIRequestEntities.GeneralResponse fetchSummaryCompletionResult =
                    openAIRepository.completeText(openAIApiKey, summaryRequest);

            String summary = fetchSummaryCompletionResult.getChoices().get(0).getText();
            result.setSummary(summary);
            OpenAIRequestEntities.ImageGenerationRequest createImageRequest = OpenAIRequestEntities.ImageGenerationRequest
                    .builder()
                    .prompt(summary)
                    .n(1)
                    .size("1024x1024")
                    .build();
            OpenAIRequestEntities.ImageGenerationResponse createImageResult = openAIRepository.createImage(
                    openAIApiKey, createImageRequest);
            result.setImageUrl(createImageResult.getData().get(0).getUrl());
        }

        return result;
    }

    @Override
    public ArticleDTO persistArticle(ArticleDTO article) {
        return null;
    }

    @Override
    public void updateArticleVisibility(UUID articleId, boolean visibility) {

    }

    @Override
    public void deleteArticle(UUID articleId) {

    }
}
