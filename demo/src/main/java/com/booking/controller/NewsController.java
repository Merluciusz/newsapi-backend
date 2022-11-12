package com.booking.controller;

import com.booking.entity.Article;
import com.booking.entity.SourceInfo;
import com.booking.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/all")
    public List<Article> getAllArticles() {
        return newsService.getAllArticles();
    }

    @GetMapping("/top-headlines")
    public List<Article> getTopHeadlines() {
        return newsService.getTopHeadlines();
    }

    @GetMapping("/sources")
    public List<SourceInfo> getAllSources() {
        return newsService.getAllSources();
    }

}
