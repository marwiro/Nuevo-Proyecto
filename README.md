# Nuevo-Proyecto
2. Creando proyecto

## Préstamos de la biblioteca universitaria

Aplicación de consola en Java (Scanner + menú), organizada en capas:

- `biblioteca.modelo`: `Material`, `Libro`, `Revista`, `Usuario`, `Prestamo`
- `biblioteca.repositorio`: `MaterialRepositorio`, `UsuarioRepositorio`, `PrestamoRepositorio` (ArrayList)
- `biblioteca.servicio`: `BibliotecaServicio` (reglas de negocio)
- `biblioteca.vista`: `Consola`, `MenuBiblioteca`
- `biblioteca.Main`: punto de entrada

Ejecutar: abrir la carpeta en IntelliJ IDEA (marcar `src` como Sources Root) y correr `biblioteca.Main`.
