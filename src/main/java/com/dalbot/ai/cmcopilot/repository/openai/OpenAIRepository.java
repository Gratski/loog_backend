package com.dalbot.ai.cmcopilot.repository.openai;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "OpenAIFeignClient", url = "https://api.openai.com")
public interface OpenAIRepository {

    @RequestMapping(method = RequestMethod.POST, value = "/v1/images/generations")
    String generateImage(@RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
                         OpenAIRequestEntities.ImageGenerationRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/completions")
    OpenAIRequestEntities.TextCompletionResponse completeText(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            OpenAIRequestEntities.TextCompletionRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/images/generations")
    OpenAIRequestEntities.ImageGenerationResponse createImage(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
            OpenAIRequestEntities.ImageGenerationRequest request);

}
