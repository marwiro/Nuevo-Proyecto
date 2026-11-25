import service.EstudianteService;
import view.Consola;

public class Main {
    public static void main(String[] args) {
        new Consola(new EstudianteService()).iniciar();
    }
}
