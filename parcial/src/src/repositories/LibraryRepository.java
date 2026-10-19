package repositories;

import entities.Library;
import java.util.ArrayList;
import java.util.List;

public class LibraryRepository {

    private List<Library> list = new ArrayList<>();

    // Crear
    public Library create(Library item) {
        list.add(item);
        return item;
    }

    // Leer todos
    public List<Library> findAll() {
        return new ArrayList<>(list);
    }

    // Leer por ID
    public Library findById(String id) {
        for (Library item : list) {
            if (item.getId().equalsIgnoreCase(id.trim())) {
                return item;
            }
        }
        return null;
    }

    // Leer por una parte del título
    public List<Library> findByTitle(String part) {
        List<Library> result = new ArrayList<>();
        for (Library item : list) {
            if (item.getTitle().toLowerCase().contains(part.trim().toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    // Leer los materiales agotados (sin ejemplares disponibles)
    public List<Library> findOutOfStock() {
        List<Library> result = new ArrayList<>();
        for (Library item : list) {
            if (!item.isAvailable()) {
                result.add(item);
            }
        }
        return result;
    }

    // Actualizar el título
    public boolean updateTitle(String id, String newTitle) {
        Library item = findById(id);
        if (item != null) {
            item.setTitle(newTitle);
            return true;
        }
        return false;
    }

    // Actualizar la cantidad de ejemplares
    public boolean updateCopies(String id, int newTotalCopies) {
        Library item = findById(id);
        if (item != null) {
            item.changeTotalCopies(newTotalCopies);
            return true;
        }
        return false;
    }

    // Eliminar
    public boolean delete(String id) {
        Library item = findById(id);
        if (item != null) {
            list.remove(item);
            return true;
        }
        return false;
    }
}
