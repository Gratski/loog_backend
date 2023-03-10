package com.dalbot.ai.cmcopilot.dto.code;

import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeAnalysisRequestDTO {
        private String model;
        private ArrayList<Message> messages;

}
