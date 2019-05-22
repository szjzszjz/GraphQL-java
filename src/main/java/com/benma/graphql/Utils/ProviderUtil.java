package com.benma.graphql.Utils;

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


/**
 * author:szjz
 * date:2019/5/22
 */
//@Component
public class ProviderUtil {
    private  String path;
    private  RuntimeWiring runtimeWiring;

    public GraphQL initGraphQL(String path, RuntimeWiring runtimeWiring){
        this.path = path;
        this.runtimeWiring= runtimeWiring;
        GraphQL graphQL ;
        try {
            graphQL = init();
            return graphQL;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public GraphQL init()throws IOException{
        URL url = Resources.getResource(path);
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        return graphQL;
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

}
