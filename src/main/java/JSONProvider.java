package main.java;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

public class JSONProvider {
    private CollectionService collection;
    private String fileName;

    public JSONProvider() {
        this.collection = collection;
        this.fileName = fileName;
    }

    public void writeToJson() throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(collection.getClass());
        FileWriter fileWriter = new FileWriter("testing.json");
        fileWriter.write(json);
        fileWriter.close();
    }

    public void wait(LinkedHashMap<Long, Organization> collection) {
    }

    public void load() {
    }
}