package com.taller.estudiantes.view;

import com.taller.estudiantes.controller.EstudianteControlador;
import com.taller.estudiantes.exception.EstudianteNoEncontradoException;
import com.taller.estudiantes.exception.ValidacionException;
import com.taller.estudiantes.model.Estudiante;

import java.util.List;
import java.util.Scanner;

/**
 * Capa de vista (consola). Solo interactúa con el usuario y delega al controlador.
 * En el taller se reemplaza por la interfaz JavaFX conservando las demás capas.
 */
public class ConsolaEstudiantes {

    private final EstudianteControlador controlador;
    private final Scanner in = new Scanner(System.in);

    public ConsolaEstudiantes(EstudianteControlador controlador) {
        this.controlador = controlador;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            try {
                switch (leerEntero("Opción: ")) {
                    case 1 -> crear();
                    case 2 -> listar();
                    case 3 -> buscar();
                    case 4 -> editar();
                    case 5 -> eliminar();
                    case 0 -> salir = true;
                    default -> System.out.println("Opción no válida");
                }
            } catch (ValidacionException | EstudianteNoEncontradoException e) {
                System.out.println("⚠ " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Debes ingresar un número válido");
            }
        }
        System.out.println("¡Hasta luego!");
    }

    private void mostrarMenu() {
        System.out.println("""

                ===== CRUD DE ESTUDIANTES =====
                1. Crear
                2. Listar
                3. Buscar por id
                4. Editar
                5. Eliminar
                0. Salir""");
    }

    private void crear() {
        Estudiante e = controlador.crear(
                leer("Nombre: "), leer("Apellido: "), leer("Email: "),
                leer("Carrera: "), leerDecimal("Promedio (0-5): "));
        System.out.println("✔ Creado: " + e.describir());
    }

    private void listar() {
        List<Estudiante> lista = controlador.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados");
            return;
        }
        lista.forEach(e -> System.out.println(e.describir()));
    }

    private void buscar() {
        System.out.println(controlador.buscar(leerEntero("Id: ")).describir());
    }

    private void editar() {
        int id = leerEntero("Id a editar: ");
        Estudiante actual = controlador.buscar(id);
        System.out.println("Actual: " + actual.describir());
        Estudiante e = controlador.editar(id,
                leer("Nuevo nombre: "), leer("Nuevo apellido: "), leer("Nuevo email: "),
                leer("Nueva carrera: "), leerDecimal("Nuevo promedio (0-5): "));
        System.out.println("✔ Actualizado: " + e.describir());
    }

    private void eliminar() {
        controlador.borrar(leerEntero("Id a eliminar: "));
        System.out.println("✔ Eliminado");
    }

    private String leer(String msg) {
        System.out.print(msg);
        return in.nextLine();
    }

    private int leerEntero(String msg) {
        return Integer.parseInt(leer(msg).trim());
    }

    private double leerDecimal(String msg) {
        return Double.parseDouble(leer(msg).trim().replace(',', '.'));
    }
}
