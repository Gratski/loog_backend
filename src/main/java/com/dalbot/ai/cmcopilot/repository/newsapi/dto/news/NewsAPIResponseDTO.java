package com.dalbot.ai.cmcopilot.repository.newsapi.dto.news;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsAPIResponseDTO {

    private String status;
    private Integer totalResults;
    private Collection<NewsArticleDTO> articles;

}
