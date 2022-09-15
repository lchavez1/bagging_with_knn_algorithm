import java.io.IOException;
import java.util.*;

public class Bagging {

    // Main method
    public static void main(String[] args) throws IOException {

        // Define the dataset path on this case iris dataset, but you can use another
        String path = "src/iris.data";

        // Define the size of the population sample
        int sampleSize = 5;

        // Instance of dataset using the path
        Dataset dataset = new Dataset(path);

        // Instance of k nearest neighbor
        Knn knn;

        // Create a new array results to save
        int[] results = new int[sampleSize];
        // Float varible to save the accuracy
        float accuracy = 0;
        // A simple counter to count the number of evaluated patterns
        float counter = 0;

        for(int i = 0; i < dataset.getPrueba().size(); i++){
            for(int j = 0; j < dataset.getPrueba().get(i).size(); j++){
                for(int k = 0; k < sampleSize; k++){
                    dataset = new Dataset(path);
                    knn = new Knn(dataset, dataset.getPrueba().get(i).get(j), 4);
                    System.out.println("Patron " + knn.getClasificacion());
                    results[k] = knn.getClasificacion();
                }
                counter++;
                if(getMostRepeated(results) == i)
                    accuracy++;
                results = new int[sampleSize];
            }
        }
        System.out.println("Total de patrones evaluados = " + counter + "\nTotal de patrones correctos = " + accuracy + "\nTotal de patrones incorrectos = " + (counter-accuracy));
        System.out.println((accuracy/counter*100) + "% de exactitud");
    }

    // Method to get the most repeated classification
    public static int getMostRepeated(int[] results){
        HashMap<Integer, Integer> mapa = new HashMap<>();
        for (int x = 0; x < results.length; x++) {
            int resultado = results[x];
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
}
