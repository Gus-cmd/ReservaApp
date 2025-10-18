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

    public Mesa buscarMesaDisponible(int personas, int hora) {
        for (Mesa m : mesas) {
            if (m.isDisponible() && m.getCapacidad() >= personas) {
                return m;
            }
        }
        return null;
    }
}
