package repositories;

import entities.Loan;
import entities.User;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    private List<Loan> list = new ArrayList<>();
    private int lastId = 0;

    // Genera el número del siguiente préstamo
    public int nextId() {
        lastId++;
        return lastId;
    }

    // Crear
    public Loan create(Loan loan) {
        list.add(loan);
        return loan;
    }

    // Leer por ID
    public Loan findById(int id) {
        for (Loan loan : list) {
            if (loan.getId() == id) {
                return loan;
            }
        }
        return null;
    }

    // Leer los préstamos activos (no devueltos)
    public List<Loan> findActive() {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : list) {
            if (!loan.isReturned()) {
                result.add(loan);
            }
        }
        return result;
    }

    // Contar los préstamos activos de un usuario
    public int countActiveByUser(String document) {
        int total = 0;
        for (Loan loan : list) {
            if (!loan.isReturned() && loan.getUser().getDocument().equalsIgnoreCase(document)) {
                total++;
            }
        }
        return total;
    }

    // Saber si un material tiene préstamos activos
    public boolean hasActiveByItem(String itemId) {
        for (Loan loan : list) {
            if (!loan.isReturned() && loan.getItem().getId().equalsIgnoreCase(itemId)) {
                return true;
            }
        }
        return false;
    }

    // Leer los préstamos vencidos en un día dado
    public List<Loan> findOverdue(int day) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : list) {
            if (loan.isOverdue(day)) {
                result.add(loan);
            }
        }
        return result;
    }

    // Leer los préstamos vencidos de un usuario en un día dado
    public List<Loan> findOverdueByUser(User user, int day) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : findOverdue(day)) {
            if (loan.getUser() == user) {
                result.add(loan);
            }
        }
        return result;
    }
}
