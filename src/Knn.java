import java.io.IOException;
import java.sql.Array;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import helpers.Distancia;

public class Knn {
    private int k;
    private Float[] patronDesconocido;
    private int clasificacion;
    private int nCaracteristicas;

    public Knn(Dataset dataset, Float[] patronDesconocido, int k){
        this.k = k;
        this.patronDesconocido = patronDesconocido;
        this.nCaracteristicas = dataset.getEntrenamiento().get(0).getFirst().length;

        clasificacion = -1;


        LinkedList<Distancia> distancias = new LinkedList<>();

        for(int i = 0; i < dataset.getEtiquetas().size(); i++){
            for(int j = 0; j < dataset.getEntrenamiento().get(i).size(); j++){
                double distancia = calcularDistancia(dataset.getEntrenamiento().get(i).get(j));
                distancias.add(new Distancia(distancia, i));
            }
        }


        Collections.sort(distancias, new Comparator<Distancia>() {
            @Override
            public int compare(Distancia o1, Distancia o2) {
                return o1.getDistancia().compareTo(o2.getDistancia());
            }
        });

        this.clasificacion = clasificar(distancias, this.k);
    }

    public double calcularDistancia(Float[] prototipo){
        double distancia = 0.0f;
        for(int i = 0; i < this.nCaracteristicas; i++){
            distancia += Math.pow(prototipo[i] - this.patronDesconocido[i], 2);
        }
        distancia = Math.sqrt(distancia);
        return distancia;
    }

    public int clasificar(LinkedList<Distancia> distancias, int k){
        int[] resultados = new int[k];
        for(int i = 0; i < k; i++){
            resultados[i] = distancias.get(k).getEtiqueta();
        }

        HashMap<Integer, Integer> mapa = new HashMap<>();
        for (int x = 0; x < resultados.length; x++) {
            int resultado = resultados[x];
            if (mapa.containsKey(resultado)) {
                mapa.put(resultado, mapa.get(resultado) + 1);
            } else {
                mapa.put(resultado, 1);
            }
        }

        int resultadoFinal = 0;
        int mayor = 0;

        for (HashMap.Entry<Integer, Integer> entry : mapa.entrySet()) {
            if (entry.getValue() > mayor) {
                mayor = entry.getValue();
                resultadoFinal = entry.getKey();
            }
        }

        return resultadoFinal;

    }

    public int getClasificacion(){
        return this.clasificacion;
    }

    /*public static void main(String[] args) throws IOException {
        Dataset ds = new Dataset("src/iris.data");
        Knn knn = new Knn(ds, new Float[]{6.2f,3.4f,5.4f,2.3f}, 4);


    }*/
}
