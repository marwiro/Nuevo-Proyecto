package biblioteca.repositorio;

import biblioteca.modelo.Prestamo;
import biblioteca.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class PrestamoRepositorio {

    private final List<Prestamo> prestamos = new ArrayList<>();
    private int ultimoId = 0;

    public int generarId() {
        ultimoId++;
        return ultimoId;
    }

    public void agregar(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public Prestamo buscarPorId(int id) {
        for (Prestamo p : prestamos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Prestamo> listarActivos() {
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (!p.isDevuelto()) {
                activos.add(p);
            }
        }
        return activos;
    }

    public int contarActivosDeUsuario(String documento) {
        int total = 0;
        for (Prestamo p : prestamos) {
            if (!p.isDevuelto() && p.getUsuario().getDocumento().equalsIgnoreCase(documento)) {
                total++;
            }
        }
        return total;
    }

    public boolean tieneActivosDeMaterial(String codigo) {
        for (Prestamo p : prestamos) {
            if (!p.isDevuelto() && p.getMaterial().getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    public List<Prestamo> listarVencidos(int dia) {
        List<Prestamo> vencidos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.estaVencido(dia)) {
                vencidos.add(p);
            }
        }
        return vencidos;
    }

    public List<Prestamo> listarVencidosDeUsuario(Usuario usuario, int dia) {
        List<Prestamo> vencidos = new ArrayList<>();
        for (Prestamo p : listarVencidos(dia)) {
            if (p.getUsuario() == usuario) {
                vencidos.add(p);
            }
        }
        return vencidos;
    }
}
