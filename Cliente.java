public class Cliente {
    @SuppressWarnings("FieldMayBeFinal")
    private String nombre;
    @SuppressWarnings("FieldMayBeFinal")
    private String telefono;

    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return nombre + " (" + telefono + ")";
    }
}
