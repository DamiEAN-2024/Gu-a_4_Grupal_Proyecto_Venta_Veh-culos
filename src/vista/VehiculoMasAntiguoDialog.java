package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Vehiculo;
import java.awt.*;

public class VehiculoMasAntiguoDialog extends JDialog {

    private Controlador controlador;
    private JTextArea areaResultado;

    public VehiculoMasAntiguoDialog(Frame owner, Controlador controlador) {
        super(owner, "Vehículo Más Antiguo", true);
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Área de resultados
        areaResultado = new JTextArea(10, 30);
        areaResultado.setEditable(false);

        // Botón para cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        // Obtener el vehículo más antiguo
        obtenerVehiculoMasAntiguo();

        // Agregar los componentes al diálogo
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);
    }

    private void obtenerVehiculoMasAntiguo() {
        Vehiculo vehiculo = controlador.obtenerVehiculoMasAntiguo();
        if (vehiculo != null) {
            areaResultado.setText("Vehículo más antiguo:\n\n" + vehiculo.toString().replace(", ", "\n"));
        } else {
            areaResultado.setText("No hay vehículos en la base de datos.");
        }
    }
}
