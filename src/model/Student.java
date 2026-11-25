package model;

// HERENCIA: Student es una Person (extends), ya trae id, name, lastName y email.
public class Student extends Person {

    private String major;
    private double gpa;

    public Student(int id, String name, String lastName, String email, String major, double gpa) {
        super(id, name, lastName, email);
        this.major = major;
        this.gpa = gpa;
    }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    // POLIMORFISMO: mismo método describe(), pero cada clase lo hace a su manera.
    public String describe() {
        return "Estudiante [" + getId() + "] " + getName() + " " + getLastName()
                + " | " + getEmail() + " | Carrera: " + major + " | Promedio: " + gpa;
    }
}
