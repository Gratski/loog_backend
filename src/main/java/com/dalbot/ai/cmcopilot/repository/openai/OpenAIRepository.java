package com.dalbot.ai.cmcopilot.repository.openai;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "Open AI Feign Client", url = "https://api.openai.com")
public interface OpenAIRepository {

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer {apiKey}"
    })
    @RequestLine("POST /v1/images/generations")
    String generateImage(@Param("apiKey") String apiKey, OpenAIRequestEntities.ImageGenerationRequest request);

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer {apiKey}"
    })
    @RequestLine("POST /v1/models/{model}/completions")
    OpenAIRequestEntities.TextCompletionResponse completeText(
            @Param("apiKey") String apiKey, @Param("model") String model,
            OpenAIRequestEntities.TextCompletionRequest request);

}
