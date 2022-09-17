import java.util.*;

public class Knn {
    private int k;
    private Float[] unknownPattern;
    private int classification;
    private int nFeatures;

    public Knn(Dataset dataset, Float[] unknownPattern, int k){

        // K is the number of neighbors
        this.k = k;
        // This is the pattern that we will classify
        this.unknownPattern = unknownPattern;
        // The number of features that patter has
        // To get the number I take the first patter and get length
        this.nFeatures = dataset.getTrain().get(0).getFirst().length;

        // Initialize classification by -1, this means the patter isn't classified yet
        classification = -1;


        // On this list "distances" we will save the distances from neighbors
        LinkedList<Distance> distances = new LinkedList<>();

        // For each neighbor we need to compute the distance
        for(int i = 0; i < dataset.getEtiquetas().size(); i++){
            for(int j = 0; j < dataset.getTrain().get(i).size(); j++){
                // This is the distance value
                double distance = computeDistance(dataset.getTrain().get(i).get(j));
                // On the list distances I add a new Distance instance with the distance value and tag
                // distance = distance value
                // tag = i
                distances.add(new Distance(distance, i));
            }
        }

        // Next I need to sort the distances, to do this I used the sort method from Collections
        // and Comparator using getDistance method from Distance
        distances.sort(Comparator.comparing(Distance::getDistance));

        // assign the tag class from the pattern
        this.classification = classify(distances, this.k);
    }

    // Function to calculate the distance between two patterns
    // is very easy just I used the Euclidean distance
    // To know more about this technique look at https://en.wikipedia.org/wiki/Euclidean_distance
    public double computeDistance(Float[] unknownPattern){
        // On this variable we will save the distance, first is initialized to zero
        double distance = 0.0f;
        // Here apply the Euclidean distance with n dimensions (nFeatures)
        for(int i = 0; i < this.nFeatures; i++){
            // Calculate sum of squares
            distance += Math.pow(unknownPattern[i] - this.unknownPattern[i], 2);
        }
        // Calculate the square root
        distance = Math.sqrt(distance);

        return distance;
    }

    // That function use the list of distances to get the classification
    public int classify(LinkedList<Distance> distances, int k){
        // Results, this array will contain the distances from k neighbors
        int[] results = new int[k];

        // Add just the k neighbors tags
        for(int i = 0; i < k; i++){
            results[i] = distances.get(i).getTag();
        }

        // Tags will be a Map with the tag and the number of times that it repeats
        HashMap<Integer, Integer> tags = new HashMap<>();

        // Add the number of times that the tag repeats
        for (int result : results) {

            // If the tag exists, we just increment the number of times
            if (tags.containsKey(result)) {
                tags.put(result, tags.get(result) + 1);
            } else {
                // If the tag doesn't exist we create the tag and initialize the number of times to one
                tags.put(result, 1);
            }
        }

        // Initialize the classification and fitness
        // Classification will be the tag assigned
        int classification = 0;
        // Fitness is an auxiliary to get the tag that repeats more times
        int fitness = 0;


        // For each key (tag) I compare if it is the highest and
        // that means that it is the label that is repeated the most times
        for (HashMap.Entry<Integer, Integer> entry : tags.entrySet()) {
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

    // Returns the class tag that has been assigned
    public int getClassification(){
        return this.classification;
    }

}
