package com.dalbot.ai.cmcopilot.persistence.domain.article;

import com.dalbot.ai.cmcopilot.persistence.domain.IdentifiableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleBody extends IdentifiableEntity<UUID> {

    private String text;

    private ArticleImage mainImage;

}
