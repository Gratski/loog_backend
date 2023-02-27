package com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsSourceDTO {

    private String id;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

}
