package com.dalbot.ai.cmcopilot.dto.github.pr;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Links {
    private Self self;
    private Html html;
    private Issue issue;
    private Comments comments;
    private Review_Comments review_comments;
    private Review_Comment review_comment;
    private Commits commits;
    private Statuses statuses;
}
