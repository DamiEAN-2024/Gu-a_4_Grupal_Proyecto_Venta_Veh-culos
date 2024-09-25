package modelo;

import java.io.Serializable;

/**
 * Clase que representa una Moto, hereda de Vehiculo.
 */
public class Moto extends Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    // Enumeración para tipo de moto
    public enum TipoMoto {
        DEPORTIVA,
        SCOOTER,
        CRUISER,
        ENDURO,
        TOURING
    }

    // Atributos específicos de Moto
    private TipoMoto tipoMoto;
    private boolean tieneMaletero;

    // Constructor
    public Moto(String placa, String marca, String modelo, int anio, int cilindrada, double valor,
                TipoMoto tipoMoto, boolean tieneMaletero) {
        super(placa, marca, modelo, anio, 1, cilindrada, valor); // número de ejes fijo en 1
        setTipoMoto(tipoMoto);
        this.tieneMaletero = tieneMaletero;
    }

    // Getters y Setters
    public TipoMoto getTipoMoto() {
        return tipoMoto;
    }

    public void setTipoMoto(TipoMoto tipoMoto) {
        if (tipoMoto != null) {
            this.tipoMoto = tipoMoto;
        } else {
            throw new IllegalArgumentException("El tipo de moto no puede ser nulo.");
        }
    }

    public boolean isTieneMaletero() {
        return tieneMaletero;
    }

    public void setTieneMaletero(boolean tieneMaletero) {
        this.tieneMaletero = tieneMaletero;
    }

    // Método toString para mostrar la información de la moto
    @Override
    public String toString() {
        return "Moto{" +
                "placa='" + getPlaca() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", anio=" + getAnio() +
                ", numeroEjes=" + getNumEjes() +
                ", cilindrada=" + getCilindrada() +
                ", valor=" + getValor() +
                ", tipoMoto=" + tipoMoto +
                ", tieneMaletero=" + tieneMaletero +
                '}';
    }
}
