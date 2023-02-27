package com.dalbot.ai.cmcopilot.service.news;

import com.dalbot.ai.cmcopilot.repository.newsapi.NewsAPIRepository;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.news.NewsAPIRequestDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.news.NewsAPIResponseDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources.GetSourcesRequestDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources.GetSourcesResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class NewsFetchServiceImpl implements NewsFetchService {

    @Value("${newsapi.api.key}")
    private String apiKey;

    @Value("${newsapi.api.pageSize}")
    private Integer pageSize;

    private final NewsAPIRepository repository;

    public NewsFetchServiceImpl(NewsAPIRepository repository) {
        this.repository = repository;
    }

    @Override
    public NewsAPIResponseDTO fetchNews(NewsAPIRequestDTO dto) {
        return repository.getNews(apiKey,
                dto.getQ(), dto.getSearchIn(), dto.getSources(),
                dto.getLanguage(), dto.getSortBy(),
                dto.getPageSize(), dto.getPage(),
                dto.getFrom(), dto.getTo());
    }

    @Override
    public GetSourcesResponseDTO fetchSources(GetSourcesRequestDTO dto) {
        return repository.getSources(apiKey, dto.getCategory(), dto.getLanguage(), dto.getCountry());
    }
}
