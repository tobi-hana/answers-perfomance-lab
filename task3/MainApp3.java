package ru.perfomancelab.task3;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MainApp3 {

    public static void main(String[] args) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

//            values.json is second argument
            JsonNode valuesNode = objectMapper.readTree(new File(args[1])).path("values");
            Map<String, String> valuesMap = new HashMap<>();
            for (JsonNode value : valuesNode) {
                valuesMap.put(value.path("id").asText(), value.path("value").asText());
            }

//            tests.json is first argument
            JsonNode testsNode = objectMapper.readTree(new File(args[0])).path("tests");
            setValues((ArrayNode) testsNode, valuesMap);

            JsonNode reportNode = objectMapper.readTree("{}");
            ((ObjectNode) reportNode).putIfAbsent("report", testsNode);
            objectMapper.writeValue(new File("report.json"), reportNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void setValues(ArrayNode arrayNode, Map<String, String> map){
        for(JsonNode node: arrayNode){
            for(Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> nested = it.next();
                if (nested.getValue().isArray()) {
                    setValues((ArrayNode)nested.getValue(), map);
                }
            }
            ((ObjectNode)node).put("value", map.getOrDefault(node.get("id").asText(), ""));
        }
    }
}
