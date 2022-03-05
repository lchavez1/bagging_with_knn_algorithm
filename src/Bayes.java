/*import java.io.IOException;
import java.util.*;

public class Bayes {

    public static void main(String[] args) throws IOException {
        int nCaracteristicas = 4;
        int n = 50;

        Dataset ds = new Dataset("src/iris.data");

        HashMap<Integer, Double[]> desviaciones = new HashMap<>();
        HashMap<Integer, Double[]> medias = new HashMap<>();
        HashMap<Integer, Double> probabilidades = new HashMap<>();

        //Aqui calculo la media de casa clase del dataset
        double sumatoria = 0;
        int contador = 0;

        Double[] list1 = new Double[ds.getPatrones().get(0).getFirst().length];
        for(int i = 0; i < ds.getEtiquetas().size(); i++){
            for(int j = 0; j < ds.getPatrones().get(i).get(0).length; j++){
                for(int k = 0; k < ds.getPatrones().get(i).size(); k++){
                    sumatoria += ds.getPatrones().get(i).get(k)[j];
                    contador++;
                }
                sumatoria = sumatoria/contador;
                list1[j] = sumatoria;
                sumatoria = 0;
                contador = 0;
            }
            medias.put(i, list1);
            list1 = new Double[ds.getPatrones().get(0).getFirst().length];
        }
        //System.out.println(Arrays.toString(medias.get(0)));
        //System.out.println(Arrays.toString(medias.get(1)));
        //System.out.println(Arrays.toString(medias.get(2)));


        //Aqui calculo la desviacion estandar de cada clase de el dataset
        for(int i = 0; i < ds.getEtiquetas().size(); i++){
            for(int j = 0; j < ds.getPatrones().get(i).get(0).length; j++){
                for(int k = 0; k < ds.getPatrones().get(i).size(); k++){
                    sumatoria += Math.pow(ds.getPatrones().get(i).get(k)[j]-medias.get(i)[j], 2);
                    contador++;
                }
                sumatoria = sumatoria/contador;
                sumatoria =  Math.sqrt(sumatoria);
                list1[j] = sumatoria;
                sumatoria = 0;
                contador = 0;
            }
            desviaciones.put(i, list1);
            list1 = new Double[ds.getPatrones().get(0).getFirst().length];
        }
        //System.out.println();
        //System.out.println(Arrays.toString(desviaciones.get(0)));
        //System.out.println(Arrays.toString(desviaciones.get(1)));
        //System.out.println(Arrays.toString(desviaciones.get(2)));

        //Aqui genero un punto nuevo o desconocido
        LinkedList<Float> nuevo = new LinkedList<Float>();
        nuevo.add(6.5f);
        nuevo.add(3.0f);
        nuevo.add(5.2f);
        nuevo.add(2.0f);

        //Aqui calculamos probabilidades
        double valor;
        double exponente;
        double producto = 1.0f;
        double probabilidad;

        for(int i = 0; i < ds.getEtiquetas().size(); i++) {
            valor = 0.0;
            exponente = 0.0;
            probabilidad = 0.0;
            for(int j = 0; j < ds.getPatrones().get(i).getFirst().length; j++) {
                exponente = (-0.5*Math.pow(((nuevo.get(j)-medias.get(i)[j])/desviaciones.get(i)[j]), 2));
                valor = (1 / Math.sqrt(2*Math.PI*desviaciones.get(i)[j])) * Math.exp(exponente);
                producto = producto * valor;
            }
            probabilidad = ds.getPatrones().get(i).size() / (float)obtenerTotalPatrones(ds);
            //System.out.println(producto);
            probabilidad = probabilidad * producto;
            //System.out.println(probabilidad);
            probabilidades.put(i, probabilidad);
        }

        System.out.println(obtenerEtiqueta(probabilidades));
    }

    public static int obtenerTotalPatrones(Dataset ds) {
    int total = 0;
        for(int i = 0; i < ds.getPatrones().size(); i++){
            total += ds.getPatrones().get(i).size();
        }
        return total;
    }

    public static int obtenerEtiqueta(HashMap<Integer, Double> probabilidades){
        LinkedList<Double> list = new LinkedList<>();
        for(int i = 0; i < probabilidades.size(); i++){
            System.out.println(probabilidades.get(i));
            list.add(probabilidades.get(i));
        }
        Collections.sort(list, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        });
        for(int i = 0; i < list.size(); i++){
            System.out.println(probabilidades.get(i));
        }
        return 0;
    }

    /*private Float[] patronDesconocido;
    private Float[] prototipo;
    private int clasificacion;
    private int nCaracteristicas;

    public Bayes(Dataset dataset, Float[] patronDesconocido){
        this.patronDesconocido = patronDesconocido;



    }
}*/
