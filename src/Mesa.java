public class Mesa {
    @SuppressWarnings("FieldMayBeFinal")
    private int id;
    @SuppressWarnings("FieldMayBeFinal")
    private int capacidad;
    private boolean disponible;

    public Mesa(int id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // ✅ Método que te faltaba
    public void reservar() {
        if (disponible) {
            disponible = false;
            System.out.println("Mesa " + id + " reservada con éxito.");
        } else {
            System.out.println("Mesa " + id + " ya está ocupada.");
        }
    }
}
