package com.dalbot.ai.cmcopilot.service.code;

import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisRequestDTO;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRepository;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
import com.dalbot.ai.cmcopilot.utils.GithubUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.output.TeeOutputStream;
import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

@Service
public class CodeService {

    @Value("${openai.api.key}")
    private String openAIApiKey;

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
