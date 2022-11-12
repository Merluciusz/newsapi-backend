package com.booking.service;

import com.booking.entity.Article;
import com.booking.entity.Root;
import com.booking.entity.RootSource;
import com.booking.entity.SourceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class NewsService {

    private String baseNewsUrl = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=153c17edf07e4ae888a1dd51972a248b";

    @Autowired
    private WebClient webClient;

    private String apiKey = "153c17edf07e4ae888a1dd51972a248b";
    private final String everything = "/everything?q=bitcoin&apiKey=";
    private final String topHeadlines = "/top-headlines?country=us&apiKey=";
    private final String newsSourcesUri = "/top-headlines/sources?apiKey=";



    public List<Article> getAllArticles() {
        Mono<Root> rootResponse = webClient.get().uri(everything+apiKey).retrieve().bodyToMono(Root.class);
        Root rootObj = rootResponse.block();
        //System.out.println(rootObj.getArticles().toString());
        return rootObj.getArticles();
    }

    public List<Article> getTopHeadlines() {
        Mono<Root> rootResponse = webClient.get().uri(topHeadlines+apiKey).retrieve().bodyToMono(Root.class);
        Root rootObj = rootResponse.block();
        //System.out.println(rootObj.getArticles().toString());
        return rootObj.getArticles();
    }


    public List<SourceInfo> getAllSources(){
        Mono<RootSource> rootSourceResponse = webClient.get().uri(newsSourcesUri+apiKey).retrieve().bodyToMono(RootSource.class);
        RootSource rootSourceObj  = rootSourceResponse.block();
        System.out.println(newsSourcesUri+apiKey);
        return rootSourceObj.getInfoSources();
    }


//    public List<Article> getAllArticles() {
//        ResponseEntity<NewsApiResponse> response = restTemplate.getForEntity(baseNewsUrl, NewsApiResponse.class);
//        Root rootObj = response.getBody().getRoot();
//        System.out.println(rootObj.getArticles().toString());
//        return rootObj.getArticles();
//    }

}
