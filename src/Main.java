import service.StudentService;
import view.Console;

public class Main {
    public static void main(String[] args) {
        new Console(new StudentService()).start();
    }
}
