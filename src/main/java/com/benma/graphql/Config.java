package com.benma.graphql;

import com.benma.graphql.provider.BookProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * author:szjz
 * date:2019/5/22
 */

@Component
public class Config {

//    private String path = "static/schema/schema-book.graphql";
//    @Bean
//    public BookProvider bookProvider(){
//        return new BookProvider(path);
//    }

    @PostConstruct
    public void st(){
        System.out.println("7777777777777777777777777");
    }
}
