package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionDB;
import modelo.Automovil;
import modelo.Bus;
import modelo.Camion;
import modelo.Moto;
import modelo.Vehiculo;

public class VehiculoDAO {

    // Método para agregar un vehículo a la base de datos
    public boolean agregarVehiculo(Vehiculo vehiculo) throws Exception {
        String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio, numero_ejes, cilindrada, valor, tipo_vehiculo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vehiculo.getPlaca());
            pstmt.setString(2, vehiculo.getMarca());
            pstmt.setString(3, vehiculo.getModelo());
            pstmt.setInt(4, vehiculo.getAnio());
            pstmt.setInt(5, vehiculo.getNumEjes());
            pstmt.setInt(6, vehiculo.getCilindrada());
            pstmt.setDouble(7, vehiculo.getValor());

            String tipoVehiculo = vehiculo instanceof Automovil ? "Automovil" :
                                  vehiculo instanceof Bus ? "Bus" :
                                  vehiculo instanceof Camion ? "Camion" :
                                  vehiculo instanceof Moto ? "Moto" : "Vehiculo";
            pstmt.setString(8, tipoVehiculo);

            int rowsInserted = pstmt.executeUpdate();

            // Inserting into specific tables based on vehicle type
            if (vehiculo instanceof Automovil) {
                Automovil automovil = (Automovil) vehiculo;
                String sqlAuto = "INSERT INTO automoviles (placa, tipo_combustible, num_puertas, tipo_transmision, vehiculos_placa) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmtAuto = conn.prepareStatement(sqlAuto)) {
                    pstmtAuto.setString(1, automovil.getPlaca());
                    pstmtAuto.setString(2, automovil.getTipoCombustible().toString());
                    pstmtAuto.setInt(3, automovil.getNumeroPuertas());
                    pstmtAuto.setString(4, automovil.getTipoTransmision().toString());
                    pstmtAuto.setString(5, automovil.getPlaca());
                    pstmtAuto.executeUpdate();
                }
            } else if (vehiculo instanceof Bus) {
                Bus bus = (Bus) vehiculo;
                String sqlBus = "INSERT INTO buses (placa, num_pasajeros, tipo_servicio, num_pisos, vehiculos_placa) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmtBus = conn.prepareStatement(sqlBus)) {
                    pstmtBus.setString(1, bus.getPlaca());
                    pstmtBus.setInt(2, bus.getCapacidadPasajeros()); // Asegúrate de que el método getCapacidadPasajeros existe
                    pstmtBus.setString(3, bus.getTipoServicio().toString()); // Asegúrate de que getTipoServicio() devuelve un valor válido
                    pstmtBus.setInt(4, bus.getNumPisos()); // Verifica que el método getNumPisos() exista en la clase Bus
                    pstmtBus.setString(5, bus.getPlaca());

                    int rowsInsertedBus = pstmtBus.executeUpdate();
                    if (rowsInsertedBus <= 0) {
                        throw new Exception("Error al agregar el bus a la base de datos.");
                    }
                } catch (SQLException e) {
                    throw new Exception("Error al agregar el bus: " + e.getMessage());
                }
            } else if (vehiculo instanceof Camion) {
                Camion camion = (Camion) vehiculo;
                String sqlCamion = "INSERT INTO camiones (placa, capacidad_carga, num_ejes, vehiculos_placa) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmtCamion = conn.prepareStatement(sqlCamion)) {
                    pstmtCamion.setString(1, camion.getPlaca());
                    pstmtCamion.setDouble(2, camion.getCapacidadCarga());
                    pstmtCamion.setInt(3, camion.getNumEjes());
                    pstmtCamion.setString(4, camion.getPlaca());
                    
                int rowsInsertedCamion = pstmtCamion.executeUpdate();
                return rowsInsertedCamion > 0;
                }
            } else if (vehiculo instanceof Moto) {
                Moto moto = (Moto) vehiculo;
                String sqlMoto = "INSERT INTO motos (placa, cilindrada, tipo, tiene_maletero, vehiculos_placa) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmtMoto = conn.prepareStatement(sqlMoto)) {
                    pstmtMoto.setString(1, moto.getPlaca());
                    pstmtMoto.setInt(2, moto.getCilindrada());
                    pstmtMoto.setString(3, moto.getTipoMoto().toString());  // Asegúrate de que `getTipo()` sea el método correcto en tu clase Moto
                    pstmtMoto.setBoolean(4, moto.isTieneMaletero());      // Verifica que `tieneMaletero()` sea el método correcto en tu clase Moto
                    pstmtMoto.setString(5, moto.getPlaca());

                    int rowsInsertedMoto = pstmtMoto.executeUpdate();
                    if (rowsInsertedMoto <= 0) {
                        throw new Exception("Error al agregar la moto a la base de datos.");
                    }
                } catch (SQLException e) {
                    throw new Exception("Error al agregar la moto: " + e.getMessage());
                }
            }

            return rowsInserted > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new Exception("Error: La placa ingresada ya existe en la base de datos.");
            }
            throw new Exception("Error al agregar el vehículo: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    // Método para obtener todos los vehículos de la base de datos
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Vehiculo vehiculo = crearVehiculoDesdeResultSet(rs);
                if (vehiculo != null) {
                    listaVehiculos.add(vehiculo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }

        return listaVehiculos;
    }

    // Método para crear un objeto Vehiculo desde un ResultSet
    
private Vehiculo crearVehiculoDesdeResultSet(ResultSet rs) throws SQLException {
        String tipoVehiculo = rs.getString("tipo_vehiculo");
        Vehiculo vehiculo = null;

        String placa = rs.getString("placa");
        String marca = rs.getString("marca");
        String modelo = rs.getString("modelo");
        int anio = rs.getInt("anio");
        int numEjes = rs.getInt("numero_ejes");
        int cilindrada = rs.getInt("cilindrada");
        double valor = rs.getDouble("valor");

        switch (tipoVehiculo) {
            case "Automovil":
            	 try (PreparedStatement pstmtAutomovil = ConexionDB.getConexion().prepareStatement("SELECT * FROM automoviles WHERE placa = ?")) {
            	        pstmtAutomovil.setString(1, placa);
            	        try (ResultSet rsAutomovil = pstmtAutomovil.executeQuery()) {
            	            if (rsAutomovil.next()) {
            	                Automovil.TipoCombustible tipoCombustible = Automovil.TipoCombustible.valueOf(rsAutomovil.getString("tipo_combustible").toUpperCase());
            	                int numeroPuertas = rsAutomovil.getInt("num_puertas");
            	                Automovil.TipoTransmision tipoTransmision = Automovil.TipoTransmision.valueOf(rsAutomovil.getString("tipo_transmision").toUpperCase());
            	                
            	                vehiculo = new Automovil(placa, marca, modelo, anio, numEjes, cilindrada, valor,
            	                        tipoCombustible, numeroPuertas, tipoTransmision);
            	            }
            	        }
            	    }
            	    break;
            case "Bus":
            	  // Realiza una consulta específica a la tabla `buses`
                try (PreparedStatement pstmtBus = ConexionDB.getConexion().prepareStatement("SELECT * FROM buses WHERE placa = ?")) {
                    pstmtBus.setString(1, placa);
                    try (ResultSet rsBus = pstmtBus.executeQuery()) {
                        if (rsBus.next()) {
                            int capacidadPasajeros = rsBus.getInt("num_pasajeros"); // Asegúrate de que la columna 'num_pasajeros' existe y se obtiene correctamente
                            Bus.TipoServicio tipoServicio = Bus.TipoServicio.valueOf(rsBus.getString("tipo_servicio").toUpperCase());
                            int numPisos = rsBus.getInt("num_pisos"); // Obtener el valor de la columna 'num_pisos'
                            
                            // Crear el objeto Bus con el nuevo parámetro numPisos
                            vehiculo = new Bus(placa, marca, modelo, anio, numEjes, cilindrada, valor, capacidadPasajeros, tipoServicio, numPisos);
                        }
                    }
                }
                break;
            case "Camion":
            	 try (PreparedStatement pstmtCamion = ConexionDB.getConexion().prepareStatement("SELECT * FROM camiones WHERE placa = ?")) {
            	        pstmtCamion.setString(1, placa);
            	        try (ResultSet rsCamion = pstmtCamion.executeQuery()) {
            	            if (rsCamion.next()) {
            	                double capacidadCarga = rsCamion.getDouble("capacidad_carga");
            	                int numeroEjes = rsCamion.getInt("num_ejes");
            	                vehiculo = new Camion(placa, marca, modelo, anio, numeroEjes, cilindrada, valor, capacidadCarga);
            	            }
            	        }
            	    }
            	    break;
            case "Moto":
                // Realiza una consulta específica a la tabla `motos`
                try (PreparedStatement pstmtMoto = ConexionDB.getConexion().prepareStatement("SELECT * FROM motos WHERE placa = ?")) {
                    pstmtMoto.setString(1, placa);
                    try (ResultSet rsMoto = pstmtMoto.executeQuery()) {
                        if (rsMoto.next()) {
                            Moto.TipoMoto tipoMoto = Moto.TipoMoto.valueOf(rsMoto.getString("tipo").toUpperCase()); // Asegúrate de que la columna 'tipo' tiene valores válidos
                            boolean tieneMaletero = rsMoto.getBoolean("tiene_maletero"); // Asegúrate de que la columna 'tiene_maletero' existe
                            
                            // Crear el objeto Moto con los valores obtenidos
                            vehiculo = new Moto(placa, marca, modelo, anio, cilindrada, valor, tipoMoto, tieneMaletero);
                        } 

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

        return vehiculo;
    }

// Método para eliminar un vehículo de la base de datos por su placa
public boolean eliminarVehiculo(String placa) {
    String sql = "DELETE FROM vehiculos WHERE placa = ?";
    
    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, placa);
        int rowsDeleted = pstmt.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        ConexionDB.cerrarConexion();
    }
}

// Método para buscar un vehículo por su placa
public Vehiculo buscarVehiculoPorPlaca(String placa) {
    String sql = "SELECT * FROM vehiculos WHERE placa = ?";
    
    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, placa);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return crearVehiculoDesdeResultSet(rs);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionDB.cerrarConexion();
    }

    return null;
}

