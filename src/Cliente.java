public class Cliente extends Persona {
    public Cliente(String nombre, String contacto) {
        super(nombre, contacto);
    }

    @Override
    public String toString() {
        return nombre + " (" + contacto + ")";
    }
}
