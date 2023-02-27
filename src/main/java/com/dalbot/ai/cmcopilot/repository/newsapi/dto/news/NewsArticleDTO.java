package com.dalbot.ai.cmcopilot.repository.newsapi.dto.news;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsArticleDTO {

    private Source source;
    private String author;
    private String tile;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class Source {
        private String id;
        private String name;
    }
}
