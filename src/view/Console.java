package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Person;
import model.Student;
import model.Teacher;
import service.StudentService;
import service.TeacherService;

// Vista: solo habla con el usuario. La lógica está en los service.
public class Console {

    private StudentService studentService;
    private TeacherService teacherService;
    private Scanner scanner = new Scanner(System.in);

    public Console(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public void start() {
        int option = -1;
        while (option != 0) {
            System.out.println();
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Estudiantes");
            System.out.println("2. Profesores");
            System.out.println("3. Ver todas las personas");
            System.out.println("0. Salir");
            option = readInt("Opción: ");

            if (option == 1) {
                studentMenu();
            } else if (option == 2) {
                teacherMenu();
            } else if (option == 3) {
                showAllPeople();
            } else if (option == 0) {
                System.out.println("¡Hasta luego!");
            } else {
                System.out.println("Opción no válida");
            }
        }
    }

    // POLIMORFISMO: una sola lista de Person con estudiantes y profesores.
    // Al llamar describe(), cada objeto responde a su manera.
    private void showAllPeople() {
        ArrayList<Person> people = new ArrayList<Person>();
        people.addAll(studentService.findAll());
        people.addAll(teacherService.findAll());

        if (people.size() == 0) {
            System.out.println("No hay personas registradas");
        }
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).describe());
        }
    }

    // ---------- ESTUDIANTES ----------

    private void studentMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println();
            System.out.println("--- ESTUDIANTES ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por id");
            System.out.println("4. Editar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            option = readInt("Opción: ");

            if (option == 1) {
                createStudent();
            } else if (option == 2) {
                listStudents();
            } else if (option == 3) {
                Student student = studentService.findById(readInt("Id: "));
                if (student == null) System.out.println("No existe ese estudiante");
                else System.out.println(student.describe());
            } else if (option == 4) {
                editStudent();
            } else if (option == 5) {
                if (studentService.delete(readInt("Id a eliminar: "))) System.out.println("✔ Eliminado");
                else System.out.println("No existe ese estudiante");
            } else if (option != 0) {
                System.out.println("Opción no válida");
            }
        }
    }

    private Student askStudent(int id) {
        String name = readText("Nombre: ");
        String lastName = readText("Apellido: ");
        String email = readText("Email: ");
        String major = readText("Carrera: ");
        double gpa = readDouble("Promedio (0-5): ");
        return new Student(id, name, lastName, email, major, gpa);
    }

    private void createStudent() {
        Student student = askStudent(0);
        String error = studentService.validate(student);
        if (error.equals("")) {
            studentService.create(student);
            System.out.println("✔ Creado: " + student.describe());
        } else {
            System.out.println("⚠ " + error);
        }
    }

    private void listStudents() {
        ArrayList<Student> students = studentService.findAll();
        if (students.size() == 0) {
            System.out.println("No hay estudiantes registrados");
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).describe());
        }
    }

    private void editStudent() {
        int id = readInt("Id a editar: ");
        Student current = studentService.findById(id);
        if (current == null) {
            System.out.println("No existe ese estudiante");
            return;
        }
        System.out.println("Actual: " + current.describe());
        Student student = askStudent(id);
        String error = studentService.validate(student);
        if (error.equals("")) {
            studentService.update(student);
            System.out.println("✔ Actualizado: " + student.describe());
        } else {
            System.out.println("⚠ " + error);
        }
    }

    // ---------- PROFESORES ----------

    private void teacherMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println();
            System.out.println("--- PROFESORES ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por id");
            System.out.println("4. Editar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            option = readInt("Opción: ");

            if (option == 1) {
                createTeacher();
            } else if (option == 2) {
                listTeachers();
            } else if (option == 3) {
                Teacher teacher = teacherService.findById(readInt("Id: "));
                if (teacher == null) System.out.println("No existe ese profesor");
                else System.out.println(teacher.describe());
            } else if (option == 4) {
                editTeacher();
            } else if (option == 5) {
                if (teacherService.delete(readInt("Id a eliminar: "))) System.out.println("✔ Eliminado");
                else System.out.println("No existe ese profesor");
            } else if (option != 0) {
                System.out.println("Opción no válida");
            }
        }
    }

    private Teacher askTeacher(int id) {
        String name = readText("Nombre: ");
        String lastName = readText("Apellido: ");
        String email = readText("Email: ");
        String subject = readText("Materia: ");
        return new Teacher(id, name, lastName, email, subject);
    }

    private void createTeacher() {
        Teacher teacher = askTeacher(0);
        String error = teacherService.validate(teacher);
        if (error.equals("")) {
            teacherService.create(teacher);
            System.out.println("✔ Creado: " + teacher.describe());
        } else {
            System.out.println("⚠ " + error);
        }
    }

    private void listTeachers() {
        ArrayList<Teacher> teachers = teacherService.findAll();
        if (teachers.size() == 0) {
            System.out.println("No hay profesores registrados");
        }
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(teachers.get(i).describe());
        }
    }

    private void editTeacher() {
        int id = readInt("Id a editar: ");
        Teacher current = teacherService.findById(id);
        if (current == null) {
            System.out.println("No existe ese profesor");
            return;
        }
        System.out.println("Actual: " + current.describe());
        Teacher teacher = askTeacher(id);
        String error = teacherService.validate(teacher);
        if (error.equals("")) {
            teacherService.update(teacher);
            System.out.println("✔ Actualizado: " + teacher.describe());
        } else {
            System.out.println("⚠ " + error);
        }
    }

    // ---------- LECTURA DE DATOS ----------

    private String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    // Si el usuario escribe algo que no es número, devuelve -1.
    private int readInt(String message) {
        try {
            return Integer.parseInt(readText(message));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Si no es número válido, devuelve -1 (el service lo marcará como error).
    private double readDouble(String message) {
        try {
            return Double.parseDouble(readText(message).replace(",", "."));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
