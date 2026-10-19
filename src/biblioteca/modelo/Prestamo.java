package biblioteca.modelo;

public class Prestamo {

    private final int id;
    private final Usuario usuario;
    private final Material material;
    private final int diaPrestamo;
    private boolean devuelto;
    private int diaDevolucion;
    private int diasRetraso;
    private long multa;

    public Prestamo(int id, Usuario usuario, Material material, int diaPrestamo) {
        if (usuario == null || material == null) {
            throw new IllegalArgumentException("El préstamo necesita un usuario y un material.");
        }
        this.id = id;
        this.usuario = usuario;
        this.material = material;
        this.diaPrestamo = diaPrestamo;
        this.devuelto = false;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public int getDiaPrestamo() {
        return diaPrestamo;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public int getDiaDevolucion() {
        return diaDevolucion;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public long getMulta() {
        return multa;
    }

    // Último día en que se puede devolver sin multa.
    public int getDiaVencimiento() {
        return diaPrestamo + material.getDiasPrestamo();
    }

    public int calcularRetraso(int dia) {
        return Math.max(0, dia - getDiaVencimiento());
    }

    public boolean estaVencido(int dia) {
        return !devuelto && calcularRetraso(dia) > 0;
    }

    public void registrarDevolucion(int dia) {
        if (devuelto) {
            throw new IllegalStateException("El préstamo #" + id + " ya fue devuelto; no se puede devolver otra vez.");
        }
        if (dia < diaPrestamo) {
            throw new IllegalArgumentException("El día de devolución (" + dia
                    + ") no puede ser anterior al día del préstamo (" + diaPrestamo + ").");
        }
        diasRetraso = calcularRetraso(dia);
        multa = (long) diasRetraso * material.getMultaPorDia();
        diaDevolucion = dia;
        devuelto = true;
    }

    @Override
    public String toString() {
        String estado = devuelto ? "Devuelto el día " + diaDevolucion : "Activo";
        return "Préstamo #" + id + " | " + usuario.getNombre() + " (" + usuario.getDocumento() + ")"
                + " | " + material.getTipo() + " [" + material.getCodigo() + "] \"" + material.getTitulo() + "\""
                + " | Prestado día " + diaPrestamo + " | Vence día " + getDiaVencimiento()
                + " | " + estado;
    }
}
