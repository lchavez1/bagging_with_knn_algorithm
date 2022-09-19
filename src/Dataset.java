import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

// The base code was programmed by my teacher Andrés Espinal Jiménez, but I did some updates

// Dataset class, that class read the dataset, next divide in train and test set
public class Dataset {
    // This list save class tags
    private LinkedList<String> tag;
    // HashMap that save the paterns on values and tags on key
    private HashMap<Integer, LinkedList<Float[]>> patterns;

    // Here we have two lists, one by train set and another by test set
    private HashMap<Integer, LinkedList<Float[]>> train;
    private HashMap<Integer, LinkedList<Float[]>> test;

    // On the next variable "trainPercentage" we have a percentage that
    // means the size of the patterns that will be used to train set
    private float trainPercentage = 0.90f;
    // Therefore, the rest will be used for testing
    private float testPercentage = 1 - trainPercentage;

    // Constructor of the class, here we received just the path of the dataset
    public Dataset(String path) throws IOException{
        // This bufferedReader will help me to read the dataset
        BufferedReader reader = new BufferedReader(new FileReader(path));

        // Empty string, we will use this variable to read each line of the dataset file
        String line;

        // Initialize tag and patterns
        this.tag = new LinkedList<>();
        // Remember patterns is a HashMap because we will save keys (tag) and values (pattern)
        this.patterns =  new HashMap<>();

        // While reader are reading new lines different from null mean that the file is not finished reading yet
        while((line = reader.readLine()) != null){
            // The dataset is .data file, on this case we have this file separated by "," (comma)
            // therefore we have to split with this character
            // Create a new string array to save each patter
            String[] sPatron = line.split(",");

            // The structure of dataset is the following:
            // 5.1,3.5,1.4,0.2,Iris-setosa
            // First 4 values are the features of the pattern and the final value is the class tag

            // Once we understand the above, we can continue ...
            // If we don't have the new tag in the Map, we need add it
            if(!this.tag.contains(sPatron[sPatron.length -1])){
                // add the key, as you can see, we add the last value of the array "sPatron"
                // this means that we will add the class tag
                this.tag.add(sPatron[sPatron.length -1]);
                // Finally, add an empty list from that key (class tag)
                this.patterns.put(this.tag.indexOf(sPatron[sPatron.length -1]), new LinkedList<>());
            }
            // Temporal float array, on this array we will save the features of the pattern
            // The size is like "sPatron" length minus 1 because on this case we don't need the tag class
            Float[] tmp = new Float[sPatron.length - 1];

            // For each pattern, add it to his respective key (class tag)
            for(int i = 0; i < sPatron.length - 1; i++)
                // Cast to Float
                tmp[i] = Float.valueOf(sPatron[i]);
            // First I get the key, next add the pattern to this key
            this.patterns.get(this.tag.indexOf(sPatron[sPatron.length -1])).add(tmp);
        }

        // At the final of read the dataset we divide into test and train sets
        divideIntoTrainAndTest();
    }

    // Return the validated tags
    public LinkedList<String> getTags(){

        // This I code is because sometimes with some datasets
        // have patterns without tag
        for( int i = 0; i < this.tag.size(); i++){
            // So, if the tag equals to null or "", we remove from the list
            if(this.tag.get(i).equals("") || this.tag.get(i) == null){
                // Remove empty tag
                this.tag.remove(i);
            }
        }
        return this.tag;

    }

    public HashMap<Integer, LinkedList<Float[]>> getPatterns(){
        for(int i = 0; i < this.patterns.size(); i++){
            if(this.patterns.get(i) == null || this.patterns.get(i).getFirst().length == 0){
                this.patterns.remove(i);
                //System.out.println("Se elimino un patron vacio");
            }
        }
        return this.patterns;

    }

    // Method to divide every data into test set and train set
    public void divideIntoTrainAndTest(){
        // We will need a random value to get random patterns
        Random random = new Random();

        // Train set process
        // Initialize train HashMap
        this.train = new HashMap<>();
        // A new list that will contain float arrays with the pattern features
        LinkedList<Float[]> list = new LinkedList<>();

        // For each tag on the dataset we will need to select the same quantity of patterns
        for(int i = 0; i < this.getTags().size(); i++){
            // Compute the number of patterns that we will need to each tag
            int nPatterns = (int) (trainPercentage * this.getPatterns().get(i).size());

            // We get "n" patterns for each class tag
            for(int j = 0; j < nPatterns; j++){
                // To get the pattern, we need to do it randomly
                // At final of this compute, we will get the index of some (random) pattern
                int index = random.nextInt(this.getPatterns().get(i).size() - 1 + 1);
                // Finally, we add it to the list
                list.add(this.getPatterns().get(i).get(index));
            }

            // Put the list of patterns for each tag
            // i = tag
            // list = list of patterns
            this.train.put(i, list);

            // We empty the list to use it again in the next iteration
            list = new LinkedList<>();
        }

        // Test set process
        // Initialize test HashMap
        this.test = new HashMap<>();

        // We empty the list to use it again
        list = new LinkedList<>();

        // For each tag on the dataset we will need to select the same quantity of patterns
        for(int i = 0; i < this.getTags().size(); i++){
            // Compute the number of patterns that we will need to each tag
            int nPatrones = (int) (testPercentage * this.getPatterns().get(i).size());

            // We get "n" patterns for each class tag
            for(int j = 0; j < nPatrones; j++){
                // To get the pattern, we need to do it randomly
                // At final of this compute, we will get the index of some (random) pattern
                int index = random.nextInt(this.getPatterns().get(i).size() - 1 + 1);
                // Finally, we add it to the list
                list.add(this.getPatterns().get(i).get(index));
            }

            // Put the list of patterns for each tag
            this.test.put(i, list);
            // We empty the list to use it again in the next iteration
            list = new LinkedList<>();
        }
    }

    // Return train set
    public HashMap<Integer, LinkedList<Float[]>> getTrain(){
        return this.train;
    }

    // Return test set
    public HashMap<Integer, LinkedList<Float[]>> getTest(){
        return this.test;
    }

}

