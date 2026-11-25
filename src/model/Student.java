package model;

/** HERENCIA (extends Person) + POLIMORFISMO (sobrescribe describe). */
public class Student extends Person {

    private int id;
    private String major;
    private double gpa;

    public Student(int id, String name, String lastName, String email,
                   String major, double gpa) {
        super(name, lastName, email);
        this.id = id;
        this.major = major;
        this.gpa = gpa;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    @Override
    public String describe() {
        return String.format("[%d] %s | %s | %s | Promedio: %.2f",
                id, getFullName(), getEmail(), major, gpa);
    }

    @Override
    public String toString() { return describe(); }
}
