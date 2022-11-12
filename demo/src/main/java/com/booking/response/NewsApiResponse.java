package com.booking.response;

import com.booking.entity.Article;
import com.booking.entity.Root;
import com.booking.entity.RootSource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class NewsApiResponse {

    //    List<Article> articles = new ArrayList();
    Root root = new Root();
    RootSource rootSource = new RootSource();

    @Override
    public String toString() {
        return "Response [articles =" + root.getArticles() + " ]";
    }

}
