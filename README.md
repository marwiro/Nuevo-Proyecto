# CRUD de Estudiantes y Profesores (Java básico)

Base para el taller guiado de JavaFX + POO. 3 carpetas + `Main`.

```
src/
├── Main.java
├── model/    Person (abstracta), Student, Teacher
├── service/  StudentService, TeacherService (CRUD con ArrayList)
└── view/     Console (en el taller se reemplaza por JavaFX)
```

## Pilares de la POO

- **Abstracción**: `Person` es `abstract`; solo se usa para crear `Student` y `Teacher`.
- **Encapsulamiento**: atributos `private` con `get` y `set`.
- **Herencia**: `Student extends Person`, `Teacher extends Person`.
- **Polimorfismo**: `describe()` se escribe distinto en `Student` y `Teacher`; en `Console.showAllPeople()` una sola lista de `Person` los muestra a los dos.

## Ejecutar

```bash
mkdir -p out && javac -cp out -d out $(find src -name "*.java")
java -cp out Main
```
