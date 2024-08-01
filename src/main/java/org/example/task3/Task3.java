package org.example.task3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Task3 {

    File fileValue = new File("src/main/java/org/example/task3/values.json");
    File fileTests = new File("src/main/java/org/example/task3/tests.json");

    public List<String> readValuesFromJson() throws IOException, ParseException {
        List<String> values = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(fileValue);
        JsonNode v = jsonNode.get("values");
        List<Map<String, Object>> list = mapper.convertValue(v, new TypeReference<List<Map<String, Object>>>() {
        });
        for(int i = 0; i < list.size(); i++) {
            String status = (String) list.get(i).get("value");
            values.add(status);
        }
        return values;
    }

    public void foo(List<String> values) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(fileTests);
        JsonNode t = jsonNode.get("tests");
        List<Map<String, Object>> list = objectMapper.convertValue(t, new TypeReference<List<Map<String, Object>>>() {
        });

        for(int i = 0; i < list.size(); i++) {
            Map<String, Object> res = list.get(i);
            res.put("value", values.get(i));
            System.out.println(res);
        }

//        for(int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(0));
//        }
    }



    public static void main(String[] args) throws IOException, ParseException {
        Task3 task3 = new Task3();
        List<String> values = task3.readValuesFromJson();
        task3.foo(values);
    }
}
