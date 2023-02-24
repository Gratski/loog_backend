package com.dalbot.ai.cmcopilot.repository.openai;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class OpenAIRequestEntities {


    @Getter
    @Setter
    @Builder
    static class ImageGenerationRequest {
        private String model;
        private String prompt;
        private int num_images;

        // getters and setters
    }

    @Getter
    @Setter
    @Builder
    public static class TextCompletionRequest {
        private String prompt;
        private int max_tokens;

    }
    @Getter
    @Setter
    @Builder
    public static class TextCompletionResponse {
        private String text;
        private float[] logits;

    }

}
