package com.dalbot.ai.cmcopilot.repository.openai;

import com.dalbot.ai.cmcopilot.dto.code.Message;
import lombok.*;

import java.util.Date;
import java.util.List;

public class OpenAIRequestEntities {

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
                    .model("gpt-3.5-turbo")
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
    public static class ChatCompletionRequest {
        private String model;
        private String messages;
        private Double temperature;

        public static ChatCompletionRequest createStandard(String messages) {
            return ChatCompletionRequest.builder()
                    .temperature(0.7)
                    .model("gpt-3.5-turbo")
                    .messages("[{\"role\":\"user\", \"content\":\""+messages+"\"}]")
                    .build();
        }

    }

    @Getter
    @Setter
    private static class ChatCompletionMessage {
        String role;

    }

    @Getter
    @Setter
    @Builder
    public static class OpenAIChoices {

        private Integer index;
        private String text;

        private Object logprobs;

        private String finish_reason;

    }

    @Getter
    @Setter
    @Builder
    public static class OpenAIChoicesChat {

        private Integer index;
        private Message message;

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
    public static class GeneralResponse {
        private String id;
        private String object;
        private Date created;
        private String model;
        private List<OpenAIChoices> choices;
        private OpenAIUsage usage;
    }

    @Getter
    @Setter
    @Builder
    public static class ChatResponse {
        private String id;
        private List<OpenAIChoicesChat> choices;

    }

    @Getter
    @Setter
    @Builder
    public static class ImageGenerationRequest {
        private String prompt;
        private Integer n;
        private String size;
    }

    @Getter
    @Setter
    @Builder
    public static class ImageGenerationResponse {
        private Date created;
        private List<ImageGenerationResponseImage> data;
    }

    @Getter
    @Setter
    public static class ImageGenerationResponseImage {
        private String url;
    }

    @Getter
    @Setter
    @Builder
    public static class CodeAnalysisRequest {
        private String input;
        private String instruction;
        private String model;
        private Double temperature;
    }

}
