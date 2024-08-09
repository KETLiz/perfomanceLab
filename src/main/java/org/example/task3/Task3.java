package org.example.task3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class Task3 {

//    File fileValue = new File("src/main/java/org/example/task3/values.json");
//    File fileTests = new File("src/main/java/org/example/task3/tests.json");
//    File fileReport = new File("src/main/java/org/example/task3/report.json");

    public String[] getFilesPath(String[] args) {
        if(args.length != 3) {
            throw new ArrayIndexOutOfBoundsException("Количество путей к файлам должно быть именно 3!");
        }
        return args;
    }

    public List<String> readValuesFromJson(String fileValue) throws IOException, ParseException {
        File file = new File(fileValue);
        List<String> values = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(file);
        JsonNode v = jsonNode.get("values");
        List<Map<String, Object>> list = mapper.convertValue(v, new TypeReference<List<Map<String, Object>>>() {
        });
        for(int i = 0; i < list.size(); i++) {
            String status = (String) list.get(i).get("value");
            values.add(status);
        }
        return values;
    }

    public List<Map<String, Object>> readTestsJson(String fileTests) throws IOException {
        File file = new File(fileTests);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        JsonNode t = jsonNode.get("tests");
        List<Map<String, Object>> list = objectMapper.convertValue(t, new TypeReference<List<Map<String, Object>>>() {
        });
        return list;
    }

    public void writeToFile(String fileReport, List<Map<String, Object>> tests, List<String> values) throws IOException {
        int i = 0; // счётчик по списку tests
        int j = 0; // счётчик по списку values
        while(i < tests.size() && j < values.size()) {
            Map<String, Object> test = tests.get(i);
            test.put("value", values.get(j));
            j++;
            if(test.containsKey("values")) {
                List<Map<String, Object>> nestedList = (List<Map<String, Object>>) test.get("values");
                List<String> nestedValues = values.subList(0, j+1);
                writeToFile(fileReport, nestedList, nestedValues);
            }
            i++;
        }

        ObjectMapper o = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        o.writeValue(new File(fileReport), tests);
    }

    public static void main(String[] args) throws IOException, ParseException {
        Task3 task3 = new Task3();
        String[] filesPath = task3.getFilesPath(args);

        String fileValue = filesPath[0]; // путь к файлу values.json
        List<String> values = task3.readValuesFromJson(fileValue);

        String fileTests = filesPath[1]; // путь к файлу tests.json
        List<Map<String, Object>> tests = task3.readTestsJson(fileTests);

        String fileReport = filesPath[2]; // путь к файлу report.java
        task3.writeToFile(fileReport, tests, values);
    }
}
