package repositories;

import entities.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> list = new ArrayList<>();

    // Crear
    public User create(User user) {
        list.add(user);
        return user;
    }

    // Leer todos
    public List<User> findAll() {
        return new ArrayList<>(list);
    }

    // Leer por documento
    public User findByDocument(String document) {
        for (User user : list) {
            if (user.getDocument().equalsIgnoreCase(document.trim())) {
                return user;
            }
        }
        return null;
    }
}
