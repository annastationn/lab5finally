package main.java.Modules;

import main.java.OrganizationObject.Organization;
import main.java.OrganizationObject.Coordinates;
import main.java.OrganizationObject.Address;
import main.java.OrganizationObject.OrganizationType;


import java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JSONProvider implements DataProvider {

    protected static String FILE_PATH;
    private LinkedHashMap<Long,Organization> hashMap = CollectionService.collection;

    public JSONProvider(String filePath) {
        this.FILE_PATH = filePath;
    }

    @Override
    public void save(LinkedHashMap<Long,Organization> collection) {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH)))) {
            writer.write("[\n");
            Long maxid = 0L;
            for (Map.Entry<Long,Organization> organizationEntry : collection.entrySet()){
                if (organizationEntry.getKey() > maxid){
                    maxid = organizationEntry.getKey();
                }
            }

            for (Map.Entry<Long, Organization> organizationEntry : collection.entrySet()) {
                Organization o = organizationEntry.getValue();
                writer.write("\t{\n");
                writer.write("\t\t\"id\": \"" + o.getId() + "\",\n");
                writer.write("\t\t\"name\": \"" + o.getName() + "\",\n");
                writer.write("\t\t\"coordinates\": \"" + o.getCoordinates().toString() + "\",\n");
                writer.write("\t\t\"creationDate\": \"" + o.getCreationDate() + "\",\n");
                writer.write("\t\t\"annualTurnover\": \"" + o.getAnnualTurnover() + "\",\n");
                writer.write("\t\t\"type\": \"" + o.getType() + "\",\n");
                writer.write("\t\t\"officialAddress\": \"" + o.getOfficialAddress() + "\"\n");
                if (o.getId() == maxid) {
                    writer.write("\t}\n");
                } else {
                    writer.write("\t},\n");
                }
            }

            writer.write("]");
            writer.close();
            System.out.println("Cities were saved successfuly");

        } catch (FileNotFoundException e){
            System.out.println("Error occured while saving collection: " + e.getMessage());
            System.exit(1);
        }
    }


    @Override
    public void load() {
        try (Scanner scanner = new Scanner(Path.of(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("{")) {
                    Organization o = new Organization((long) 0L,"",new Coordinates(0L,0), ZonedDateTime.now(),0,OrganizationType.TRUST, new Address("Unknown")); // Create a new Organization object
                    while (!(line = scanner.nextLine()).contains("}")) {
                        if (line.contains("\"id\":")) {
                            try {
                                o.setId(Long.parseLong(line.replaceAll("\\D+", "")));
                                CollectionService.existingIds.add(Long.parseLong(line.replaceAll("\\D+", "")));
                            } catch (NumberFormatException e){
                                System.out.println("Wrong ID found while parsing data. Default value set.");
                            }
                        } else if (line.contains("\"name\":")) {
                            try {
                                o.setName(line.replaceAll("\"name\": \"(.*)\",", "$1").trim());
                            } catch (IllegalArgumentException e){
                                System.out.println("Wrong name found while parsing data. Default value set.");
                            }
                        } else if (line.contains("\"coordinates\":")) {
                            // Assuming coordinates are stored in a specific format, parse them accordingly
                            o.setCoordinates(parseCoordinates(line));
                        } else if (line.contains("\"creationDate\":")) {
                            // Parse creation date (assuming it's stored in a specific format)
                            o.setCreationDate(parseCreationDate(line));
                        } else if (line.contains("\"annualTurnover\":")) {
                            try {
                                o.setAnnualTurnover(Float.parseFloat(line.replaceAll("\"annualTurnover\": \"(.*)\",", "$1").trim()));
                            } catch (NumberFormatException e){
                                System.out.println("Wrong annual turnover found while parsing data. Default value set.");
                            }
                        }
                         else if (line.contains("\"type\":")) {
                            // Parse standard of living (assuming it's stored in a specific format)
                            o.setType(parseOrganizationType(line));
                        } else if (line.contains("\"officialAddress\"")) {
                            o.setOfficialAddress(new Address(line));
                        }
                    }
                    hashMap.put(o.getId(),o); // Add the parsed Organization object to the collection
                }
            }
        } catch (SecurityException e){
            System.out.println("You have no access to " + FILE_PATH + ". Ask your admin for rights.");
        } catch (NoSuchFileException e){
            System.out.println("Error occured while loading collection" + e.getMessage() +". Bye!");
            System.exit(1);
        } catch (IOException e){
            System.out.println("No such file or directory. Bye!");
            System.exit(1);
        }
    }

    private OrganizationType parseOrganizationType(String line) {
        String standardOfLivingString = line.replaceAll("\t\t\"type\": \"(.*)\",", "$1").trim();

        try {
            return OrganizationType.valueOf(standardOfLivingString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong type. Default value set.");
            return OrganizationType.TRUST; // Return a default value or handle the error as needed
        }
    }


    public static ZonedDateTime parseCreationDate(String line) {
        // Define the date format pattern
        String date = line.replaceAll("\t\t\"creationDate\": \"(.*)\",","$1");
//        String dateFormat = "uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        // Parse the date string into a ZonedDateTime object
        ZonedDateTime creationDate = ZonedDateTime.parse(date);

        return creationDate;
    }


    private Coordinates parseCoordinates(String line) {
        String coordinatesString = line.replaceAll(
                "\t\t\"coordinates\": \"Coordinates: \\(x=(.*), y=(.*)\\)\",", "$1, $2");
        String[] coordinatesArray = coordinatesString.split(", ");

        try {
            Long x = Long.parseLong(coordinatesArray[0]);
            int y = Integer.parseInt(coordinatesArray[1]);
            return new Coordinates(x, y);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Some coordinates are invalid. Default value set.");;
            return new Coordinates(0L,0);
        }
    }
}