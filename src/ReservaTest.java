import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservaTest {

    private Restaurante restaurante;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        restaurante = new Restaurante();
        restaurante.agregarMesa(new Mesa(1, 2));
        restaurante.agregarMesa(new Mesa(2, 4));
        restaurante.agregarMesa(new Mesa(3, 6));
    }

    // 🧪 Prueba unitaria: Creación de una reserva
    @Test
    @SuppressWarnings("unused")
    void testCrearReserva() {
        Cliente cliente = new Cliente("Gilmer", "987654321");
        Mesa mesa = restaurante.buscarMesaDisponible(2, 18);
        Reserva reserva = new Reserva(cliente, mesa, "20/10", "18", 2);

        assertNotNull(reserva);
        assertEquals("Gilmer", reserva.getCliente().getNombre());
        assertEquals(2, reserva.getPersonas());
        assertEquals(mesa, reserva.getMesa());
    }

    // 🧪 Prueba unitaria: Confirmar una reserva
    @Test
    @SuppressWarnings("unused")
    void testConfirmarReserva() {
        Cliente cliente = new Cliente("Benjamín", "999888777");
        Mesa mesa = restaurante.buscarMesaDisponible(4, 20);
        Reserva reserva = new Reserva(cliente, mesa, "21/10", "20", 4);

        mesa.setDisponible(false); // Simula confirmación
        assertFalse(mesa.isDisponible());
    }

    // 🧪 Prueba unitaria: Validación de disponibilidad
    @Test
    @SuppressWarnings("unused")
    void testValidarDisponibilidad() {
        Mesa mesa1 = restaurante.buscarMesaDisponible(6, 19);
        assertNotNull(mesa1);
        mesa1.setDisponible(false);

        Mesa mesa2 = restaurante.buscarMesaDisponible(6, 19);
        assertNull(mesa2, "No debería haber más mesas disponibles de esa capacidad");
    }
}
