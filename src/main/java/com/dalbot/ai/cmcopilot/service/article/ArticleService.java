package com.dalbot.ai.cmcopilot.service.article;

import com.dalbot.ai.cmcopilot.dto.article.ArticleDTO;
import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleRequestDTO;
import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleResponseDTO;

import java.util.UUID;

public interface ArticleService {

    GenerateArticleResponseDTO generateArticle(GenerateArticleRequestDTO dto);

    ArticleDTO persistArticle(ArticleDTO article);

    void updateArticleVisibility(UUID articleId, boolean visibility);

    void deleteArticle(UUID articleId);

}
