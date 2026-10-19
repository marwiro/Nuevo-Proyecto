package biblioteca.modelo;

public class Libro extends Material {

    public static final int DIAS_PRESTAMO = 15;
    public static final int MULTA_POR_DIA = 1500;

    private String autor;

    // Si no se indica la cantidad de ejemplares, se asume uno.
    public Libro(String codigo, String titulo, String autor) {
        this(codigo, titulo, autor, 1);
    }

    public Libro(String codigo, String titulo, String autor, int ejemplares) {
        super(codigo, titulo, ejemplares);
        setAutor(autor);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }
        this.autor = autor.trim();
    }

    @Override
    public int getDiasPrestamo() {
        return DIAS_PRESTAMO;
    }

    @Override
    public int getMultaPorDia() {
        return MULTA_POR_DIA;
    }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    public String toString() {
        return super.toString() + " | Autor: " + autor;
    }
}
