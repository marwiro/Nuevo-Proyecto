import entities.Book;
import entities.Library;
import entities.Loan;
import entities.Magazine;
import entities.User;
import services.LibraryServices;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryServices service = new LibraryServices();

    // Opciones del menú guardadas en un arreglo
    private static final String[] MENU_OPTIONS = {
            "Salir",
            "Registrar libro",
            "Registrar revista",
            "Listar inventario",
            "Buscar material por ID",
            "Buscar materiales por título",
            "Actualizar material (título o cantidad)",
            "Eliminar material",
            "Registrar usuario",
            "Listar usuarios",
            "Prestar material",
            "Devolver material",
            "Listar préstamos activos",
            "Reporte: materiales agotados",
            "Reporte: usuarios con préstamos vencidos"
    };

    public static void main(String[] args) {
        // Datos de prueba iniciales
        service.registerItem(new Book("B01", "Cien Años de Soledad", 3, "Gabriel García Márquez"));
        service.registerItem(new Magazine("M01", "National Geographic", 2, 145));

        boolean running = true;
        while (running) {
            try {
                showMenu();
                int option = readInt("Seleccione una opción: ");
                running = executeOption(option);
            } catch (NoSuchElementException e) {
                // Se cerró la entrada de datos: salir de forma ordenada
                System.out.println("\nSe cerró la entrada de datos. Fin del programa.");
                running = false;
            } catch (IllegalArgumentException | IllegalStateException e) {
                // Errores esperados (datos inválidos o reglas de la biblioteca)
                System.out.println("  ERROR: " + e.getMessage());
            } catch (Exception e) {
                // Cualquier otro error: el programa nunca debe cerrarse
                System.out.println("  ERROR inesperado: " + e.getMessage());
            }
        }
        System.out.println("Gracias por usar el sistema de la biblioteca.");
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
        for (int i = 1; i < MENU_OPTIONS.length; i++) {
            System.out.println(i + ". " + MENU_OPTIONS[i]);
        }
        System.out.println("0. " + MENU_OPTIONS[0]);
    }

    private static boolean executeOption(int option) {
        switch (option) {
            case 1:
                registerBook();
                break;
            case 2:
                registerMagazine();
                break;
            case 3:
                System.out.println("\n--- INVENTARIO ACTUAL ---");
                printList(service.getAllItems(), "No hay materiales registrados.");
                break;
            case 4:
                findItemById();
                break;
            case 5:
                findItemsByTitle();
                break;
            case 6:
                updateItem();
                break;
            case 7:
                deleteItem();
                break;
            case 8:
                registerUser();
                break;
            case 9:
                System.out.println("\n--- USUARIOS ---");
                printList(service.getAllUsers(), "No hay usuarios registrados.");
                break;
            case 10:
                borrowItem();
                break;
            case 11:
                returnItem();
                break;
            case 12:
                System.out.println("\n--- PRÉSTAMOS ACTIVOS ---");
                printList(service.getActiveLoans(), "No hay préstamos activos.");
                break;
            case 13:
                reportOutOfStock();
                break;
            case 14:
                reportOverdueUsers();
                break;
            case 0:
                System.out.println("Saliendo del sistema...");
                return false;
            default:
                System.out.println("Opción no válida.");
        }
        return true;
    }

    // ---------- Materiales ----------

    // Crear libro
    private static void registerBook() {
        String id = readText("ID del Libro: ");
        String title = readText("Título: ");
        String author = readText("Autor: ");
        Integer copies = readOptionalInt("Cantidad de ejemplares (Enter = 1): ");
        Book book;
        if (copies == null) {
            book = new Book(id, title, author);
        } else {
            book = new Book(id, title, copies, author);
        }
        service.registerItem(book);
        System.out.println("  Registrado con éxito: " + book);
    }

    // Crear revista
    private static void registerMagazine() {
        String id = readText("ID de la Revista: ");
        String title = readText("Título: ");
        int copies = readInt("Cantidad de ejemplares: ");
        int edition = readInt("Número de Edición: ");
        Magazine magazine = new Magazine(id, title, copies, edition);
        service.registerItem(magazine);
        System.out.println("  Registrada con éxito: " + magazine);
    }

    // Leer por ID
    private static void findItemById() {
        String id = readText("ID del material: ");
        Library item = service.getItemById(id);
        if (item == null) {
            System.out.println("  No existe un material con ese ID.");
        } else {
            System.out.println("  " + item);
        }
    }

    // Leer por título
    private static void findItemsByTitle() {
        String part = readText("Parte del título: ");
        printList(service.searchByTitle(part), "No se encontraron materiales con ese título.");
    }

    // Actualizar
    private static void updateItem() {
        String id = readText("ID del material a actualizar: ");
        String newTitle = readLine("Nuevo título (Enter = no cambiar): ");
        Integer newCopies = readOptionalInt("Nueva cantidad total de ejemplares (Enter = no cambiar): ");

        if (service.getItemById(id) == null) {
            throw new IllegalArgumentException("No existe un material con el ID " + id + ".");
        }
        if (!newTitle.isEmpty()) {
            service.updateTitle(id, newTitle);
        }
        if (newCopies != null) {
            service.updateCopies(id, newCopies);
        }
        System.out.println("  Material actualizado: " + service.getItemById(id));
    }

    // Eliminar
    private static void deleteItem() {
        String id = readText("ID del material a eliminar: ");
        service.deleteItem(id);
        System.out.println("  Material con ID " + id + " eliminado correctamente.");
    }

    // ---------- Usuarios ----------

    // Crear usuario
    private static void registerUser() {
        String document = readText("Documento: ");
        String name = readText("Nombre: ");
        User user = new User(document, name);
        service.registerUser(user);
        System.out.println("  Usuario registrado: " + user);
    }

    // ---------- Préstamos ----------

    // Prestar material
    private static void borrowItem() {
        String document = readText("Documento del usuario: ");
        String itemId = readText("ID del material a pedir prestado: ");
        int day = readInt("Día del préstamo: ");
        Library item = service.getItemById(itemId);
        try {
            Loan loan = service.borrowItem(document, itemId, day);
            System.out.println("  Préstamo exitoso: " + loan);
            System.out.println("  Debe devolverlo a más tardar el día " + loan.getDueDay() + ".");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("  Préstamo rechazado: " + e.getMessage());
        } finally {
            // Siempre se muestran los ejemplares que quedan, haya funcionado o no
            showCopies(item);
        }
    }

    // Devolver material
    private static void returnItem() {
        int loanId = readInt("Número del préstamo a devolver: ");
        int day = readInt("Día de devolución: ");
        Loan loan = service.getLoanById(loanId);
        Library item = (loan == null) ? null : loan.getItem();
        try {
            service.returnItem(loanId, day);
            System.out.println("\n=== RESUMEN DE DEVOLUCIÓN ===");
            System.out.println("Material devuelto: " + item.getTitle());
            if (loan.getFine() > 0) {
                System.out.println("Estado: ENTREGA CON RETRASO");
                System.out.println(loan.getLateDays() + " días de retraso, multa de " + money(loan.getFine()));
            } else {
                System.out.println("Estado: A TIEMPO (Sin multa)");
            }
            System.out.println("=============================");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("  Devolución rechazada: " + e.getMessage());
        } finally {
            // Siempre se muestran los ejemplares que quedan, haya funcionado o no
            showCopies(item);
        }
    }

    // ---------- Reportes ----------

    private static void reportOutOfStock() {
        System.out.println("\n--- MATERIALES AGOTADOS ---");
        printList(service.getOutOfStockItems(), "No hay materiales agotados.");
    }

    private static void reportOverdueUsers() {
        int day = readInt("Día de consulta: ");
        List<User> users = service.getUsersWithOverdueLoans(day);
        System.out.println("\n--- USUARIOS CON PRÉSTAMOS VENCIDOS AL DÍA " + day + " ---");
        if (users.isEmpty()) {
            System.out.println("  Ningún usuario tiene préstamos vencidos.");
            return;
        }
        for (User user : users) {
            System.out.println("  " + user);
            for (Loan loan : service.getOverdueLoansOfUser(user, day)) {
                int lateDays = loan.getLateDaysAt(day);
                int fine = lateDays * loan.getItem().getFinePerDay();
                System.out.println("    - " + loan.getItem().getTypeName() + " '" + loan.getItem().getTitle()
                        + "' (préstamo #" + loan.getId() + ", vencía el día " + loan.getDueDay() + "): "
                        + lateDays + " días de retraso, multa acumulada " + money(fine));
            }
        }
    }

    // ---------- Utilidades de entrada y salida ----------

    // Leer una línea (puede venir vacía)
    private static String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    // Leer texto obligatorio: repite hasta que no esté vacío
    private static String readText(String message) {
        String text = readLine(message);
        while (text.isEmpty()) {
            System.out.println("  Este dato es obligatorio.");
            text = readLine(message);
        }
        return text;
    }

    // Leer un entero: repite hasta que el usuario escriba un número válido
    private static int readInt(String message) {
        while (true) {
            String text = readLine(message);
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida: escriba un número entero.");
            }
        }
    }

    // Leer un entero opcional: devuelve null si el usuario solo presiona Enter
    private static Integer readOptionalInt(String message) {
        while (true) {
            String text = readLine(message);
            if (text.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida: escriba un número entero o deje vacío.");
            }
        }
    }

    private static void showCopies(Library item) {
        if (item != null) {
            System.out.println("  Ejemplares disponibles de '" + item.getTitle() + "': "
                    + item.getAvailableCopies() + " de " + item.getTotalCopies());
        }
    }

    private static void printList(List<?> list, String emptyMessage) {
        if (list.isEmpty()) {
            System.out.println("  " + emptyMessage);
            return;
        }
        for (Object element : list) {
            System.out.println("  " + element);
        }
    }

    // Formatea 9000 como $9.000
    private static String money(int value) {
        return String.format(Locale.GERMANY, "$%,d", value);
    }
}
