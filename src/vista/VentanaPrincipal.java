package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Vehiculo;
import java.awt.*;
import java.net.URL;
import java.text.DecimalFormat;
import modelo.Automovil;
import modelo.Bus;
import modelo.Camion;
import modelo.Moto;
import modelo.Vehiculo;


public class VentanaPrincipal extends JFrame {

    private Controlador controlador;

    public VentanaPrincipal() {
        controlador = new Controlador();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración básica de la ventana
        setTitle("Venta de Vehículos - CruzMan Concesionaria");
        setSize(900, 600); // Tamaño de la ventana ajustado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        // Panel superior que contendrá el nombre de la agencia, la leyenda y los logos
        JPanel panelSuperior = new JPanel(new BorderLayout());

        // Panel para el logo izquierdo
        JPanel panelLogoIzquierdo = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alineado a la derecha
        try {
            URL url = getClass().getResource("/resources/logo.png");
            if (url != null) {
                ImageIcon logo = new ImageIcon(url);
                Image imagenEscalada = logo.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // Tamaño ajustado
                JLabel labelLogoIzquierdo = new JLabel(new ImageIcon(imagenEscalada));
                panelLogoIzquierdo.add(labelLogoIzquierdo);
            } else {
                System.err.println("No se pudo encontrar la imagen en la ruta especificada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Añadir el panel del logo izquierdo al lado izquierdo del panel superior
        panelSuperior.add(panelLogoIzquierdo, BorderLayout.WEST);

        // Panel central para el nombre de la agencia y la leyenda
        JPanel panelCentral = new JPanel(new GridLayout(2, 1)); 
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajustar márgenes
        JLabel lblNombreAgencia = new JLabel("CruzMan Concesionaria", JLabel.CENTER);
        lblNombreAgencia.setFont(new Font("Serif", Font.BOLD, 30));
        panelCentral.add(lblNombreAgencia);

        JLabel lblLeyenda = new JLabel("¡La mejor selección de vehículos usados!", JLabel.CENTER);
        lblLeyenda.setFont(new Font("Serif", Font.ITALIC, 22));
        panelCentral.add(lblLeyenda);

        // Añadir el panel central al panel superior
        panelSuperior.add(panelCentral, BorderLayout.CENTER);

        // Panel para el logo derecho
        JPanel panelLogoDerecho = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alineado a la izquierda
        try {
            URL url = getClass().getResource("/resources/logo1.png");
            if (url != null) {
                ImageIcon logo1 = new ImageIcon(url);
                Image imagenEscalada = logo1.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // Tamaño ajustado
                JLabel labelLogoDerecho = new JLabel(new ImageIcon(imagenEscalada));
                panelLogoDerecho.add(labelLogoDerecho);
            } else {
                System.err.println("No se pudo encontrar la imagen en la ruta especificada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Añadir el panel del logo derecho al lado derecho del panel superior
        panelSuperior.add(panelLogoDerecho, BorderLayout.EAST);

        // Añadir el panel superior a la ventana principal
        add(panelSuperior, BorderLayout.NORTH);

        // Crear el panel principal que contendrá los 3 paneles de categorías de botones
        JPanel panelBotonesPrincipal = new JPanel(new GridLayout(1, 3, 10, 10)); // 1 fila y 3 columnas

        // Panel de "Gestión de Vehículos"
        JPanel panelGestion = new JPanel(new GridLayout(5, 1, 5, 5)); 
        panelGestion.setBorder(BorderFactory.createTitledBorder("Gestión de Vehículos"));
        JButton btnAgregar = new JButton("Agregar Vehículo");
        JButton btnListar = new JButton("Listar Vehículos");
        JButton btnConsultar = new JButton("Consultar Vehículo");
        JButton btnOrdenar = new JButton("Ordenar Vehículos");
        JButton btnBuscar = new JButton("Buscar por Modelo y Año"); // Botón de búsqueda agregado
        panelGestion.add(btnAgregar);
        panelGestion.add(btnListar);
        panelGestion.add(btnConsultar);
        panelGestion.add(btnOrdenar);
        panelGestion.add(btnBuscar);

        // Panel de "Operaciones de Compra/Venta"
        JPanel panelOperaciones = new JPanel(new GridLayout(2, 1, 5, 5)); 
        panelOperaciones.setBorder(BorderFactory.createTitledBorder("Operaciones de Compra/Venta"));
        JButton btnComprar = new JButton("Comprar Vehículo");
        JButton btnDisminuirPrecio = new JButton("Disminuir Precio");
        panelOperaciones.add(btnComprar);
        panelOperaciones.add(btnDisminuirPrecio);

        // Panel de "Análisis y Estadísticas"
        JPanel panelAnalisis = new JPanel(new GridLayout(3, 1, 5, 5));
        panelAnalisis.setBorder(BorderFactory.createTitledBorder("Análisis y Estadísticas"));
        JButton btnMasAntiguo = new JButton("Vehículo Más Antiguo");
        JButton btnMasPotente = new JButton("Vehículo Más Potente");
        JButton btnMasBarato = new JButton("Vehículo Más Barato");
        panelAnalisis.add(btnMasAntiguo);
        panelAnalisis.add(btnMasPotente);
        panelAnalisis.add(btnMasBarato);

        // Añadir los paneles de categorías al panel principal de botones
        panelBotonesPrincipal.add(panelGestion);
        panelBotonesPrincipal.add(panelOperaciones);
        panelBotonesPrincipal.add(panelAnalisis);

        // Añadir el panel principal de botones a la ventana
        add(panelBotonesPrincipal, BorderLayout.CENTER);

        // Configurar los eventos de los botones
        configurarEventosBotones(btnAgregar, btnListar, btnConsultar, btnOrdenar, btnBuscar, btnComprar, 
                                 btnDisminuirPrecio, btnMasAntiguo, btnMasPotente, btnMasBarato);
    }

    private void configurarEventosBotones(JButton btnAgregar, JButton btnListar, JButton btnConsultar,
                                          JButton btnOrdenar, JButton btnBuscar, JButton btnComprar,
                                          JButton btnDisminuirPrecio, JButton btnMasAntiguo, 
                                          JButton btnMasPotente, JButton btnMasBarato) {

        btnAgregar.addActionListener(e -> {
            AgregarVehiculoDialog agregarDialog = new AgregarVehiculoDialog(this, controlador);
            agregarDialog.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            ListarVehiculosDialog listarDialog = new ListarVehiculosDialog(this, controlador);
            listarDialog.setSize(1000, 600); // Aumenta el tamaño del diálogo para listar
            listarDialog.setVisible(true);
        });

        btnConsultar.addActionListener(e -> {
            ConsultarVehiculoDialog consultarDialog = new ConsultarVehiculoDialog(this, controlador);
            consultarDialog.setVisible(true);
        });

        btnOrdenar.addActionListener(e -> {
            OrdenarVehiculosDialog ordenarDialog = new OrdenarVehiculosDialog(this, controlador);
            ordenarDialog.setSize(1000, 600); // Ajusta el tamaño del diálogo para ordenar
            ordenarDialog.setVisible(true);
        });

        btnBuscar.addActionListener(e -> {
            BuscarPlacasDialog buscarDialog = new BuscarPlacasDialog(this, controlador);
            buscarDialog.setVisible(true);
        });

        btnComprar.addActionListener(e -> {
            ComprarVehiculoDialog comprarDialog = new ComprarVehiculoDialog(this, controlador);
            comprarDialog.setVisible(true);
        });

        btnDisminuirPrecio.addActionListener(e -> {
            DisminuirPrecioDialog disminuirDialog = new DisminuirPrecioDialog(this, controlador);
            disminuirDialog.setVisible(true);
        });
        
        btnMasAntiguo.addActionListener(e -> {
            try {
                Vehiculo vehiculo = controlador.obtenerVehiculoMasAntiguo();

                if (vehiculo != null) {
                    // Crear un cuadro de diálogo para mostrar el resultado
                    JDialog resultadoDialog = new JDialog(this, "Vehículo Más Antiguo", true);
                    resultadoDialog.setSize(600, 200);
                    resultadoDialog.setLocationRelativeTo(this);

                    // Crear los encabezados de la tabla
                    String[] columnas = {"Placa", "Marca", "Modelo", "Año", "Cilindrada", "Número de Ejes", "Valor", "Tipo"};

                    // Crear la fila con los datos del vehículo más antiguo
                    Object[][] datos = {{
                        vehiculo.getPlaca(),
                        vehiculo.getMarca(),
                        vehiculo.getModelo(),
                        vehiculo.getAnio(),
                        vehiculo.getCilindrada(),
                        vehiculo.getNumEjes(),
                        new DecimalFormat("#,###").format(vehiculo.getValor()), // Formato del valor
                        vehiculo instanceof Automovil ? "Automovil" :
                        vehiculo instanceof Bus ? "Bus" :
                        vehiculo instanceof Camion ? "Camion" : "Moto"
                    }};

                    // Crear la tabla con los datos y columnas
                    JTable tablaResultado = new JTable(datos, columnas);

                    // Añadir la tabla a un JScrollPane
                    JScrollPane scrollPane = new JScrollPane(tablaResultado);
                    resultadoDialog.add(scrollPane, BorderLayout.CENTER);

                    // Botón para cerrar el cuadro de diálogo
                    JButton btnCerrar = new JButton("Cerrar");
                    btnCerrar.addActionListener(ev -> resultadoDialog.dispose());
                    resultadoDialog.add(btnCerrar, BorderLayout.SOUTH);

                    resultadoDialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay vehículos en la base de datos.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener el vehículo más antiguo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnMasPotente.addActionListener(e -> {
        	    try {
        	        // Obtener el vehículo más potente desde el controlador
        	        Vehiculo vehiculo = controlador.obtenerVehiculoMasPotente();

        	        if (vehiculo != null) {
        	            // Definir las columnas para la tabla
        	            String[] columnas = {"Placa", "Marca", "Modelo", "Año", "Cilindrada", "Número de Ejes", "Valor", "Tipo"};

        	            // Crear la matriz de datos con la información del vehículo
        	            Object[][] datos = new Object[1][8];
        	            datos[0][0] = vehiculo.getPlaca();
        	            datos[0][1] = vehiculo.getMarca();
        	            datos[0][2] = vehiculo.getModelo();
        	            datos[0][3] = vehiculo.getAnio();
        	            datos[0][4] = vehiculo.getCilindrada();
        	            datos[0][5] = vehiculo.getNumEjes();
        	            datos[0][6] = new DecimalFormat("#,###").format(vehiculo.getValor());
        	            datos[0][7] = vehiculo instanceof Automovil ? "Automovil" :
        	                          vehiculo instanceof Bus ? "Bus" :
        	                          vehiculo instanceof Camion ? "Camion" : "Moto";

        	            // Crear la tabla
        	            JTable tabla = new JTable(datos, columnas);
        	            JScrollPane scrollPane = new JScrollPane(tabla);

        	            // Crear un cuadro de diálogo para mostrar la tabla
        	            JDialog dialogo = new JDialog(this, "Vehículo Más Potente", true);
        	            dialogo.setSize(600, 200);
        	            dialogo.setLocationRelativeTo(this);
        	            dialogo.setLayout(new BorderLayout());

        	            dialogo.add(scrollPane, BorderLayout.CENTER);

        	            // Botón para cerrar el cuadro de diálogo
        	            JButton btnCerrar = new JButton("Cerrar");
        	            btnCerrar.addActionListener(ev -> dialogo.dispose());
        	            dialogo.add(btnCerrar, BorderLayout.SOUTH);

        	            dialogo.setVisible(true);
        	        } else {
        	            JOptionPane.showMessageDialog(this, "No hay vehículos en la base de datos.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        	        }
        	    } catch (Exception ex) {
        	        JOptionPane.showMessageDialog(this, "Error al obtener el vehículo más potente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	    }
        	});


        btnMasBarato.addActionListener(e -> {
        	    try {
        	        // Obtener el vehículo más barato desde el controlador
        	        Vehiculo vehiculo = controlador.obtenerVehiculoMasBarato();

        	        if (vehiculo != null) {
        	            // Definir las columnas para la tabla
        	            String[] columnas = {"Placa", "Marca", "Modelo", "Año", "Cilindrada", "Número de Ejes", "Valor", "Tipo"};

        	            // Crear la matriz de datos con la información del vehículo
        	            Object[][] datos = new Object[1][8];
        	            datos[0][0] = vehiculo.getPlaca();
        	            datos[0][1] = vehiculo.getMarca();
        	            datos[0][2] = vehiculo.getModelo();
        	            datos[0][3] = vehiculo.getAnio();
        	            datos[0][4] = vehiculo.getCilindrada();
        	            datos[0][5] = vehiculo.getNumEjes();
        	            datos[0][6] = new DecimalFormat("#,###").format(vehiculo.getValor());
        	            datos[0][7] = vehiculo instanceof Automovil ? "Automovil" :
        	                          vehiculo instanceof Bus ? "Bus" :
        	                          vehiculo instanceof Camion ? "Camion" : "Moto";

        	            // Crear la tabla
        	            JTable tabla = new JTable(datos, columnas);
        	            JScrollPane scrollPane = new JScrollPane(tabla);

        	            // Crear un cuadro de diálogo para mostrar la tabla
        	            JDialog dialogo = new JDialog(this, "Vehículo Más Barato", true);
        	            dialogo.setSize(600, 200);
        	            dialogo.setLocationRelativeTo(this);
        	            dialogo.setLayout(new BorderLayout());

        	            dialogo.add(scrollPane, BorderLayout.CENTER);

        	            // Botón para cerrar el cuadro de diálogo
        	            JButton btnCerrar = new JButton("Cerrar");
        	            btnCerrar.addActionListener(ev -> dialogo.dispose());
        	            dialogo.add(btnCerrar, BorderLayout.SOUTH);

        	            dialogo.setVisible(true);
        	        } else {
        	            JOptionPane.showMessageDialog(this, "No hay vehículos en la base de datos.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        	        }
        	    } catch (Exception ex) {
        	        JOptionPane.showMessageDialog(this, "Error al obtener el vehículo más barato: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	    }
        	});

    }

    private void mostrarResultado(Vehiculo vehiculo, String titulo) {
        if (vehiculo != null) {
            JDialog resultadoDialog = new JDialog(this, titulo, true);
            resultadoDialog.setSize(500, 300);
            resultadoDialog.setLocationRelativeTo(this);

            JTextArea areaResultado = new JTextArea(10, 30);
            areaResultado.setEditable(false);
            areaResultado.setText(vehiculo.toString().replace(",", "\n"));

            JScrollPane scrollPane = new JScrollPane(areaResultado);
            resultadoDialog.add(scrollPane, BorderLayout.CENTER);

            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(ev -> resultadoDialog.dispose());
            resultadoDialog.add(btnCerrar, BorderLayout.SOUTH);

            resultadoDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No hay vehículos en la base de datos.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
