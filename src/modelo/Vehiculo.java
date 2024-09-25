package modelo;

/**
 * Clase que representa un vehículo genérico.
 */
public abstract class Vehiculo {

    // Atributos
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private int numEjes;
    private int cilindrada;
    private double valor;

    // Constructor
    public Vehiculo(String placa, String marca, String modelo, int anio, int numEjes, int cilindrada, double valor) {
        setPlaca(placa);
        setMarca(marca);
        setModelo(modelo);
        setAnio(anio);
        setNumEjes(numEjes);
        setCilindrada(cilindrada);
        setValor(valor);
    }

    // Métodos getters y setters con validaciones

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa != null && !placa.trim().isEmpty()) {
            this.placa = placa;
        } else {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca != null && !marca.trim().isEmpty()) {
            this.marca = marca;
        } else {
            throw new IllegalArgumentException("La marca no puede ser nula o vacía.");
        }
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo != null && !modelo.trim().isEmpty()) {
            this.modelo = modelo;
        } else {
            throw new IllegalArgumentException("El modelo no puede ser nulo o vacío.");
        }
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        int anioActual = java.time.Year.now().getValue();
        if (anio >= 1886 && anio <= anioActual) {
            this.anio = anio;
        } else {
            throw new IllegalArgumentException("El año ingresado es inválido.");
        }
    }

    public int getNumEjes() {
        return numEjes;
    }

    public void setNumEjes(int numEjes) {
        if (numEjes >= 0) {
            this.numEjes = numEjes;
        } else {
            throw new IllegalArgumentException("El número de ejes no puede ser negativo.");
        }
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        if (cilindrada >= 0) {
            this.cilindrada = cilindrada;
        } else {
            throw new IllegalArgumentException("La cilindrada no puede ser negativa.");
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor >= 0) {
            this.valor = valor;
        } else {
            throw new IllegalArgumentException("El valor no puede ser negativo.");
        }
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", numEjes=" + numEjes +
                ", cilindrada=" + cilindrada +
                ", valor=" + valor +
                '}';
    }
   
  }
