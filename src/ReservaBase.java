public abstract class ReservaBase {
    protected Cliente cliente;
    protected Mesa mesa;
    protected String fecha;
    protected String hora;

    public abstract void confirmarReserva();
}
