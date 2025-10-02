import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ReservaApp extends JFrame {
    private Restaurante restaurante;

    private JTextField nombreField;
    private JTextField contactoField;
    private JTextField personasField;
    private JTextField fechaField;
    private JTextField horaField;
    private JTextArea resultadoArea;

    @SuppressWarnings("Convert2Lambda")
    public ReservaApp() {
        restaurante = new Restaurante();
        // Mesas de prueba
        restaurante.agregarMesa(new Mesa(1, 2));
        restaurante.agregarMesa(new Mesa(2, 4));
        restaurante.agregarMesa(new Mesa(3, 6));

        setTitle("🍽️ Sistema de Reservas");
        setSize(520, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos de la Reserva"));
        formPanel.setBackground(new Color(245, 245, 250));

        formPanel.add(new JLabel("👤 Nombre del Cliente:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("📞 Contacto:"));
        contactoField = new JTextField();
        formPanel.add(contactoField);

        formPanel.add(new JLabel("👥 Personas:"));
        personasField = new JTextField();
        formPanel.add(personasField);

        formPanel.add(new JLabel("📅 Fecha (dd/mm):"));
        fechaField = new JTextField();
        formPanel.add(fechaField);

        formPanel.add(new JLabel("⏰ Hora (0-23):"));
        horaField = new JTextField();
        formPanel.add(horaField);

        // Botón de reservar (en un panel pequeño)
        JButton reservarBtn = new JButton(" Reservar Mesa");
        reservarBtn.setBackground(new Color(52, 152, 219));
        reservarBtn.setForeground(Color.WHITE);
        reservarBtn.setFocusPainted(false);
        reservarBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        reservarBtn.setPreferredSize(new Dimension(150, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 250));
        buttonPanel.add(reservarBtn);

        // Área de resultados
        resultadoArea = new JTextArea(8, 40);
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado de la Reserva"));
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Añadir todo al layout
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Acción del botón
        reservarBtn.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreField.getText().trim();
                    String contacto = contactoField.getText().trim();
                    int personas = Integer.parseInt(personasField.getText().trim());
                    String fecha = fechaField.getText().trim();
                    String hora = horaField.getText().trim();

                    if (nombre.isEmpty() || contacto.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "⚠️ Complete todos los campos antes de reservar.");
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(null,
                            "¿Desea reservar una mesa para " + personas + " personas\n" +
                            "el día " + fecha + " a las " + hora + " horas?",
                            "Confirmar Reserva", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        Mesa mesaAsignada = restaurante.buscarMesaDisponible(personas, Integer.parseInt(hora));
                        if (mesaAsignada != null) {
                            Reserva reserva = new Reserva(
                                    new Cliente(nombre, contacto),
                                    mesaAsignada,
                                    fecha,
                                    hora,
                                    personas
                            );
                            mesaAsignada.setDisponible(false);

                            resultadoArea.setText("✅ Reserva confirmada\n\n" +
                                    "👤 Cliente: " + nombre + "\n" +
                                    "📞 Contacto: " + contacto + "\n" +
                                    "👥 Personas: " + personas + "\n" +
                                    "📅 Fecha: " + fecha + "\n" +
                                    "⏰ Hora: " + hora + ":00\n" +
                                    "🍽️ Mesa asignada: Nº " + mesaAsignada.getId() +
                                    " (Capacidad: " + mesaAsignada.getCapacidad() + ")");
                        } else {
                            resultadoArea.setText("❌ No hay mesas disponibles para " + personas +
                                    " personas en la fecha " + fecha + " a las " + hora + " horas.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "⚠️ Ingrese valores numéricos válidos en Personas y Hora.");
                }
            }
        });

        setLocationRelativeTo(null); // Centrar ventana
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReservaApp().setVisible(true);
        });
    }
}
