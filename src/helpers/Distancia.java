package helpers;

public class Distancia{

    private Double distancia;
    private Integer etiqueta;

    public Distancia(double distancia, int etiqueta) {
        this.distancia = distancia;
        this.etiqueta = etiqueta;
    }

    public Double getDistancia() {
        return this.distancia;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return "Distancia{" +
                "distancia=" + distancia +
                ", etiqueta=" + etiqueta +
                '}';
    }
}
