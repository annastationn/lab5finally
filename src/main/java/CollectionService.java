package main.java;

import main.java.Exceptions.*;
import main.java.OrganizationObject.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

import static main.java.OrganizationObject.OrganizationType.*;

public class CollectionService {
    private long maxId = 0;
    private Set<Long> existingIds = new HashSet<>();
    protected static Long elementsCount = 0L; //объявление статической переменной elementsCount, которая используется для хранения количества элементов в коллекции.
    private Date initializationDate;
    protected static LinkedHashMap<Long, Organization> collection;
    private boolean isReversed = false;

    protected static Scanner InputScanner;
    private long id;

    public CollectionService() {
        collection = new LinkedHashMap<>();
        this.initializationDate = new Date();
    }

    private class CompareOrganization implements Comparator<Organization> {

        @Override
        public int compare(Organization o1, Organization o2) {
            return (int) (o1.getAnnualTurnover() - o2.getAnnualTurnover());
        }
        @Override
        public Comparator<Organization> reversed() {
            return Comparator.super.reversed();
        }
    }
    protected record OrganizationWithOutId (String name, Coordinates coordinates, ZonedDateTime creationDate, float annualTurnover, OrganizationType organizationType, Address officialAddress) {}

    public void addElement(long id) {
        if (id > maxId) {
            maxId = id;
        }
        if (!collection.containsKey(id)) {
            OrganizationWithOutId source = createElement();
            Organization newElement = new Organization(
                    id,
                    source.name,
                    source.coordinates,
                    source.creationDate,
                    source.annualTurnover,
                    source.organizationType,
                    source.officialAddress
            );
            collection.put(id, newElement);
            System.out.println("Элемент успешно добавлен");
        }
        else{
            System.out.println("Element already exists. Use update command.");
        }
        existingIds.add(id);
}
    public long getMaxId() {
        return maxId;
    }
    public boolean containsId(long id) {
        return existingIds.contains(id);
    }

    public void info() {
        System.out.println("Тип коллекции: " + collection.getClass().getName());
        System.out.println("Дата создания: " + initializationDate);
        System.out.println("Количество элементов: " + collection.size());
    }

    public void show(){
        if (collection.isEmpty()){
            System.out.println("There is no elements in collection yet");
        } else{
            for (Map.Entry<Long,Organization> set : collection.entrySet()){
                System.out.println(set.getValue().toString() + "\n");
            }
        }
    }

    public void update(long current_id) {
        if (!collection.containsValue(getElementById(collection, current_id))) {
            System.out.println("Элемент с таким id не существует");
            return;
        }
        for (Map.Entry<Long, Organization> o : collection.entrySet()) {
            if (current_id == o.getValue().getId()) {
                // Создаем новый объект с обновленными данными, но с тем же id
                OrganizationWithOutId ref = createElement();

                Organization newElement = new Organization(
                        current_id, // Используем исходный id
                        ref.name,
                        ref.coordinates,
                        ref.creationDate,
                        ref.annualTurnover,
                        ref.organizationType,
                        ref.officialAddress
                );

                // Заменяем старый объект новым, сохраняя исходный ключ
                collection.put(o.getKey(), newElement);
                System.out.println("Элемент с id " + current_id + " обновлен успешно");
                break;
            }
        }
    }

    public void removeKey(long id) {
        if (!collection.containsKey(id)) {
            System.out.println("Элемента с таким id не существует");
            return;
        }

        collection.remove(id);
        System.out.println("Элемент с id " + id + " успешно удалён");

        // Создаем временную карту для обновления ключей
        Map<Long, Organization> updatedCollection = new LinkedHashMap<>();
        long newId = 1;
        for (Organization org : collection.values()) {
            updatedCollection.put(newId, org);
            org.setId(newId++);
        }

        // Обновляем исходную коллекцию
        collection.clear();
        collection.putAll(updatedCollection);
    }




    public void clear(){
        collection.clear();
        System.out.println("Все элементы успешно удалены");
    }
    public void removeLower() {
        OrganizationWithOutId ref = createElement();

        elementsCount+=1;
        Organization newElement = new Organization(
                elementsCount,
                ref.name,
                ref.coordinates,
                ref.creationDate,
                ref.annualTurnover,
                ref.organizationType,
                ref.officialAddress
        );
        CompareOrganization comparator = new CompareOrganization();
        for (Map.Entry<Long,Organization> set : collection.entrySet()) {
            if (comparator.compare(newElement, set.getValue()) > 0) {
                collection.remove(set.getKey());
            }
        }
    }

    public void removeGreaterKey(long startId) {
        int counter = 0;
        ArrayList<Organization> om = new ArrayList<>();
        for (Map.Entry<Long, Organization> set : collection.entrySet()){
            if (set.getValue().getId() > startId) {
                om.add(set.getValue());
                counter++;
            }
            else{
                System.out.print("");
            }
        }
        if (counter == 0){
            System.out.println("There is no elements with keys greater than yours");
        }
        else{
            for (Organization o : om){
                collection.remove(o.getId());
            }
            System.out.println("Success: elements deleted: " + counter);
        }
    }