// Método para obtener la lista de todas las placas
public List<String> obtenerPlacas() {
    List<String> placas = new ArrayList<>();
    String sql = "SELECT placa FROM vehiculos";
    
    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            placas.add(rs.getString("placa"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionDB.cerrarConexion();
    }

    return placas;
}

// Método para ordenar vehículos por criterio
public List<Vehiculo> ordenarVehiculos(String criterio) {
    List<Vehiculo> listaVehiculos = new ArrayList<>();
    String columnaOrden = "";

    if (criterio.equalsIgnoreCase("modelo")) {
        columnaOrden = "modelo";
    } else if (criterio.equalsIgnoreCase("marca")) {
        columnaOrden = "marca";
    } else if (criterio.equalsIgnoreCase("año")) {
        columnaOrden = "anio";
    } else {
        System.err.println("Criterio de ordenación no válido.");
        return listaVehiculos;
    }

    String sql = "SELECT * FROM vehiculos ORDER BY " + columnaOrden;

    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Vehiculo vehiculo = crearVehiculoDesdeResultSet(rs);
            if (vehiculo != null) {
                listaVehiculos.add(vehiculo);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionDB.cerrarConexion();
    }

    return listaVehiculos;
}

// Método para disminuir el precio de los vehículos
public void disminuirPrecioVehiculos(double valorMinimo) {
    String sql = "UPDATE vehiculos SET valor = valor * 0.9 WHERE valor > ?";
    
    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setDouble(1, valorMinimo);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionDB.cerrarConexion();
    }
}

