package modelo;

/**
 * Clase que representa un Camión, hereda de Vehiculo.
 */
public class Camion extends Vehiculo {

    // Atributo específico de Camion
    private double capacidadCarga; // Capacidad de carga en toneladas

    // Constructor
    public Camion(String placa, String marca, String modelo, int anio, int numeroEjes, int cilindrada, double valor,
                  double capacidadCarga) {
        super(placa, marca, modelo, anio, numeroEjes, cilindrada, valor);
        setCapacidadCarga(capacidadCarga);
    }

    // Getter y Setter
    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        if (capacidadCarga >= 0) {
            this.capacidadCarga = capacidadCarga;
        } else {
            throw new IllegalArgumentException("La capacidad de carga no puede ser negativa.");
        }
    }

    // Método toString para mostrar la información del camión
    @Override
    public String toString() {
        return "Camion{" +
                "placa='" + getPlaca() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", anio=" + getAnio() +
                ", numeroEjes=" + getNumEjes() +
                ", cilindrada=" + getCilindrada() +
                ", valor=" + getValor() +
                ", capacidadCarga=" + capacidadCarga +
                '}';
    }
}
