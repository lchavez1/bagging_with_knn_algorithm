import java.io.IOException;
import java.util.*;

public class Bagging {

    public static void main(String[] args) throws IOException {

        String ruta = "src/iris.data";
        int sizeMuestra = 5;
        Dataset dataset = new Dataset(ruta);
        //MinimaDistancia minimaDistancia = null;
        Knn knn = null;
        int[] resultados = new int[sizeMuestra];
        float exactitud = 0;
        float contador = 0;

        for(int i = 0; i < dataset.getPrueba().size(); i++){
            for(int j = 0; j < dataset.getPrueba().get(i).size(); j++){
                for(int k = 0; k < sizeMuestra; k++){
                    dataset = new Dataset(ruta);
                    //minimaDistancia = new MinimaDistancia(dataset, dataset.getPrueba().get(i).get(j));
                    knn = new Knn(dataset, dataset.getPrueba().get(i).get(j), 4);
                    System.out.println("Patron " + knn.getClasificacion());
                    resultados[k] = knn.getClasificacion();
                }
                //System.out.println("Patron " + Arrays.toString(dataset.getPrueba().get(i).get(j)) + " = " + obtenerMasRepetido(resultados));
                contador++;
                if(obtenerMasRepetido(resultados) == i)
                    exactitud++;
                resultados = new int[sizeMuestra];
            }
        }
        System.out.println("Total de patrones evaluados = " + contador + "\nTotal de patrones correctos = " + exactitud + "\nTotal de patrones incorrectos = " + (contador-exactitud));
        System.out.println((exactitud/contador*100) + "% de exactitud");
    }

    public static int obtenerMasRepetido(int[] resultados){
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
}
