public class Reserva {
    @SuppressWarnings("FieldMayBeFinal")
    private Cliente cliente;
    @SuppressWarnings("FieldMayBeFinal")
    private Mesa mesa;
    @SuppressWarnings("FieldMayBeFinal")
    private String fecha;
    @SuppressWarnings("FieldMayBeFinal")
    private String hora;
    @SuppressWarnings("FieldMayBeFinal")
    private int personas;

    public Reserva(Cliente cliente, Mesa mesa, String fecha, String hora, int personas) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.fecha = fecha;
        this.hora = hora;
        this.personas = personas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getPersonas() {
        return personas;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente +
               "\nMesa: " + "Nº " + mesa.getId() + " (capacidad: " + mesa.getCapacidad() + ")" +
               "\nFecha: " + fecha +
               "\nHora: " + hora +
               "\nPersonas: " + personas;
    }
}
