package entities;

public class Magazine extends Library {

    private static final int MAX_DAYS = 5;
    private static final int FINE_PER_DAY = 1000;

    private int editionNumber;

    public Magazine(String id, String title, int totalCopies, int editionNumber) {
        super(id, title, totalCopies);
        setEditionNumber(editionNumber);
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        if (editionNumber < 1) {
            throw new IllegalArgumentException("El número de edición debe ser al menos 1.");
        }
        this.editionNumber = editionNumber;
    }

    // Una revista se presta por 5 días y cada día de retraso cuesta $1.000
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
        return "Revista";
    }

    @Override
    public String toString() {
        return "[REVISTA] " + super.toString() + ", Edición N°: " + editionNumber;
    }
}
