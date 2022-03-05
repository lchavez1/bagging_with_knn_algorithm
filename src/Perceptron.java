import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Perceptron {

    private int clasificacion;
    private HashMap<Integer, LinkedList<Float[]>> conjunto = new HashMap<>();
    private Float[] w = {4.5f, 3.0f, 2.6f, 1.0f, 1.8f};
    private Float productoPunto = 0.0f;
    private int t = 0;
    private int claseA = 0;
    private int claseB = 1;

    public Perceptron(Dataset dataset){
        clasificacion = -1;
        conjunto = generarNuevoHashMap(dataset, this.claseA, this.claseB);
        Random random = new Random();


        Float[] copiaPesos = new Float[w.length];
        while(true){
            if(conjunto.get(this.claseA).size() == 0 && conjunto.get(this.claseB).size() == 0){
                if(Arrays.equals(copiaPesos, this.w)){
                    System.out.println("Converge");
                    break;
                }
                System.out.println("Epoca nueva");
                copiaPesos = this.w;
                conjunto = generarNuevoHashMap(dataset, this.claseA, this.claseB);
            } else {
                int j = random.nextInt(2-1+1) + 0;
                int index = random.nextInt(conjunto.get(j).size() -1 + 1) + 0;
                System.out.println(index);
                if(conjunto.get(j).get(index) != null){
                    Float[] x = conjunto.get(j).get(index);
                    conjunto.get(j).remove(index);
                    //0 es true y 1 es false
                    if(j == 0){
                        if(productoPunto(x, w) > 0){
                            System.out.println();
                        } else {
                            add(x);
                        }
                    } else {
                        if(productoPunto(x, w) < 0){
                            System.out.println();
                        } else {
                            subtract(x);
                        }
                    }
                    if(this.t == 100){
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(this.w));
    }

    public HashMap<Integer, LinkedList<Float[]>> generarNuevoHashMap(Dataset dataset, int claseA, int claseB){
        HashMap<Integer, LinkedList<Float[]>> nuevoConjunto = new HashMap<>();
        int x = 0;
        for(int i = 0; i < dataset.getEntrenamiento().size(); i++){
            if(i == claseA || i == claseB){
                LinkedList<Float[]> nuevaLista = new LinkedList<>();
                for(int j = 0; j < dataset.getEntrenamiento().get(i).size(); j++){
                    Float[] nuevoPatron = new Float[dataset.getEntrenamiento().get(i).get(j).length+1];
                    for(int k = 0; k < dataset.getEntrenamiento().get(i).get(j).length; k++){
                        nuevoPatron[k] = dataset.getEntrenamiento().get(i).get(j)[k];
                    }
                    nuevoPatron[nuevoPatron.length-1] = -1f;
                    nuevaLista.add(nuevoPatron);
                }
                nuevoConjunto.put(x, nuevaLista);
                x++;
            }
        }
        return nuevoConjunto;
    }

    public Float productoPunto(Float[] x, Float[] w){
        float sumatoria = 0f;
        for(int i = 0; i < x.length-1; i++){
            sumatoria += x[i] * w[i];
        }
        return sumatoria;
    }

    public void add(Float[] x){
        for(int i = 0; i < this.w.length; i++){
            this.w[i] = this.w[i] + x[i];
        }
        this.t++;
    }

    public void subtract(Float[] x){
        for(int i = 0; i < this.w.length; i++){
            this.w[i] = this.w[i] - x[i];
        }
        this.t--;
    }

    public static void main(String[] args) throws IOException {
        Perceptron perceptron = new Perceptron(new Dataset("src/iris.data"));
    }
}
