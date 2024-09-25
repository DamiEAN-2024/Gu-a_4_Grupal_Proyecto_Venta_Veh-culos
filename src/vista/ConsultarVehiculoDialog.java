package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Vehiculo;
import java.awt.*;

public class ConsultarVehiculoDialog extends JDialog {
    
    private Controlador controlador;
    private JTextField txtPlaca;
    private JTextArea areaResultado;

    public ConsultarVehiculoDialog(Frame owner, Controlador controlador) {
        super(owner, "Consultar Vehículo", true);
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Panel para la placa
        JPanel panelPlaca = new JPanel();
        panelPlaca.add(new JLabel("Ingrese la placa:"));
        txtPlaca = new JTextField(10);
        panelPlaca.add(txtPlaca);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> consultarVehiculo());
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnConsultar);
        panelBotones.add(btnCerrar);

        // Área de resultados
        areaResultado = new JTextArea(10, 30);
        areaResultado.setEditable(false);

        // Agregar los componentes al diálogo
        add(panelPlaca, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void consultarVehiculo() {
        String placa = txtPlaca.getText().trim();
        if (!placa.isEmpty()) {
            Vehiculo vehiculo = controlador.obtenerInformacionVehiculo(placa);
            if (vehiculo != null) {
                // Modificar la visualización para que aparezca cada dato en una línea separada
                StringBuilder detalleVehiculo = new StringBuilder();
                detalleVehiculo.append("Placa: ").append(vehiculo.getPlaca()).append("\n");
                detalleVehiculo.append("Marca: ").append(vehiculo.getMarca()).append("\n");
                detalleVehiculo.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
                detalleVehiculo.append("Año: ").append(vehiculo.getAnio()).append("\n");
                detalleVehiculo.append("Número de Ejes: ").append(vehiculo.getNumEjes()).append("\n");
                detalleVehiculo.append("Cilindrada: ").append(vehiculo.getCilindrada()).append("\n");
                detalleVehiculo.append("Valor: ").append(vehiculo.getValor()).append("\n");

                // Verificar el tipo específico de vehículo y agregar detalles adicionales
                if (vehiculo instanceof modelo.Automovil) {
                    modelo.Automovil automovil = (modelo.Automovil) vehiculo;
                    detalleVehiculo.append("Tipo de Combustible: ").append(automovil.getTipoCombustible()).append("\n");
                    detalleVehiculo.append("Número de Puertas: ").append(automovil.getNumeroPuertas()).append("\n");
                    detalleVehiculo.append("Tipo de Transmisión: ").append(automovil.getTipoTransmision()).append("\n");
                } else if (vehiculo instanceof modelo.Bus) {
                    modelo.Bus bus = (modelo.Bus) vehiculo;
                    detalleVehiculo.append("Capacidad de Pasajeros: ").append(bus.getCapacidadPasajeros()).append("\n");
                    detalleVehiculo.append("Tipo de Servicio: ").append(bus.getTipoServicio()).append("\n");
                } else if (vehiculo instanceof modelo.Camion) {
                    modelo.Camion camion = (modelo.Camion) vehiculo;
                    detalleVehiculo.append("Capacidad de Carga: ").append(camion.getCapacidadCarga()).append("\n");
                } else if (vehiculo instanceof modelo.Moto) {
                    modelo.Moto moto = (modelo.Moto) vehiculo;
                    detalleVehiculo.append("Tipo de Moto: ").append(moto.getTipoMoto()).append("\n");
                    detalleVehiculo.append("Tiene Maletero: ").append(moto.isTieneMaletero() ? "Sí" : "No").append("\n");
                }

                areaResultado.setText(detalleVehiculo.toString());
            } else {
                areaResultado.setText("No se encontró ningún vehículo con esa placa.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar una placa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
