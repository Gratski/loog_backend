package com.dalbot.ai.cmcopilot.repository.newsapi.dto.news;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsAPIRequestDTO {

    private String q;
    private String searchIn;
    private String sources;
    private String language;
    private String sortBy;
    private Integer pageSize;
    private Integer page;
    private String from;
    private String to;

}
