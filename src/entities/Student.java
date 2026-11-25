package entities;

// HERENCIA: Student es una Person (extends), ya trae id, name, gender, phone y email.
public class Student extends Person {

    private String numberCard;
    private String grade;
    private String subject;

    public Student() {
        super();
    }

    public Student(Long id, String name, String gender, String phone, String email,
                   String numberCard, String grade, String subject) {
        super(id, name, gender, phone, email);
        this.numberCard = numberCard;
        this.grade = grade;
        this.subject = subject;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // POLIMORFISMO: mismo método describe(), distinto resultado según la clase.
    @Override
    public String describe() {
        return "Estudiante [" + getId() + "] " + getName() + " | " + getEmail()
                + " | Carnet: " + numberCard + " | Semestre: " + grade + " | Materia: " + subject;
    }
}
