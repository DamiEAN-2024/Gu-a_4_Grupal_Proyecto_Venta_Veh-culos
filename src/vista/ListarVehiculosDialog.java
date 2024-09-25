package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Automovil;
import modelo.Bus;
import modelo.Camion;
import modelo.Moto;
import modelo.Vehiculo;
import java.awt.*;
import java.util.List;

public class ListarVehiculosDialog extends JDialog {

    private Controlador controlador;
    private JTable tablaVehiculos;
    private JScrollPane scrollPane;

    public ListarVehiculosDialog(Frame owner, Controlador controlador) {
        super(owner, "Listar Vehículos", true);
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setSize(600, 400);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Crear la tabla y cargar los datos
        cargarDatosVehiculos();

        // Agregar la tabla dentro de un JScrollPane para poder desplazarse
        scrollPane = new JScrollPane(tablaVehiculos);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para cerrar el diálogo
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
    }

    private void cargarDatosVehiculos() {
        // Obtener la lista de vehículos del controlador
        List<Vehiculo> listaVehiculos = controlador.obtenerTodosLosVehiculos();

        // Definir las columnas que tendrá la tabla
        String[] columnas = {"Placa", "Marca", "Modelo", "Año", "Cilindrada", "Número de Ejes", "Valor", "Tipo"};

        // Crear la matriz de datos
        Object[][] datos = new Object[listaVehiculos.size()][8];
        for (int i = 0; i < listaVehiculos.size(); i++) {
            Vehiculo vehiculo = listaVehiculos.get(i);
            datos[i][0] = vehiculo.getPlaca();
            datos[i][1] = vehiculo.getMarca();
            datos[i][2] = vehiculo.getModelo();
            datos[i][3] = vehiculo.getAnio();
            datos[i][4] = vehiculo.getCilindrada();
            datos[i][5] = vehiculo.getNumEjes();
            datos[i][6] = vehiculo.getValor();
            datos[i][7] = vehiculo instanceof Automovil ? "Automovil" :
                          vehiculo instanceof Bus ? "Bus" :
                          vehiculo instanceof Camion ? "Camion" : "Moto";
        }

        // Crear la tabla con los datos y columnas
        tablaVehiculos = new JTable(datos, columnas);
    }
}
