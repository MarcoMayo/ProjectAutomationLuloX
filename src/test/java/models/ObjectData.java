package models;

public class ObjectData {

    private String name;
    private String data;

    public ObjectData(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public static ObjectData from(String name, String data) {
        return new ObjectData(name, data);
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String toJson() {
        return String.format("{\n" +
                "  \"name\": \"%s\",\n" +
                "  \"data\": %s\n" +
                "}", name, data);
    }
}