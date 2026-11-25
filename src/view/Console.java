package view;

import model.Student;
import service.Crud;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Vista de consola. Solo pide datos y muestra resultados; la lógica está en el service. */
public class Console {

    private final Crud<Student> service;
    private final Scanner input = new Scanner(System.in);

    public Console(Crud<Student> service) {
        this.service = service;
    }

    public void start() {
        int option = -1;
        while (option != 0) {
            System.out.println("""

                    ===== CRUD DE ESTUDIANTES =====
                    1. Crear
                    2. Listar
                    3. Buscar por id
                    4. Editar
                    5. Eliminar
                    0. Salir""");
            try {
                option = readInt("Opción: ");
                switch (option) {
                    case 1 -> create();
                    case 2 -> list();
                    case 3 -> System.out.println(service.findById(readInt("Id: ")));
                    case 4 -> edit();
                    case 5 -> remove();
                    case 0 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción no válida");
                }
            } catch (IllegalArgumentException | NoSuchElementException ex) {
                System.out.println("⚠ " + ex.getMessage());
            }
        }
    }

    private void create() {
        Student student = askData(0);
        System.out.println("✔ Creado: " + service.create(student));
    }

    private void list() {
        List<Student> students = service.findAll();
        if (students.isEmpty()) System.out.println("No hay estudiantes registrados");
        else students.forEach(System.out::println);
    }

    private void edit() {
        int id = readInt("Id a editar: ");
        System.out.println("Actual: " + service.findById(id));
        System.out.println("✔ Actualizado: " + service.update(askData(id)));
    }

    private void remove() {
        service.delete(readInt("Id a eliminar: "));
        System.out.println("✔ Eliminado");
    }

    private Student askData(int id) {
        String name = readLine("Nombre: ");
        String lastName = readLine("Apellido: ");
        String email = readLine("Email: ");
        String major = readLine("Carrera: ");
        double gpa = readDouble("Promedio (0-5): ");
        return new Student(id, name, lastName, email, major, gpa);
    }

    private String readLine(String message) {
        System.out.print(message);
        return input.nextLine().trim();
    }

    private int readInt(String message) {
        try {
            return Integer.parseInt(readLine(message));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Debes ingresar un número entero");
        }
    }

    private double readDouble(String message) {
        try {
            return Double.parseDouble(readLine(message).replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Debes ingresar un número válido");
        }
    }
}
