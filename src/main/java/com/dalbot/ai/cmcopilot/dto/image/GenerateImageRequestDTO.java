package com.dalbot.ai.cmcopilot.dto.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenerateImageRequestDTO {
    private String imageName;
    private String prompt;

}
