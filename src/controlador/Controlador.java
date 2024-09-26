package controlador;

import java.util.List;
import dao.VehiculoDAO;
import modelo.Vehiculo;
import java.text.DecimalFormat;

public class Controlador {

    private VehiculoDAO vehiculoDAO;

    public Controlador() {
        vehiculoDAO = new VehiculoDAO();
    }

    // Método para agregar un vehículo
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        try {
            return vehiculoDAO.agregarVehiculo(vehiculo);
        } catch (Exception e) {
            System.err.println("Error al agregar el vehículo: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener todos los vehículos
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoDAO.obtenerTodosLosVehiculos();
    }

    // Método para eliminar un vehículo (comprar)
    public boolean comprarVehiculo(String placa) {
        try {
            // Obtener el tipo de vehículo usando el método de búsqueda por placa
            Vehiculo vehiculo = vehiculoDAO.buscarVehiculoPorPlaca(placa);

            if (vehiculo != null) {
                String tipoVehiculo = vehiculo.getClass().getSimpleName().toLowerCase(); 
                return vehiculoDAO.eliminarVehiculo(placa, tipoVehiculo);
            } else {
                System.out.println("No se encontró un vehículo con la placa: " + placa);
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error al eliminar el vehículo: " + e.getMessage());
            return false;
        }
    }

    public Vehiculo obtenerInformacionVehiculo(String placa) {
        return vehiculoDAO.buscarVehiculoPorPlaca(placa);
    }

    public List<Vehiculo> ordenarVehiculos(String criterio) {
        try {
            return vehiculoDAO.ordenarVehiculos(criterio.toLowerCase());
        } catch (Exception e) {
            System.err.println("Error al ordenar los vehículos: " + e.getMessage());
            return null;
        }

    }
    
    public List<String> buscarPlacasPorModeloYAño(String modelo, int anio) {
        try {
            return vehiculoDAO.buscarPlacasPorModeloYAnio(modelo, anio);
        } catch (Exception e) {
            System.err.println("Error al buscar placas por modelo y año: " + e.getMessage());
            return null;
        }
    }
    
    public void disminuirPrecioVehiculos(double valorMinimo) {
        try {
            vehiculoDAO.disminuirPrecioVehiculos(valorMinimo);
        } catch (Exception e) {
            System.err.println("Error al disminuir el precio de los vehículos: " + e.getMessage());
        }
    }
  
    public Vehiculo obtenerVehiculoMasAntiguo() {
        
        // Obtener la lista completa de vehículos
        List<Vehiculo> listaVehiculos = obtenerTodosLosVehiculos();

        // Verificar si la lista no está vacía
        if (listaVehiculos != null && !listaVehiculos.isEmpty()) {
            // Encontrar el vehículo con el año más antiguo
            Vehiculo vehiculoMasAntiguo = listaVehiculos.stream()
                    .min((v1, v2) -> Integer.compare(v1.getAnio(), v2.getAnio()))
                    .orElse(null);

            if (vehiculoMasAntiguo != null) {
                System.out.println("Vehículo más antiguo encontrado: " + vehiculoMasAntiguo.getPlaca());
            }
            
            return vehiculoMasAntiguo;
        }

        System.out.println("No se encontraron vehículos en la lista.");
        return null;
    }

    public Vehiculo obtenerVehiculoMasPotente() {
        System.out.println("Buscando el vehículo más potente utilizando la lista existente.");
        
        // Obtener la lista completa de vehículos
        List<Vehiculo> listaVehiculos = obtenerTodosLosVehiculos();

        // Verificar si la lista no está vacía
        if (listaVehiculos != null && !listaVehiculos.isEmpty()) {
            // Encontrar el vehículo con la mayor cilindrada
            Vehiculo vehiculoMasPotente = listaVehiculos.stream()
                    .max((v1, v2) -> Integer.compare(v1.getCilindrada(), v2.getCilindrada()))
                    .orElse(null);

            if (vehiculoMasPotente != null) {
                System.out.println("Vehículo más potente encontrado: " + vehiculoMasPotente.getPlaca());
            }
            
            return vehiculoMasPotente;
        }

        System.out.println("No se encontraron vehículos en la lista.");
        return null;
    }
    
    public Vehiculo obtenerVehiculoMasBarato() {
        System.out.println("Llamando a obtenerVehiculoMasBarato desde Controlador.");
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoMasBarato();

        if (vehiculo != null) {
            System.out.println("Vehículo más barato obtenido con placa: " + vehiculo.getPlaca());
        } else {
            System.out.println("No se encontró ningún vehículo más barato desde el DAO.");
        }

        return vehiculo;
    }

}
