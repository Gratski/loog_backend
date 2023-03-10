package com.dalbot.ai.cmcopilot.dto.image;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateImageResponseDTO {
    private String id;
    private String imageUrl;
}
