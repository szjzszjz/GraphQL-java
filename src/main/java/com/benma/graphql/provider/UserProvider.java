package com.benma.graphql.provider;

import com.benma.graphql.dataFetcher.UserDataFetcher;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * author:szjz
 * date:2019/5/22
 */

@Component
public class UserProvider {

    @Autowired
    private UserDataFetcher userDataFetcher;

    public final String schema_path = "static/schema/schema-user.graphql";


    public TypeRuntimeWiring.Builder buildQueryRuntimeWiring() {
        TypeRuntimeWiring.Builder builder = newTypeWiring("Query")
                .dataFetcher("save", userDataFetcher.save())
                .dataFetcher("login", userDataFetcher.login())
                .dataFetcher("userById", userDataFetcher.userById())
                .dataFetcher("userByGender", userDataFetcher.userByGender());
        return builder;
    }

}
