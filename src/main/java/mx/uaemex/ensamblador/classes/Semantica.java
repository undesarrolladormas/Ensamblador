package mx.uaemex.ensamblador.classes;

import java.util.ArrayList;

public class Semantica {
    private final String instan = "[0-9]+";
    private final String pseudoins = "db|dq|dw|dd|equ|data segment|stack segment|code segment|ends";
    private final String var = "([a-zA-Z]{1,8}[0-9]{0,3})";
    private final String reg = "AH|BH|CH|DH|AL|BL|CL|DL|AX|BX|CX|DX|EAX|EBX|ECX|EDX|ESI|EDI|ESP|EBP|SI|DI";
    private final String hex = "(0(\\d|[a-fA-F])+(h|H))";
    private final String bin = "(0|1)+(B|b)";
    private final String oct = "[O|o] ([a-zA-Z]{1,})";
    private final String num = "([0-9]+)|[D|d|.] ([a-zA-Z]{1,}) | ([a-zA-Z]{1,}) [D|d|.] ";
    private final String pila2 = "(dup|dupy)";
    private final String cadena = "(\"|\')([a-zA-Z]*[0-9]*){0,9}(\"|\')";
    private final String decEti = "[a-zA-Z]+[0-9]*";

    public ArrayList<String> Simbolos;

