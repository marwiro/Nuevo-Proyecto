package entities;

public class Loan {

    private int id;
    private User user;
    private Library item;
    private int loanDay;
    private boolean returned;
    private int returnDay;
    private int lateDays;
    private int fine;

    public Loan(int id, User user, Library item, int loanDay) {
        if (user == null || item == null) {
            throw new IllegalArgumentException("El préstamo necesita un usuario y un material.");
        }
        this.id = id;
        this.user = user;
        this.item = item;
        this.loanDay = loanDay;
        this.returned = false;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Library getItem() {
        return item;
    }

    public int getLoanDay() {
        return loanDay;
    }

    public boolean isReturned() {
        return returned;
    }

    public int getReturnDay() {
        return returnDay;
    }

    public int getLateDays() {
        return lateDays;
    }

    public int getFine() {
        return fine;
    }

    // Último día en que se puede devolver sin multa
    public int getDueDay() {
        return loanDay + item.getMaxDays();
    }

    // Un préstamo está vencido si sigue activo y ya pasó su día de vencimiento
    public boolean isOverdue(int day) {
        return !returned && day > getDueDay();
    }

    // Días de retraso que lleva (o llevaría) el préstamo en un día dado
    public int getLateDaysAt(int day) {
        return item.getLateDays(day - loanDay);
    }

    // Registrar la devolución y calcular la multa
    public void registerReturn(int day) {
        if (returned) {
            throw new IllegalStateException("El préstamo #" + id + " ya fue devuelto; no se puede devolver otra vez.");
        }
        if (day < loanDay) {
            throw new IllegalArgumentException("El día de devolución (" + day
                    + ") no puede ser anterior al día del préstamo (" + loanDay + ").");
        }
        int daysLoaned = day - loanDay;
        lateDays = item.getLateDays(daysLoaned);
        fine = item.calculateFine(daysLoaned);
        returnDay = day;
        returned = true;
    }

    @Override
    public String toString() {
        String status = returned ? "Devuelto el día " + returnDay : "Activo";
        return "Préstamo #" + id + " | " + user.getName() + " (" + user.getDocument() + ")"
                + " | " + item.getTypeName() + " [" + item.getId() + "] '" + item.getTitle() + "'"
                + " | Prestado día " + loanDay + " | Vence día " + getDueDay()
                + " | " + status;
    }
}
