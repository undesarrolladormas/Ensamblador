package mx.uaemex.ensamblador.classes;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uaemex.ensamblador.Enums.TipoElementos;

public class Listado {

    public static ArrayList<String> quitarComentarios(ArrayList<String> lista) {
        ArrayList<String> listanueva = new ArrayList<>();
        for (String string : lista) {
            String aux = string.replaceAll(";(.*)", "");
            if(aux.length() > 0)
                listanueva.add(string.replaceAll(";(.*)", ""));         
        }
        return listanueva;
    }

    public static ArrayList<String> separarElementos(ArrayList<String> lista) {
        ArrayList<String> listanueva = new ArrayList<>();
        String patter = "STACK SEGMENT|"
                + "DATA SEGMENT|"
                + "CODE SEGMENT|"
                + "BYTE(\\s+)PTR|"
                + "WORD(\\s+)PTR|"
                + "DUP(\\s*)\\(.*\\)|"
                + "\\[.*\\]|"
                + "[^\\s,:\"']+|"
                + "[\"'](.*)[\"']";
        Pattern regex = Pattern.compile(patter, Pattern.CASE_INSENSITIVE);
        for (String string : lista) {
            Matcher regexMatcher = regex.matcher(string);
            while (regexMatcher.find()) {
                listanueva.add(regexMatcher.group());
            }
        }
        return listanueva;
    }

    public static ArrayList<Elementos> clasificarElementos(ArrayList<String> lista) {
        ArrayList<Elementos> listanueva = new ArrayList<>();
        for (String string : lista) {
            for (TipoElementos elemento : TipoElementos.values()) {
                if (string.matches(elemento.getCondicion())) {
                    listanueva.add(new Elementos(string, elemento));
                    break;
                }
            }
        }
        return listanueva;
    }
}
