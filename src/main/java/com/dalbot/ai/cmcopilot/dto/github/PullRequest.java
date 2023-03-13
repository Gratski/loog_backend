package com.dalbot.ai.cmcopilot.dto.github;

import lombok.Builder;

import java.math.BigInteger;
import java.util.ArrayList;

@Builder
public class PullRequest {
    private String url;
    private BigInteger id;
    private String node_id;
    private String html_url;
    private String diff_url;
    private String patch_url;
    private String issue_url;
    private Integer number;
    private String state;
    private Boolean locked;
    private String title;
    private User user;
    private Object body;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private String merged_at;
    private String merge_commit_sha;
    private ArrayList<User> assignee;
    private ArrayList<User> assignees;
    private ArrayList<User> requested_reviewers;
    private ArrayList <Object> requested_teams;
    private ArrayList <Object> labels;
    private String milestone;
    private Boolean draft;
    private String commits_url;
    private String review_comments_url;
    private String review_comment_url;
    private String comments_url;
    private String statuses_url;
    private Head head;
    private Base base;
    private Links _links;
    private String author_association;
    private Object auto_merge;
    private Object active_lock_reason;
    private Boolean merged;
    private Boolean mergeable;
    private Boolean rebaseable;
    private String mergeable_state;
    private Object merged_by;
    private Integer comments;
    private Integer review_comments;
    private Boolean maintainer_can_modify;
    private Integer commits;
    private Integer additions;
    private Integer deletions;
    private Integer changed_files;

}
