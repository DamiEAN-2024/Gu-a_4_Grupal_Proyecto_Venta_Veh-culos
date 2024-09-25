package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Vehiculo;
import java.awt.*;

public class VehiculoMasPotenteDialog extends JDialog {

    private Controlador controlador;
    private JTextArea areaResultado;

    public VehiculoMasPotenteDialog(Frame owner, Controlador controlador) {
        super(owner, "Vehículo Más Potente", true);
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setSize(500, 300);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Área de resultados
        areaResultado = new JTextArea(10, 30);
        areaResultado.setEditable(false);

        // Botón para cerrar el diálogo
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        // Consultar el vehículo más potente
        consultarVehiculoMasPotente();

        // Agregar componentes al diálogo
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);
    }

    private void consultarVehiculoMasPotente() {
        // Llamar al controlador para obtener el vehículo más potente
        Vehiculo vehiculo = controlador.obtenerVehiculoMasPotente();

        if (vehiculo != null) {
            // Mostrar la información del vehículo en líneas separadas
            areaResultado.setText(vehiculo.toString().replace(", ", "\n"));
        } else {
            areaResultado.setText("No se encontró ningún vehículo potente en la base de datos.");
        }
    }
}
