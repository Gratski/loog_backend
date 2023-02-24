package com.dalbot.ai.cmcopilot.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private String title;
    private ArticleBodyDTO body;
    private Boolean isVisible;

}
