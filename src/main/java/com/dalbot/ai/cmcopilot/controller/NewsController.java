package com.dalbot.ai.cmcopilot.controller;

import com.dalbot.ai.cmcopilot.repository.newsapi.dto.news.NewsAPIRequestDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.news.NewsAPIResponseDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources.GetSourcesRequestDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources.GetSourcesResponseDTO;
import com.dalbot.ai.cmcopilot.service.news.NewsFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/news")
public class NewsController {

    private Logger logger = LoggerFactory.getLogger(GeneratorController.class);

    private final NewsFetchService service;

    public NewsController(NewsFetchService service) {
        this.service = service;
    }

    @PostMapping (path = "/sources")
    public ResponseEntity<GetSourcesResponseDTO> getSources(
            @RequestBody GetSourcesRequestDTO dto
            ) {
        GetSourcesResponseDTO result = this.service.fetchSources(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping (path = "/articles")
    public ResponseEntity<NewsAPIResponseDTO> getNews(
            @RequestBody NewsAPIRequestDTO dto
    ) {
        NewsAPIResponseDTO result = this.service.fetchNews(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
