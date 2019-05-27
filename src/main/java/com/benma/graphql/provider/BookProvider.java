package com.benma.graphql.provider;

import com.benma.graphql.dataFetcher.BookDataFetcher;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class BookProvider {

    @Autowired
    public static BookDataFetcher bookDataFetcher;
    public static final String schema_path = "static/schema/schema-book.graphql";


    //   被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，
    //   类似于Servlet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。

    public static RuntimeWiring buildRuntimeWiring(BookDataFetcher bookDataFetcher) {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query").dataFetcher("books", bookDataFetcher.books()))
                .type(newTypeWiring("Query").dataFetcher("bookById", bookDataFetcher.bookById()))
                .type(newTypeWiring("Query").dataFetcher("bookByPageCountAndId", bookDataFetcher.bookByPageCountAndId()))
                .type(newTypeWiring("Query").dataFetcher("booksPaging", bookDataFetcher.booksPaging()))
                .type(newTypeWiring("Query").dataFetcher("booksPagingByPageCount", bookDataFetcher.booksPagingByPageCount()))
                .type(newTypeWiring("Query").dataFetcher("booksLikeByName", bookDataFetcher.booksLikeByName()))
                .type(newTypeWiring("Mutation").dataFetcher("modifyBook", bookDataFetcher.modifyBook()))
                .type(newTypeWiring("Mutation").dataFetcher("createBook", bookDataFetcher.createBook()))
                .type(newTypeWiring("Mutation").dataFetcher("deleteBook", bookDataFetcher.deleteBook()))
                .type(newTypeWiring("Book").dataFetcher("author", bookDataFetcher.author()))
                .build();
    }



}
