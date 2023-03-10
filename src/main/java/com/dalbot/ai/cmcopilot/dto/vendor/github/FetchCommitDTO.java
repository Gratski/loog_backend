package com.dalbot.ai.cmcopilot.dto.vendor.github;

import lombok.Builder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Builder
public class FetchCommitDTO {
    private URI url;
    private String sha;
    private String nodeId;
    private URI htmlUrl;
    private URI commentsUrl;
    private GitCommitDTO commit;
    private Object author;
    private Object committer;
    private List<GitParentDTO> parents;
    private GitStatsDTO stats;
    private List<GitFileDTO> files;
    private Map<String, Object> additionalProperties;

}
