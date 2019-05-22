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
@Component
public class ProviderUtil {

    private GraphQL graphQL;
    private  String path;
    private  RuntimeWiring runtimeWiring;


    public void initGraphQL(String path, RuntimeWiring runtimeWiring){
        this.path = path;
        this.runtimeWiring= runtimeWiring;
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //   被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，
    //   类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
   // @PostConstruct
    public void init()throws IOException{
        URL url = Resources.getResource(path);
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
//        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }



    @Bean
    public GraphQL graphQL() {
        return this.graphQL;
    }

}
