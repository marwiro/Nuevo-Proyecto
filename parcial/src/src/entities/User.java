package entities;

public class User {

    private String document;
    private String name;

    public User(String document, String name) {
        if (document == null || document.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacío.");
        }
        this.document = document.trim();
        setName(name);
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return name + " (documento " + document + ")";
    }
}
