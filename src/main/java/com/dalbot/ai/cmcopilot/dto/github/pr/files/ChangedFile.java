package com.dalbot.ai.cmcopilot.dto.github.pr.files;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangedFile {

    private String sha;
    private String filename;
    private String status;
    private Integer additions;
    private Integer deletions;
    private Integer changes;
    private String blob_url;
    private String raw_url;
    private String contents_url;
    private String patch;

}