// Método para obtener el vehículo más antiguo

public Vehiculo obtenerVehiculoMasAntiguo() {
    String sql = "SELECT * FROM vehiculos ORDER BY anio ASC";
    
    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
            return crearVehiculoDesdeResultSet(rs);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionDB.cerrarConexion();
    }

    return null;
}

// Método para obtener el vehículo más potente
public Vehiculo obtenerVehiculoMasPotente() {
    String sql = "SELECT * FROM vehiculos ORDER BY cilindrada DESC LIMIT 1";
    
    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
            return crearVehiculoDesdeResultSet(rs);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionDB.cerrarConexion();
    }

    return null;
}

// Método para obtener el vehículo más barato
public Vehiculo obtenerVehiculoMasBarato() {
    String sql = "SELECT * FROM vehiculos ORDER BY valor ASC LIMIT 1";

    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
            return crearVehiculoDesdeResultSet(rs);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionDB.cerrarConexion();
    }

    return null;
}

// Método para buscar placas por modelo y año
public List<String> buscarPlacasPorModeloYAnio(String modelo, int anio) {
    List<String> placas = new ArrayList<>();
    String sql = "SELECT placa FROM vehiculos WHERE modelo = ? AND anio = ?";
    
    try (Connection conn = ConexionDB.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, modelo);
        pstmt.setInt(2, anio);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            placas.add(rs.getString("placa"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConexionDB.cerrarConexion();
    }

    return placas;
  }
}
