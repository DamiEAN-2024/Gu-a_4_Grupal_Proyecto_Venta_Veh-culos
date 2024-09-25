package modelo;

/**
 * Clase que representa un Automóvil, hereda de Vehiculo.
 */
public class Automovil extends Vehiculo {

    // Enumeraciones para tipo de combustible y tipo de transmisión
    public enum TipoCombustible {
        GASOLINA,
        DIESEL,
        ELECTRICO,
        HIBRIDO
    }

    public enum TipoTransmision {
        MANUAL,
        AUTOMATICA
    }

    // Atributos específicos de Automovil
    private TipoCombustible tipoCombustible;
    private int numeroPuertas;
    private TipoTransmision tipoTransmision;

    // Constructor
    public Automovil(String placa, String marca, String modelo, int anio, int numeroEjes, int cilindrada, double valor,
                     TipoCombustible tipoCombustible, int numeroPuertas, TipoTransmision tipoTransmision) {
        super(placa, marca, modelo, anio, numeroEjes, cilindrada, valor);
        setTipoCombustible(tipoCombustible);
        setNumeroPuertas(numeroPuertas);
        setTipoTransmision(tipoTransmision);
    }

    // Getters y Setters
    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(TipoCombustible tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        if (numeroPuertas >= 0) {
            this.numeroPuertas = numeroPuertas;
        } else {
            throw new IllegalArgumentException("El número de puertas no puede ser negativo.");
        }
    }

    public TipoTransmision getTipoTransmision() {
        return tipoTransmision;
    }

    public void setTipoTransmision(TipoTransmision tipoTransmision) {
        this.tipoTransmision = tipoTransmision;
    }

    // Método toString para mostrar la información del automóvil
    @Override
    public String toString() {
        return "Automovil{" +
                "placa='" + getPlaca() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", anio=" + getAnio() +
                ", numeroEjes=" + getNumEjes() +
                ", cilindrada=" + getCilindrada() +
                ", valor=" + getValor() +
                ", tipoCombustible=" + tipoCombustible +
                ", numeroPuertas=" + numeroPuertas +
                ", tipoTransmision=" + tipoTransmision +
                '}';
    }
}
