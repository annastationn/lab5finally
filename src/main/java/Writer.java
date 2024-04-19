package main.java;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Map;

public class Writer {

    private String filename;

    public Writer(String filename) {
        this.filename = filename;
    }
    public void write(Collection<Organization> rows){

        filename = "./data.json";
        StringBuilder data = new StringBuilder();

        data.append("[\n");
        for (Organization row : rows){
            String json = Converter.mapToJson(Converter.organisationToMap(row));
            data.append(Converter.tabJson(json));
        }
        data.deleteCharAt(data.length() - 1);
        data.append("\n]");

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename))){
            writer.write(data.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
