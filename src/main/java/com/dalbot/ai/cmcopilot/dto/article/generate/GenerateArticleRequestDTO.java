package com.dalbot.ai.cmcopilot.dto.article.generate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateArticleRequestDTO {
    private String link;
    private String atitude;
    private Boolean includeImage;
    private String suffix;
}
