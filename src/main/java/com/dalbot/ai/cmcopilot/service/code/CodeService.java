package com.dalbot.ai.cmcopilot.service.code;

import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisRequestDTO;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRepository;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    @Value("${openai.api.key}")
    private String openAIApiKey;

    @Value("${github.api.key}")
    private String githubApiKey;

    private final OpenAIRepository repository;

    public CodeService(OpenAIRepository repository) {
        this.repository = repository;
    }

    public String performCodeAnalysis(CodeAnalysisRequestDTO dto) {
        OpenAIRequestEntities.ChatResponse fetchCompletionResult =
                repository.completeTextV2("Bearer " + openAIApiKey, dto);
        return fetchCompletionResult.getChoices().get(0).getMessage().getContent();
    }

}
