package com.benma.graphql.Utils;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * author:szjz
 * date:2019/5/23
 * schema 类型注册工具
 */
public class RegistryUtil {

    public static List<String> pathList = new ArrayList<>();

    public static TypeDefinitionRegistry registry() throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        for (String schema_path : pathList) {
//            File file = new File(RegistryUtil.class.getClassLoader().getResource(schema_path).getFile());
            URL url = Resources.getResource(schema_path);
            String sdl = Resources.toString(url, Charsets.UTF_8);
          typeRegistry.merge(schemaParser.parse(sdl));
        }
        System.err.println(typeRegistry);
        return typeRegistry;
    }
}
