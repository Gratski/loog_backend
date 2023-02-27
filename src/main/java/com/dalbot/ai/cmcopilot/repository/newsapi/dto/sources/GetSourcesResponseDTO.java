package com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSourcesResponseDTO {

    private String status;
    private Collection<NewsSourceDTO> sources;

}
