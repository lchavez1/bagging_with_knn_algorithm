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

        // We need to iterate as follows

        // First, for each tag in the dataset
        for(int i = 0; i < dataset.getTest().size(); i++){
            // Second, for each pattern of the tag "i"
            for(int j = 0; j < dataset.getTest().get(i).size(); j++){
                // Finally, I execute k times the knn algorithm, this is the bagging method
                // On this case k is just an iterator don't confuse by k from knn algorithm
                for(int k = 0; k < sampleSize; k++){
                    // Pass the values to knn algorithm
                    // Dataset, pattern to classify and the k value (neighbors)
                    knn = new Knn(dataset, dataset.getTest().get(i).get(j), 4);
                    // After we have the classification from knn algorithm and put on results
                    results[k] = knn.getClassification();
                }
                // Increment the counter, because independently of the classification, it was an evaluated pattern
                counter++;
                // We call the method "getMostRepeated" to know the classification using bagging method
                // You might ask yourself, why is it equal to "i"?
                // It's because the iteration "i" is the tag and if the classification is equals to tag this means
                // the classification is correct
                if(getMostRepeated(results) == i)
                    // Print out the classification
                    System.out.println("[" + (int) counter + "]Pattern " + Arrays.toString(dataset.getTest().get(i).get(j)) + " classified as -> " + getMostRepeated(results));
                    // That means the classification was correct, and we need to increment the accuracy by 1
                    accuracy++;
                // We empty the array to use it again
                results = new int[sampleSize];
            }
        }
        System.out.println("Terminology:");
        // It's just and index to use in the print
        AtomicInteger index = new AtomicInteger();
        // Print out the name of the tags
        dataset.getTags().forEach(tag -> {System.out.println(tag + " -> " + (index));
            // Increment the index
            index.getAndIncrement();
        });

        System.out.println("Total of evaluated patterns = " + (int) counter + "\nTotal of patterns with a correct evaluation = " + (int) accuracy + "\nTotal of patterns with an incorrect evaluation = " + (int) (counter-accuracy));
        // To calculate the accuracy just divide the number of correct evaluations
        // by the number of patterns evaluated and multiply by 100 to get as percentage
        System.out.println((int) (accuracy/counter*100) + "% accuracy");
    }

    // Method to get the most repeated classification
    public static int getMostRepeated(int[] results){
        HashMap<Integer, Integer> map = new HashMap<>();
        // First I count each repetition from each tag
        for (int x = 0; x < results.length; x++) {
            // This means the tag exists, and we just need to increment by 1
            if (map.containsKey(results[x])) {
                map.put(results[x], map.get(results[x]) + 1);
            } else {
                // On case the tag doesn't exist in the map, we add it and initialize to 1
                map.put(results[x], 1);
            }
        }

        int classification = 0;
        int fitness = 0;

        // For each key (tag) I compare if it is the highest and
        // that means that it is the label that is repeated the most times
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            // If the value is higher than fitness assign that tag class
            // At the final we will get the classification
            if (entry.getValue() > fitness) {
                fitness = entry.getValue();
                // Assign the classification
                classification = entry.getKey();
            }
        }

        return classification;
    }
}
