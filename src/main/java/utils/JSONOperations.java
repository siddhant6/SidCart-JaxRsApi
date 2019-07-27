package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JSONOperations {
    
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static Map<String, Object> castFromJsonToMap(String JSON) {
        
        if (JSON == null) {
            return null;
        }
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(JSON, new TypeReference<Map<String, Object>>() {
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return map;
    }
    
    public static String listMapToJson(List<Map<String, Object>> list) throws Exception {
        
        if (list == null) {
            return null;
        }
        return objectMapper.writeValueAsString(list);
    }
    
    public static String castFromMapToJson(Map jsonMap) {
        
        String jsonResponse = null;
        
        try {
            jsonResponse = objectMapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return jsonResponse;
    }
}
