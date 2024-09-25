package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Vehiculo;
import java.awt.*;
import java.util.List;

public class OrdenarVehiculosDialog extends JDialog {

    private Controlador controlador;
    private JComboBox<String> comboCriterio;
    private JTextArea areaResultado;

    public OrdenarVehiculosDialog(Frame owner, Controlador controlador) {
        super(owner, "Ordenar Vehículos", true);
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setSize(500, 400);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Panel para seleccionar el criterio de ordenación
        JPanel panelCriterio = new JPanel();
        panelCriterio.add(new JLabel("Seleccione el criterio de ordenación:"));
        comboCriterio = new JComboBox<>(new String[]{"Modelo", "Marca", "Año"});
        panelCriterio.add(comboCriterio);

        // Botón para ordenar y Cerrar
        JPanel panelBotones = new JPanel();
        JButton btnOrdenar = new JButton("Ordenar");
        btnOrdenar.addActionListener(e -> ordenarVehiculos());
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        
        panelBotones.add(btnOrdenar);
        panelBotones.add(btnCerrar);

        // Área de resultados
        areaResultado = new JTextArea(15, 40);
        areaResultado.setEditable(false);

        // Agregar los componentes al diálogo
        add(panelCriterio, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        add( panelBotones, BorderLayout.SOUTH);
    }

    private void ordenarVehiculos() {
        String criterio = (String) comboCriterio.getSelectedItem();
        List<Vehiculo> vehiculosOrdenados = controlador.ordenarVehiculos(criterio);

        if (vehiculosOrdenados != null && !vehiculosOrdenados.isEmpty()) {
            StringBuilder resultado = new StringBuilder();
            for (Vehiculo vehiculo : vehiculosOrdenados) {
                resultado.append(vehiculo.toString()).append("\n");
            }
            areaResultado.setText(resultado.toString());
        } else {
            areaResultado.setText("No se encontraron vehículos o hubo un problema al ordenar.");
        }
    }
}
