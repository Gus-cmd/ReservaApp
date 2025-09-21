import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class ReservaMesas extends JFrame {

    private final JTextField txtFecha = new JTextField(8);
    private final JTextField txtHora = new JTextField(3);
    private final JTextField txtNumPersonas = new JTextField(3);
    private final JTextArea txtResultado = new JTextArea(12, 40);
    private final JButton btnVerificar = new JButton("Verificar disponibilidad");

    private static final int DURACION_RESERVA_HORAS = 2;

    // Mesas: id -> capacidad
    private final Map<Integer, Integer> mesas = Map.of(1, 2, 2, 4, 3, 6);

    // Reservas por fecha: "dd/mm" -> (idMesa -> listaHorasReservadas)
    private final Map<String, Map<Integer, List<Integer>>> reservasPorFecha = new HashMap<>();

    public ReservaMesas() {
        super("Sistema de Reservas - Grupo 2");
        initUI();
        inicializarDatos();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unused")
    private void initUI() {
        setLayout(new BorderLayout(8, 8));

        JPanel panelInputs = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        panelInputs.add(new JLabel("Fecha (dd/mm):"));
        panelInputs.add(txtFecha);
        panelInputs.add(new JLabel("Hora (0-23):"));
        panelInputs.add(txtHora);
        panelInputs.add(new JLabel("Personas:"));
        panelInputs.add(txtNumPersonas);
        panelInputs.add(btnVerificar);

        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);

        add(panelInputs, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnVerificar.addActionListener(e -> verificarDisponibilidad());
    }

    private void inicializarDatos() {
        // Ejemplo: reservas para la fecha "20/09"
        reservasPorFecha.put("20/09", Map.of(
            1, new ArrayList<>(Arrays.asList(12, 14)), // mesa 1 ocupada a las 12 y 14
            2, new ArrayList<>(Arrays.asList(13))      // mesa 2 ocupada a las 13
        ));
    }

    private void verificarDisponibilidad() {
        txtResultado.setText("");

        String fecha = txtFecha.getText().trim();
        int horaSolicitada, numPersonas;

        try {
            horaSolicitada = Integer.parseInt(txtHora.getText().trim());
            numPersonas = Integer.parseInt(txtNumPersonas.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La hora y el número de personas deben ser números enteros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fecha.isEmpty() || horaSolicitada < 0 || horaSolicitada > 23 || numPersonas <= 0) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Integer> mesasDisponibles = mesas.entrySet().stream()
            .filter(entry -> entry.getValue() >= numPersonas)
            .filter(entry -> !isConflicto(fecha, entry.getKey(), horaSolicitada))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        if (mesasDisponibles.isEmpty()) {
            txtResultado.setText("Lo sentimos, no hay mesas disponibles para " + numPersonas + " personas el " + fecha + " a las " + horaSolicitada + "h.");
        } else {
            String listaMesas = mesasDisponibles.stream()
                .map(id -> " - Mesa " + id + " (capacidad " + mesas.get(id) + ")")
                .collect(Collectors.joining("\n"));
            txtResultado.setText("Mesas disponibles:\n" + listaMesas);

            int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea reservar la mesa " + mesasDisponibles.get(0) + " el " + fecha + " a las " + horaSolicitada + "h?",
                "Confirmar reserva", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                reservarMesa(fecha, mesasDisponibles.get(0), horaSolicitada);
            }
        }
    }

    private boolean isConflicto(String fecha, int idMesa, int horaSolicitada) {
        List<Integer> horasReservadas = reservasPorFecha.getOrDefault(fecha, Collections.emptyMap())
            .getOrDefault(idMesa, Collections.emptyList());

        for (int hReservada : horasReservadas) {
            if (horaSolicitada < hReservada + DURACION_RESERVA_HORAS && horaSolicitada + DURACION_RESERVA_HORAS > hReservada) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    private void reservarMesa(String fecha, int idMesa, int horaSolicitada) {
        reservasPorFecha.computeIfAbsent(fecha, k -> new HashMap<>())
            .computeIfAbsent(idMesa, k -> new ArrayList<>())
            .add(horaSolicitada);
        txtResultado.append("\n✅ Reserva confirmada en mesa " + idMesa + " a las " + horaSolicitada + "h.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservaMesas().setVisible(true));
    }
}