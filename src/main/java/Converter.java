package main.java;


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class Converter {

    public static Collection<Organization> linkedHashMapToCollection(LinkedHashMap<Long, Organization> LHM){
        return new HashSet<>(LHM.values());
    }

    public static Map<String, Object> organisationToMap(Organization organization){
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("id", organization.getId());
        data.put("name", organization.getName());
        data.put("coordinatesX", organization.getCoordinates().getX());
        data.put("coordinatesY", organization.getCoordinates().getY());
        data.put("creationDate", organization.getCreationDate());
        data.put("annualTurnover", organization.getAnnualTurnover());
        data.put("type", organization.getType());
        data.put("adress", organization.getOfficialAddress().toString());
        return data;
    }

    public static String mapToJson(Map<String, Object> map){
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        for (Map.Entry<String, Object> entry : map.entrySet()){
            json.append("\t\"").append(entry.getKey()).append("\":");
            if ((entry.getValue() instanceof Boolean) || (entry.getValue() instanceof Integer)){
                json.append(entry.getValue());
            } else {
                json.append("\"").append(entry.getValue()).append("\"");
            }
            json.append(",\n");
        }
        json.deleteCharAt(json.length() - 2);
        json.append("},\n");
        return json.toString();
    }

    public static String tabJson(String json){
        String[] rows = json.split("\n");
        StringBuilder tabbedJson = new StringBuilder();
        for (String row : rows){
            tabbedJson.append("\n\t").append(row);
        }
        return tabbedJson.toString();
    }
}
