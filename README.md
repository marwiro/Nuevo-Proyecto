# CRUD de Estudiantes en capas (Java)

Base para el taller guiado de JavaFX + POO. Estructura simple: 3 carpetas + `Main`.

```
src/
├── Main.java
├── model/    Persona (abstracta), Estudiante
├── service/  Crud<T> (interfaz), EstudianteService (lógica + datos en memoria)
└── view/     Consola (en el taller se reemplaza por JavaFX)
```

## Pilares de la POO

- **Abstracción**: `Persona` (clase abstracta) e interfaz `Crud<T>`.
- **Encapsulamiento**: atributos `private` con getters/setters; `listar()` devuelve una copia.
- **Herencia**: `Estudiante extends Persona`.
- **Polimorfismo**: `describir()` sobrescrito; `EstudianteService implements Crud<Estudiante>` y la vista solo conoce `Crud`.

## Ejecutar

```bash
mkdir -p out && javac -cp out -d out $(find src -name "*.java")
java -cp out Main
```
