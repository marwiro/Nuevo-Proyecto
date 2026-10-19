package biblioteca.vista;

import java.util.Locale;
import java.util.Scanner;

public class Consola {

    private final Scanner scanner = new Scanner(System.in);

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public String leerTextoNoVacio(String mensaje) {
        String texto = leerTexto(mensaje);
        while (texto.isEmpty()) {
            System.out.println("  Este dato es obligatorio.");
            texto = leerTexto(mensaje);
        }
        return texto;
    }

    public int leerEntero(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida: escriba un número entero.");
            }
        }
    }

    // Devuelve null si el usuario deja el dato en blanco.
    public Integer leerEnteroOpcional(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            if (texto.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("  Entrada inválida: escriba un número entero o deje vacío.");
            }
        }
    }

    // Formatea 9000 como $9.000
    public static String moneda(long valor) {
        return String.format(Locale.GERMANY, "$%,d", valor);
    }
}
