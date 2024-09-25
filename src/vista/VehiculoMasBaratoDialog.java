package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Vehiculo;
import java.awt.*;

public class VehiculoMasBaratoDialog extends JDialog {

    private Controlador controlador;
    private JTextArea areaResultado;

    public VehiculoMasBaratoDialog(Frame owner, Controlador controlador) {
        super(owner, "Vehículo Más Barato", true);
        this.controlador = controlador;
        inicializarComponentes();
        cargarVehiculoMasBarato();
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setSize(500, 300);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Área de resultados
        areaResultado = new JTextArea(10, 30);
        areaResultado.setEditable(false);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        // Botón para cerrar el diálogo
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
    }

    private void cargarVehiculoMasBarato() {
        try {
            // Obtener el vehículo más barato desde el controlador
            Vehiculo vehiculo = controlador.obtenerVehiculoMasBarato();

            if (vehiculo != null) {
                areaResultado.setText(vehiculo.toString().replace(",", "\n")); // Mostrar la información en líneas separadas
            } else {
                areaResultado.setText("No hay vehículos en la base de datos.");
            }
        } catch (Exception ex) {
            areaResultado.setText("Error al obtener el vehículo más barato: " + ex.getMessage());
        }
    }
}
