package com.benma.graphql.Utils;

import com.benma.graphql.dataFetcher.BookDataFetcher;
import com.benma.graphql.dataFetcher.UserDataFetcher;
import com.benma.graphql.provider.BookProvider;
import com.benma.graphql.provider.UserProvider;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;


/**
 * author:szjz
 * date:2019/5/22
 */
@Component
public class ProviderUtil {

    @Autowired
    private UserDataFetcher userDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;

    private GraphQL graphQL;


    public ProviderUtil() {
        System.err.println("0000");
    }

    @PostConstruct
    private void buildSchema() throws IOException {
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        RegistryUtil.pathList.add("static/schema/query.graphql");
        RegistryUtil.pathList.add("static/schema/schema-user.graphql");
        RegistryUtil.pathList.add(BookProvider.schema_path);
        TypeDefinitionRegistry typeRegistry = RegistryUtil.registry();
        System.err.println(BookProvider.schema_path);

        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry,  BookProvider.buildRuntimeWiring(bookDataFetcher));

        RuntimeWiring bookWiring = BookProvider.buildRuntimeWiring(bookDataFetcher);
        RuntimeWiring userWiring = UserProvider.buildRuntimeWiring(userDataFetcher);
//        TypeRuntimeWiring.Builder builder = newTypeWiring("Mutation").dataFetcher("save", userDataFetcher.save());

        Map<String, Map<String, DataFetcher>> bookFetchers = bookWiring.getDataFetchers();
        Map<String, Map<String, DataFetcher>> userFetchers = userWiring.getDataFetchers();
        System.err.println("userFetchers"+userFetchers);
        System.err.println("bookFetchers"+bookFetchers);

//        bookFetchers.put("Mutation",userFetchers.get("Mutation"));
//        bookFetchers.put("Query",userFetchers.get("Query"));
//        bookFetchers.putAll(userFetchers);

        System.err.println("bookFetchers"+bookFetchers);

        Map<String, DataFetcher> mutationMapBook = bookFetchers.get("Mutation");
        Set<Map.Entry<String, DataFetcher>> entriesBook = mutationMapBook.entrySet();

        Map<String, DataFetcher> mutationMapUser = userFetchers.get("Mutation");
        Set<Map.Entry<String, DataFetcher>> entriesUser = mutationMapUser.entrySet();


        System.err.println("==="+entriesBook );


        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }


//    public RuntimeWiring buildRuntimeWiring() {
//        return RuntimeWiring.newRuntimeWiring()
//                .type(newTypeWiring("Mutation").dataFetcher("save", userDataFetcher.save()))
//                .type(newTypeWiring("Query").dataFetcher("save", userDataFetcher.save()))
//                .type(newTypeWiring("Query").dataFetcher("login", userDataFetcher.login()))
//                .build();
//    }

    @Bean
    public GraphQL graphQL() {
        System.err.println(graphQL);
        return graphQL;
    }


}
