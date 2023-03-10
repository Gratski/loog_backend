package com.dalbot.ai.cmcopilot.service.code;

import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisRequestDTO;
import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisResponseDTO;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRepository;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    @Value("${openai.api.key}")
    private String openAIApiKey;

    private final OpenAIRepository repository;

    public CodeService(OpenAIRepository repository) {
        this.repository = repository;
    }

    public String theMeaningOfLive(CodeAnalysisRequestDTO dto) {
        OpenAIRequestEntities.ChatResponse fetchCompletionResult =
                repository.completeTextV2("Bearer " + openAIApiKey, dto);

        return fetchCompletionResult.getChoices().get(0).getMessage().getContent();
    }



}
