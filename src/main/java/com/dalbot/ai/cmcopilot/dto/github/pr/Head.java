package com.dalbot.ai.cmcopilot.dto.github.pr;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Head {
    private String label;
    private String ref;
    private String sha;
    User user;
    Repository repo;

}
