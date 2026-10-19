package biblioteca.modelo;

public class Usuario {

    private final String documento;
    private String nombre;

    public Usuario(String documento, String nombre) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacío.");
        }
        this.documento = documento.trim();
        setNombre(nombre);
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    @Override
    public String toString() {
        return nombre + " (documento " + documento + ")";
    }
}
