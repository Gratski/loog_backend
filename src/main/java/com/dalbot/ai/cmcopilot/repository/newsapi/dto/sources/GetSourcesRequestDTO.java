package com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSourcesRequestDTO {

    private String category;
    private String language;
    private String country;


}
