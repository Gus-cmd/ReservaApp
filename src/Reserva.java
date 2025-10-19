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
    private boolean confirmada;

    public Reserva(Cliente cliente, Mesa mesa, String fecha, String hora, int personas) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.fecha = fecha;
        this.hora = hora;
        this.personas = personas;
        this.confirmada = false;
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

    public boolean isConfirmada() {
        return confirmada;
    }

    // ✅ Método que te faltaba
    public void confirmarReserva() {
        this.confirmada = true;
        System.out.println("Reserva confirmada para " + cliente.getNombre() + " en mesa " + mesa.getId());
    }
}
