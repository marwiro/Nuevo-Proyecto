package biblioteca.repositorio;

import biblioteca.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    private final List<Usuario> usuarios = new ArrayList<>();

    public void agregar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarPorDocumento(String documento) {
        for (Usuario u : usuarios) {
            if (u.getDocumento().equalsIgnoreCase(documento.trim())) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }
}
