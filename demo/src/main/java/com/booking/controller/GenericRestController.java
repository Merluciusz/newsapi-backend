package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class GenericRestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/details")
    public List<Object> getDetails(){
        //String url = "http://localhost:8080/details";
        String url = "https://jsonplaceholder.typicode.com/posts";
        Object [] objects = restTemplate.getForObject(url, Object [].class);
        return Arrays.asList(objects);
    }

}
