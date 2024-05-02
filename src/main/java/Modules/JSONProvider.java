package main.java.Modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.OrganizationObject.Address;
import main.java.OrganizationObject.Coordinates;
import main.java.OrganizationObject.Organization;
import main.java.OrganizationObject.OrganizationType;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class JSONProvider implements DataProvider {

    private LinkedHashMap<Long,Organization> hashMap = CollectionService.collection;
    protected static String FILE_PATH;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public JSONProvider(String pathToCollection) {
        this.FILE_PATH = pathToCollection;
    }

    @Override
    public void save(LinkedHashMap<Long, Organization> collection) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            writer.write(GSON.toJson(collection));
            System.out.println("Organizations were saved successfully");
        } catch (IOException e) {
            System.out.println("Error occurred while saving collection: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void load() {
        try (Scanner scanner = new Scanner(Path.of(FILE_PATH))) {
            LinkedHashMap<Long, Organization> collection = GSON.fromJson(scanner.nextLine(), LinkedHashMap.class);
            collection.forEach((id, organization) -> {
                validateAndFixOrganization(organization);
                hashMap.put(id,organization);
            });
        } catch (SecurityException e) {
            System.out.println("You have no access to " + FILE_PATH + ". Ask your admin for rights.");
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred while loading collection" + e.getMessage() + ". Bye!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("No such file or directory. Bye!");
            System.exit(1);
        }
    }

    private void validateAndFixOrganization(Organization organization) {
        if (organization.getId() <= 0) {
            organization.setId((long) (Math.random() * Long.MAX_VALUE));
        }
        if (organization.getName() == null || organization.getName().isEmpty()) {
            organization.setName("Unnamed");
        }
        if (organization.getCoordinates() == null) {
            organization.setCoordinates(new Coordinates(0, 0));
        }
        if (organization.getCreationDate() == null) {
            organization.setCreationDate(ZonedDateTime.now());
        }
        if (organization.getAnnualTurnover() <= 0) {
            organization.setAnnualTurnover(0);
        }
        if (organization.getType() == null) {
            organization.setType(OrganizationType.COMMERCIAL);
        }
        if (organization.getOfficialAddress() == null) {
            organization.setOfficialAddress(new Address(""));
        }
    }
}
