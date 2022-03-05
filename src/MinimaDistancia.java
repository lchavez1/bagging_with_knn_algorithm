/*import java.util.Arrays;

public class MinimaDistancia {

    private Float[] patronDesconocido;
    private Float[] prototipo;
    private int clasificacion;
    private int nCaracteristicas;

    public MinimaDistancia(Dataset dataset, Float[] patronDesconocido){
        this.patronDesconocido = patronDesconocido;
        this.nCaracteristicas = dataset.getEntrenamiento().get(0).getFirst().length;
        this.prototipo = new Float[this.nCaracteristicas];

        clasificacion = -1;

        double tmp = 0;

        for(int i = 0; i < dataset.getEtiquetas().size(); i++){
            float sumatoria = 0.0f;
            this.prototipo =  new Float[this.nCaracteristicas];
            for(int j = 0; j < dataset.getEntrenamiento().get(i).get(0).length; j++){
                for(int k = 0; k < dataset.getEntrenamiento().get(i).size(); k++){
                    sumatoria += dataset.getEntrenamiento().get(i).get(k)[j];
                }
                sumatoria = sumatoria/dataset.getEntrenamiento().get(i).size();
                this.prototipo[j] = sumatoria;
            }
            if(i == 0){
                tmp = calcularDistancia(this.prototipo);
            }
            if(calcularDistancia(this.prototipo) <= tmp){
                tmp = calcularDistancia(this.prototipo);
                this.clasificacion = i;
            }
        }
    }

    public double calcularDistancia(Float[] prototipo){
        double distancia = 0.0f;
        for(int i = 0; i < this.nCaracteristicas; i++){
            distancia += Math.pow(prototipo[i] - this.patronDesconocido[i], 2);
        }
        distancia = Math.sqrt(distancia);
        return distancia;
    }
    
    public int getClasificacion(){
        return this.clasificacion;
    }

}*/
