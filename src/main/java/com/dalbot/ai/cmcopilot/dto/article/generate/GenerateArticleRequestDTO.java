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

    private String prompt;
    private String suffix;

}
