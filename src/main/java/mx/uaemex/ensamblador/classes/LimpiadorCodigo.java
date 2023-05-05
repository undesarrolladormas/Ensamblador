package mx.uaemex.ensamblador.classes;

import java.util.ArrayList;
import java.util.Collections;

public class LimpiadorCodigo {

    public ArrayList<String> filtrarArchivo(ArrayList<String> codArchivo) { //Quitar espacios al principio o final de una linea y las lineas en blanco
        ArrayList<String> aux = new ArrayList<String>();
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> comas = new ArrayList<String>();
        int cont = 0;
        String cad;
        while (cont < codArchivo.size()) {
            cad = codArchivo.get(cont);
            cad = cad.replaceAll(",", " ");
            comas.add(cad);
            cont++;
        }
        cont=0;
        codArchivo = comas;
        cad="";
        while (cont < codArchivo.size()) { //Elimina comentarios
            cad = codArchivo.get(cont).replaceAll(";(.)*", "");
            cad = cad.replaceAll("\\s+", " ");
            cad = cad.replaceAll("\\s+\\(", "\\(");
            cad = cad.replaceAll("\\s*\\+\\s*", "\\+");
            lista.add(cont, cad);
            cont += 1;
        }

        cont = 0;
        while (cont < lista.size()) {
            if (!lista.get(cont).equals("")) {
                aux.add(lista.get(cont).trim()); //El método trim elimina espacios al principio o final de una linea
            }
            cont++;
        }
        aux.removeAll(Collections.singleton("")); //Elimina cadenas vacias
        return aux;
    }

    public ArrayList<String> filtro(ArrayList<String> CA) { //Función para quitar espacios en blanco y comentarios
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> aux = new ArrayList<String>();
        int cont = 0;

        while (cont < CA.size()) { //Elimina comentarios
            lista.add(cont, CA.get(cont).replaceAll(";(.)*", ""));
            cont += 1;
        }
        cont = 0;
        while (cont < lista.size()) { //Elimina espacios al final y al inicio de la cadena
            aux.add(cont, lista.get(cont).trim());
            cont += 1;
        }
        aux.removeAll(Collections.singleton("")); //Elimina cadenas vacias
        return aux;
    }
}