    public void minByName() {
        if (!collection.isEmpty()) {
            Organization minEntry = null;
            for (Map.Entry<Long, Organization> set : collection.entrySet()) {
                if (minEntry == null || set.getValue().getName().length() < minEntry.getName().length()) {
                    minEntry = set.getValue();
                }
            }
            System.out.println("Минимальный элемент коллекции по имени: " + minEntry.toString());
        }
        else{
            System.out.println("Коллекция пуста!");
        }
    }

    public void filterGreaterThanType(OrganizationType standard){
        boolean hasItem = false;
        for (Map.Entry<Long,Organization> el : collection.entrySet()) {
            if (el.getValue().getType().equals(standard)) {
                hasItem = true;
                break;
            }
        }
        if (hasItem){
            for (Map.Entry<Long,Organization> o : collection.entrySet()) {
                if (String.valueOf(standard).length() < String.valueOf(o.getValue().getType()).length()){
                    System.out.println(o.getValue().toString() + "\n");
                }
            }
        } else {
            System.out.println("Organizations with such standard of living don't exist");
        }
    }

    public void printUniqueAnnualTurnover() {
        if (!collection.isEmpty()) {
            TreeSet<Float> set = new TreeSet<Float>();
            for (Map.Entry<Long, Organization> o : collection.entrySet()) {
                set.add(o.getValue().getAnnualTurnover());
            }
            for (Float num : set) {
                System.out.println("Unique turnover: " + num);
            }
        }
        else{
            System.out.println("Коллекция пуста!");
        }

    }
    private String askString (Scanner InputScanner) {
        while(true) {
            try {
                String name = InputScanner.nextLine().trim();
                if (name.isBlank()) {
                    throw new EmptyFieldException("Поле не может быть пустым. Введите его ещё раз: ");
                }
                return name;
            } catch (EmptyFieldException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private float askX(Scanner InputScanner) {
        while(true) {
            try {
                return Float.parseFloat(InputScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Введите его повторно: ");
            }
        }
    }
    private double askY(Scanner InputScanner) {
        while(true) {
            try {
                return Double.parseDouble(InputScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Введите его повторно: ");
            }
        }
    }
    private float askFloat (Scanner InputScanner) {
        while (true) {
            try {
                float num = Float.parseFloat(InputScanner.nextLine());
                if (num > 0) {
                    return num;
                } else {
                    throw new NegativeFieldException("Число не может быть отрицательным. Введите его ещё раз: ");
                }}
                catch(NumberFormatException e){
                    System.out.println("Неверный формат числа. Введите его повторно: ");
                } catch(NegativeFieldException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    private OrganizationType askOrganizationType(Scanner InputScanner) {
        while (true) {
            try{
                String type = InputScanner.nextLine().toUpperCase();
                OrganizationType organizationType;
                switch (type) {
                    case "COMMERCIAL":
                        organizationType = COMMERCIAL;
                        break;
                    case "GOVERNMENT":
                        organizationType = GOVERNMENT;
                        break;
                    case "TRUST":
                        organizationType = TRUST;
                        break;
                    case "PRIVATE_LIMITED_COMPANY":
                        organizationType = PRIVATE_LIMITED_COMPANY;
                        break;
                    case "OPEN_JOINT_STOCK_COMPANY":
                        organizationType = OPEN_JOINT_STOCK_COMPANY;
                        break;
                    default:
                        throw new EmptyFieldException("Такого типа организации не существует. " + "Заполните тип корректно: ");
                }
                return organizationType;
        } catch (EmptyFieldException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public int generateId() {
        // Реализация метода генерации уникального идентификатора
        return Math.toIntExact(++elementsCount); // Пример генерации уникального идентификатора
    }

    public void setId(int id) {
        // Реализация метода для установки идентификатора элемента коллекции
        // Например, можно просто установить значение id объекта
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        // Реализация метода для установки даты создания элемента коллекции
        this.initializationDate = creationDate;
    }


    public OrganizationWithOutId createElement(){
        InputScanner = PromptScanner.getUserScanner();

        System.out.println("Введите имя");
        String name = askString(InputScanner);

        System.out.println("Введите координату x:");
        float x = askX(InputScanner);

        System.out.println("Введите координату y:");
        double y = askY(InputScanner);

        Coordinates coordinates = new Coordinates((long) x, (int) y);

        ZonedDateTime creationDate = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);

        System.out.println("Введите годовой оборот");
        float annualTurnover = askFloat(InputScanner);

        System.out.println("Введите адрес организации");
        Address officialAddress = new Address(askString(InputScanner));

        System.out.print("""
                Введите один из доступных типов организации:
                 COMMERCIAL,
                 GOVERNMENT, 
                 TRUST, 
                 PRIVATE_LIMITED_COMPANY,
                 OPEN_JOINT_STOCK_COMPANY
                """);
        OrganizationType organizationType = askOrganizationType(InputScanner);
        return new OrganizationWithOutId(name, coordinates, creationDate, annualTurnover, organizationType, officialAddress);
    }

    public Organization getElementById(LinkedHashMap<Long,Organization> collection, Long id) {
        for (Map.Entry<Long,Organization> o : collection.entrySet()) {
            if (o.getValue().getId() == id) {
                return o.getValue();
            }
        }
        return null;
    }

    public LinkedHashMap<Long, Organization> getCollection() {
        return collection;
    }
}