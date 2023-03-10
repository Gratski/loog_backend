package com.dalbot.ai.cmcopilot.dto.code;

import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities.GeneralResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeAnalysisResponseDTO {

    private String result;

}
