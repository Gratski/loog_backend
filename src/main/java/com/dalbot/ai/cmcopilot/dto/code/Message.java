package com.dalbot.ai.cmcopilot.dto.code;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
        private String role;
        private String content;
}
