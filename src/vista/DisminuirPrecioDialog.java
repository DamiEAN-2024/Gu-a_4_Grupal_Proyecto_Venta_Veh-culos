package vista;

import javax.swing.*;
import controlador.Controlador;
import java.awt.*;

public class DisminuirPrecioDialog extends JDialog {

    private Controlador controlador;
    private JTextField txtValorMinimo;

    public DisminuirPrecioDialog(Frame owner, Controlador controlador) {
        super(owner, "Disminuir Precio de Vehículos", true);
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setSize(400, 200);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Panel para ingresar el valor mínimo
        JPanel panelValor = new JPanel();
        panelValor.add(new JLabel("Ingrese el valor mínimo:"));
        txtValorMinimo = new JTextField(10);
        panelValor.add(txtValorMinimo);

        // Botón para aplicar la disminución
        JButton btnAplicar = new JButton("Aplicar Descuento");
        btnAplicar.addActionListener(e -> disminuirPrecio());

        // Agregar el panel y el botón al diálogo
        add(panelValor, BorderLayout.CENTER);
        add(btnAplicar, BorderLayout.SOUTH);
    }

    private void disminuirPrecio() {
        try {
            double valorMinimo = Double.parseDouble(txtValorMinimo.getText().trim());
            controlador.disminuirPrecioVehiculos(valorMinimo);
            JOptionPane.showMessageDialog(this, "Se ha aplicado un 10% de descuento a los vehículos con un valor mayor a " + valorMinimo);
            dispose(); // Cierra el diálogo
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
