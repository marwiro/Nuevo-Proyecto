package biblioteca.vista;

import biblioteca.modelo.Material;
import biblioteca.modelo.Prestamo;
import biblioteca.modelo.Usuario;
import biblioteca.servicio.BibliotecaServicio;

import java.util.List;
import java.util.NoSuchElementException;

public class MenuBiblioteca {

    private static final String[] OPCIONES = {
            "Salir",
            "Registrar libro",
            "Registrar revista",
            "Buscar material por código",
            "Buscar materiales por título",
            "Listar materiales",
            "Modificar título de un material",
            "Modificar cantidad de ejemplares",
            "Eliminar material",
            "Registrar usuario",
            "Listar usuarios",
            "Prestar material",
            "Devolver préstamo",
            "Listar préstamos activos",
            "Reporte: materiales agotados",
            "Reporte: usuarios con préstamos vencidos"
    };

    private final Consola consola = new Consola();
    private final BibliotecaServicio servicio = new BibliotecaServicio();

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            try {
                mostrarMenu();
                int opcion = consola.leerEntero("Seleccione una opción: ");
                continuar = ejecutar(opcion);
            } catch (NoSuchElementException e) {
                System.out.println("\nSe cerró la entrada de datos. Fin del programa.");
                continuar = false;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("  ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("  ERROR inesperado: " + e.getMessage());
            }
        }
        System.out.println("Gracias por usar el sistema de la biblioteca.");
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("===== BIBLIOTECA UNIVERSITARIA =====");
        for (int i = 1; i < OPCIONES.length; i++) {
            System.out.println(i + ". " + OPCIONES[i]);
        }
        System.out.println("0. " + OPCIONES[0]);
    }

    private boolean ejecutar(int opcion) {
        switch (opcion) {
            case 1:
                registrarLibro();
                break;
            case 2:
                registrarRevista();
                break;
            case 3:
                buscarPorCodigo();
                break;
            case 4:
                buscarPorTitulo();
                break;
            case 5:
                mostrarLista(servicio.listarMateriales(), "No hay materiales registrados.");
                break;
            case 6:
                modificarTitulo();
                break;
            case 7:
                modificarEjemplares();
                break;
            case 8:
                eliminarMaterial();
                break;
            case 9:
                registrarUsuario();
                break;
            case 10:
                mostrarLista(servicio.listarUsuarios(), "No hay usuarios registrados.");
                break;
            case 11:
                prestar();
                break;
            case 12:
                devolver();
                break;
            case 13:
                mostrarLista(servicio.listarPrestamosActivos(), "No hay préstamos activos.");
                break;
            case 14:
                reporteAgotados();
                break;
            case 15:
                reporteVencidos();
                break;
            case 0:
                return false;
            default:
                System.out.println("  Opción no válida. Elija un número del menú.");
        }
        return true;
    }

    // ---------- Materiales ----------

    private void registrarLibro() {
        String codigo = consola.leerTextoNoVacio("Código: ");
        String titulo = consola.leerTextoNoVacio("Título: ");
        String autor = consola.leerTextoNoVacio("Autor: ");
        Integer ejemplares = consola.leerEnteroOpcional("Ejemplares (Enter = 1): ");
        Material libro;
        if (ejemplares == null) {
            libro = servicio.registrarLibro(codigo, titulo, autor);
        } else {
            libro = servicio.registrarLibro(codigo, titulo, autor, ejemplares);
        }
        System.out.println("  OK, libro registrado: " + libro);
    }

    private void registrarRevista() {
        String codigo = consola.leerTextoNoVacio("Código: ");
        String titulo = consola.leerTextoNoVacio("Título: ");
        int edicion = consola.leerEntero("Número de edición: ");
        int ejemplares = consola.leerEntero("Ejemplares: ");
        Material revista = servicio.registrarRevista(codigo, titulo, edicion, ejemplares);
        System.out.println("  OK, revista registrada: " + revista);
    }

    private void buscarPorCodigo() {
        String codigo = consola.leerTextoNoVacio("Código a buscar: ");
        Material material = servicio.buscarMaterial(codigo);
        if (material == null) {
            System.out.println("  No existe un material con ese código.");
        } else {
            System.out.println("  " + material);
        }
    }

    private void buscarPorTitulo() {
        String parte = consola.leerTextoNoVacio("Parte del título: ");
        mostrarLista(servicio.buscarMaterialesPorTitulo(parte), "No se encontraron materiales con ese título.");
    }

    private void modificarTitulo() {
        String codigo = consola.leerTextoNoVacio("Código del material: ");
        String titulo = consola.leerTextoNoVacio("Nuevo título: ");
        servicio.modificarTitulo(codigo, titulo);
        System.out.println("  OK, título actualizado: " + servicio.buscarMaterial(codigo));
    }

    private void modificarEjemplares() {
        String codigo = consola.leerTextoNoVacio("Código del material: ");
        int total = consola.leerEntero("Nueva cantidad total de ejemplares: ");
        servicio.modificarEjemplares(codigo, total);
        System.out.println("  OK, ejemplares actualizados: " + servicio.buscarMaterial(codigo));
    }

    private void eliminarMaterial() {
        String codigo = consola.leerTextoNoVacio("Código del material a eliminar: ");
        servicio.eliminarMaterial(codigo);
        System.out.println("  OK, material eliminado.");
    }

    // ---------- Usuarios ----------

    private void registrarUsuario() {
        String documento = consola.leerTextoNoVacio("Documento: ");
        String nombre = consola.leerTextoNoVacio("Nombre: ");
        Usuario usuario = servicio.registrarUsuario(documento, nombre);
        System.out.println("  OK, usuario registrado: " + usuario);
    }

    // ---------- Préstamos ----------

    private void prestar() {
        String documento = consola.leerTextoNoVacio("Documento del usuario: ");
        String codigo = consola.leerTextoNoVacio("Código del material: ");
        int dia = consola.leerEntero("Día del préstamo: ");
        Material material = servicio.buscarMaterial(codigo);
        try {
            Prestamo prestamo = servicio.prestar(documento, codigo, dia);
            System.out.println("  OK, préstamo registrado: " + prestamo);
            System.out.println("  Debe devolverlo a más tardar el día " + prestamo.getDiaVencimiento() + ".");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("  Préstamo rechazado: " + e.getMessage());
        } finally {
            mostrarEjemplares(material);
        }
    }

    private void devolver() {
        int id = consola.leerEntero("Número del préstamo: ");
        int dia = consola.leerEntero("Día de devolución: ");
        Prestamo prestamo = servicio.buscarPrestamo(id);
        Material material = (prestamo == null) ? null : prestamo.getMaterial();
        try {
            servicio.devolver(id, dia);
            System.out.println("  OK, devolución registrada.");
            if (prestamo.getDiasRetraso() == 0) {
                System.out.println("  Sin multa.");
            } else {
                System.out.println("  " + prestamo.getDiasRetraso() + " días de retraso, multa de "
                        + Consola.moneda(prestamo.getMulta()));
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("  Devolución rechazada: " + e.getMessage());
        } finally {
            mostrarEjemplares(material);
        }
    }

    // ---------- Reportes ----------

    private void reporteAgotados() {
        System.out.println("--- Materiales agotados ---");
        mostrarLista(servicio.materialesAgotados(), "No hay materiales agotados.");
    }

    private void reporteVencidos() {
        int dia = consola.leerEntero("Día de consulta: ");
        List<Usuario> usuarios = servicio.usuariosConPrestamosVencidos(dia);
        System.out.println("--- Usuarios con préstamos vencidos al día " + dia + " ---");
        if (usuarios.isEmpty()) {
            System.out.println("  Ningún usuario tiene préstamos vencidos.");
            return;
        }
        for (Usuario usuario : usuarios) {
            System.out.println("  " + usuario);
            for (Prestamo p : servicio.prestamosVencidosDeUsuario(usuario, dia)) {
                int retraso = p.calcularRetraso(dia);
                long multa = (long) retraso * p.getMaterial().getMultaPorDia();
                System.out.println("    - " + p.getMaterial().getTipo() + " \"" + p.getMaterial().getTitulo()
                        + "\" (préstamo #" + p.getId() + ", vencía el día " + p.getDiaVencimiento() + "): "
                        + retraso + " días de retraso, multa acumulada " + Consola.moneda(multa));
            }
        }
    }

    // ---------- Utilidades de impresión ----------

    private void mostrarEjemplares(Material material) {
        if (material != null) {
            System.out.println("  Ejemplares disponibles de \"" + material.getTitulo() + "\": "
                    + material.getDisponibles() + " de " + material.getEjemplares());
        }
    }

    private void mostrarLista(List<?> elementos, String mensajeVacio) {
        if (elementos.isEmpty()) {
            System.out.println("  " + mensajeVacio);
            return;
        }
        for (Object elemento : elementos) {
            System.out.println("  " + elemento);
        }
    }
}
