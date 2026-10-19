package biblioteca.modelo;

public class Revista extends Material {

    public static final int DIAS_PRESTAMO = 5;
    public static final int MULTA_POR_DIA = 1000;

    private int numeroEdicion;

    public Revista(String codigo, String titulo, int numeroEdicion, int ejemplares) {
        super(codigo, titulo, ejemplares);
        setNumeroEdicion(numeroEdicion);
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        if (numeroEdicion < 1) {
            throw new IllegalArgumentException("El número de edición debe ser al menos 1.");
        }
        this.numeroEdicion = numeroEdicion;
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
        return "Revista";
    }

    @Override
    public String toString() {
        return super.toString() + " | Edición: " + numeroEdicion;
    }
}
