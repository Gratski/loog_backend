package com.dalbot.ai.cmcopilot.dto.article.generate;

import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateArticleResponseDTO {

    private String body;

    private String imageUrl;


}
