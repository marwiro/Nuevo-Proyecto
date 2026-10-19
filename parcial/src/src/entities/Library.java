package entities;

public class Library {

    private String id;
    private String title;
    private int totalCopies;
    private int availableCopies;

    // Constructor: al registrar, todos los ejemplares quedan disponibles
    public Library(String id, String title, int totalCopies) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del material no puede estar vacío.");
        }
        if (totalCopies < 1) {
            throw new IllegalArgumentException("La cantidad de ejemplares debe ser al menos 1.");
        }
        this.id = id.trim();
        setTitle(title);
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.title = title.trim();
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getBorrowedCopies() {
        return totalCopies - availableCopies;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    // Prestar: baja un ejemplar disponible
    public void lend() {
        if (!isAvailable()) {
            throw new IllegalStateException("No quedan ejemplares disponibles de '" + title + "'.");
        }
        availableCopies--;
    }

    // Devolver: sube un ejemplar disponible
    public void giveBack() {
        if (availableCopies >= totalCopies) {
            throw new IllegalStateException("Todos los ejemplares de '" + title + "' ya están en la biblioteca.");
        }
        availableCopies++;
    }

    // Cambiar la cantidad total sin perder de vista los que están prestados
    public void changeTotalCopies(int newTotal) {
        if (newTotal < 1) {
            throw new IllegalArgumentException("La cantidad de ejemplares debe ser al menos 1.");
        }
        if (newTotal < getBorrowedCopies()) {
            throw new IllegalArgumentException("No se puede dejar en " + newTotal
                    + " ejemplares: hay " + getBorrowedCopies() + " prestados actualmente.");
        }
        availableCopies = newTotal - getBorrowedCopies();
        totalCopies = newTotal;
    }

    // Los hijos (Book y Magazine) sobrescriben estos métodos con sus propias reglas
    public int getMaxDays() {
        return 0;
    }

    public int getFinePerDay() {
        return 0;
    }

    public String getTypeName() {
        return "Material";
    }

    // Días de retraso según los días que se tuvo el material
    public int getLateDays(int daysLoaned) {
        return Math.max(0, daysLoaned - getMaxDays());
    }

    // Multa = días de retraso * multa por día
    public int calculateFine(int daysLoaned) {
        return getLateDays(daysLoaned) * getFinePerDay();
    }

    @Override
    public String toString() {
        return "ID: '" + id + "', Título: '" + title + "', Ejemplares: " + totalCopies
                + ", Disponibles: " + availableCopies;
    }
}
