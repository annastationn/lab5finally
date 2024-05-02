package main.java.Modules;

import main.java.OrganizationObject.Organization;
import java.util.LinkedHashMap;

public interface DataProvider {

    void save(LinkedHashMap<Long,Organization> collection);

    void load();
}

