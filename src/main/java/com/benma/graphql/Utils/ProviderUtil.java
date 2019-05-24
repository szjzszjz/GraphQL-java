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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.coxautodev.graphql.tools.SchemaParser.newParser;
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

    @PostConstruct
    private void buildSchema() throws IOException {
        SchemaGenerator schemaGenerator1 = new SchemaGenerator();

        RegistryUtil.pathList.add("static/schema/schema.graphql");
        RegistryUtil.pathList.add("static/schema/schema-user.graphql");
        RegistryUtil.pathList.add(BookProvider.schema_path);
        TypeDefinitionRegistry typeRegistry = RegistryUtil.registry();
        System.err.println(BookProvider.schema_path);

        RuntimeWiring userWiring = UserProvider.buildRuntimeWiring(userDataFetcher);
        RuntimeWiring bookWiring = BookProvider.buildRuntimeWiring(bookDataFetcher);

        GraphQLSchema graphQLSchema1= schemaGenerator1.makeExecutableSchema(typeRegistry,  userWiring);

        this.graphQL = GraphQL.newGraphQL(graphQLSchema1)
                .build();

    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }


}
