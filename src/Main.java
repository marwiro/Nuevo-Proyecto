import service.StudentService;
import service.TeacherService;
import view.Console;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        Console console = new Console(studentService, teacherService);
        console.start();
    }
}
