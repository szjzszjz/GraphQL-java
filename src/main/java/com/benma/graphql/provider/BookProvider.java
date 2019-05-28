package com.benma.graphql.provider;

import com.benma.graphql.dataFetcher.BookDataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentImpl;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class BookProvider {

    @Autowired
    public BookDataFetcher bookDataFetcher;
    public final String schema_path = "static/schema/schema-book.graphql";


    //   被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，
    //   类似于Servlet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。

//    public static RuntimeWiring buildRuntimeWiring(BookDataFetcher bookDataFetcher) {
//        return RuntimeWiring.newRuntimeWiring()
//                //查询
//                .type(newTypeWiring("Query")
//                        .dataFetcher("books", bookDataFetcher.books())
//                        .dataFetcher("bookById", bookDataFetcher.bookById())
//                        .dataFetcher("bookByPageCountAndId", bookDataFetcher.bookByPageCountAndId())
//                        .dataFetcher("booksPaging", bookDataFetcher.booksPaging())
//                        .dataFetcher("booksPagingByPageCount", bookDataFetcher.booksPagingByPageCount())
//                        .dataFetcher("booksLikeByName", bookDataFetcher.booksLikeByName()))
//
//                //更新
//                .type(newTypeWiring("Mutation")
//                        .dataFetcher("modifyBook", bookDataFetcher.modifyBook())
//                        .dataFetcher("createBook", bookDataFetcher.createBook())
//                        .dataFetcher("deleteBook", bookDataFetcher.deleteBook()))
//
//                .type(newTypeWiring("Book")
//                        .dataFetcher("author", bookDataFetcher.author()))
//                .build();
//    }

    //查询
    public TypeRuntimeWiring.Builder buildQueryRuntimeWiring() {
        TypeRuntimeWiring.Builder builder = newTypeWiring("Query")
                .dataFetcher("books", bookDataFetcher.books())
                .dataFetcher("bookById", bookDataFetcher.bookById())
                .dataFetcher("bookByPageCountAndId", bookDataFetcher.bookByPageCountAndId())
                .dataFetcher("booksPaging", bookDataFetcher.booksPaging())
                .dataFetcher("booksPagingByPageCount", bookDataFetcher.booksPagingByPageCount())
                .dataFetcher("booksLikeByName", bookDataFetcher.booksLikeByName());
        return builder;
    }

    //更新
    public TypeRuntimeWiring.Builder buildMutationRuntimeWiring() {
        TypeRuntimeWiring.Builder builder = newTypeWiring("Mutation")
                .dataFetcher("modifyBook", bookDataFetcher.modifyBook())
                .dataFetcher("createBook", bookDataFetcher.createBook())
                .dataFetcher("deleteBook", bookDataFetcher.deleteBook());
        return builder;
    }

    public TypeRuntimeWiring.Builder buildBookRuntimeWiring() {
        TypeRuntimeWiring.Builder builder = newTypeWiring("Book")
                .dataFetcher("author", bookDataFetcher.author());
        return builder;
    }


}
