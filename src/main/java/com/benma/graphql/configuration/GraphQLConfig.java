package com.benma.graphql.configuration;

import com.benma.graphql.Utils.HttpUtil;
import com.benma.graphql.provider.BookProvider;
import com.benma.graphql.provider.UserProvider;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * author:szjz
 * date:2019/5/22
 */
@Configuration
@Slf4j
public class GraphQLConfig {

    @Autowired
    private BookProvider bookProvider;
    @Autowired
    private UserProvider userProvider;

//    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {
        return GraphQL.newGraphQL(graphQLSchema()).build();
    }

    // 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次,此时还没有web环境，所有无法获取Request，影响用户认证
//    @Bean
    @PostConstruct
    public GraphQLSchema graphQLSchema() {
        log.debug("graphQLSchema");
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        //合并schema文件路径
        List<String> pathList = new ArrayList<>();
        pathList.add("static/schema/schema.graphql");
        pathList.add(bookProvider.schema_path);
        pathList.add(userProvider.schema_path);
        TypeDefinitionRegistry registry = null;
        try {
            registry = typeDefinitionRegistry(pathList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(registry, runtimeWiring());
        return graphQLSchema;
    }

    //问题：无法合并runtimeWiring  官方回复目前没有这种机制
    @Bean
    public RuntimeWiring runtimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                //但是可以通过对TypeRuntimeWiring 分类分模块处理，达到分类抽取最后合并生成唯一的runtimeWiring的效果
                .type(userProvider.buildQueryRuntimeWiring())
                .type(bookProvider.buildQueryRuntimeWiring())
                .type(bookProvider.buildMutationRuntimeWiring())
                .type(bookProvider.buildBookRuntimeWiring())
                .build();
    }


    //schema 类型注册 主要是用来整合各个schema文件路径
    public TypeDefinitionRegistry typeDefinitionRegistry(List<String> pathList) throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        for (String schema_path : pathList) {
//            File file = new File(RegistryUtil.class.getClassLoader().getResource(schema_path).getFile());
            URL url = Resources.getResource(schema_path);
            String sdl = Resources.toString(url, Charsets.UTF_8);
            typeRegistry.merge(schemaParser.parse(sdl));
        }
        return typeRegistry;
    }


}
