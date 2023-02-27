package com.dalbot.ai.cmcopilot.repository.openai;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

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
        private String model;
        private String prompt;
        private Integer max_tokens;
        private Double temperature;
        private Double top_p;
        private Integer n;
        private Boolean stream;
        private String stop;
        private String suffix;
        private Integer best_of;

        public static TextCompletionRequest createStandard(String prompt) {
            return TextCompletionRequest.builder()
                    .temperature(0.7)
                    .model("text-davinci-003")
                    .top_p(0.0)
                    .prompt(prompt)
                    .n(1)
                    .best_of(1)
                    .max_tokens(256)
                    .build();
        }

    }

    @Getter
    @Setter
    @Builder
    public static class OpenAIChoices {

        private String text;

        private Integer index;

        private Object logprobs;

        private String finish_reason;

    }

    @Getter
    @Setter
    @Builder
    public static class OpenAIUsage {
        private Integer prompt_tokens;

        private Integer completion_tokens;

        private Integer total_tokens;
    }

    @Getter
    @Setter
    @Builder
    public static class TextCompletionResponse {
        private String id;
        private String object;

        private Date created;

        private String model;

        private Collection<OpenAIChoices> choices;

        private OpenAIUsage usage;
    }

}
