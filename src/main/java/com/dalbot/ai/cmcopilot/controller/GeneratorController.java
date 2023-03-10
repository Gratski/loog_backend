package com.dalbot.ai.cmcopilot.controller;

import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleRequestDTO;
import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleResponseDTO;
import com.dalbot.ai.cmcopilot.dto.image.GenerateImageRequestDTO;
import com.dalbot.ai.cmcopilot.dto.image.GenerateImageResponseDTO;
import com.dalbot.ai.cmcopilot.service.article.ArticleService;
import com.dalbot.ai.cmcopilot.service.image.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GeneratorController {
    private final ArticleService articleService;

    private final ImageService imageService;
    private Logger logger = LoggerFactory.getLogger(GeneratorController.class);

    public GeneratorController(ArticleService articleService, ImageService imageService) {
        this.articleService = articleService;
        this.imageService = imageService;
    }

    @PostMapping("/generator/text")
    public ResponseEntity<GenerateArticleResponseDTO> generateArticle(@RequestBody GenerateArticleRequestDTO article) {
        logger.debug("Generating article: Started-" + Thread.currentThread().getId());
        GenerateArticleResponseDTO generatedArticle = articleService.generateArticle(article);
        logger.debug("Generating article: Article has been generated-"+Thread.currentThread().getId());
        return new ResponseEntity<GenerateArticleResponseDTO>(generatedArticle, HttpStatus.CREATED);
    }

    @PostMapping("/generator/image")
    public ResponseEntity<GenerateImageResponseDTO> generateImage(@RequestBody GenerateImageRequestDTO dto) {
        logger.debug("Generating image: Started-" + Thread.currentThread().getId());
        GenerateImageResponseDTO result = imageService.generateImage(dto);
        logger.debug("Generating image: Ended-" + Thread.currentThread().getId());
        return new ResponseEntity<GenerateImageResponseDTO>(result, HttpStatus.CREATED);
    }

    @PostMapping("/combinator/image")
    public ResponseEntity<GenerateImageResponseDTO> handleFileUpload(
            @RequestParam("backgroundFile") MultipartFile backgroundFile,
            @RequestParam("productFile") MultipartFile productFile,
            @RequestParam("logoFile") MultipartFile logoFile) {
        logger.debug("Generating image: Started-" + Thread.currentThread().getId());
        //imageService.create(logoFile, backgroundFile, productFile);
        logger.debug("Generating image: Ended-" + Thread.currentThread().getId());
        return ResponseEntity.ok().build();
    }


}
