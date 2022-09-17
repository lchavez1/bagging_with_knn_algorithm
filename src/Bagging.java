import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

        for(int i = 0; i < dataset.getTest().size(); i++){
            for(int j = 0; j < dataset.getTest().get(i).size(); j++){
                for(int k = 0; k < sampleSize; k++){
                    dataset = new Dataset(path);
                    knn = new Knn(dataset, dataset.getTest().get(i).get(j), 4);
                    System.out.println("Pattern " + Arrays.toString(dataset.getTest().get(i).get(j)) + " classified as -> " + knn.getClassification());
                    results[k] = knn.getClassification();
                }
                counter++;
                if(getMostRepeated(results) == i)
                    accuracy++;
                results = new int[sampleSize];
            }
        }
        System.out.println("Terminology:\n[Tag -> representation]");
        // It's just and index to use in the print
        AtomicInteger index = new AtomicInteger();
        // Print out the name of the tags
        dataset.getEtiquetas().forEach(tag -> {System.out.println(tag + " -> " + (index));
            // Increment the index
            index.getAndIncrement();
        });

        System.out.println("Total of evaluated patterns = " + (int) counter + "\nTotal of patterns with  a correct evaluation = " + (int) accuracy + "\nTotal of patterns with  a incorrect evaluation = " + (int) (counter-accuracy));
        // To calculate the accuracy just divide the number of correct evaluations
        // by the number of patterns evaluated and multiply by 100 to get as percentage
        System.out.println((int) (accuracy/counter*100) + "% accuracy");
    }

    // Method to get the most repeated classification
    public static int getMostRepeated(int[] results){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x = 0; x < results.length; x++) {
            int resultado = results[x];
            if (map.containsKey(resultado)) {
                map.put(resultado, map.get(resultado) + 1);
            } else {
                map.put(resultado, 1);
            }
        }

        int resultadoFinal = 0;
        int mayor = 0;

        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > mayor) {
                mayor = entry.getValue();
                resultadoFinal = entry.getKey();
            }
        }

        return resultadoFinal;
    }
}
