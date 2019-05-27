package com.benma.graphql.provider;

import com.benma.graphql.Utils.ProviderUtil;
import com.benma.graphql.dataFetcher.BookDataFetcher;
import com.benma.graphql.dataFetcher.UserDataFetcher;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jdk.nashorn.internal.runtime.PrototypeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * author:szjz
 * date:2019/5/22
 */

@Component
public class UserProvider {

//    @Autowired
//    private static UserDataFetcher userDataFetcher;

    public static final String schema_path = "static/schema/schema-user.graphql";


    public static RuntimeWiring buildRuntimeWiring(UserDataFetcher userDataFetcher) {
        return RuntimeWiring.newRuntimeWiring()

                .type(newTypeWiring("Query")
                        .dataFetcher("save", userDataFetcher.save())
                        .dataFetcher("login", userDataFetcher.login())
                        .dataFetcher("userById", userDataFetcher.userById())
                        .dataFetcher("userByGender", userDataFetcher.userByGender()))
                .build();

    }

}
