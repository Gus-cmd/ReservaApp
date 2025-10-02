import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    @SuppressWarnings("FieldMayBeFinal")
    private List<Mesa> mesas;

    public Restaurante() {
        this.mesas = new ArrayList<>();
    }

    public void agregarMesa(Mesa m) {
        mesas.add(m);
    }

    // Método para buscar una mesa disponible
    public Mesa buscarMesaDisponible(int personas, int hora) {
        for (Mesa m : mesas) {
            if (m.isDisponible() && m.getCapacidad() >= personas) {
                return m;
            }
        }
        return null; // si no hay mesas disponibles
    }

    public List<Mesa> getMesas() {
        return mesas;
    }
}
