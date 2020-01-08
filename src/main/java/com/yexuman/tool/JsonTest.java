package com.yexuman.tool;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * @author yexuman
 * @date 2020/1/7 16:25
 */
public class JsonTest {

    //JsonNodeFactory 实例，可全局共享
    private JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
    //JsonFactory 实例，线程安全
    private JsonFactory jsonFactory = new JsonFactory();

    /**
     * 使用树模型(tree model) API 来构造 json 字符串
     * {
     *         "name": "aaa",
     *         "email": "aaa@aa.com",
     *         "age": 24,
     *         "pets": {
     *             "petName": "kitty",
     *             "petAge": 3
     *         },
     *         "skills": [ "java","linux","mysql"]
     *     }
     * @throws IOException
     */
    public void treeModelGenerate() throws IOException {
        //根节点
        ObjectNode rootNode = jsonNodeFactory.objectNode();
        //往根节点中添加普通键值对
        rootNode.put("name","李白");
        rootNode.put("email","123@aa.com");
        rootNode.put("age",24);
        //往根节点中添加一个子对象
        ObjectNode petsNode = jsonNodeFactory.objectNode();
        petsNode.put("petName","kitty")
                .put("petAge",3);
        rootNode.set("pets", petsNode);
        //往根节点中添加一个数组
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        arrayNode.add("java")
                .add("linux")
                .add("mysql");
        rootNode.set("skills", arrayNode);
        //调用ObjectMapper的writeTree方法根据节点生成最终json字符串
        JsonGenerator generator = jsonFactory.createGenerator(System.out);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeTree(generator, rootNode);
    }

    /**
     * 使用树模型(tree model) API 来解析 json 字符串
     */
    public void treeModelParse(String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //使用ObjectMapper的readValue方法将json字符串解析到JsonNode实例中
        JsonNode rootNode = objectMapper.readTree(jsonString);
        //直接从rootNode中获取某个键的值，
        //这种方式在如果我们只需要一个长json串中某个字段值时非常方便
        JsonNode nameNode = rootNode.get("name");
        String name = nameNode.asText();
        System.out.println(name);
        //从 rootNode 中获取数组节点
        JsonNode skillsNode = rootNode.get("skills");
        for(int i = 0;i < skillsNode.size();i++) {
            System.out.println(skillsNode.get(i).asText());
        }
        //从 rootNode 中获取子对象节点
        JsonNode petsNode = rootNode.get("pets");
        String petsName = petsNode.get("petName").asText();
        System.out.println(petsName);
    }

    /**
     * Jackson包中可以构造树
     * @param args
     * @throws JsonProcessingException
     */
    public static void main(String[] args) throws Exception {
        JsonTest jsonTest =new JsonTest();
        jsonTest.treeModelGenerate();
        //System.out.println("-----------------------分割线------------------------");
        String jsonString ="{  \n" +
                "        \"name\": \"李白\",  \n" +
                "        \"email\": \"123@aa.com\",  \n" +
                "        \"age\": 24,  \n" +
                "        \"pets\": {  \n" +
                "            \"petName\": \"kitty\",  \n" +
                "            \"petAge\": 3  \n" +
                "        },  \n" +
                "        \"skills\": [ \"java\",\"linux\",\"mysql\"]  \n" +
                "    }  ";
       // jsonTest.treeModelParse(jsonString);

    }

}
