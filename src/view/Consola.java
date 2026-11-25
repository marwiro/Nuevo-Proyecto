package view;

import model.Estudiante;
import service.Crud;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Vista de consola. Solo pide datos y muestra resultados; la lógica está en el service. */
public class Consola {

    private final Crud<Estudiante> servicio;
    private final Scanner in = new Scanner(System.in);

    public Consola(Crud<Estudiante> servicio) {
        this.servicio = servicio;
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("""

                    ===== CRUD DE ESTUDIANTES =====
                    1. Crear
                    2. Listar
                    3. Buscar por id
                    4. Editar
                    5. Eliminar
                    0. Salir""");
            try {
                opcion = leerEntero("Opción: ");
                switch (opcion) {
                    case 1 -> crear();
                    case 2 -> listar();
                    case 3 -> System.out.println(servicio.buscar(leerEntero("Id: ")));
                    case 4 -> editar();
                    case 5 -> eliminar();
                    case 0 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción no válida");
                }
            } catch (IllegalArgumentException | NoSuchElementException ex) {
                System.out.println("⚠ " + ex.getMessage());
            }
        }
    }

    private void crear() {
        Estudiante e = pedirDatos(0);
        System.out.println("✔ Creado: " + servicio.crear(e));
    }

    private void listar() {
        List<Estudiante> lista = servicio.listar();
        if (lista.isEmpty()) System.out.println("No hay estudiantes registrados");
        else lista.forEach(System.out::println);
    }

    private void editar() {
        int id = leerEntero("Id a editar: ");
        System.out.println("Actual: " + servicio.buscar(id));
        System.out.println("✔ Actualizado: " + servicio.actualizar(pedirDatos(id)));
    }

    private void eliminar() {
        servicio.eliminar(leerEntero("Id a eliminar: "));
        System.out.println("✔ Eliminado");
    }

    private Estudiante pedirDatos(int id) {
        String nombre = leer("Nombre: ");
        String apellido = leer("Apellido: ");
        String email = leer("Email: ");
        String carrera = leer("Carrera: ");
        double promedio = leerDecimal("Promedio (0-5): ");
        return new Estudiante(id, nombre, apellido, email, carrera, promedio);
    }

    private String leer(String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }

    private int leerEntero(String msg) {
        try {
            return Integer.parseInt(leer(msg));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Debes ingresar un número entero");
        }
    }

    private double leerDecimal(String msg) {
        try {
            return Double.parseDouble(leer(msg).replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Debes ingresar un número válido");
        }
    }
}
