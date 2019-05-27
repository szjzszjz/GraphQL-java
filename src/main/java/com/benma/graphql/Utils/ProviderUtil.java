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
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

import static com.coxautodev.graphql.tools.SchemaParser.newParser;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;


/**
 * author:szjz
 * date:2019/5/22
 */
@Configuration
public class ProviderUtil {

    @Autowired
    private UserDataFetcher userDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;

    private GraphQL graphQL;



    @PostConstruct
    public void userGraphQLSchema() throws IOException {
        //问题：无法合并runtimeWiring  官方回复目前没有这种机制
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        SchemaGenerator bookschemaGenerator = new SchemaGenerator();
        List<String > pathList = new ArrayList<>();
        pathList.add("static/schema/schema.graphql");
        pathList.add(BookProvider.schema_path);
        pathList.add(UserProvider.schema_path);
//        RegistryUtil.pathList.add(BookProvider.schema_path);
        TypeDefinitionRegistry typeRegistry= RegistryUtil.registry(pathList);

        RuntimeWiring userWiring = UserProvider.buildRuntimeWiring(userDataFetcher);
        RuntimeWiring bookWiring = BookProvider.buildRuntimeWiring(bookDataFetcher);

        GraphQLSchema graphQLSchema= schemaGenerator.makeExecutableSchema(typeRegistry,  userWiring);
        GraphQLSchema bookgraphQLSchema= bookschemaGenerator.makeExecutableSchema(typeRegistry,  bookWiring);

        //只对最后一个graphqlSchema 生效
        this.graphQL = GraphQL.newGraphQL(bookgraphQLSchema).schema(graphQLSchema)
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return this.graphQL;
    }


}
