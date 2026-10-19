package biblioteca.modelo;

public class Material {

    private final String codigo;
    private String titulo;
    private int ejemplares;
    private int disponibles;

    public Material(String codigo, String titulo, int ejemplares) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del material no puede estar vacío.");
        }
        if (ejemplares < 1) {
            throw new IllegalArgumentException("La cantidad de ejemplares debe ser al menos 1.");
        }
        this.codigo = codigo.trim();
        setTitulo(titulo);
        this.ejemplares = ejemplares;
        this.disponibles = ejemplares;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.titulo = titulo.trim();
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public int getPrestados() {
        return ejemplares - disponibles;
    }

    public boolean hayDisponibles() {
        return disponibles > 0;
    }

    public void prestarEjemplar() {
        if (!hayDisponibles()) {
            throw new IllegalStateException("No quedan ejemplares disponibles de \"" + titulo + "\".");
        }
        disponibles--;
    }

    public void devolverEjemplar() {
        if (disponibles >= ejemplares) {
            throw new IllegalStateException("Todos los ejemplares de \"" + titulo + "\" ya están en la biblioteca.");
        }
        disponibles++;
    }

    public void actualizarEjemplares(int nuevoTotal) {
        if (nuevoTotal < 1) {
            throw new IllegalArgumentException("La cantidad de ejemplares debe ser al menos 1.");
        }
        if (nuevoTotal < getPrestados()) {
            throw new IllegalArgumentException("No se puede dejar en " + nuevoTotal
                    + " ejemplares: hay " + getPrestados() + " prestados actualmente.");
        }
        disponibles = nuevoTotal - getPrestados();
        ejemplares = nuevoTotal;
    }

    // Los hijos (Libro y Revista) sobrescriben estos métodos con sus propias reglas.
    public int getDiasPrestamo() {
        return 0;
    }

    public int getMultaPorDia() {
        return 0;
    }

    public String getTipo() {
        return "Material";
    }

    @Override
    public String toString() {
        return getTipo() + " [" + codigo + "] \"" + titulo + "\" | Ejemplares: " + ejemplares
                + " | Disponibles: " + disponibles;
    }
}
