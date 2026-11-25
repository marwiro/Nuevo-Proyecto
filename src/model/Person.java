package model;

/** ABSTRACCIÓN + ENCAPSULAMIENTO: base común, no se puede instanciar. */
public abstract class Person {

    private String name;
    private String lastName;
    private String email;

    protected Person(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return name + " " + lastName; }

    /** Cada subclase decide cómo se describe. */
    public abstract String describe();
}