    public String semanticaDS(String linea) {
        if (linea.matches(pseudoins)) { // Comparar la linea completa con las pseudoinstrucciones
            return "Correcto";
        } else { // Si no es una pseudoinstrucción, cortará la linea cada que encuentre un
                 // espacio
            try {
                linea = linea.replaceAll("\\(", " \\(");
            } catch (Exception e) {

            }

            String[] aux1 = linea.split(" ");
            if (aux1.length == 6) {
                linea = linea.replaceAll("\\(' '\\)", "\\(''\\)");
            }
            String[] parts = linea.split(" ");
            switch (parts.length) { // Comparará cada dependiendo el número de elementos en la linea separada
                case 1:
                    return "Incorrecto: Se esperaba un Pseudoinstrucción";
                case 2:
                    return "Incorrecto: Mala declaración de variable";
                case 3:
                    if (parts[0].matches(var) && parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])) {
                        return "Correcto";
                    }
                    if (!parts[0].matches(var) && parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de variable";
                    }
                    if (parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de tipo de dato ";
                    }
                    if (parts[0].matches(var) && parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de valor";
                    }
                    if (parts[0].matches(var) && parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Valor fuera de rango";
                    }
                    //////////////////////////
                    if (!parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de variable, tipo de dato, valor y fuera de rango ";
                    }
                    if (!parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de variable, tipo de dato y fuera de rango";
                    }
                    if (!parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de variable, tipo de dato y fuera de rango";
                    }
                    if (!parts[0].matches(var) && parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de variable, valor y fuera de rango";
                    }
                    if (parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración tipo de dato, valor y fuera de rango";
                    }
                    if (parts[0].matches(var) && parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de valor";
                    }
                    if (parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración tipo de dato y fuera de rango";
                    }
                    if (!parts[0].matches(var) && parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de variable y fuera de rango";
                    }
                    if (parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración tipo de dato y valor";
                    }
                    if (!parts[0].matches(var) && parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de variable y valor";
                    }
                    if (!parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración de variable y tipo de dato";
                    }
                    if (!parts[0].matches(var) && !parts[1].matches(pseudoins)
                            && !parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && !VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: Mala declaración tipo de dato, valor y fuera de rango";
                    }

                    break;
                case 5:
                    if (parts[0].matches(var) && parts[1].matches(pseudoins)
                            && parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                            && VerificaPseu(parts[1], parts[2])
                            && parts[3].matches(pila2)
                            && VerificaDup(parts[1], parts[4])) {
                        return "Correcto";
                    }
                    if (!parts[0].matches(var)) {
                        return "Incorrecto: mala declaración de variable";
                    }
                    if (!parts[1].matches(pseudoins)) {
                        return "Incorrecto: mala declaración de variable";
                    }
                    if (!parts[2].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)) {
                        return "Incorrecto: mala declaración de tipo de dato";
                    }
                    if (!VerificaPseu(parts[1], parts[2])) {
                        return "Incorrecto: valor del dato fuera de rango";
                    }
                    if (!parts[3].matches(pila2)) {
                        return "Incorrecto: mala declaración de dup o dupy";
                    }
                    if (!VerificaDup(parts[1], parts[4])) {
                        return "Incorrecto: valor del dato fuera de rango";
                    }
                    if (!VerificaDup(parts[1], parts[4])) {
                        return "Incorrecto: mala declaración del valor del dup o dupy";
                    }
                    break;
                default:
                    return "Incorrecto: Mala declaración de variable";
            }
        }
        return "No se ha determinado el error";
    }

    public String semanticaSG(String linea) {
        String cad = "Incorrecto: Mala declaración de ";
        linea = linea.replaceAll("\\(", " \\(");
        String[] parts = linea.split(" ");
        switch (parts.length) {
            case 1:
                return "Correcto";
            case 2:
                return "Correcto";
            case 4:
                if (parts[0].matches(pseudoins)
                        && parts[1].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)
                        && VerificaPseu(parts[0], parts[1])
                        && parts[2].matches(pila2)
                        && VerificaDup(parts[0], parts[3])) {
                    return cad = "Correcto";
                }
                if (!parts[0].matches(pseudoins)) {
                    cad += "Tipo de dato-";
                }
                if (!parts[1].matches(num + "|" + hex + "|" + bin + "|" + oct + "|" + cadena)) {
                    cad += "Tipo de valor-";
                }
                if (!VerificaPseu(parts[0], parts[1])) {
                    cad += "Valor fuera de rango-";
                }
                if (!parts[2].matches(pila2)) {
                    cad += "Definición de dup o dupy-";
                }
                if (!VerificaDup(parts[0], parts[3])) {
                    cad += "Valor del dup o dupy-";
                }
                break;

            default:
                cad += "Incorrecto problemas con la cantidad de datos";
                break;
        }

        return cad;
    }

    public String semanticaCS(String linea, String accMem, ArrayList<String> Simb, ArrayList<String> Etiq,
            String Cons) {
        String cad = "Incorrecto: ";
        linea = linea.toUpperCase();
        Cons = Cons.toUpperCase();
        linea = linea.replaceAll("PTR\\[", "PTR \\[");
        accMem = accMem.toUpperCase();
        String[] parts = linea.split(" ");
        String aux = "";
        switch (parts[0]) {
            // Instrucciones sin operando
            case "CMPSW":
            case "CWD":
            case "POPF":
            case "PUSHA":
            case "AAS":
            case "CBW":
                if (parts.length == 1) {
                    cad = "Correcto";
                } else {
                    cad = "La instrucción no lleva operandos";
                }
                break;
            // Instrucciones de salto
            case "JNZ":
            case "JZ":
            case "JGE":
            case "JNA":
            case "LOOPNZ":
                if (parts.length == 2 && Etiq.size() > 0) {
                    for (int i = 0; i < Etiq.size(); i++) {
                        if (Etiq.get(i).equalsIgnoreCase(parts[1])) {
                            cad = "Correcto";
                            break;
                        } else {
                            cad = "Incorrecto: No se ha definido la etiqueta";
                        }
                    }
                } else {
                    cad += "No se ha definido la etiqueta";
                }
                break;

                
            // Instrucciones un operando
            case "NOT":
                if (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE")) {
                    if (parts[2].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 3; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                            return cad = "Correcto";
                        } else {
                            cad += "Se esperaba un acceso a memoria o registro";
                        }
                    }
                }
                if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                    return cad = "Correcto";
                }
                if (parts[1].matches(reg)) {
                    return cad = "Correcto";
                }
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].equalsIgnoreCase(Simb.get(i))) {
                        cad = "Correcto";
                        break;
                    } else {
                        cad = "Incorrecto: Se esperaba un acceso a memoria o registro";
                    }
                }
                break;

            // Instrucciones dos operandos
            case "ADC":
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts.length == 3 && (parts[1].matches(reg) && parts[2].equalsIgnoreCase(Simb.get(i)))) {
                        return cad = "Correcto";
                    }
                    if (parts.length == 3 && (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].matches(reg))) {
                        return cad = "Correcto";
                    }
                    if (parts.length == 3 && (parts[1].equalsIgnoreCase(Simb.get(i))
                            && parts[2].matches(instan + "|" + hex + "|" + bin + "|" + Cons))) {
                        return cad = "Correcto";
                    }
                    if (parts.length == 5 && (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE"))
                            && parts[parts.length - 1].equalsIgnoreCase(Simb.get(i))) {
                        return cad += "No se acepta memora , memoria";
                    }
                }
                if (parts.length >= 4 && (parts[1].matches(reg) && parts[2].equalsIgnoreCase("WORD")
                        || parts[2].equalsIgnoreCase("BYTE"))) {
                    if (parts[3].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 3; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                            cad = "Correcto";
                        } else {
                            cad += "Se esperaba un acceso a memoria";
                        }
                    }
                } else if (parts.length >= 4 && (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE"))
                        && parts[parts.length - 1].matches(reg)) {
                    if (parts[2].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 3; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                            cad = "Correcto";
                        } else {
                            cad += "Se esperaba un acceso a memoria";
                        }
                    }
                } else if (parts.length >= 4 && (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE"))
                        && parts[parts.length - 1].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) {
                    if (parts[2].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 3; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                            cad = "Correcto";
                        } else {
                            cad += "Se esperaba un acceso a memoria";
                        }
                    }

                }
                for (int i = 3; i < parts.length; i++) {
                    aux += " " + parts[i];
                }
                if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                    return cad = "Correcto";
                } else if (parts.length == 3 && (parts[1].matches(reg) && parts[2].matches(reg))) {
                    return cad = "Correcto";
                } else if (parts.length == 3
                        && (parts[1].matches(reg) && parts[2].matches(instan + "|" + hex + "|" + bin + "|" + Cons))) {
                    return cad = "Correcto";
                } else if (parts.length >= 3 && parts.length < 5) {
                    cad += "Error, la instrucción solo lleva 2 operandos";
                } else if (parts.length < 3) {
                    cad += "Error, a la instrucción le faltan operandos";
                }
                break;
            case "LES":
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts.length == 3 && (parts[1].matches(reg) && parts[2].equalsIgnoreCase(Simb.get(i)))) {
                        return cad = "Correcto";
                    }
                }
                if (parts.length >= 4 && (parts[1].matches(reg) && parts[2].equalsIgnoreCase("WORD")
                        || parts[2].equalsIgnoreCase("BYTE"))) {
                    if (parts[3].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 3; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        if (!Desplazamiento(aux, Cons, Simb).equalsIgnoreCase("Null")) {
                            return cad = "Correcto";
                        } else {
                            return cad += "Mala declaración de Memoria";
                        }
                    }
                }
                if (parts[1].matches(reg) && (parts[2].contains("[") && parts[2].contains("]"))) {
                    return cad = "Correcto";
                } else {
                    return cad += "Se esperaba un registro y memoria";
                }
            default:
                for (int i = 0; i < Etiq.size(); i++) {
                    if (parts[0].equalsIgnoreCase(Etiq.get(i) + ":")) {
                        return "Correcto";
                    }
                }
                if (parts[0].equalsIgnoreCase("ENDS")
                        || parts[0].equalsIgnoreCase("CODE") && parts[1].equalsIgnoreCase("SEGMENT")) {
                    cad = "Correcto";
                } else {
                    cad += "Instrucción no reconocida";
                    break;
                }
        }
        return cad;
    }

    // Verificar pseudo
    private boolean VerificaPseu(String cadena1, String cadena2) {
        switch (cadena1) {
            case "db":
                if (cadena2.charAt(cadena2.length() - 1) == 'h' || cadena2.charAt(cadena2.length() - 1) == 'H') {
                    int numHex = convierteHaD(cadena2);
                    if (!(numHex >= 0 && numHex <= 255)) {
                        // error = "Incorrecto: Data Byte (db) solo acepta valores de 0 a 255";
                        return false;
                    } else {
                        return true;
                    }
                } else if (cadena2.charAt(cadena2.length() - 1) == 'b' || cadena2.charAt(cadena2.length() - 1) == 'B') {
                    int numbin = convierteBaD(cadena2);
                    if (!(numbin >= 0 && numbin <= 255)) {
                        // error = "Incorrecto: Data Byte (db) solo acepta valores de 0 a 255";
                        return false;
                    } else {
                        return true;
                    }
                } else if (cadena2.matches(cadena)) {
                    return true;
                } else {
                    try {
                        if ((Integer.parseInt(cadena2) >= 0 && Integer.parseInt(cadena2) <= 255)) {
                            // error = "Incorrecto: Data Byte (db) solo acepta valores de 0 a 255";
                            return true;
                        } else {
                            return false;
                        }
                    } catch (Exception e) {
                        break;
                    }
                }
                // break;
            case "dw":
                if (cadena2.charAt(cadena2.length() - 1) == 'h' || cadena2.charAt(cadena2.length() - 1) == 'H') {
                    int numHex = convierteHaD(cadena2);
                    if (!(numHex >= 0 && numHex <= 65535)) {
                        // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65,535";
                        return false;
                    } else {
                        return true;
                    }
                } else if (cadena2.charAt(cadena2.length() - 1) == 'b' || cadena2.charAt(cadena2.length() - 1) == 'B') {
                    int numbin = convierteBaD(cadena2);
                    if (!(numbin >= 0 && numbin <= 65535)) {
                        // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65,535";
                        return false;
                    } else {
                        return true;
                    }
                } else if (cadena2.matches(cadena)) {
                    return true;
                } else {
                    try {
                        if (!(Integer.parseInt(cadena2) >= 0 && Integer.parseInt(cadena2) <= 65535)) {
                            // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65535";
                            return false;
                        } else {
                            return true;
                        }
                    } catch (Exception e) {
                        break;
                    }
                }
                // break;

            case "equ":
                try {
                    if (cadena2.charAt(cadena2.length() - 1) == 'h' || cadena2.charAt(cadena2.length() - 1) == 'H') {
                        int numHex = convierteHaD(cadena2);
                        if (!(numHex >= 0 && numHex <= 65535)) {
                            // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65,535";
                            return false;
                        } else {
                            return true;
                        }
                    } else if (cadena2.charAt(cadena2.length() - 1) == 'b'
                            || cadena2.charAt(cadena2.length() - 1) == 'B') {
                        int numbin = convierteBaD(cadena2);
                        if (!(numbin >= 0 && numbin <= 65535)) {
                            // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65,535";
                            return false;
                        } else {
                            return true;
                        }
                    } else if (cadena2.matches(cadena)) {
                        return true;
                    } else {
                        try {
                            if (!(Integer.parseInt(cadena2) >= 0 && Integer.parseInt(cadena2) <= 65535)) {
                                // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65535";
                                return false;
                            } else {
                                return true;
                            }
                        } catch (Exception e) {
                            break;
                        }

                    }
                } catch (Exception e) {
                    break;
                }
                // break;
        }
        return false;
    }

    // Verificar dub
    private boolean VerificaDup(String cadena1, String cadena2) {
        cadena2 = cadena2.replaceAll("\\(", "");
        cadena2 = cadena2.replaceAll("\\)", "");
        switch (cadena1) {
            case "db":
                if (cadena2.equals("'$'")) {
                    return true;
                }
                if (cadena2.isEmpty()) {
                    return false;
                }
                if (cadena2.equals(" ")) {
                    return false;
                }
                if (cadena2.equals("?")) {
                    return true;
                }
                if (cadena2.charAt(cadena2.length() - 1) == 'h' || cadena2.charAt(cadena2.length() - 1) == 'H') {
                    int numHex = convierteHaD(cadena2);
                    if (!(numHex >= 0 && numHex <= 255)) {
                        // error = "Incorrecto: Data Byte (db) solo acepta valores de 0 a 255";
                        return false;
                    } else {
                        return true;
                    }
                } else if (cadena2.charAt(cadena2.length() - 1) == 'b' || cadena2.charAt(cadena2.length() - 1) == 'B') {
                    int numbin = convierteBaD(cadena2);
                    if (!(numbin >= 0 && numbin <= 255)) {
                        // error = "Incorrecto: Data Byte (db) solo acepta valores de 0 a 255";
                        return false;
                    } else {
                        return true;
                    }
                } else if (cadena2.matches(cadena)) {
                    return true;
                } else {
                    try {
                        if (!(Integer.parseInt(cadena2) >= 0 && Integer.parseInt(cadena2) <= 255)) {
                            // error = "Incorrecto: Data Byte (db) solo acepta valores de 0 a 255";
                            return false;
                        } else {
                            return true;
                        }
                    } catch (Exception e) {
                        break;
                    }
                }
                // break;
            case "dw":
                if (cadena2.equals("'$'")) {
                    return true;
                }
                if (cadena2.equals("")) {
                    return false;
                }
                if (cadena2.equals(" ")) {
                    return false;
                }
                if (cadena2.equals("?")) {
                    return true;
                }
                if (cadena2.charAt(cadena2.length() - 1) == 'h' || cadena2.charAt(cadena2.length() - 1) == 'H') {
                    int numHex = convierteHaD(cadena2);
                    if (!(numHex >= 0 && numHex <= 65535)) {
                        // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65,535";
                        return false;
                    } else {
                        return true;
                    }
                } else if (cadena2.charAt(cadena2.length() - 1) == 'b' || cadena2.charAt(cadena2.length() - 1) == 'B') {
                    int numbin = convierteBaD(cadena2);
                    if (!(numbin >= 0 && numbin <= 65535)) {
                        // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65,535";
                        return false;
                    } else {
                        return true;
                    }
                } else if (cadena2.matches(cadena)) {
                    return true;
                } else {
                    try {
                        if (!(Integer.parseInt(cadena2) >= 0 && Integer.parseInt(cadena2) <= 65535)) {
                            // error = "Incorrecto: Data Word (dw) solo acepta valores de 0 a 65535";
                            return false;
                        } else {
                            return true;
                        }
                    } catch (Exception e) {
                        break;
                    }

                }
                // break;
        }
        return false;
    }

    // Convertir Binario a Decimal, la funcion regresa un entero
    private int convierteBaD(String cN) {// Cadena del numero que se cambiara
        int numD = 0;// Numero Decimal
        StringBuilder sb = new StringBuilder(cN);
        cN = sb.reverse().toString();// Invertir la cadena
        for (int i = 1; i < cN.length(); i++) {// Recorrer la cadena
            String n = "" + cN.charAt(i);
            numD += Integer.parseInt(n) * ((int) (Math.pow(2, i - 1)));// Sumar valores de los digitos
        }
        return numD;// Valor decimal
    }

    // Convertir Hexadecimal a decimal, se regresa un entero
    public int convierteHaD(String cN) {
        int numD = 0;
        StringBuilder sb = new StringBuilder(cN);
        cN = sb.reverse().toString().toUpperCase();// Invertir la cadena
        int nA;
        for (int i = 1; i < cN.length(); i++) {// Recorrer la cadena caracter por caracter
            String n = "" + cN.charAt(i);
            switch (n) {
                case "A":
                    nA = 10;
                    break;
                case "B":
                    nA = 11;
                    break;
                case "C":
                    nA = 12;
                    break;
                case "D":
                    nA = 13;
                    break;
                case "E":
                    nA = 14;
                    break;
                case "F":
                    nA = 15;
                    break;
                default:
                    nA = Integer.parseInt(n);
                    break;
            }
            numD += nA * ((int) (Math.pow(16, i - 1)));// Sumar valores de los digitos
        }
        return numD;// valor decimal
    }

    public Integer contarBytesDS(String tipo, String valor) {
        ArrayList<Character> caracter = new ArrayList<Character>();
        if (tipo.equalsIgnoreCase("db")) {
            if (valor.matches(cadena)) {
                for (int i = 0; i < valor.length(); i++) {
                    caracter.add(valor.charAt(i));
                }
                return caracter.size() - 2;
            } else if (valor.matches(hex)) {
                return 1;
            } else if (valor.matches(num)) {
                return 1;
            } else if (valor.matches(bin)) {
                return 1;
            }
        } else if (tipo.equalsIgnoreCase("dw")) {
            if (valor.matches(cadena)) {
                for (int i = 0; i < cadena.length(); i++) {
                    caracter.add(cadena.charAt(i));
                }
                return ((caracter.size() - 2) * 2);
            } else if (valor.matches(hex)) {
                return 2;
            } else if (valor.matches(num)) {
                return 2;
            } else if (valor.matches(bin)) {
                return 2;
            }
        }
        return 0;
    }

    public Integer contarBytesDSE(String tipo, String valor) {
        if (tipo.equalsIgnoreCase("db")) {
            if (valor.matches(num)) {
                return Integer.parseInt(valor);
            } else if (valor.matches(hex)) {
                return convierteHaD(valor);
            } else if (valor.matches(bin)) {
                return convierteBaD(valor);
            }
        } else if (tipo.equalsIgnoreCase("dw")) {
            if (valor.matches(num)) {
                return Integer.parseInt(valor) * 2;
            } else if (valor.matches(hex)) {
                return convierteHaD(valor) * 2;
            } else if (valor.matches(bin)) {
                return convierteBaD(valor) * 2;
            }
        }
        return 0;
    }

    public Integer contarBytesSS(String tipo, String valor) {
        if (tipo.equalsIgnoreCase("db")) {
            if (valor.matches(num)) {
                return Integer.parseInt(valor);
            }
        } else if (tipo.equalsIgnoreCase("dw")) {
            if (valor.matches(num)) {
                return Integer.parseInt(valor) * 2;
            }
        }
        return 0;
    }

    public int ContadorMemDS(String linea, String clasificacion) {
        int contI = 0;
        if (clasificacion.equals("Correcto")) {
            try {
                linea = linea.replaceAll("\\(", " \\(");
            } catch (Exception e) {

            }
            String[] aux1 = linea.split(" ");
            if (aux1.length == 6) {
                linea = linea.replaceAll("\\(' '\\)", "\\(''\\)");
            }
            String[] parts = linea.split(" ");

            if (parts.length == 3) {
                contI = contarBytesDS(parts[1], parts[2]);
                return contI;
            } else if (parts.length > 3) {
                if (parts.length == 5) {
                    contI = contarBytesDSE(parts[1], parts[2]);
                    return contI;
                } else if (parts[1].equalsIgnoreCase("db") || parts[1].equalsIgnoreCase("dw")) {
                    contI = contarBytesDSE(parts[1], parts[2]);
                    return contI;
                }
            } else if (parts[1].equalsIgnoreCase("equ")) {
                return contI;
            }
        }
        return contI;
    }

    public int ContadorMemSS(String linea, String clasificacion) {
        int contI = 0;
        if (clasificacion.equals("Correcto")) {
            String[] parts = linea.split(" ");
            if (parts[0].equalsIgnoreCase("db")) {
                contI = contarBytesSS(parts[0], parts[1]);
                return contI;
            } else if (parts[0].equalsIgnoreCase("dw")) {
                contI = contarBytesSS(parts[0], parts[1]);
                return contI;
            }
        }
        return contI;
    }

    public int ContadorMemCS(String linea, String clasificacion, ArrayList<String> Etiq, ArrayList<String> Simb,
            String Cons, String accMem) {
        int contI = 0;
        String parts[] = linea.split(" ");
        switch (parts[0].toUpperCase()) {
            case "AAA":
                if (parts.length == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "CLC":
                if (parts.length == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "CMPSW":
                if (parts.length == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "MOVSB":
                if (parts.length == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "INTO":
                if (parts.length == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "PUSHA":
                if (parts.length == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "STD":
                if (parts.length == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "AAD":
                if (parts.length == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "DEC":
                if (parts[1].matches(reg)) {
                    return 2;
                } else {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 2;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d16") || desplazamiento.equalsIgnoreCase("Variable")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                }
            case "IDIV":
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].equalsIgnoreCase(Simb.get(i))) {
                        return 4;
                    }
                }
                if (parts[1].matches(reg)) {
                    return 2;
                } else {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 2;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d16") | desplazamiento.equalsIgnoreCase("Variable")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                }
            case "INT":
                if (parts[1].matches(bin) && (convierteBaD(parts[1]) >= 0 && convierteBaD(parts[1]) <= 128)) {
                    return 2;
                }
                if (parts[1].matches(hex) && (convierteHaD(parts[1]) >= 0 && convierteHaD(parts[1]) <= 128)) {
                    return 2;
                }
                if (parts[1].matches(num) && (Integer.parseInt(parts[1]) >= 0 && Integer.parseInt(parts[1]) <= 128)) {
                    return 2;
                }
                if (parts[1].matches(Cons)) {
                    return 4;
                }
                break;
            case "NOT":
                if (parts[1].matches(reg)) {
                    return 2;
                } else {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 2;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d16") | desplazamiento.equalsIgnoreCase("Variable")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                }
            case "ADC":
                if (parts[1].matches(reg) && parts[2].matches(reg)) {
                    return 2;
                } else if (parts[1].matches(reg) && parts[parts.length - 1].matches(instan)) {
                    if (Integer.parseInt(parts[parts.length - 1]) >= 0
                            && Integer.parseInt(parts[parts.length - 1]) <= 128) {
                        return 3;
                    } else if (Integer.parseInt(parts[parts.length - 1]) >= 129
                            && Integer.parseInt(parts[parts.length - 1]) <= 65535) {
                        return 4;
                    }

                } else if (parts[1].matches(reg) && parts[parts.length - 1].matches(hex)) {
                    if (convierteHaD(parts[parts.length - 1]) >= 0 && convierteHaD(parts[parts.length - 1]) <= 128) {
                        return 3;
                    } else if (convierteHaD(parts[parts.length - 1]) >= 129
                            && convierteHaD(parts[parts.length - 1]) <= 65535) {
                        return 4;
                    }

                } else if (parts[1].matches(reg) && parts[parts.length - 1].matches(bin)) {
                    if (convierteBaD(parts[parts.length - 1]) >= 0 && convierteBaD(parts[parts.length - 1]) <= 128) {
                        return 3;
                    } else if (convierteBaD(parts[parts.length - 1]) >= 129
                            && convierteBaD(parts[parts.length - 1]) <= 65535) {
                        return 4;
                    }
                } else if (parts[1].matches(reg) && parts[parts.length - 1].matches(Cons)) {
                    return 4;
                } else if ((parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE")
                        || parts[1].contains("["))
                        && parts[parts.length - 1].matches(reg)) {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 2;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d16")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                } else if (parts[1].matches(reg) && ((parts[2].equalsIgnoreCase("WORD"))
                        || parts[2].equalsIgnoreCase("BYTE") || parts[2].contains("["))) {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 2;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d16")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                } else if (parts[parts.length - 1].matches(Cons)
                        && (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE"))
                        || parts[1].contains("[")) {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 5;
                    } else if (desplazamiento.equalsIgnoreCase("d16")) {
                        return 6;
                    }
                } else if ((parts[parts.length - 1].matches(instan + "|" + hex + "|" + bin))
                        && (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE"))
                        || parts[1].contains("[")) {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind") && (Integer.parseInt(parts[parts.length - 1]) >= 0
                            && Integer.parseInt(parts[parts.length - 1]) <= 128)) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d8") && (Integer.parseInt(parts[parts.length - 1]) >= 0
                            && Integer.parseInt(parts[parts.length - 1]) <= 128)) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("d16") && (Integer.parseInt(parts[parts.length - 1]) >= 0
                            && Integer.parseInt(parts[parts.length - 1]) <= 128)) {
                        return 5;
                    } else if (desplazamiento.equalsIgnoreCase("Sind")
                            && (Integer.parseInt(parts[parts.length - 1]) >= 129
                                    && Integer.parseInt(parts[parts.length - 1]) <= 65535)) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("d8")
                            && (Integer.parseInt(parts[parts.length - 1]) >= 129
                                    && Integer.parseInt(parts[parts.length - 1]) <= 65535)) {
                        return 5;
                    } else if (desplazamiento.equalsIgnoreCase("d16")
                            && (Integer.parseInt(parts[parts.length - 1]) >= 129
                                    && Integer.parseInt(parts[parts.length - 1]) <= 65535)) {
                        return 6;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                }
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].matches(reg) && parts[2].equalsIgnoreCase(Simb.get(i))) {
                        return 4;
                    } else if (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].matches(reg)) {
                        return 4;
                    } else if (parts[1].equalsIgnoreCase(Simb.get(i))
                            && parts[2].matches(instan + "|" + bin + "|" + hex)) {
                        if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 128) {
                            return 5;
                        } else if (Integer.parseInt(parts[2]) >= 129 && Integer.parseInt(parts[2]) <= 65535) {
                            return 6;
                        }
                    } else if (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].matches(Cons)) {
                        return 6;
                    }
                }
                break;
            case "CMP":
                if (parts[1].matches(reg) && parts[2].matches(reg)) {
                    return 2;
                } else if (parts[1].matches(reg) && parts[parts.length - 1].matches(instan)) {
                    if (Integer.parseInt(parts[parts.length - 1]) >= 0
                            && Integer.parseInt(parts[parts.length - 1]) <= 128) {
                        return 3;
                    } else if (Integer.parseInt(parts[parts.length - 1]) >= 129
                            && Integer.parseInt(parts[parts.length - 1]) <= 65535) {
                        return 4;
                    }

                } else if (parts[1].matches(reg) && parts[parts.length - 1].matches(hex)) {
                    if (convierteHaD(parts[parts.length - 1]) >= 0 && convierteHaD(parts[parts.length - 1]) <= 128) {
                        return 3;
                    } else if (convierteHaD(parts[parts.length - 1]) >= 129
                            && convierteHaD(parts[parts.length - 1]) <= 65535) {
                        return 4;
                    }

                } else if (parts[1].matches(reg) && parts[parts.length - 1].matches(bin)) {
                    if (convierteBaD(parts[parts.length - 1]) >= 0 && convierteBaD(parts[parts.length - 1]) <= 128) {
                        return 3;
                    } else if (convierteBaD(parts[parts.length - 1]) >= 129
                            && convierteBaD(parts[parts.length - 1]) <= 65535) {
                        return 4;
                    }
                } else if (parts[1].matches(reg) && parts[parts.length - 1].matches(Cons)) {
                    return 4;
                } else if ((parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE")
                        || parts[1].contains("["))
                        && parts[parts.length - 1].matches(reg)) {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 2;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d16")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                } else if (parts[1].matches(reg) && ((parts[2].equalsIgnoreCase("WORD"))
                        || parts[2].equalsIgnoreCase("BYTE") || parts[2].contains("["))) {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 2;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d16")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                } else if (parts[parts.length - 1].matches(Cons)
                        && (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE"))
                        || parts[1].contains("[")) {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind")) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("d8")) {
                        return 5;
                    } else if (desplazamiento.equalsIgnoreCase("d16")) {
                        return 6;
                    }
                } else if ((parts[parts.length - 1].matches(instan + "|" + hex + "|" + bin))
                        && (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE"))
                        || parts[1].contains("[")) {
                    String desplazamiento = Desplazamiento(linea, Cons, Simb);
                    if (desplazamiento.equalsIgnoreCase("Sind") && (Integer.parseInt(parts[parts.length - 1]) >= 0
                            && Integer.parseInt(parts[parts.length - 1]) <= 128)) {
                        return 3;
                    } else if (desplazamiento.equalsIgnoreCase("d8") && (Integer.parseInt(parts[parts.length - 1]) >= 0
                            && Integer.parseInt(parts[parts.length - 1]) <= 128)) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("d16") && (Integer.parseInt(parts[parts.length - 1]) >= 0
                            && Integer.parseInt(parts[parts.length - 1]) <= 128)) {
                        return 5;
                    } else if (desplazamiento.equalsIgnoreCase("Sind")
                            && (Integer.parseInt(parts[parts.length - 1]) >= 129
                                    && Integer.parseInt(parts[parts.length - 1]) <= 65535)) {
                        return 4;
                    } else if (desplazamiento.equalsIgnoreCase("d8")
                            && (Integer.parseInt(parts[parts.length - 1]) >= 129
                                    && Integer.parseInt(parts[parts.length - 1]) <= 65535)) {
                        return 5;
                    } else if (desplazamiento.equalsIgnoreCase("d16")
                            && (Integer.parseInt(parts[parts.length - 1]) >= 129
                                    && Integer.parseInt(parts[parts.length - 1]) <= 65535)) {
                        return 6;
                    } else if (desplazamiento.equalsIgnoreCase("null")) {
                        return 0;
                    }
                }
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].matches(reg) && parts[2].equalsIgnoreCase(Simb.get(i))) {
                        return 4;
                    } else if (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].matches(reg)) {
                        return 4;
                    } else if (parts[1].equalsIgnoreCase(Simb.get(i))
                            && parts[2].matches(instan + "|" + bin + "|" + hex)) {
                        if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 128) {
                            return 5;
                        } else if (Integer.parseInt(parts[2]) >= 129 && Integer.parseInt(parts[2]) <= 65535) {
                            return 6;
                        }
                    } else if (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].matches(Cons)) {
                        return 6;
                    }
                }
                break;
            case "LES":
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].matches(reg) && parts[2].equalsIgnoreCase(Simb.get(i))) {
                        return 4;
                    }
                    if (parts[1].matches(reg) && (parts[2].equalsIgnoreCase("WORD"))
                            || parts[2].equalsIgnoreCase("BYTE")) {
                        String desplazamiento = Desplazamiento(linea, Cons, Simb);
                        if (desplazamiento.equalsIgnoreCase("Sind")) {
                            return 2;
                        } else if (desplazamiento.equalsIgnoreCase("d8")) {
                            return 3;
                        } else if (desplazamiento.equalsIgnoreCase("d16")) {
                            return 4;
                        } else if (desplazamiento.equalsIgnoreCase("null")) {
                            return 0;
                        }
                    }
                    if (parts[1].matches(reg) && parts[2].contains("[") && parts[2].contains("]")) {
                        String desplazamiento = Desplazamiento(linea, Cons, Simb);
                        if (desplazamiento.equalsIgnoreCase("Sind")) {
                            return 2;
                        } else if (desplazamiento.equalsIgnoreCase("d8")) {
                            return 3;
                        } else if (desplazamiento.equalsIgnoreCase("d16")) {
                            return 4;
                        } else if (desplazamiento.equalsIgnoreCase("null")) {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                }
                break;
            case "JGE":
            case "JNA":
            case "JNC":
            case "JNZ":
            case "JZ":
                try {
                    if (parts[1].matches(decEti)) {
                        for (int i = 0; i < Etiq.size(); i++) {
                            if (parts[1].equalsIgnoreCase(Etiq.get(i))) {
                                return 4;
                            }
                        }
                    }
                } catch (Exception e) {
                    return 0;
                }
            default:
                return 0;

        }

        return contI;
    }

    public boolean verificaSintaxisDesplazamiento(String linea, String Cons, ArrayList<String> simb) {
        String AccM = "(BX|SI|DI)|"
                + "(BX\\s*\\+\\s*SI|BX\\s*\\+\\s*DI)"
                + "|" + "SI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                + "|" + "DI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                + "|" + "BP\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                + "|" + "BX\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                + "|" + "SI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                + "|" + "BX\\s*\\+\\s*(SI|DI)\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")";
        String[] parts = null;
        String lineaSC = linea.replaceAll("\\[", "");
        lineaSC = lineaSC.replaceAll("\\]", "");
        parts = lineaSC.split("\\+");
        if (parts.length == 1) {
            if (parts[0].matches(AccM)) {
                return true;
            }
            for (int i = 0; i < simb.size(); i++) {
                if (parts[0].equalsIgnoreCase(simb.get(i))) {
                    return true;
                }
            }
            if (parts[0].matches(instan + "|" + hex + "|" + bin)) {
                return false;
            } else {
                return false;
            }
        } else if (parts.length >= 2 && parts.length <= 3) {
            if (lineaSC.matches(AccM)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String Desplazamiento(String linea, String Cons, ArrayList<String> simb) {
        if (linea.contains("WORD")) {
            if (linea.contains("[") && linea.contains("]")) {
                int inicioCorchete, finCorchete;
                inicioCorchete = linea.indexOf("[");
                finCorchete = linea.indexOf("]");
                String desplazamiento = linea.substring(inicioCorchete + 1, finCorchete);
                String[] parts = desplazamiento.split("\\+");
                if (verificaSintaxisDesplazamiento(desplazamiento, Cons, simb)) {
                    for (int j = 0; j < simb.size(); j++) {
                        if (simb.get(j).equalsIgnoreCase(desplazamiento) && parts.length == 1) {
                            return "Variable";
                        }
                    }
                    if (parts.length == 1 && parts[0].matches("(BX|SI|DI)")) {
                        return "Sind";
                    } else if (parts.length == 1 && parts[0].matches(reg)) {
                        return "Sind";
                    } else if (parts.length == 2 && desplazamiento.matches("(BX\\s*\\+\\s*SI|BX\\s*\\+\\s*DI)")) {
                        return "Sind";
                    } else if (parts.length == 2
                            && desplazamiento.matches("SI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|"
                                    + Cons + ")"
                                    + "|" + "DI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                                    + "|" + "BP\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                                    + "|" + "BX\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")")
                            && parts[1].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) {
                        if (parts[1].matches(instan)) {
                            if (Integer.parseInt(parts[1]) >= 0 && Integer.parseInt(parts[1]) <= 255) {
                                return "d8";
                            } else if (Integer.parseInt(parts[1]) >= 256 && Integer.parseInt(parts[1]) <= 65535) {
                                return "d16";
                            }
                        } else if (parts[1].matches(hex)) {
                            if (convierteHaD(parts[1]) >= 0 && convierteHaD(parts[1]) <= 255) {
                                return "d8";
                            } else if (convierteHaD(parts[1]) >= 256 && convierteHaD(parts[1]) <= 65535) {
                                return "d16";
                            }
                        } else if (parts[1].matches(bin)) {
                            if (convierteBaD(parts[1]) >= 0 && convierteBaD(parts[1]) <= 255) {
                                return "d8";
                            } else if (convierteBaD(parts[1]) >= 256 && convierteBaD(parts[1]) <= 65535) {
                                return "d16";
                            }
                        } else if (parts[1].matches(Cons)) {
                            if (Integer.parseInt(parts[1]) >= 0 && Integer.parseInt(parts[1]) <= 255) {
                                return "d8";
                            } else if (Integer.parseInt(parts[1]) >= 256 && Integer.parseInt(parts[1]) <= 65535) {
                                return "d16";
                            }
                        }
                    } else if (parts.length == 3 && desplazamiento.matches("BX\\s*\\+\\s*(SI|DI)\\s*\\+\\s*" + "("
                            + instan + "|" + hex + "|" + bin + "|" + Cons + ")")) {
                        if (parts[2].matches(instan)) {
                            if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                                return "d8";
                            } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 65535) {
                                return "d16";
                            }
                        } else if (parts[2].matches(hex)) {
                            if (convierteHaD(parts[2]) >= 0 && convierteHaD(parts[2]) <= 255) {
                                return "d8";
                            } else if (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 65535) {
                                return "d16";
                            }
                        } else if (parts[1].matches(bin)) {
                            if (convierteBaD(parts[2]) >= 0 && convierteBaD(parts[2]) <= 255) {
                                return "d8";
                            } else if (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 65535) {
                                return "d16";
                            }
                        } else if (parts[2].matches(Cons)) {
                            if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                                return "d8";
                            } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 65535) {
                                return "d16";
                            }
                        }
                    }
                }
            } else {
                return "null";
            }
        } else if (linea.contains("[") && linea.contains("]")) {
            int inicioCorchete, finCorchete;
            inicioCorchete = linea.indexOf("[");
            finCorchete = linea.indexOf("]");
            String desplazamiento = linea.substring(inicioCorchete + 1, finCorchete);
            String[] parts = desplazamiento.split("\\+");
            if (verificaSintaxisDesplazamiento(desplazamiento, Cons, simb)) {
                for (int j = 0; j < simb.size(); j++) {
                    if (simb.get(j).equalsIgnoreCase(desplazamiento) && parts.length == 1) {
                        return "Variable";
                    }
                }
                if (parts.length == 1 && parts[0].matches("(BX|SI|DI)")) {
                    return "Sind";
                } else if (parts.length == 1 && parts[0].matches(reg)) {
                    return "Sind";
                } else if (parts.length == 2 && desplazamiento.matches("(BX\\s*\\+\\s*SI|BX\\s*\\+\\s*DI)")) {
                    return "Sind";
                } else if (parts.length == 2
                        && desplazamiento.matches("SI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons
                                + ")"
                                + "|" + "DI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                                + "|" + "BP\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
                                + "|" + "BX\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")")
                        && parts[1].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) {
                    if (parts[1].matches(instan)) {
                        if (Integer.parseInt(parts[1]) >= 0 && Integer.parseInt(parts[1]) <= 255) {
                            return "d8";
                        } else if (Integer.parseInt(parts[1]) >= 256 && Integer.parseInt(parts[1]) <= 65535) {
                            return "d16";
                        }
                    } else if (parts[1].matches(hex)) {
                        if (convierteHaD(parts[1]) >= 0 && convierteHaD(parts[1]) <= 255) {
                            return "d8";
                        } else if (convierteHaD(parts[1]) >= 256 && convierteHaD(parts[1]) <= 65535) {
                            return "d16";
                        }
                    } else if (parts[1].matches(bin)) {
                        if (convierteBaD(parts[1]) >= 0 && convierteBaD(parts[1]) <= 255) {
                            return "d8";
                        } else if (convierteBaD(parts[1]) >= 256 && convierteBaD(parts[1]) <= 65535) {
                            return "d16";
                        }
                    } else if (parts[1].matches(Cons)) {
                        if (Integer.parseInt(parts[1]) >= 0 && Integer.parseInt(parts[1]) <= 255) {
                            return "d8";
                        } else if (Integer.parseInt(parts[1]) >= 256 && Integer.parseInt(parts[1]) <= 65535) {
                            return "d16";
                        }
                    }
                } else if (parts.length == 3 && desplazamiento.matches(
                        "BX\\s*\\+\\s*(SI|DI)\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")")) {
                    if (parts[2].matches(instan)) {
                        if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                            return "d8";
                        } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 65535) {
                            return "d16";
                        }
                    } else if (parts[2].matches(hex)) {
                        if (convierteHaD(parts[2]) >= 0 && convierteHaD(parts[2]) <= 255) {
                            return "d8";
                        } else if (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 65535) {
                            return "d16";
                        }
                    } else if (parts[1].matches(bin)) {
                        if (convierteBaD(parts[2]) >= 0 && convierteBaD(parts[2]) <= 255) {
                            return "d8";
                        } else if (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 65535) {
                            return "d16";
                        }
                    } else if (parts[2].matches(Cons)) {
                        if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                            return "d8";
                        } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 65535) {
                            return "d16";
                        }
                    }
                }
            }
        } else {
            String[] parts = linea.split(" ");
            if (parts.length >= 2 && parts.length <= 3) {
                for (int j = 0; j < simb.size(); j++) {
                    if (simb.get(j).equalsIgnoreCase(parts[1]) && parts.length == 2) {
                        return "Sind";
                    }
                }
                if (parts[1].matches(reg)) {
                    return "Sind";
                } else {
                    return "null";
                }
            } else {
                return "null";
            }
        }
        return "null";
    }

    public String inmWPTR(String inmed) {
        while ((inmed.length() % 4) != 0) {
            inmed = "0" + inmed;
        }
        return inmed;
    }
}
