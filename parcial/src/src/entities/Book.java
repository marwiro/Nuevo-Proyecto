package entities;

public class Book extends Library {

    private static final int MAX_DAYS = 15;
    private static final int FINE_PER_DAY = 1500;

    private String author;

    // Constructor sobrecargado: si no se indica la cantidad de ejemplares, se asume uno
    public Book(String id, String title, String author) {
        this(id, title, 1, author);
    }

    public Book(String id, String title, int totalCopies, String author) {
        super(id, title, totalCopies);
        setAuthor(author);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }
        this.author = author.trim();
    }

    // Un libro se presta por 15 días y cada día de retraso cuesta $1.500
    @Override
    public int getMaxDays() {
        return MAX_DAYS;
    }

    @Override
    public int getFinePerDay() {
        return FINE_PER_DAY;
    }

    @Override
    public String getTypeName() {
        return "Libro";
    }

    @Override
    public String toString() {
        return "[LIBRO] " + super.toString() + ", Autor: '" + author + "'";
    }
}
