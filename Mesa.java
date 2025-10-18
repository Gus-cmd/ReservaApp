public class Mesa implements Reservable {
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

    public int getId() { return id; }
    public int getCapacidad() { return capacidad; }
    public boolean isDisponible() { return disponible; }

    @Override
    public void reservar() {
        this.disponible = false;
    }

    @Override
    public void liberar() {
        this.disponible = true;
    }
}
