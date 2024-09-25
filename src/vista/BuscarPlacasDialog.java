package vista;

import javax.swing.*;
import controlador.Controlador;
import java.awt.*;
import java.util.List;

public class BuscarPlacasDialog extends JDialog {
    
    private Controlador controlador;
    private JTextField txtModelo, txtAnio;
    private JTextArea areaResultado;

    public BuscarPlacasDialog(Frame owner, Controlador controlador) {
        super(owner, "Buscar Placas", true);
        this.controlador = controlador;
        inicializarComponentes();
        setSize(500, 400); // Ajusta el tamaño del diálogo
        setLocationRelativeTo(owner);
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setLayout(new BorderLayout());

        // Panel para ingresar el modelo y el año
        JPanel panelEntrada = new JPanel(new GridLayout(2, 2, 5, 5));
        panelEntrada.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelEntrada.add(txtModelo);

        panelEntrada.add(new JLabel("Año:"));
        txtAnio = new JTextField();
        panelEntrada.add(txtAnio);

        // Botón para realizar la búsqueda
        JPanel panelBotones = new JPanel();
        JButton btnBuscar = new JButton("Buscar Placas");
        btnBuscar.addActionListener(e -> buscarPlacas());
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        
        panelBotones.add(btnBuscar);
        panelBotones.add(btnCerrar);

        // Área de resultados
        areaResultado = new JTextArea(10, 30);
        areaResultado.setEditable(false);

        // Agregar los componentes al diálogo
        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void buscarPlacas() {
        try {
            String modelo = txtModelo.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());

            List<String> placasEncontradas = controlador.buscarPlacasPorModeloYAño(modelo, anio);

            if (placasEncontradas != null && !placasEncontradas.isEmpty()) {
                StringBuilder sb = new StringBuilder("Placas encontradas:\n");
                for (String placa : placasEncontradas) {
                    sb.append(placa).append("\n");
                }
                areaResultado.setText(sb.toString());
            } else {
                areaResultado.setText("No se encontraron vehículos con ese modelo y año.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
