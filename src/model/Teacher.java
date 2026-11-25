package model;

// HERENCIA: Teacher también es una Person.
public class Teacher extends Person {

    private String subject;

    public Teacher(int id, String name, String lastName, String email, String subject) {
        super(id, name, lastName, email);
        this.subject = subject;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    // POLIMORFISMO: describe() del profesor muestra otros datos que el del estudiante.
    public String describe() {
        return "Profesor [" + getId() + "] " + getName() + " " + getLastName()
                + " | " + getEmail() + " | Materia: " + subject;
    }
}
