package entities;

// ABSTRACCIÓN: Person es la idea general de "persona".
// Es abstract: no se crea directamente, solo a través de Student o Teacher.
public abstract class Person {

    // ENCAPSULAMIENTO: atributos private, se usan con get y set.
    private Long id;
    private String name;
    private String gender;
    private String phone;
    private String email;

    public Person() {
    }

    public Person(Long id, String name, String gender, String phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método abstract: cada hijo lo escribe a su manera (polimorfismo).
    public abstract String describe();
}
