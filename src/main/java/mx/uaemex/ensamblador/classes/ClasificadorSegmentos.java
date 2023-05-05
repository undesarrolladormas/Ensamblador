package mx.uaemex.ensamblador.classes;

import java.util.ArrayList;

public class ClasificadorSegmentos {
    public ArrayList<String> CS(ArrayList<String> codigoArchivo) {
        ArrayList<String> aux = new ArrayList<String>();
        int n = 0;
        while(n < codigoArchivo.size() && !codigoArchivo.get(n).equalsIgnoreCase("code segment")){
            if (!codigoArchivo.get(n).equalsIgnoreCase("code segment")) {
                codigoArchivo.remove(n);
            }else{
                n = codigoArchivo.size();
            }
        }
        n = 0;
        while(n < codigoArchivo.size() && !codigoArchivo.get(n).equalsIgnoreCase("ends")){
            if (!codigoArchivo.get(n).equalsIgnoreCase("ends")) {
                aux.add(codigoArchivo.get(n));
                n++;
            }else{
                aux.add(codigoArchivo.get(n));
                n = codigoArchivo.size();
            }
        }
        n = 0;
        int contEnd = 0;
        while(n < codigoArchivo.size() && contEnd < 1){
            if (codigoArchivo.get(n).equalsIgnoreCase("ends")) {
                aux.add(codigoArchivo.get(n));
                contEnd++;
                n++;
            }
            n++;
        }
        return aux;
    }
    
    public ArrayList<String> DS(ArrayList<String> codigoArchivo) {
        ArrayList<String> aux = new ArrayList<String>();
        int n = 0;
        while(n < codigoArchivo.size() && !codigoArchivo.get(n).equalsIgnoreCase("data segment")){
            if (!codigoArchivo.get(n).equalsIgnoreCase("data segment")) {
                codigoArchivo.remove(n);
            }else{
                n = codigoArchivo.size();
            }
        }
        n = 0;
        while(n < codigoArchivo.size() && !codigoArchivo.get(n).equalsIgnoreCase("ends")){
            if (!codigoArchivo.get(n).equalsIgnoreCase("ends")) {
                aux.add(codigoArchivo.get(n));
                n++;
            }else{
                aux.add(codigoArchivo.get(n));
                n = codigoArchivo.size();
            }
        }
        n = 0;
        int contEnd = 0;
        while(n < codigoArchivo.size() && contEnd < 1){
            if (codigoArchivo.get(n).equalsIgnoreCase("ends")) {
                aux.add(codigoArchivo.get(n));
                contEnd++;
                n++;
            }
            n++;
        }
        return aux;
    }
    
    public ArrayList<String> SS(ArrayList<String> codigoArchivo) {
        ArrayList<String> aux = new ArrayList<String>();
        int n = 0;
        while(n < codigoArchivo.size() && !codigoArchivo.get(n).equalsIgnoreCase("stack segment")){
            if (!codigoArchivo.get(n).equalsIgnoreCase("stack segment")) {
                codigoArchivo.remove(n);
            }else{
                n = codigoArchivo.size();
            }
        }
        n = 0;
        while(n < codigoArchivo.size() && !codigoArchivo.get(n).equalsIgnoreCase("ends")){
            if (!codigoArchivo.get(n).equalsIgnoreCase("ends")) {
                aux.add(codigoArchivo.get(n));
                n++;
            }else{
                aux.add(codigoArchivo.get(n));
                n = codigoArchivo.size();
            }
        }
        n = 0;
        int contEnd = 0;
        while(n < codigoArchivo.size() && contEnd < 1){
            if (codigoArchivo.get(n).equalsIgnoreCase("ends")) {
                aux.add(codigoArchivo.get(n));
                contEnd++;
                n++;
            }
            n++;
        }
        return aux;
    }
}
