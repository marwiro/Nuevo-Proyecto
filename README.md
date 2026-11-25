# CRUD de Estudiantes y Profesores (Java básico)

Base para el taller guiado de JavaFX + POO. Misma estructura que el repo del profesor (`prog2-01`).

```
src/
├── Main.java            (menús por consola)
├── entities/            Person (abstract), Student, Teacher
├── repositories/        StudentRepository, TeacherRepository (listas)
└── services/            IStudentService + StudentServiceImpl,
                         ITeacherService + TeacherServiceImpl
```

Flujo: `Main` → `service` (validaciones) → `repository` (guarda los datos).

## Pilares de la POO

- **Abstracción**: `Person` es `abstract` y las interfaces `IStudentService` / `ITeacherService` solo declaran qué se puede hacer.
- **Encapsulamiento**: atributos `private` con `get` y `set`.
- **Herencia**: `Student extends Person`, `Teacher extends Person`.
- **Polimorfismo**: `describe()` se escribe distinto en `Student` y `Teacher`; en `Main.showAllPeople()` una lista de `Person` muestra a los dos.

## Ejecutar

```bash
mkdir -p out && javac -cp out -d out $(find src -name "*.java")
java -cp out Main
```
