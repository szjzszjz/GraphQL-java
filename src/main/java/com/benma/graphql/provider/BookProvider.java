package com.benma.graphql.provider;

import com.benma.graphql.Utils.ProviderUtil;
import com.benma.graphql.dataFetcher.BookDataFetcher;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class BookProvider {

    private String path = "static/schema/schema-book.graphql";
//     GraphQL graphQL;
    @Autowired
    BookDataFetcher bookDataFetcher;

//    public BookProvider(String path) {
//       new ProviderUtil(path,buildWiring());
//    }
    @PostConstruct
    public void initGraphQL(){
        new ProviderUtil().initGraphQL(path,buildWiring());
    }


//    @PostConstruct
//    public void init()throws IOException {
//        URL url = Resources.getResource(path);
//        String sdl = Resources.toString(url, Charsets.UTF_8);
//        GraphQLSchema graphQLSchema = buildSchema(sdl);
//        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
//    }
//
//    private GraphQLSchema buildSchema(String sdl) {
//        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
//        RuntimeWiring runtimeWiring = buildWiring();
//        SchemaGenerator schemaGenerator = new SchemaGenerator();
//        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
//    }



//    @Bean
//    public GraphQL graphQL() {
//        return graphQL;
//    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Mutation")
                        .dataFetcher("modifyBook", bookDataFetcher.modifyBook()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createBook", bookDataFetcher.createBook()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("deleteBook", bookDataFetcher.deleteBook()))
                .type(newTypeWiring("Query")
                        .dataFetcher("books", bookDataFetcher.books()))
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", bookDataFetcher.bookById()))
                .type(newTypeWiring("Query")
                        .dataFetcher("bookByPageCountAndId", bookDataFetcher.bookByPageCountAndId()))
                .type(newTypeWiring("Query")
                        .dataFetcher("booksPaging", bookDataFetcher.booksPaging()))
                .type(newTypeWiring("Query")
                        .dataFetcher("booksPagingByPageCount", bookDataFetcher.booksPagingByPageCount()))
                .type(newTypeWiring("Query")
                        .dataFetcher("booksLikeByName", bookDataFetcher.booksLikeByName()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", bookDataFetcher.author()))
                .build();
    }


}
