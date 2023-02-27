package com.dalbot.ai.cmcopilot.service.news;

import com.dalbot.ai.cmcopilot.repository.newsapi.dto.news.NewsAPIRequestDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.news.NewsAPIResponseDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources.GetSourcesRequestDTO;
import com.dalbot.ai.cmcopilot.repository.newsapi.dto.sources.GetSourcesResponseDTO;

public interface NewsFetchService {

    NewsAPIResponseDTO fetchNews(NewsAPIRequestDTO dto);

    GetSourcesResponseDTO fetchSources(GetSourcesRequestDTO dto);

}
