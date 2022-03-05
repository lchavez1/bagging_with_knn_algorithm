import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.LinkedList;

public class Clases {

    private Double desviacion;
    private Double media;
    private Dataset dataset;
    private LinkedList<Clases> clases;

    public Clases(Dataset dataset) {
        this.dataset = dataset;
        this.desviacion = 0.0;
        this.media = 0.0;
        this.clases = new LinkedList<>();
    }

    public Double obtenerDesviaciones(){
        return 0.0;
    }
    public Double obtenerMedias(){
        return 0.0;
    }

    /*public static void calcularDesviaciones(Clase clase, LinkedList<U> list){
        LinkedList<Double> doubleLinkedList = new LinkedList<>();
        double sumatoria = 0;
        int n = 0;
        for(int i = 0; i < list.get(i).getCaracteristicas().size(); i++){
            n = 0;
            sumatoria = 0;
            for(int j = 0; j < list.size(); j++){
                if(clase.getTag().equals(list.get(j).getTag())){
                    sumatoria += Math.pow(list.get(j).getCaracteristicas().get(i)-clase.getMedia().get(i), 2);
                    n++;
                }
            }
            sumatoria = sumatoria / n;
            sumatoria =  Math.sqrt(sumatoria);
            doubleLinkedList.add(sumatoria);
        }
        clase.setDesviacion(doubleLinkedList);
    }*/

    /*public void calcularPromedios(){
        LinkedList<Double> doubleLinkedList = new LinkedList<>();
        double sumatoria = 0;
        int n = 0;
        for(int i = 0; i < this.dataset.getEtiquetas().size(); i++){
            n = 0;
            sumatoria = 0;
            for(int j = 0; j < list.size(); j++){
                if(clase.getTag().equals(list.get(j).getTag())){
                    sumatoria += list.get(j).getCaracteristicas().get(i);
                    n++;
                }
            }
            doubleLinkedList.add(sumatoria/n);
        }
        clase.setMedia(doubleLinkedList);
    }*/




}
