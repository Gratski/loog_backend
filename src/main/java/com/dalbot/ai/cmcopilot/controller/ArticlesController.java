package com.dalbot.ai.cmcopilot.controller;

import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleRequestDTO;
import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleResponseDTO;
import com.dalbot.ai.cmcopilot.service.article.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticlesController {

    private final ArticleService articleService;

    private Logger logger = LoggerFactory.getLogger(ArticlesController.class);

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/api/generator/text")
    public ResponseEntity<GenerateArticleResponseDTO> generateArticle(@RequestBody GenerateArticleRequestDTO article) {
        logger.debug("Generating article: Started-" + Thread.currentThread().getId());
        GenerateArticleResponseDTO generatedArticle = articleService.generateArticle(article);
        logger.debug("Generating article: Article has been generated-"+Thread.currentThread().getId());
        return new ResponseEntity<GenerateArticleResponseDTO>(generatedArticle, HttpStatus.CREATED);
    }

}
