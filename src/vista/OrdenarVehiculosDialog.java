package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Vehiculo;
import java.awt.*;
import java.util.List;
import java.text.DecimalFormat;
import modelo.Automovil;
import modelo.Bus;
import modelo.Camion;
import modelo.Moto;
import modelo.Vehiculo;


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
            // Definir las columnas para la tabla
            String[] columnas = {"Placa", "Marca", "Modelo", "Año", "Cilindrada", "Número de Ejes", "Valor", "Tipo"};

            // Crear la matriz de datos con la información de los vehículos
            Object[][] datos = new Object[vehiculosOrdenados.size()][8];
            DecimalFormat formatoPrecio = new DecimalFormat("#,###");
            for (int i = 0; i < vehiculosOrdenados.size(); i++) {
                Vehiculo vehiculo = vehiculosOrdenados.get(i);
                datos[i][0] = vehiculo.getPlaca();
                datos[i][1] = vehiculo.getMarca();
                datos[i][2] = vehiculo.getModelo();
                datos[i][3] = vehiculo.getAnio();
                datos[i][4] = vehiculo.getCilindrada();
                datos[i][5] = vehiculo.getNumEjes();
                datos[i][6] = formatoPrecio.format(vehiculo.getValor());
                datos[i][7] = vehiculo instanceof Automovil ? "Automovil" :
                              vehiculo instanceof Bus ? "Bus" :
                              vehiculo instanceof Camion ? "Camion" : "Moto";
            }

            // Crear la tabla
            JTable tabla = new JTable(datos, columnas);
            JScrollPane scrollPane = new JScrollPane(tabla);

            // Crear un cuadro de diálogo para mostrar la tabla
            JDialog dialogo = new JDialog(this, "Vehículos Ordenados", true);
            dialogo.setSize(800, 400);
            dialogo.setLocationRelativeTo(this);
            dialogo.setLayout(new BorderLayout());

            dialogo.add(scrollPane, BorderLayout.CENTER);

            // Botón para cerrar el cuadro de diálogo
            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(ev -> dialogo.dispose());
            dialogo.add(btnCerrar, BorderLayout.SOUTH);

            dialogo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron vehículos o hubo un problema al ordenar.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
