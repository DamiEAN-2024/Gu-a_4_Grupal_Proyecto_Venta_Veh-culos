package modelo;

/**
 * Clase que representa un Bus, hereda de Vehiculo.
 */
public class Bus extends Vehiculo {

    // Enumeración para tipo de servicio
    public enum TipoServicio {
        URBANO,
        INTERMUNICIPAL,
        TURISTICO,
        ESCOLAR
    }

    // Atributos específicos de Bus
    private int capacidadPasajeros;
    private TipoServicio tipoServicio;
    private int numPisos;  // Nuevo atributo para el número de pisos

    // Constructor
    public Bus(String placa, String marca, String modelo, int anio, int numeroEjes, int cilindrada, double valor,
               int capacidadPasajeros, TipoServicio tipoServicio, int numPisos) {
        super(placa, marca, modelo, anio, numeroEjes, cilindrada, valor);
        setCapacidadPasajeros(capacidadPasajeros);
        setTipoServicio(tipoServicio);
        setNumPisos(numPisos);  // Inicializar el número de pisos
    }

    // Getters y Setters
    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        if (capacidadPasajeros >= 0) {
            this.capacidadPasajeros = capacidadPasajeros;
        } else {
            throw new IllegalArgumentException("La capacidad de pasajeros no puede ser negativa.");
        }
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getNumPisos() {
        return numPisos;
    }

    public void setNumPisos(int numPisos) {
        if (numPisos >= 1) {
            this.numPisos = numPisos;
        } else {
            throw new IllegalArgumentException("El número de pisos debe ser al menos 1.");
        }
    }

    // Método toString para mostrar la información del bus
    @Override
    public String toString() {
        return "Bus{" +
                "placa='" + getPlaca() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", anio=" + getAnio() +
                ", numeroEjes=" + getNumEjes() +
                ", cilindrada=" + getCilindrada() +
                ", valor=" + getValor() +
                ", capacidadPasajeros=" + capacidadPasajeros +
                ", tipoServicio=" + tipoServicio +
                ", numPisos=" + numPisos +
                '}';
    }
}
