package biblioteca.servicio;

import biblioteca.modelo.Libro;
import biblioteca.modelo.Material;
import biblioteca.modelo.Prestamo;
import biblioteca.modelo.Revista;
import biblioteca.modelo.Usuario;
import biblioteca.repositorio.MaterialRepositorio;
import biblioteca.repositorio.PrestamoRepositorio;
import biblioteca.repositorio.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaServicio {

    public static final int MAX_PRESTAMOS_ACTIVOS = 3;
    public static final int DIA_MAXIMO = 1_000_000;

    private final MaterialRepositorio materiales = new MaterialRepositorio();
    private final UsuarioRepositorio usuarios = new UsuarioRepositorio();
    private final PrestamoRepositorio prestamos = new PrestamoRepositorio();

    // ---------- Materiales ----------

    // Si no se indica la cantidad de ejemplares, se asume uno.
    public Libro registrarLibro(String codigo, String titulo, String autor) {
        validarCodigoNuevo(codigo);
        Libro libro = new Libro(codigo, titulo, autor);
        materiales.agregar(libro);
        return libro;
    }

    public Libro registrarLibro(String codigo, String titulo, String autor, int ejemplares) {
        validarCodigoNuevo(codigo);
        Libro libro = new Libro(codigo, titulo, autor, ejemplares);
        materiales.agregar(libro);
        return libro;
    }

    public Revista registrarRevista(String codigo, String titulo, int numeroEdicion, int ejemplares) {
        validarCodigoNuevo(codigo);
        Revista revista = new Revista(codigo, titulo, numeroEdicion, ejemplares);
        materiales.agregar(revista);
        return revista;
    }

    public Material buscarMaterial(String codigo) {
        return materiales.buscarPorCodigo(codigo);
    }

    public List<Material> buscarMaterialesPorTitulo(String parteDelTitulo) {
        return materiales.buscarPorTitulo(parteDelTitulo);
    }

    public List<Material> listarMateriales() {
        return materiales.listar();
    }

    public void modificarTitulo(String codigo, String nuevoTitulo) {
        obtenerMaterial(codigo).setTitulo(nuevoTitulo);
    }

    public void modificarEjemplares(String codigo, int nuevoTotal) {
        obtenerMaterial(codigo).actualizarEjemplares(nuevoTotal);
    }

    public void eliminarMaterial(String codigo) {
        Material material = obtenerMaterial(codigo);
        if (prestamos.tieneActivosDeMaterial(material.getCodigo())) {
            throw new IllegalStateException("No se puede eliminar \"" + material.getTitulo()
                    + "\": tiene préstamos activos.");
        }
        materiales.eliminar(material);
    }

    public List<Material> materialesAgotados() {
        return materiales.listarAgotados();
    }

    // ---------- Usuarios ----------

    public Usuario registrarUsuario(String documento, String nombre) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacío.");
        }
        if (usuarios.buscarPorDocumento(documento) != null) {
            throw new IllegalArgumentException("Ya existe un usuario con el documento " + documento.trim() + ".");
        }
        Usuario usuario = new Usuario(documento, nombre);
        usuarios.agregar(usuario);
        return usuario;
    }

    public Usuario buscarUsuario(String documento) {
        return usuarios.buscarPorDocumento(documento);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios.listar();
    }

    // ---------- Préstamos ----------

    public Prestamo prestar(String documento, String codigo, int dia) {
        validarDia(dia);
        Usuario usuario = usuarios.buscarPorDocumento(documento);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe un usuario con el documento " + documento.trim() + ".");
        }
        Material material = obtenerMaterial(codigo);
        if (prestamos.contarActivosDeUsuario(usuario.getDocumento()) >= MAX_PRESTAMOS_ACTIVOS) {
            throw new IllegalStateException(usuario.getNombre() + " ya tiene " + MAX_PRESTAMOS_ACTIVOS
                    + " préstamos activos.");
        }
        material.prestarEjemplar();
        Prestamo prestamo = new Prestamo(prestamos.generarId(), usuario, material, dia);
        prestamos.agregar(prestamo);
        return prestamo;
    }

    public Prestamo devolver(int idPrestamo, int dia) {
        validarDia(dia);
        Prestamo prestamo = prestamos.buscarPorId(idPrestamo);
        if (prestamo == null) {
            throw new IllegalArgumentException("No existe el préstamo #" + idPrestamo + ".");
        }
        prestamo.registrarDevolucion(dia);
        prestamo.getMaterial().devolverEjemplar();
        return prestamo;
    }

    public Prestamo buscarPrestamo(int id) {
        return prestamos.buscarPorId(id);
    }

    public List<Prestamo> listarPrestamosActivos() {
        return prestamos.listarActivos();
    }

    public List<Usuario> usuariosConPrestamosVencidos(int dia) {
        validarDia(dia);
        List<Usuario> resultado = new ArrayList<>();
        for (Prestamo p : prestamos.listarVencidos(dia)) {
            if (!resultado.contains(p.getUsuario())) {
                resultado.add(p.getUsuario());
            }
        }
        return resultado;
    }

    public List<Prestamo> prestamosVencidosDeUsuario(Usuario usuario, int dia) {
        return prestamos.listarVencidosDeUsuario(usuario, dia);
    }

    // ---------- Validaciones ----------

    private Material obtenerMaterial(String codigo) {
        Material material = materiales.buscarPorCodigo(codigo);
        if (material == null) {
            throw new IllegalArgumentException("No existe un material con el código " + codigo.trim() + ".");
        }
        return material;
    }

    private void validarCodigoNuevo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del material no puede estar vacío.");
        }
        if (materiales.buscarPorCodigo(codigo) != null) {
            throw new IllegalArgumentException("Ya existe un material con el código " + codigo.trim() + ".");
        }
    }

    private void validarDia(int dia) {
        if (dia < 1 || dia > DIA_MAXIMO) {
            throw new IllegalArgumentException("El día debe ser un número entre 1 y " + DIA_MAXIMO + ".");
        }
    }
}
