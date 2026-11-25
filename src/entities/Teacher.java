package entities;

// HERENCIA: Teacher también es una Person.
public class Teacher extends Person {

    private String subject;

    public Teacher() {
        super();
    }

    public Teacher(Long id, String name, String gender, String phone, String email, String subject) {
        super(id, name, gender, phone, email);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // POLIMORFISMO: el profesor se describe distinto al estudiante.
    @Override
    public String describe() {
        return "Profesor [" + getId() + "] " + getName() + " | " + getEmail()
                + " | Materia que dicta: " + subject;
    }
}
