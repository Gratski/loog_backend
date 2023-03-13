package com.dalbot.ai.cmcopilot.repository.github;

import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisRequestDTO;
import com.dalbot.ai.cmcopilot.dto.github.pr.files.ChangedFile;
import com.dalbot.ai.cmcopilot.dto.vendor.github.FetchCommitDTO;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "GithubRepository", url = "https://api.github.com")
public interface GithubRepository {
    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/commits/{id}")
    FetchCommitDTO getCommitById(@RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
                                 @PathVariable("repo") String repo,
                                 @PathVariable("owner") String owner,
                                 @PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/pulls/{id}/files")
    List<ChangedFile> GetPullRequestAffectedFiles(@RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
                                                  @PathVariable("repo") String repo,
                                                  @PathVariable("owner") String owner,
                                                  @PathVariable("id") String id);
}

