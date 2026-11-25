# CRUD de Estudiantes en capas (Java 21)

Base para el taller guiado de JavaFX + POO. La UI actual es de consola; en el taller
solo se reemplaza la capa `view` por JavaFX, el resto queda igual.

## Arquitectura en capas

```
view  →  controller  →  service  →  repository  →  (datos)
                  model (Persona, Estudiante) y exception se usan en todas
```

| Capa | Paquete | Responsabilidad |
|------|---------|-----------------|
| Vista | `view` | Interacción con el usuario (consola; luego JavaFX) |
| Controlador | `controller` | Recibe las acciones de la vista y delega |
| Servicio | `service` | Reglas de negocio y validaciones |
| Repositorio | `repository` | Almacenamiento (hoy en memoria) |
| Modelo | `model` | Entidades del dominio |

## Pilares de la POO dónde verlos

- **Abstracción**: `Persona` (clase abstracta), interfaces `Repositorio<T,ID>`, `EstudianteRepositorio`, `EstudianteServicio`.
- **Encapsulamiento**: atributos `private` con getters/setters; copia defensiva en `listarTodos()`.
- **Herencia**: `Estudiante extends Persona`.
- **Polimorfismo**: `describir()` sobrescrito; `EstudianteRepositorioMemoria` intercambiable por otra implementación; el servicio solo conoce la interfaz.

## Ejecutar

```bash
mvn compile exec:java -Dexec.mainClass=com.taller.estudiantes.Main
# o sin Maven:
javac -d out $(find src/main -name '*.java') && java -cp out com.taller.estudiantes.Main
```

## Para el taller (siguiente paso)

Crear `view/EstudiantesApp.java` (JavaFX) que use `EstudianteControlador`, y ensamblarlo en `Main`
igual que hoy se ensambla `ConsolaEstudiantes`.
