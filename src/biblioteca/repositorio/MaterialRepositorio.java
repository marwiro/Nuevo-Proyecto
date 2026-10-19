package biblioteca.repositorio;

import biblioteca.modelo.Material;

import java.util.ArrayList;
import java.util.List;

public class MaterialRepositorio {

    private final List<Material> materiales = new ArrayList<>();

    public void agregar(Material material) {
        materiales.add(material);
    }

    public Material buscarPorCodigo(String codigo) {
        for (Material m : materiales) {
            if (m.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return m;
            }
        }
        return null;
    }

    public List<Material> buscarPorTitulo(String parteDelTitulo) {
        List<Material> resultado = new ArrayList<>();
        String buscado = parteDelTitulo.trim().toLowerCase();
        for (Material m : materiales) {
            if (m.getTitulo().toLowerCase().contains(buscado)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    public List<Material> listar() {
        return new ArrayList<>(materiales);
    }

    public List<Material> listarAgotados() {
        List<Material> agotados = new ArrayList<>();
        for (Material m : materiales) {
            if (!m.hayDisponibles()) {
                agotados.add(m);
            }
        }
        return agotados;
    }

    public boolean eliminar(Material material) {
        return materiales.remove(material);
    }
}
