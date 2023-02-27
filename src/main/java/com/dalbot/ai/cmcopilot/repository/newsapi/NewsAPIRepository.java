package com.dalbot.ai.cmcopilot.repository.newsapi;

import com.dalbot.ai.cmcopilot.repository.newsapi.dto.news.NewsAPIRequestDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.news.NewsAPIResponseDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources.GetSourcesRequestDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources.GetSourcesResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "NewsAPIRepository", url = "https://newsapi.org/v2")
public interface NewsAPIRepository {

    @RequestMapping(method = RequestMethod.GET, path = "/top-headlines/sources")
    public GetSourcesResponseDTO getSources(
            @RequestHeader(value = "X-API-KEY", required = true) String apiKey,
            @RequestParam String category,
            @RequestParam String language,
            @RequestParam String country
    );
    @RequestMapping(method = RequestMethod.GET, path = "/everything")
    public NewsAPIResponseDTO getNews(
            @RequestHeader(value = "X-API-KEY", required = true) String apiKey,
            @RequestBody NewsAPIRequestDTO request
    );

}
