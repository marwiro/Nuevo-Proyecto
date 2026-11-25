# CRUD de Estudiantes en capas (Java)

Base para el taller guiado de JavaFX + POO. Estructura simple: 3 carpetas + `Main`.

```
src/
├── Main.java
├── model/    Person (abstracta), Student
├── service/  Crud<T> (interfaz), StudentService (lógica + datos en memoria)
└── view/     Console (en el taller se reemplaza por JavaFX)
```

## Pilares de la POO

- **Abstracción**: `Person` (clase abstracta) e interfaz `Crud<T>`.
- **Encapsulamiento**: atributos `private` con getters/setters; `findAll()` devuelve una copia.
- **Herencia**: `Student extends Person`.
- **Polimorfismo**: `describe()` sobrescrito; `StudentService implements Crud<Student>` y la vista solo conoce `Crud`.

## Ejecutar

```bash
mkdir -p out && javac -cp out -d out $(find src -name "*.java")
java -cp out Main
```
