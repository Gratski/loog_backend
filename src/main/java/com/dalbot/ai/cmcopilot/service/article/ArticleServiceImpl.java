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

    @Value("${openai.api.key}")
    private String openAIApiKey;
    private final OpenAIRepository openAIRepository;

    public ArticleServiceImpl(OpenAIRepository openAIRepository) {
        this.openAIRepository = openAIRepository;
    }

    @Override
    public GenerateArticleResponseDTO generateArticle(GenerateArticleRequestDTO dto) {
        if(Objects.isNull(dto) | Objects.isNull(dto.getPrompt())) {
            throw new IllegalArgumentException("Article must contain the minimum required details");
        }

        //todo: validar o length do prompt para gerir custos

        OpenAIRequestEntities.TextCompletionRequest request = OpenAIRequestEntities
                .TextCompletionRequest.createStandard(dto.getPrompt());
        if(Objects.nonNull(dto.getSuffix())) {
            request.setSuffix(dto.getSuffix());
        }

        OpenAIRequestEntities.TextCompletionResponse generatedText =
                openAIRepository.completeText("Bearer " + openAIApiKey, request);

        return GenerateArticleResponseDTO.builder()
                .body(generatedText.getChoices())
                .build();

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
