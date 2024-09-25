package vista;

import javax.swing.*;
import controlador.Controlador;

import java.awt.*;

public class ComprarVehiculoDialog extends JDialog {
    
    private Controlador controlador;
    private JTextField txtPlaca;

    public ComprarVehiculoDialog(Frame owner, Controlador controlador) {
        super(owner, "Comprar Vehículo", true);
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setSize(400, 200);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Panel para la placa
        JPanel panelPlaca = new JPanel();
        panelPlaca.add(new JLabel("Ingrese la placa del vehículo a comprar (eliminar):"));
        txtPlaca = new JTextField(10);
        panelPlaca.add(txtPlaca);

        // Botón para realizar la compra/eliminación
        JButton btnComprar = new JButton("Comprar Vehículo");
        btnComprar.addActionListener(e -> comprarVehiculo());

        // Agregar el panel y el botón al diálogo
        add(panelPlaca, BorderLayout.CENTER);
        add(btnComprar, BorderLayout.SOUTH);
    }

    private void comprarVehiculo() {
        String placa = txtPlaca.getText().trim();
        if (!placa.isEmpty()) {
            boolean exito = controlador.comprarVehiculo(placa);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Vehículo comprado y eliminado exitosamente.");
                dispose(); // Cierra el diálogo
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún vehículo con esa placa.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar una placa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
