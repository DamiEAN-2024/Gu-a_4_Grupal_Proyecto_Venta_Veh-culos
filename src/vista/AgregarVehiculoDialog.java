package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Automovil;
import modelo.Bus;
import modelo.Camion;
import modelo.Moto;
import modelo.Vehiculo;

import java.awt.*;

public class AgregarVehiculoDialog extends JDialog {

    private Controlador controlador;
    private JTextField txtPlaca, txtMarca, txtModelo, txtAnio, txtNumEjes, txtCilindrada, txtValor;
    private JComboBox<String> comboTipoVehiculo;

    public AgregarVehiculoDialog(Frame owner, Controlador controlador) {
        super(owner, "Agregar Vehículo", true);
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración básica del diálogo
        setSize(400, 400);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        // Panel del formulario
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 5, 5));

        panelFormulario.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        panelFormulario.add(txtPlaca);

        panelFormulario.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panelFormulario.add(txtMarca);

        panelFormulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelFormulario.add(txtModelo);

        panelFormulario.add(new JLabel("Año:"));
        txtAnio = new JTextField();
        panelFormulario.add(txtAnio);

        panelFormulario.add(new JLabel("Número de Ejes:"));
        txtNumEjes = new JTextField();
        panelFormulario.add(txtNumEjes);

        panelFormulario.add(new JLabel("Cilindrada:"));
        txtCilindrada = new JTextField();
        panelFormulario.add(txtCilindrada);

        panelFormulario.add(new JLabel("Valor:"));
        txtValor = new JTextField();
        panelFormulario.add(txtValor);

        panelFormulario.add(new JLabel("Tipo de Vehículo:"));
        comboTipoVehiculo = new JComboBox<>(new String[]{"Automovil", "Bus", "Camion", "Moto"});
        panelFormulario.add(comboTipoVehiculo);

        // Botón para agregar el vehículo
        JButton btnAgregar = new JButton("Agregar Vehículo");
        btnAgregar.addActionListener(e -> agregarVehiculo());

        add(panelFormulario, BorderLayout.CENTER);
        add(btnAgregar, BorderLayout.SOUTH);
    }

    private void agregarVehiculo() {
        try {
            // Capturar y validar los datos ingresados
            String placa = txtPlaca.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());
            int numEjes = Integer.parseInt(txtNumEjes.getText().trim());
            int cilindrada = Integer.parseInt(txtCilindrada.getText().trim());
            double valor = Double.parseDouble(txtValor.getText().trim());
            String tipoVehiculo = (String) comboTipoVehiculo.getSelectedItem();

            // Crear el objeto Vehículo correspondiente
            Vehiculo vehiculo = null;
            switch (tipoVehiculo) {
                case "Automovil":
                    vehiculo = new Automovil(placa, marca, modelo, anio, numEjes, cilindrada, valor, 
                                              Automovil.TipoCombustible.GASOLINA, 4, Automovil.TipoTransmision.MANUAL);
                    break;
                case "Bus":
                	 int capacidadPasajeros = 40; // Puedes reemplazarlo por un valor obtenido de la interfaz si es necesario
                	    int numPisos = 2; // Asignar un valor por defecto o permitir que el usuario lo ingrese desde la interfaz
                	    vehiculo = new Bus(placa, marca, modelo, anio, numEjes, cilindrada, valor, capacidadPasajeros, Bus.TipoServicio.URBANO, numPisos);
                	    break;
                case "Camion":
                    vehiculo = new Camion(placa, marca, modelo, anio, numEjes, cilindrada, valor, 10.0);
                    break;
                case "Moto":
                    vehiculo = new Moto(placa, marca, modelo, anio, cilindrada, valor, Moto.TipoMoto.DEPORTIVA, false);
                    break;
            }

            // Llamar al controlador para agregar el vehículo
            if (vehiculo != null) {
                if (controlador.agregarVehiculo(vehiculo)) {
                    JOptionPane.showMessageDialog(this, "Vehículo agregado exitosamente.");
                    dispose(); // Cierra el diálogo
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Mostrar el mensaje de error al usuario en una ventana de diálogo
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
