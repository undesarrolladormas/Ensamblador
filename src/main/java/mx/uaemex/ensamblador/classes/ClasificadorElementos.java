package mx.uaemex.ensamblador.classes;

import java.util.ArrayList;

public class ClasificadorElementos {
	
    private String ins = "CMPSW|CWD|POPF|PUSHA|AAS|CBW|NEG|PUSH|NOT|DIV|SAR|TEST|ADC|LES|JNZ|"
            + "JZ|LOOPNZ|JGE|JNA|JNC";
    private String pseudoins = "DB|DQ|DW|DD|EQU|DATA SEGMENT|STACK SEGMENT|CODE SEGMENT|ENDS";
    private String var = "([a-zA-Z]{1,8}[0-9]{0,3})";
    private String reg = "AH|BH|CH|DH|AL|BL|CL|DL|AX|BX|CX|DX|EAX|EBX|ECX|EDX|ESI|EDI|ESP|EBP";
    private String memo = "SI|DI";
    private String num = "([0-9]+)|[D|d|.] ([a-zA-Z]{1,}) | ([a-zA-Z]{1,}) [D|d|.] ";
    private String hex = "(0(\\d|[a-fA-F])+(h|H))";
    private String bin = "(0|1)+(B|b)";
    private String oct = "[O|o] ([a-zA-Z]{1,})";
    private String pila = "(DUP|DUPY)(\\s)*\\((((\"|\')(\\w+|\\s*|\\W)+(\"|\'))|(\\d)+|(0(\\d|[a-fA-F])+(h|H))|(0|1)+(B|b))\\)";
    private String cadena = "(\"|\')([a-zA-Z]*[0-9]*){0,9}(\"|\')";
    private String decEti = "[a-zA-Z]+[0-9]*\\:";

    public ArrayList<String> sepElementos(ArrayList<String> codFiltro) { //Clase para separar los elementos del codigo filtrado
        ArrayList<String> aux = new ArrayList<String>();
        for (int i = 0; i < codFiltro.size(); i++) {
            String linea = codFiltro.get(i);
            linea = linea.toUpperCase().replaceAll("PTR\\[", "PTR \\[");
            if (linea.matches(pseudoins)) {//Agregar la línea dirctamente si tiene esas palabras
                aux.add(linea);
                linea = "";
            } else {
                if (linea.toLowerCase().contains("word ptr") || linea.toLowerCase().contains("byte ptr")) {
                    String[] parts = linea.split(" ");
                    aux.add(parts[0]);
                    try {
                        aux.add(parts[1] + " " + parts[2] + " " + parts[3]);
                    } catch (Exception e) {
                        System.out.println("Error en la linea: " + linea);
                    }

                } else {
                    String[] parts = linea.split(" ");
                    for (int j = 0; j < parts.length; j++) {
                        aux.add(parts[j]);
                    }
                }
            }
        }
        return aux;
    }

    public String clasElementoDS(String cad) { //Clase para clasificar los elementos de Data Segment
        cad = cad.toUpperCase();
        if (cad.matches(pseudoins)) {
            return "Pseudoinstrucción";
        } else if (cad.matches(pila)) {
            return "Pseudoinstrucción con constante";
        }
        else if (cad.matches(num)) {
            return "Constante: decimal";
        } else if (cad.matches(hex)) {
            return "Constante: hexadecimal";
        } else if (cad.matches(oct)) {
            return "Constante: octal";
        } else if (cad.matches(bin)) {
            return "Constante: binario";
        } else if (cad.matches(var)) {
            return "Identificador";
        } else if (cad.matches(cadena)) {
            return "Elemento de tipo cadena";
        }
        else return "Elemento no identificado";
    }

    public String clasElementoSS(String cad) { //Clase para clasificar los elementos de Stack Segment
        cad = cad.toUpperCase();
        if (cad.matches(pseudoins)) {
            return "Pseudoinstrucción";
        } else if (cad.matches(num)) {
            return "Constante: decimal";
        } else if (cad.matches(hex)) {
            return "Constante: hexadecimal";
        } else if (cad.matches(oct)) {
            return "Constante: octal";
        } else if (cad.matches(bin)) {
            return "Constante: binario";
        } else if (cad.matches(pila)) {
            return "Pseudoinstrucción con constante";
        } else if (cad.matches(cadena)) {
            return "Elemento de tipo cadena";
        }
        return "Elemento no identificado";
    }

    public String clasElementoCS(String cad, ArrayList<String> simbolos) { //Clase para clasificar los elementos de Code Segment
        cad = cad.toUpperCase();
        if (cad.matches(ins)) {
            return "Instrucción";
        } else if (cad.toLowerCase().matches(pseudoins)) {
            return "Pseudoinstrucción";
        } else if (cad.matches(var)) {
            return "Identificador";
        } else if (cad.matches(reg)) {
            return "Registro de propósito general";
        } else if (cad.matches(memo)) {
            return "Constante: cadena";
        } else if (cad.matches(num)) {
            return "Constante: decimal";
        } else if (cad.matches(hex)) {
            return "Constante: hexadecimal";
        } else if (cad.matches(oct)) {
            return "Constante: octal";
        } else if (cad.matches(bin)) {
            return "Constante: binario";
        } else if (cad.matches(pila)) {
            return "Pseudoinstrucción con constante";
        } else if (cad.matches(decEti)) {
            return "Etiqueta";
        } else if (simbolos != null) {
            for (String simbolo : simbolos) {
                if (cad.equalsIgnoreCase(simbolo)) {
                    return "Simbolo";
                }
            }
        }
        return "Elemento no identificado";
    }
    
}
