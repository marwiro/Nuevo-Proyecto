import entities.Person;
import entities.Student;
import entities.Teacher;
import services.IStudentService;
import services.ITeacherService;
import services.StudentServiceImpl;
import services.TeacherServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IStudentService studentService = new StudentServiceImpl();
        ITeacherService teacherService = new TeacherServiceImpl();

        int option;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Estudiantes");
            System.out.println("2. Profesores");
            System.out.println("3. Ver todas las personas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = readInt(sc);

            switch (option) {
                case 1:
                    studentMenu(sc, studentService);
                    break;

                case 2:
                    teacherMenu(sc, teacherService);
                    break;

                case 3:
                    showAllPeople(studentService, teacherService);
                    break;

                case 0:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (option != 0);

        sc.close();
    }

    // POLIMORFISMO: una sola lista de Person con estudiantes y profesores.
    // Al llamar describe(), cada objeto responde a su manera.
    private static void showAllPeople(IStudentService studentService, ITeacherService teacherService) {
        List<Person> people = new ArrayList<>();
        people.addAll(studentService.listAll());
        people.addAll(teacherService.listAll());

        if (people.isEmpty()) {
            System.out.println("No hay personas registradas.");
        }
        for (Person person : people) {
            System.out.println(person.describe());
        }
    }

    // ---------- ESTUDIANTES ----------

    private static void studentMenu(Scanner sc, IStudentService service) {
        int option;

        do {
            System.out.println("\n--- ESTUDIANTES ---");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por id");
            System.out.println("4. Editar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = readInt(sc);

            switch (option) {
                case 1:
                    Student created = service.create(askStudent(sc));
                    if (created != null) {
                        System.out.println("✔ Registrado: " + created.describe());
                    }
                    break;

                case 2:
                    listStudents(service);
                    break;

                case 3:
                    System.out.print("Id: ");
                    Student found = service.findOne(readLong(sc));
                    if (found == null) {
                        System.out.println("No existe ese estudiante.");
                    } else {
                        System.out.println(found.describe());
                    }
                    break;

                case 4:
                    editStudent(sc, service);
                    break;

                case 5:
                    System.out.print("Id a eliminar: ");
                    if (service.delete(readLong(sc))) {
                        System.out.println("✔ Eliminado");
                    } else {
                        System.out.println("No existe ese estudiante.");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (option != 0);
    }

    private static Student askStudent(Scanner sc) {
        Student student = new Student();

        System.out.print("Ingrese Nombre: ");
        student.setName(sc.nextLine());

        System.out.print("Ingrese Género: ");
        student.setGender(sc.nextLine());

        System.out.print("Ingrese Teléfono: ");
        student.setPhone(sc.nextLine());

        System.out.print("Ingrese Email: ");
        student.setEmail(sc.nextLine());

        System.out.print("Ingrese Número de carnet: ");
        student.setNumberCard(sc.nextLine());

        System.out.print("Ingrese Semestre: ");
        student.setGrade(sc.nextLine());

        System.out.print("Ingrese la Materia: ");
        student.setSubject(sc.nextLine());

        return student;
    }

    private static void listStudents(IStudentService service) {
        List<Student> students = service.listAll();
        if (students.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        }
        for (Student student : students) {
            System.out.println(student.describe());
        }
    }

    private static void editStudent(Scanner sc, IStudentService service) {
        System.out.print("Id a editar: ");
        Long id = readLong(sc);
        Student current = service.findOne(id);
        if (current == null) {
            System.out.println("No existe ese estudiante.");
            return;
        }
        System.out.println("Actual: " + current.describe());
        Student student = askStudent(sc);
        student.setId(id);
        if (service.update(student)) {
            System.out.println("✔ Actualizado: " + student.describe());
        }
    }

    // ---------- PROFESORES ----------

    private static void teacherMenu(Scanner sc, ITeacherService service) {
        int option;

        do {
            System.out.println("\n--- PROFESORES ---");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por id");
            System.out.println("4. Editar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            option = readInt(sc);

            switch (option) {
                case 1:
                    Teacher created = service.create(askTeacher(sc));
                    if (created != null) {
                        System.out.println("✔ Registrado: " + created.describe());
                    }
                    break;

                case 2:
                    listTeachers(service);
                    break;

                case 3:
                    System.out.print("Id: ");
                    Teacher found = service.findOne(readLong(sc));
                    if (found == null) {
                        System.out.println("No existe ese profesor.");
                    } else {
                        System.out.println(found.describe());
                    }
                    break;

                case 4:
                    editTeacher(sc, service);
                    break;

                case 5:
                    System.out.print("Id a eliminar: ");
                    if (service.delete(readLong(sc))) {
                        System.out.println("✔ Eliminado");
                    } else {
                        System.out.println("No existe ese profesor.");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (option != 0);
    }

    private static Teacher askTeacher(Scanner sc) {
        Teacher teacher = new Teacher();

        System.out.print("Ingrese Nombre: ");
        teacher.setName(sc.nextLine());

        System.out.print("Ingrese Género: ");
        teacher.setGender(sc.nextLine());

        System.out.print("Ingrese Teléfono: ");
        teacher.setPhone(sc.nextLine());

        System.out.print("Ingrese Email: ");
        teacher.setEmail(sc.nextLine());

        System.out.print("Ingrese la Materia que dicta: ");
        teacher.setSubject(sc.nextLine());

        return teacher;
    }

    private static void listTeachers(ITeacherService service) {
        List<Teacher> teachers = service.listAll();
        if (teachers.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        }
        for (Teacher teacher : teachers) {
            System.out.println(teacher.describe());
        }
    }

    private static void editTeacher(Scanner sc, ITeacherService service) {
        System.out.print("Id a editar: ");
        Long id = readLong(sc);
        Teacher current = service.findOne(id);
        if (current == null) {
            System.out.println("No existe ese profesor.");
            return;
        }
        System.out.println("Actual: " + current.describe());
        Teacher teacher = askTeacher(sc);
        teacher.setId(id);
        if (service.update(teacher)) {
            System.out.println("✔ Actualizado: " + teacher.describe());
        }
    }

    // ---------- LECTURA DE NÚMEROS ----------
    // Si el usuario escribe algo que no es número, devuelve -1.

    private static int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static Long readLong(Scanner sc) {
        try {
            return Long.parseLong(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1L;
        }
    }
}
