package com.booking;

import com.booking.entity.RootSource;
import com.booking.entity.SourceInfo;
import com.booking.response.NewsApiResponse;
import com.booking.entity.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericRestTests {

    private RestTemplate restTemplate;
    private String baseNewsUrl;
    private String apiKey;
    private String composedUrl;
    private WebClient webClient;

//    @BeforeAll
//    public static void init(){
//       RestTemplate restTemplate = new RestTemplate();
//
//    }

    @BeforeEach
    public void initComponents(){
        System.out.println("-----------------------");
        this.restTemplate = new RestTemplate();
        this.baseNewsUrl = "https://newsapi.org/v2/top-headlines?country=us&apiKey=";
        this.apiKey = "153c17edf07e4ae888a1dd51972a248b";
        this.composedUrl = baseNewsUrl+apiKey;
        this.webClient = WebClient.builder().baseUrl(composedUrl).build();





    }

    @Test
    public void testGenericRestApi(){
        String url = "https://jsonplaceholder.typicode.com/posts";
        Object [] objects = restTemplate.getForObject(url, Object [].class);
        List<Object> objectsList = Arrays.asList(objects);
        objectsList.forEach(x -> System.out.println(x.toString()));
        assertNotNull(objectsList);
        assertTrue(objectsList.size() > 0);
    }

    @Test
    public void testNewsApi(){
        ResponseEntity<String> response = restTemplate.getForEntity(composedUrl, String.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    public void testResponseNewsApi(){
        ResponseEntity<NewsApiResponse> response = restTemplate.getForEntity(composedUrl, NewsApiResponse.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    public void testResponseNewsApiWithWebClient(){
        Flux<Root> root = webClient.get().retrieve().bodyToFlux(Root.class);
        Assertions.assertNotNull(root);
        //System.out.println(root2.subscribe());
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
    }

    @Test
    public void testResponseNewsApiRootObject(){
        Mono<Root> rootResponse = webClient.get().uri(composedUrl).retrieve().bodyToMono(Root.class);
        Assertions.assertNotNull(rootResponse);
        String result = String.valueOf(rootResponse.block());
        Assertions.assertNotNull(result);
        System.out.println("------------------------------------------------------------------------------------");
    }

    @Test
   public void testFirstArticleFromRootResponse(){
        Mono<Root> rootResponse = webClient.get().uri(composedUrl).retrieve().bodyToMono(Root.class);
        Root rootObj = rootResponse.block();
        Assertions.assertNotNull(rootObj);
        System.out.println(rootObj.getArticles().get(0).toString());
    }

    @Test
    public void testSourcesNotNull(){
        String sourceUri = "/top-headlines/sources?apiKey=";
        Mono<RootSource> rootSourceResponse = webClient.get().uri(sourceUri+apiKey).retrieve().bodyToMono(RootSource.class);
        RootSource rootSourceObj  = rootSourceResponse.block();
        ArrayList<SourceInfo> rootSources =  rootSourceObj.getInfoSources();
        Assertions.assertNotNull(rootSources);
    }

    @Test
    public void testAsSet(){
        //Set<String> names = new HashSet<>();
        List<String> names = new ArrayList<>();
        names.add("john");
        names.add("jane");
        names.add("jack");
        names.add("jill");
        names.add("john");
        Assertions.assertTrue(names.size()==5);
        names.forEach(name -> System.out.println("name "+name));
    }

}
