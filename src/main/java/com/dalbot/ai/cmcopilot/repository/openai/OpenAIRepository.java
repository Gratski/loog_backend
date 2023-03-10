package com.dalbot.ai.cmcopilot.repository.openai;

import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisRequestDTO;
import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "OpenAIFeignClient", url = "https://api.openai.com")
public interface OpenAIRepository {

    @RequestMapping(method = RequestMethod.POST, value = "/v1/images/generations")
    String generateImage(@RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
                         OpenAIRequestEntities.ImageGenerationRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/completions")
    OpenAIRequestEntities.GeneralResponse completeText(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            OpenAIRequestEntities.TextCompletionRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/chat/completions")
    OpenAIRequestEntities.ChatResponse completeTextV2(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            CodeAnalysisRequestDTO request);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/images/generations")
    OpenAIRequestEntities.ImageGenerationResponse createImage(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            OpenAIRequestEntities.ImageGenerationRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/edits")
    OpenAIRequestEntities.GeneralResponse codeAnalysis(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            OpenAIRequestEntities.CodeAnalysisRequest request
    );


}
