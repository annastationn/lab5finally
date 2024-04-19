package main.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {
    private String filename;

    public Parser(String filename) {
        this.filename = filename;
    }

    public List<CollectionService> parse() throws IOException, ParseException {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<CollectionService> collectionServices = gson.fromJson(reader, new TypeToken<List<CollectionService>>(){}.getType());
        reader.close();

        List<CollectionService> processedServices = new ArrayList<>();
        for (CollectionService service : collectionServices) {
            service.setId(service.generateId());
            service.setCreationDate(new Date());
            processedServices.add(service);
        }
        return processedServices;
    }
}
