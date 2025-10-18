public class Reserva extends ReservaBase {
    @SuppressWarnings("FieldMayBeFinal")
    private int personas;

    public Reserva(Cliente cliente, Mesa mesa, String fecha, String hora, int personas) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.fecha = fecha;
        this.hora = hora;
        this.personas = personas;
    }

    @Override
    public void confirmarReserva() {
        System.out.println("Reserva confirmada para " + cliente.getNombre());
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s | Mesa: %d | Fecha: %s %s:00 | Personas: %d",
                cliente.getNombre(), mesa.getId(), fecha, hora, personas);
    }
}
