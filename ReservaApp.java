import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class ReservaApp extends JFrame {
    @SuppressWarnings("FieldMayBeFinal")
    private Restaurante restaurante;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextField nombreField;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextField contactoField;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextField personasField;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextField fechaField;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextField horaField;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextPane resultadoPane;

    public ReservaApp() {
        restaurante = new Restaurante();
        restaurante.agregarMesa(new Mesa(1, 2));
        restaurante.agregarMesa(new Mesa(2, 4));
        restaurante.agregarMesa(new Mesa(3, 6));

        setTitle("🍽️ Sistema de Reservas - Restaurante");
        setSize(680, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel superior
        JLabel titleLabel = new JLabel("Sistema de Reservas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        JPanel header = new JPanel();
        header.setBackground(new Color(52, 152, 219));
        header.add(titleLabel);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Formulario de Reserva"));
        formPanel.setBackground(new Color(245, 245, 250));

        Font labelFont = new Font("Segoe UI Emoji", Font.BOLD, 13);
        formPanel.add(new JLabel("👤 Nombre:")).setFont(labelFont);
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("📞 Contacto:")).setFont(labelFont);
        contactoField = new JTextField();
        formPanel.add(contactoField);

        formPanel.add(new JLabel("👥 Personas:")).setFont(labelFont);
        personasField = new JTextField();
        formPanel.add(personasField);

        formPanel.add(new JLabel("📅 Fecha (dd/mm):")).setFont(labelFont);
        fechaField = new JTextField();
        formPanel.add(fechaField);

        formPanel.add(new JLabel("⏰ Hora (0-23):")).setFont(labelFont);
        horaField = new JTextField();
        formPanel.add(horaField);

        JButton reservarBtn = new JButton("💾 Reservar Mesa");
        reservarBtn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        reservarBtn.setBackground(new Color(41, 128, 185));
        reservarBtn.setForeground(Color.WHITE);
        reservarBtn.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 250));
        buttonPanel.add(reservarBtn);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Panel de resultados
        resultadoPane = new JTextPane();
        resultadoPane.setEditable(false);
        resultadoPane.setContentType("text/html");
        resultadoPane.setBorder(BorderFactory.createTitledBorder("🧾 Resultado"));
        resultadoPane.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(resultadoPane);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        centerPanel.add(leftPanel);
        centerPanel.add(scroll);

        add(header, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        reservarBtn.addActionListener(e -> procesarReserva());
        setLocationRelativeTo(null);
    }

    private void procesarReserva() {
        try {
            String nombre = nombreField.getText().trim();
            String contacto = contactoField.getText().trim();
            int personas = Integer.parseInt(personasField.getText().trim());
            String fecha = fechaField.getText().trim();
            String hora = horaField.getText().trim();

            if (nombre.isEmpty() || contacto.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Complete todos los campos.");
                return;
            }

            Mesa mesaAsignada = restaurante.buscarMesaDisponible(personas, Integer.parseInt(hora));
            if (mesaAsignada != null) {
                Reserva reserva = new Reserva(new Cliente(nombre, contacto), mesaAsignada, fecha, hora, personas);
                reserva.confirmarReserva();
                mesaAsignada.reservar();

                String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());

                resultadoPane.setText(String.format("""
                        <html><body style='font-family: Segoe UI Emoji; font-size: 13px;'>
                        <b style='color:green;'>✅ Reserva Confirmada</b><br><br>
                        👤 Cliente: %s<br>
                        📞 Contacto: %s<br>
                        👥 Personas: %d<br>
                        📅 Fecha: %s<br>
                        ⏰ Hora: %s:00<br>
                        🍽️ Mesa: Nº %d (Capacidad: %d)<br><br>
                        🕒 Registro: %s %s
                        </body></html>
                        """, nombre, contacto, personas, fecha, hora,
                        mesaAsignada.getId(), mesaAsignada.getCapacidad(), fechaActual, horaActual));
            } else {
                resultadoPane.setText("<html><body style='color:red;'>❌ No hay mesas disponibles.</body></html>");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Ingrese valores numéricos válidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservaApp().setVisible(true));
    }
}
