package services;

import entities.Library;
import entities.Loan;
import entities.User;
import repositories.LibraryRepository;
import repositories.LoanRepository;
import repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class LibraryServices {

    private static final int MAX_ACTIVE_LOANS = 3;
    private static final int MAX_DAY = 1000000;

    private LibraryRepository libraryRepository = new LibraryRepository();
    private UserRepository userRepository = new UserRepository();
    private LoanRepository loanRepository = new LoanRepository();

    public LibraryServices() {
    }

    // ---------- Materiales ----------

    // Crear
    public void registerItem(Library item) {
        if (libraryRepository.findById(item.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un material con el ID " + item.getId() + ".");
        }
        libraryRepository.create(item);
    }

    // Leer
    public List<Library> getAllItems() {
        return libraryRepository.findAll();
    }

    public Library getItemById(String id) {
        return libraryRepository.findById(id);
    }

    public List<Library> searchByTitle(String part) {
        return libraryRepository.findByTitle(part);
    }

    public List<Library> getOutOfStockItems() {
        return libraryRepository.findOutOfStock();
    }

    // Actualizar
    public void updateTitle(String id, String newTitle) {
        if (!libraryRepository.updateTitle(id, newTitle)) {
            throw new IllegalArgumentException("No existe un material con el ID " + id + ".");
        }
    }

    public void updateCopies(String id, int newTotalCopies) {
        if (!libraryRepository.updateCopies(id, newTotalCopies)) {
            throw new IllegalArgumentException("No existe un material con el ID " + id + ".");
        }
    }

    // Eliminar (nunca un material que tenga préstamos activos)
    public void deleteItem(String id) {
        Library item = libraryRepository.findById(id);
        if (item == null) {
            throw new IllegalArgumentException("No existe un material con el ID " + id + ".");
        }
        if (loanRepository.hasActiveByItem(item.getId())) {
            throw new IllegalStateException("No se puede eliminar '" + item.getTitle() + "': tiene préstamos activos.");
        }
        libraryRepository.delete(id);
    }

    // ---------- Usuarios ----------

    // Crear
    public void registerUser(User user) {
        if (userRepository.findByDocument(user.getDocument()) != null) {
            throw new IllegalArgumentException("Ya existe un usuario con el documento " + user.getDocument() + ".");
        }
        userRepository.create(user);
    }

    // Leer
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ---------- Préstamos ----------

    // Prestar material
    public Loan borrowItem(String document, String itemId, int day) {
        checkDay(day);
        User user = userRepository.findByDocument(document);
        if (user == null) {
            throw new IllegalArgumentException("No existe un usuario con el documento " + document + ".");
        }
        Library item = libraryRepository.findById(itemId);
        if (item == null) {
            throw new IllegalArgumentException("No existe un material con el ID " + itemId + ".");
        }
        if (loanRepository.countActiveByUser(user.getDocument()) >= MAX_ACTIVE_LOANS) {
            throw new IllegalStateException(user.getName() + " ya tiene " + MAX_ACTIVE_LOANS + " préstamos activos.");
        }
        item.lend();
        Loan loan = new Loan(loanRepository.nextId(), user, item, day);
        return loanRepository.create(loan);
    }

    // Devolver material
    public Loan returnItem(int loanId, int day) {
        checkDay(day);
        Loan loan = loanRepository.findById(loanId);
        if (loan == null) {
            throw new IllegalArgumentException("No existe el préstamo #" + loanId + ".");
        }
        loan.registerReturn(day);
        loan.getItem().giveBack();
        return loan;
    }

    // Leer
    public Loan getLoanById(int id) {
        return loanRepository.findById(id);
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findActive();
    }

    // Reporte: usuarios con préstamos vencidos en un día dado (sin repetir usuarios)
    public List<User> getUsersWithOverdueLoans(int day) {
        checkDay(day);
        List<User> users = new ArrayList<>();
        for (Loan loan : loanRepository.findOverdue(day)) {
            if (!users.contains(loan.getUser())) {
                users.add(loan.getUser());
            }
        }
        return users;
    }

    public List<Loan> getOverdueLoansOfUser(User user, int day) {
        return loanRepository.findOverdueByUser(user, day);
    }

    // ---------- Validaciones ----------

    private void checkDay(int day) {
        if (day < 1 || day > MAX_DAY) {
            throw new IllegalArgumentException("El día debe ser un número entre 1 y " + MAX_DAY + ".");
        }
    }
}
