package mx.uaemex.ensamblador.classes;

import java.util.ArrayList;

public class Codificador {
    private static String instan = "[0-9]+";
    private final String var = "([a-zA-Z]{1,8}[0-9]{0,3})";
    private final String reg = "AH|BH|CH|DH|AL|BL|CL|DL|AX|BX|CX|DX|EAX|EBX|ECX|EDX|ESI|EDI|ESP|EBP|SI|DI|BP|SP";
    private final String hex = "(0(\\d|[a-fA-F])+(h|H))";
    private final String bin = "(0|1)+(B|b)";
    private final String num = "([0-9]+)|[D|d|.] ([a-zA-Z]{1,}) | ([a-zA-Z]{1,}) [D|d|.] ";
    private static ArrayList<String> variables = new ArrayList<>();
    private static ArrayList<String> cVar = new ArrayList<>();

    public String codificacionCS(String linea, String accMem, ArrayList<String> Simb, ArrayList<String> Etiq,
            String Cons, ArrayList<String> Vars, ArrayList<String> contEtiq, ArrayList<String> contVars, String contH) {
        String cad = "Incorrecto: ";
        linea = linea.toUpperCase();
        Cons = Cons.toUpperCase();
        linea = linea.replaceAll("PTR\\[", "PTR \\[");
        accMem = accMem.toUpperCase();
        String[] parts = linea.split(" ");
        String aux = "";
        String codigo;
        String w;
        String mod;
        String rm;
        String inm;
        String des;
        String dir;
        variables = Vars;
        cVar = contVars;
        switch (parts[0]) {
            // Instrucciones sin operando
            case "CMPSW":
                if (parts.length == 1) {
                    codigo = "10100111";
                    cad = codificacionH(codigo);
                } else {
                    cad += "La instrucción no lleva operandos";
                }
                break;
            case "CWD":
                if (parts.length == 1) {
                    codigo = "10011001";
                    cad = codificacionH(codigo);
                } else {
                    cad += "La instrucción no lleva operandos";
                }
                break;
            case "POPF":
                if (parts.length == 1) {
                    codigo = "10011101";
                    cad = codificacionH(codigo);
                } else {
                    cad += "La instrucción no lleva operandos";
                }
                break;
            case "PUSHA":
                if (parts.length == 1) {
                    codigo = "01100000";
                    cad = codificacionH(codigo);
                } else {
                    cad += "La instrucción no lleva operandos";
                }
                break;
            case "AAS":
                if (parts.length == 1) {
                    // cad = "Correcto";
                    codigo = "00111111";
                    cad = codificacionH(codigo);
                } else {
                    cad += "La instrucción no lleva operandos";
                }
                break;
            case "CBW":
                if (parts.length == 1) {
                    codigo = "10011000";
                    cad = codificacionH(codigo);
                } else {
                    cad += "La instrucción no lleva operandos";
                }
                break;

            // Instrucciones de salto
            case "JGE":
                if (parts.length == 2 && Etiq.size() > 0) {
                    for (int i = 0; i < Etiq.size(); i++) {
                        if (Etiq.get(i).equalsIgnoreCase(parts[1])) {
                            codigo = "0000111110001101";
                            cad = codificacionH(codigo) + contEtiq.get(i);
                            break;
                        } else {
                            cad = "Incorrecto: No se ha definido la etiqueta";
                        }
                    }
                } else {
                    cad += "No se ha definido la etiqueta";
                }
                break;
            case "JNA":
                if (parts.length == 2 && Etiq.size() > 0) {
                    for (int i = 0; i < Etiq.size(); i++) {
                        if (Etiq.get(i).equalsIgnoreCase(parts[1])) {
                            codigo = "0000111110000110";
                            cad = codificacionH(codigo) + contEtiq.get(i);
                            break;
                        } else {
                            cad = "Incorrecto: No se ha definido la etiqueta";
                        }
                    }
                } else {
                    cad += "No se ha definido la etiqueta";
                }
                break;
            case "JNC":
                if (parts.length == 2 && Etiq.size() > 0) {
                    for (int i = 0; i < Etiq.size(); i++) {
                        if (Etiq.get(i).equalsIgnoreCase(parts[1])) {
                            codigo = "0000111110000010";
                            cad = codificacionH(codigo) + contEtiq.get(i);
                            break;
                        } else {
                            cad = "Incorrecto: No se ha definido la etiqueta";
                        }
                    }
                } else {
                    cad += "No se ha definido la etiqueta";
                }
                break;
            case "JNZ":
                if (parts.length == 2 && Etiq.size() > 0) {
                    for (int i = 0; i < Etiq.size(); i++) {
                        if (Etiq.get(i).equalsIgnoreCase(parts[1])) {
                            codigo = "0000111110000101";
                            cad = codificacionH(codigo) + contEtiq.get(i);
                            break;
                        } else {
                            cad = "Incorrecto: No se ha definido la etiqueta";
                        }
                    }
                } else {
                    cad += "No se ha definido la etiqueta";
                }
                break;
            case "JZ":
                if (parts.length == 2 && Etiq.size() > 0) {
                    for (int i = 0; i < Etiq.size(); i++) {
                        if (Etiq.get(i).equalsIgnoreCase(parts[1])) {
                            codigo = "0000111110000100";
                            cad = codificacionH(codigo) + contEtiq.get(i);
                            break;
                        } else {
                            cad = "Incorrecto: No se ha definido la etiqueta";
                        }
                    }
                } else {
                    cad += "No se ha definido la etiqueta";
                }
                break;
            case "LOOPNZ":
                if (parts.length == 2 && Etiq.size() > 0) {
                    for (int i = 0; i < Etiq.size(); i++) {
                        if (Etiq.get(i).equalsIgnoreCase(parts[1])) {
                            codigo = "11100000";
                            cad = codificacionH(codigo) + contEtiq.get(i);
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
            case "NEG":
                w = valW(parts[1]);
                codigo = "1111011" + w;
                dir = "011";
                if (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE")) {
                    if (parts[2].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 3; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 1]);
                            codigo = codigo + mod + dir + rm;
                            cad = codificacionH(codigo) + des;
                            return cad;
                        } else {
                            cad += "Se esperaba un acceso a memoria o registro";
                        }
                    }
                }
                if (parts[1].contains("[") && parts[1].contains("]")) {
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        des = " " + inmHex(parts[parts.length - 1]); // this
                        codigo = codigo + mod + dir + rm;
                        cad = codificacionH(codigo) + des;
                        return cad;
                    } else {
                        cad += "Se esperaba un acceso a memoria válido o registro";
                    }
                }
                if (parts[1].matches(reg)) {
                    mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                    rm = casosRM(parts[1]);
                    des = " " + BuscaVar(parts[1]);
                    codigo = codigo + mod + dir + rm;
                    cad = codificacionH(codigo) + des;
                    return cad;
                }
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].equalsIgnoreCase(Simb.get(i))) {
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        codigo = codigo + mod + dir + rm;
                        cad = codificacionH(codigo) + contVars.get(i);
                        return cad;
                        // break;
                    } else {
                        cad = "Incorrecto: Se esperaba un acceso a memoria o registro";
                    }
                }
                if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                    mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                    rm = casosRM(parts[1]);
                    des = Bdes(Desplazamiento(linea, Cons, Simb), parts[1]);// this
                    codigo = codigo + mod + dir + rm;
                    cad = codificacionH(codigo) + des;
                    return cad;
                }
                break;
            case "DIV":
                w = valW(parts[1]);
                codigo = "1111011" + w;
                dir = "110";
                if (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE")) {
                    if (parts[2].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 3; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 1]);// this
                            codigo = codigo + mod + dir + rm;
                            cad = codificacionH(codigo) + des;
                            return cad;
                        } else {
                            cad += "Se esperaba un acceso a memoria válido o registro";
                        }
                    }
                }
                if (parts[1].contains("[") && parts[1].contains("]")) {
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        des = " " + inmHex(parts[parts.length - 1]); // this
                        codigo = codigo + mod + dir + rm;
                        cad = codificacionH(codigo) + des;
                        return cad;
                    } else {
                        cad += "Se esperaba un acceso a memoria válido o registro";
                    }
                }
                if (parts[1].matches(reg)) {
                    mod = casosMod(parts[1], "Sind");
                    rm = casosRM(parts[1]);
                    codigo = codigo + mod + dir + rm;
                    cad = codificacionH(codigo);
                    return cad;
                }
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].equalsIgnoreCase(Simb.get(i))) {
                        mod = casosMod(parts[1], "Sind");
                        rm = casosRM(parts[1]);
                        codigo = codigo + mod + dir + rm;
                        cad = codificacionH(codigo) + contVars.get(i);
                        return cad;
                        // break;
                    } else {
                        cad = "Incorrecto: Se esperaba un acceso a memoria válido o registro";
                    }
                }
                if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                    mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                    rm = casosRM(parts[1]);
                    des = Bdes(Desplazamiento(linea, Cons, Simb), parts[1]);// this
                    codigo = codigo + mod + dir + rm;
                    cad = codificacionH(codigo) + des;
                    return cad;
                }
                break;
            case "INT":
                codigo = "11001101";
                if ((parts[1].matches(num) || parts[1].matches(bin) || parts[1].matches(hex)
                        || parts[1].matches(Cons))) {
                    if ((parts[1].matches(num))
                            && (Integer.parseInt(parts[1]) >= 0 && Integer.parseInt(parts[1]) <= 255)) {
                        cad = codificacionH(codigo) + " " + inmHex(parts[1]);
                        return cad;
                    } else if ((parts[1].matches(hex))
                            && (convierteHaD((parts[1])) >= 0 && convierteHaD((parts[1])) <= 255)) {
                        cad = codificacionH(codigo) + " " + inmHex(parts[1]);
                        return cad;
                    } else if ((parts[1].matches(bin))
                            && (convierteBaD((parts[1])) >= 0 && convierteBaD((parts[1])) <= 255)) {
                        cad = codificacionH(codigo) + " " + inmHex(parts[1]);
                        return cad;
                    }
                    try {
                        if (Integer.parseInt(parts[1]) < 0 || Integer.parseInt(parts[1]) > 255) {
                            return cad += "Valor fuera de rango";
                        }
                    } catch (Exception e) {
                        try {
                            if (convierteHaD(parts[1]) < 0 || convierteHaD(parts[1]) > 255) {
                                return cad += "Valor fuera de rango";
                            }
                        } catch (Exception ex) {
                            if (convierteBaD(parts[1]) < 0 || convierteBaD(parts[1]) > 255) {
                                return cad += "Valor fuera de rango";
                            }
                        }
                    }
                } else {
                    cad += "Se esperaba un valor decimal, binario o hexadecimal";
                }
                break;
            case "NOT":
                w = valW(parts[1]);
                codigo = "1111011" + w;
                dir = "010";
                if (parts[1].equalsIgnoreCase("WORD") || parts[1].equalsIgnoreCase("BYTE")) {
                    if (parts[2].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 3; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 1]);
                            codigo = codigo + mod + dir + rm;
                            cad = codificacionH(codigo) + des;
                            return cad;
                        } else {
                            cad += "Se esperaba un acceso a memoria o registro";
                        }
                    }
                }
                if (parts[1].contains("[") && parts[1].contains("]")) {
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        des = " " + inmHex(parts[parts.length - 1]); // this
                        codigo = codigo + mod + dir + rm;
                        cad = codificacionH(codigo) + des;
                        return cad;
                    } else {
                        cad += "Se esperaba un acceso a memoria válido o registro";
                    }
                }
                if (parts[1].matches(reg)) {
                    mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                    rm = casosRM(parts[1]);
                    des = " " + BuscaVar(parts[1]);
                    codigo = codigo + mod + dir + rm;
                    cad = codificacionH(codigo) + des;
                    return cad;
                }
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].equalsIgnoreCase(Simb.get(i))) {
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        codigo = codigo + mod + dir + rm;
                        cad = codificacionH(codigo) + contVars.get(i);
                        return cad;
                        // break;
                    } else {
                        cad = "Incorrecto: Se esperaba un acceso a memoria o registro";
                    }
                }
                if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                    mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                    rm = casosRM(parts[1]);
                    des = Bdes(Desplazamiento(linea, Cons, Simb), parts[1]);// this
                    codigo = codigo + mod + dir + rm;
                    cad = codificacionH(codigo) + des;
                    return cad;
                }
                break;

            // Instrucciones dos operandos
            case "ADC":
                if (parts.length == 3 && (parts[1].matches(reg) && parts[2].matches(reg))) { // REG, REG
                    cad = "000100" + "0" + valW(parts[2]) + "11" + casosReg(parts[1]) + casosRM(parts[2]);
                    return codificacionH(cad);

                } else if (parts.length == 3
                        && (parts[1].matches(reg) && parts[2].matches(instan + "|" + hex + "|" + bin + "|" + Cons))) { // REG,
                                                                                                                       // INM
                    String ss = "";
                    if (parts[2].matches(hex)) {
                        if (convierteHaD(parts[2]) >= 0 && convierteHaD(parts[2]) <= 255) {
                            ss = "1";
                        } else if (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 35535) {
                            ss = "0";
                        }
                    } else if (parts[2].matches(bin)) {
                        if (convierteBaD(parts[2]) >= 0 && convierteBaD(parts[2]) <= 255) {
                            ss = "1";
                        } else if (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 35535) {
                            ss = "0";
                        }
                    } else if (parts[2].matches(instan)) {
                        if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                            ss = "1";
                        } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 35535) {
                            ss = "0";
                        }
                    }
                    cad = "100000" + ss + valW(parts[1]) + "11" + "010" + casosRM(parts[1]);
                    return codificacionH(cad) + inmHex(parts[2]);
                }

                for (int i = 0; i < Simb.size(); i++) {
                    if (parts.length == 3 && (parts[1].matches(reg) && parts[2].equalsIgnoreCase(Simb.get(i)))) { // REG,
                                                                                                                  // MEM
                                                                                                                  // var
                        cad = "0001001" + valW(parts[1]) + "00" + casosReg(parts[1]) + "110";
                        return codificacionH(cad) + BuscaVar(parts[2]);
                    }

                    if (parts.length == 3 && (parts[1].equalsIgnoreCase(Simb.get(i))
                            && parts[2].matches(instan + "|" + hex + "|" + bin + "|" + Cons))) { // MEN var, INM
                        String ss = "";
                        if (parts[2].matches(hex)) {
                            if (convierteHaD(parts[2]) >= 0 && convierteHaD(parts[2]) <= 255) {
                                ss = "1";
                            } else if (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        } else if (parts[2].matches(bin)) {
                            if (convierteBaD(parts[2]) >= 0 && convierteBaD(parts[2]) <= 255) {
                                ss = "1";
                            } else if (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        } else if (parts[2].matches(instan)) {
                            if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                                ss = "1";
                            } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        }
                        cad = "100000" + ss + valW(parts[2]) + "00" + "010" + "110";
                        return codificacionH(cad) + inmHex(parts[2]) + " " + BuscaVar(parts[1]);
                    }
                    if (parts.length == 3 && (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].matches(reg))) { // MEM
                                                                                                                  // var,
                                                                                                                  // REG
                        cad = "0001000" + valW(parts[2]) + "00" + casosReg(parts[2]) + "110";
                        return codificacionH(cad) + BuscaVar(parts[1]);
                    }
                }

                if (parts[1].contains("[") && parts[1].contains("]")
                        && parts[2].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) { // MEM solo corchetes, INM
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        String ss = "";
                        if (parts[2].matches(hex)) {
                            if (convierteHaD(parts[2]) >= 0 && convierteHaD(parts[2]) <= 255) {
                                ss = "1";
                            } else if (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        } else if (parts[2].matches(bin)) {
                            if (convierteBaD(parts[2]) >= 0 && convierteBaD(parts[2]) <= 255) {
                                ss = "1";
                            } else if (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        } else if (parts[2].matches(instan)) {
                            if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                                ss = "1";
                            } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        }
                        codigo = "100000" + ss + "0";
                        dir = "010";
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        des = " " + inmHex(parts[parts.length - 2]);
                        inm = " " + inmHex(parts[parts.length - 1]);
                        codigo = codigo + mod + dir + rm;
                        if (des.equalsIgnoreCase(" ")) {
                            cad = codificacionH(codigo) + inm;
                        } else {
                            cad = codificacionH(codigo) + des + inm;
                        }
                        return cad;
                    }
                } else if (parts[1].matches(reg) && (parts[2].contains("[") && parts[2].contains("]"))) { // REG, MEM
                                                                                                          // solo
                                                                                                          // corchetes
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        codigo = "0001001" + valW(parts[1]);
                        mod = casosMod(parts[2], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[2]);
                        des = " " + inmHex(parts[parts.length - 1]);
                        codigo = codigo + mod + casosReg(parts[1]) + rm;
                        cad = codificacionH(codigo) + des;
                        return cad;
                    }
                } else if (parts[1].matches(reg) && (parts[0].contains("[") && parts[0].contains("]"))) { // MEM solo
                                                                                                          // corchetes,
                                                                                                          // REG
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        codigo = "0001000" + valW(parts[2]);
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        des = " " + inmHex(parts[parts.length - 2]);
                        codigo = codigo + mod + casosReg(parts[2]) + rm;
                        cad = codificacionH(codigo) + des;
                        return cad;
                    }
                }

                if (linea.contains("WORD")) {
                    if (parts[1].equalsIgnoreCase("WORD") && parts[parts.length - 1].matches(reg)) {
                        aux = "";
                        for (int i = 3; i < parts.length - 1; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")
                                && parts[parts.length - 1].matches(reg)) { // MEM corchetes, REG
                            codigo = "0001000" + valW(parts[parts.length - 1]);
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 2]);
                            codigo = codigo + mod + casosReg(parts[parts.length - 1]) + rm;
                            cad = codificacionH(codigo) + des;
                            return cad;
                        }
                    } else if (parts[1].matches(reg) && parts[2].equalsIgnoreCase("WORD")) {
                        aux = "";
                        for (int i = 4; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null") && parts[1].matches(reg)) { // REG,
                                                                                                                    // MEM
                                                                                                                    // corchetes
                            codigo = "0001001" + valW(parts[1]);
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 1]);
                            codigo = codigo + mod + casosReg(parts[1]) + rm;
                            cad = codificacionH(codigo) + des;
                            return cad;
                        }
                    } else if (parts[1].equalsIgnoreCase("WORD")
                            && parts[parts.length - 1].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) {
                        aux = "";
                        for (int i = 3; i < parts.length - 1; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")
                                && parts[parts.length - 1].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) { // MEM
                                                                                                                   // corchetes,
                                                                                                                   // INM
                            String ss = "";
                            if (parts[parts.length - 1].matches(hex)) {
                                if (convierteHaD(parts[parts.length - 1]) >= 0
                                        && convierteHaD(parts[parts.length - 1]) <= 255) {
                                    ss = "1";
                                } else if (convierteHaD(parts[parts.length - 1]) >= 256
                                        && convierteHaD(parts[parts.length - 1]) <= 35535) {
                                    ss = "0";
                                }
                            } else if (parts[parts.length - 1].matches(bin)) {
                                if (convierteBaD(parts[parts.length - 1]) >= 0
                                        && convierteBaD(parts[parts.length - 1]) <= 255) {
                                    ss = "1";
                                } else if (convierteBaD(parts[parts.length - 1]) >= 256
                                        && convierteBaD(parts[parts.length - 1]) <= 35535) {
                                    ss = "0";
                                }
                            } else if (parts[parts.length - 1].matches(instan)) {
                                if (Integer.parseInt(parts[parts.length - 1]) >= 0
                                        && Integer.parseInt(parts[parts.length - 1]) <= 255) {
                                    ss = "1";
                                } else if (Integer.parseInt(parts[parts.length - 1]) >= 256
                                        && Integer.parseInt(parts[parts.length - 1]) <= 35535) {
                                    ss = "0";
                                }
                            }
                            codigo = "100000" + ss + "0";
                            dir = "010";
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 2]);
                            inm = " " + inmHex(parts[parts.length - 1]);
                            codigo = codigo + mod + dir + rm;
                            if (des.equalsIgnoreCase(" ")) {
                                cad = codificacionH(codigo) + inm;
                            } else {
                                cad = codificacionH(codigo) + des + inm;
                            }
                            return cad;
                        }
                    }
                } else if (parts.length == 3 && parts[2].matches(var)) {
                    cad += "El segundo operando esta mal definido";
                } else if (parts.length >= 3 && parts.length < 5) {
                    cad += "Error, la instrucción solo lleva 2 operandos";
                } else if (parts.length < 3) {
                    cad += "Error, a la instrucción le faltan operandos";
                }
                return cad + " la instrucción no tiene ese caso";
            case "CMP":
                if (parts.length == 3 && (parts[1].matches(reg) && parts[2].matches(reg))) { // REG, REG
                    cad = "001110" + "0" + valW(parts[2]) + "11" + casosReg(parts[1]) + casosRM(parts[2]);
                    return codificacionH(cad);

                } else if (parts.length == 3
                        && (parts[1].matches(reg) && parts[2].matches(instan + "|" + hex + "|" + bin + "|" + Cons))) { // REG,
                                                                                                                       // INM
                    String ss = "";
                    if (parts[2].matches(hex)) {
                        if (convierteHaD(parts[2]) >= 0 && convierteHaD(parts[2]) <= 255) {
                            ss = "1";
                        } else if (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 35535) {
                            ss = "0";
                        }
                    } else if (parts[2].matches(bin)) {
                        if (convierteBaD(parts[2]) >= 0 && convierteBaD(parts[2]) <= 255) {
                            ss = "1";
                        } else if (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 35535) {
                            ss = "0";
                        }
                    } else if (parts[2].matches(instan)) {
                        if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                            ss = "1";
                        } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 35535) {
                            ss = "0";
                        }
                    }
                    cad = "100000" + ss + valW(parts[1]) + "11" + "111" + casosRM(parts[1]);
                    return codificacionH(cad) + inmHex(parts[2]);
                }

                for (int i = 0; i < Simb.size(); i++) {
                    if (parts.length == 3 && (parts[1].matches(reg) && parts[2].equalsIgnoreCase(Simb.get(i)))) { // REG,
                                                                                                                  // MEM
                                                                                                                  // var
                        cad = "0011101" + valW(parts[1]) + "00" + casosReg(parts[1]) + "110";
                        return codificacionH(cad) + BuscaVar(parts[2]);
                    }

                    if (parts.length == 3 && (parts[1].equalsIgnoreCase(Simb.get(i))
                            && parts[2].matches(instan + "|" + hex + "|" + bin + "|" + Cons))) { // MEN var, INM
                        String ss = "";
                        if (parts[2].matches(hex)) {
                            if (convierteHaD(parts[2]) >= 0 && convierteHaD(parts[2]) <= 255) {
                                ss = "1";
                            } else if (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        } else if (parts[2].matches(bin)) {
                            if (convierteBaD(parts[2]) >= 0 && convierteBaD(parts[2]) <= 255) {
                                ss = "1";
                            } else if (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        } else if (parts[2].matches(instan)) {
                            if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                                ss = "1";
                            } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        }
                        cad = "100000" + ss + valW(parts[2]) + "00" + "111" + "110";
                        return codificacionH(cad) + inmHex(parts[2]) + " " + BuscaVar(parts[1]);
                    }
                    if (parts.length == 3 && (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].matches(reg))) { // MEM
                                                                                                                  // var,
                                                                                                                  // REG
                        cad = "0011100" + valW(parts[2]) + "00" + casosReg(parts[2]) + "110";
                        return codificacionH(cad) + BuscaVar(parts[1]);
                    }
                }

                if (parts[1].contains("[") && parts[1].contains("]")
                        && parts[2].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) { // MEM solo corchetes, INM
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        String ss = "";
                        if (parts[2].matches(hex)) {
                            if (convierteHaD(parts[2]) >= 0 && convierteHaD(parts[2]) <= 255) {
                                ss = "1";
                            } else if (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        } else if (parts[2].matches(bin)) {
                            if (convierteBaD(parts[2]) >= 0 && convierteBaD(parts[2]) <= 255) {
                                ss = "1";
                            } else if (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        } else if (parts[2].matches(instan)) {
                            if (Integer.parseInt(parts[2]) >= 0 && Integer.parseInt(parts[2]) <= 255) {
                                ss = "1";
                            } else if (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 35535) {
                                ss = "0";
                            }
                        }
                        codigo = "100000" + ss + "0";
                        dir = "111";
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        des = " " + inmHex(parts[parts.length - 2]);
                        inm = " " + inmHex(parts[parts.length - 1]);
                        codigo = codigo + mod + dir + rm;
                        if (des.equalsIgnoreCase(" ")) {
                            cad = codificacionH(codigo) + inm;
                        } else {
                            cad = codificacionH(codigo) + des + inm;
                        }
                        return cad;
                    }
                } else if (parts[1].matches(reg) && (parts[2].contains("[") && parts[2].contains("]"))) { // REG, MEM
                                                                                                          // solo
                                                                                                          // corchetes
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        codigo = "0011101" + valW(parts[1]);
                        mod = casosMod(parts[2], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[2]);
                        des = " " + inmHex(parts[parts.length - 1]);
                        codigo = codigo + mod + casosReg(parts[1]) + rm;
                        cad = codificacionH(codigo) + des;
                        return cad;
                    }
                } else if (parts[2].matches(reg) && (parts[1].contains("[") && parts[1].contains("]"))) { // MEM solo
                                                                                                          // corchetes,
                                                                                                          // REG
                    if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")) {
                        codigo = "0011100" + valW(parts[2]);
                        mod = casosMod(parts[1], Desplazamiento(linea, Cons, Simb));
                        rm = casosRM(parts[1]);
                        des = " " + inmHex(parts[parts.length - 2]);
                        codigo = codigo + mod + casosReg(parts[2]) + rm;
                        cad = codificacionH(codigo) + des;
                        return cad;
                    }
                }

                if (linea.contains("WORD")) {
                    if (parts[1].equalsIgnoreCase("WORD") && parts[parts.length - 1].matches(reg)) {
                        aux = "";
                        for (int i = 3; i < parts.length - 1; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")
                                && parts[parts.length - 1].matches(reg)) { // MEM corchetes, REG
                            codigo = "0011100" + valW(parts[parts.length - 1]);
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 2]);
                            codigo = codigo + mod + casosReg(parts[parts.length - 1]) + rm;
                            cad = codificacionH(codigo) + des;
                            return cad;
                        }
                    } else if (parts[1].matches(reg) && parts[2].equalsIgnoreCase("WORD")) {
                        aux = "";
                        for (int i = 4; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null") && parts[1].matches(reg)) { // REG,
                                                                                                                    // MEM
                                                                                                                    // corchetes
                            codigo = "0011101" + valW(parts[1]);
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 1]);
                            codigo = codigo + mod + casosReg(parts[1]) + rm;
                            cad = codificacionH(codigo) + des;
                            return cad;
                        }
                    } else if (parts[1].equalsIgnoreCase("WORD")
                            && parts[parts.length - 1].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) {
                        aux = "";
                        for (int i = 3; i < parts.length - 1; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceAll("^\\s*", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")
                                && parts[parts.length - 1].matches(instan + "|" + hex + "|" + bin + "|" + Cons)) { // MEM
                                                                                                                   // corchetes,
                                                                                                                   // INM
                            String ss = "";
                            if (parts[parts.length - 1].matches(hex)) {
                                if (convierteHaD(parts[parts.length - 1]) >= 0
                                        && convierteHaD(parts[parts.length - 1]) <= 255) {
                                    ss = "1";
                                } else if (convierteHaD(parts[parts.length - 1]) >= 256
                                        && convierteHaD(parts[parts.length - 1]) <= 35535) {
                                    ss = "0";
                                }
                            } else if (parts[parts.length - 1].matches(bin)) {
                                if (convierteBaD(parts[parts.length - 1]) >= 0
                                        && convierteBaD(parts[parts.length - 1]) <= 255) {
                                    ss = "1";
                                } else if (convierteBaD(parts[parts.length - 1]) >= 256
                                        && convierteBaD(parts[parts.length - 1]) <= 35535) {
                                    ss = "0";
                                }
                            } else if (parts[parts.length - 1].matches(instan)) {
                                if (Integer.parseInt(parts[parts.length - 1]) >= 0
                                        && Integer.parseInt(parts[parts.length - 1]) <= 255) {
                                    ss = "1";
                                } else if (Integer.parseInt(parts[parts.length - 1]) >= 256
                                        && Integer.parseInt(parts[parts.length - 1]) <= 35535) {
                                    ss = "0";
                                }
                            }
                            codigo = "100000" + ss + "0";
                            dir = "111";
                            mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                            rm = casosRM(aux);
                            des = " " + inmHex(parts[parts.length - 2]);
                            inm = " " + inmHex(parts[parts.length - 1]);
                            codigo = codigo + mod + dir + rm;
                            if (des.equalsIgnoreCase(" ")) {
                                cad = codificacionH(codigo) + inm;
                            } else {
                                cad = codificacionH(codigo) + des + inm;
                            }
                            return cad;
                        }
                    }
                } else if (parts.length == 3 && parts[2].matches(var)) {
                    cad += "El segundo operando esta mal definido";
                } else if (parts.length >= 3 && parts.length < 5) {
                    cad += "Error, la instrucción solo lleva 2 operandos";
                } else if (parts.length < 3) {
                    cad += "Error, a la instrucción le faltan operandos";
                }
                return cad + " la instrucción no tiene ese caso";
            case "LES":
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts.length == 3 && (parts[1].matches(reg) && parts[2].equalsIgnoreCase(Simb.get(i)))) { // reg,
                                                                                                                  // men
                                                                                                                  // var
                        cad = "11000100" + "00" + casosReg(parts[1]) + "110";
                        return codificacionH(cad) + BuscaVar(parts[2]);
                    }
                }
                if (parts.length >= 4 && (parts[1].matches(reg) && parts[2].equalsIgnoreCase("WORD")
                        || parts[2].equalsIgnoreCase("BYTE"))) { // reg, mem w
                    if (parts[3].equalsIgnoreCase("PTR")) {
                        aux = "";
                        for (int i = 4; i < parts.length; i++) {
                            aux += " " + parts[i];
                        }
                        aux = aux.replaceFirst(" ", "");
                        if (!Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Null")
                                && Desplazamiento(linea, Cons, Simb).equalsIgnoreCase("Sind")) {
                            cad = "11000100" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "111" + casosRM(aux);
                            return codificacionH(cad);
                        } else {
                            cad = "11000100" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "111" + casosRM(aux);
                            return codificacionH(cad) + inmHex(aux);
                        }
                    }
                }
                if (!Desplazamiento(linea, Cons, Simb).equals("Null") && parts[2].contains("[")
                        && parts[2].contains("]") && parts[1].matches(reg)) { // R,M
                    for (int i = 2; i < parts.length; i++) {
                        aux += parts[i];
                    }
                    codigo = "11000100";
                    mod = casosMod(aux, Desplazamiento(linea, Cons, Simb));
                    rm = casosRM(aux);
                    des = " " + inmHex(parts[parts.length - 1]);
                    codigo = codigo + mod + casosReg(parts[1]) + rm;
                    if (des.equalsIgnoreCase(" ")) {
                        cad = codificacionH(codigo);
                    } else {
                        cad = codificacionH(codigo) + des;
                    }
                    return cad;
                } else {
                    cad += "Mala declaración de un operando, se esperaba un Registro, Memoria";
                }
                return cad + " la instrucción no tiene ese caso";
            case "RCL":
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].equalsIgnoreCase("1")) { // MEM Var, 1
                        cad = "1101001000010110";
                        return codificacionH(cad) + BuscaVar(parts[1]);
                    }
                }

                if (parts[1].matches(reg) && parts[2].equalsIgnoreCase("1")) { // REG, 1
                    cad = "1101000" + valW(parts[1]) + casosMod(parts[1], "Sind") + "010" + casosRM(parts[1]);
                    return codificacionH(cad);
                }
                if (!Desplazamiento(linea, Cons, Simb).equals("Null") && !linea.toUpperCase().contains("WORD")
                        && parts[parts.length - 1].equalsIgnoreCase("1")) { // MEM solo corchetes, 1
                    for (int i = 1; i < parts.length - 1; i++) {
                        aux += parts[i];
                    }
                    des = " " + inmHex(parts[parts.length - 2]);
                    cad = "11010000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010" + casosRM(aux);
                    return codificacionH(cad) + des;
                }
                if (!Desplazamiento(linea, Cons, Simb).equals("Null") && linea.toUpperCase().contains("WORD")
                        && parts[parts.length - 1].equalsIgnoreCase("1")) { // MEM corchetes, 1
                    for (int i = 3; i < parts.length - 1; i++) {
                        aux += parts[i];
                    }
                    des = " " + inmHex(parts[parts.length - 2]);
                    cad = "11010000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010" + casosRM(aux);
                    return codificacionH(cad) + des;
                }
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].equalsIgnoreCase(Simb.get(i)) && parts[2].equalsIgnoreCase("CL")) { // MEM Var, CL
                        cad = "1101001000010110";
                        return codificacionH(cad) + BuscaVar(parts[1]);
                    }
                }
                if (parts[1].matches(reg) && parts[2].equalsIgnoreCase("CL")) { // REG, CL
                    cad = "1101001" + valW(parts[1]) + casosMod(parts[1], "Sind") + "010" + casosRM(parts[1]);
                    return codificacionH(cad);
                }
                if (!Desplazamiento(linea, Cons, Simb).equals("Null") && !linea.toUpperCase().contains("WORD")
                        && parts[parts.length - 1].equalsIgnoreCase("CL")) { // MEM solo corchetes, CL
                    for (int i = 1; i < parts.length - 1; i++) {
                        aux += parts[i];
                    }
                    des = " " + inmHex(aux);
                    cad = "11010010" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010" + casosRM(aux);
                    return codificacionH(cad) + des;
                }
                if (!Desplazamiento(linea, Cons, Simb).equals("Null") && linea.toUpperCase().contains("WORD")
                        && parts[parts.length - 1].equalsIgnoreCase("CL")) {// MEM corchetes, CL
                    for (int i = 3; i < parts.length - 1; i++) {
                        aux += parts[i];
                    }
                    des = " " + inmHex(parts[parts.length - 2]);
                    cad = "11010011" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010" + casosRM(aux);
                    return codificacionH(cad) + des;
                }
                for (int i = 0; i < Simb.size(); i++) {
                    if (parts[1].equalsIgnoreCase(Simb.get(i))
                            && parts[2].matches(instan + "|" + bin + "|" + hex + "|" + Cons)) { // MEM var, INST
                        try {
                            if (parts[1].equalsIgnoreCase(Simb.get(i)) && (convierteHaD(parts[2]) == 0
                                    && convierteHaD(parts[2]) != 1 && convierteHaD(parts[2]) <= 255)) {
                                cad = "1100000000010110";
                                return codificacionH(cad) + " " + inmHex(parts[2]) + " " + BuscaVar(parts[1]);
                            } else if (parts[1].equalsIgnoreCase(Simb.get(i))
                                    && (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 65535)) {
                                cad = "1100000000010110";
                                return codificacionH(cad) + " " + inmHex(parts[2]) + " " + BuscaVar(parts[1]);
                            }
                        } catch (Exception e) {
                            try {
                                if (parts[1].equalsIgnoreCase(Simb.get(i)) && (convierteBaD(parts[2]) == 0
                                        && convierteBaD(parts[2]) != 1 && convierteBaD(parts[2]) <= 255)) {
                                    cad = "1100000000010110";
                                    return codificacionH(cad) + " " + inmHex(parts[2]) + " " + BuscaVar(parts[1]);
                                } else if (parts[1].equalsIgnoreCase(Simb.get(i))
                                        && (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 65535)) {
                                    cad = "1100000000010110";
                                    return codificacionH(cad) + " " + inmHex(parts[2]) + " " + BuscaVar(parts[1]);
                                }
                            } catch (Exception ex) {
                                if (parts[1].equalsIgnoreCase(Simb.get(i)) && (Integer.parseInt(parts[2]) == 0
                                        && Integer.parseInt(parts[2]) != 1 && Integer.parseInt(parts[2]) <= 255)) {
                                    cad = "1100000000010110";
                                    return codificacionH(cad) + " " + inmHex(parts[2]) + " " + BuscaVar(parts[1]);
                                } else if (parts[1].equalsIgnoreCase(Simb.get(i))
                                        && (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 65535)) {
                                    cad = "1100000000010110";
                                    return codificacionH(cad) + " " + inmHex(parts[2]) + " " + BuscaVar(parts[1]);
                                }
                            }
                        }
                    }

                }
                if (parts[1].matches(reg) && parts[2].matches(instan + "|" + bin + "|" + hex + "|" + Cons)) { // REG,
                                                                                                              // INST
                    try {
                        if (parts[1].matches(reg) && (convierteHaD(parts[2]) == 0 && convierteHaD(parts[2]) != 1
                                && convierteHaD(parts[2]) <= 255)) {// Mem Var, Reg
                            cad = "1100000" + valW(parts[1]) + casosMod(parts[1], "Sind") + "010" + casosRM(parts[1]);
                            return codificacionH(cad) + " " + inmHex(parts[2]);
                        } else if (parts[1].matches(reg)
                                && (convierteHaD(parts[2]) >= 256 && convierteHaD(parts[2]) <= 65535)) {// Mem Var, Reg
                            cad = "1100000" + valW(parts[1]) + casosMod(parts[1], "Sind") + "010" + casosRM(parts[1]);
                            return codificacionH(cad) + " " + inmHex(parts[2]);
                        }
                    } catch (Exception e) {
                        try {
                            if (parts[1].matches(reg) && (convierteBaD(parts[2]) == 0 && convierteBaD(parts[2]) != 1
                                    && convierteBaD(parts[2]) <= 255)) {// Mem Var, Reg
                                cad = "1100000" + valW(parts[1]) + casosMod(parts[1], "Sind") + "010"
                                        + casosRM(parts[1]);
                                return codificacionH(cad) + " " + inmHex(parts[2]);
                            } else if (parts[1].matches(reg)
                                    && (convierteBaD(parts[2]) >= 256 && convierteBaD(parts[2]) <= 65535)) {// Mem Var,
                                                                                                            // Reg
                                cad = "1100000" + valW(parts[1]) + casosMod(parts[1], "Sind") + "010"
                                        + casosRM(parts[1]);
                                return codificacionH(cad) + " " + inmHex(parts[2]);
                            }
                        } catch (Exception ex) {

                            if (parts[1].matches(reg) && (Integer.parseInt(parts[2]) == 0
                                    && Integer.parseInt(parts[2]) != 1 && Integer.parseInt(parts[2]) <= 255)) {// Mem
                                                                                                               // Var,
                                                                                                               // Reg
                                cad = "1100000" + valW(parts[1]) + casosMod(parts[1], "Sind") + "010"
                                        + casosRM(parts[1]);
                                return codificacionH(cad) + " " + inmHex(parts[2]);
                            } else if (parts[1].matches(reg)
                                    && (Integer.parseInt(parts[2]) >= 256 && Integer.parseInt(parts[2]) <= 65535)) {// Mem
                                                                                                                    // Var,
                                                                                                                    // Reg
                                cad = "1100000" + valW(parts[1]) + casosMod(parts[1], "Sind") + "010"
                                        + casosRM(parts[1]);
                                return codificacionH(cad) + " " + inmHex(parts[2]);
                            }
                        }
                    }
                }
                if (!Desplazamiento(linea, Cons, Simb).equals("Null") && !linea.toUpperCase().contains("WORD")
                        && parts[2].matches(instan + "|" + bin + "|" + hex + "|" + Cons)) { // MEM solo corchetes, INST
                    try {
                        if (!Desplazamiento(linea, Cons, Simb).equals("Null") && !linea.toUpperCase().contains("WORD")
                                && (convierteHaD(parts[parts.length - 1]) > 0
                                        && convierteHaD(parts[parts.length - 1]) <= 255)) {// Mem Var, Reg
                            for (int i = 1; i < parts.length - 1; i++) {
                                aux += parts[i];
                            }
                            des = " " + inmHex(parts[parts.length - 2]);
                            cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010" + casosRM(aux);
                            return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                        } else if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                && !linea.toUpperCase().contains("WORD")
                                && (convierteHaD(parts[parts.length - 1]) >= 256
                                        && convierteHaD(parts[parts.length - 1]) <= 65535)) {// Mem Var, Reg
                            for (int i = 1; i < parts.length - 1; i++) {
                                aux += parts[i];
                            }
                            des = " " + inmHex(parts[parts.length - 2]);
                            cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010" + casosRM(aux);
                            return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                        }
                    } catch (Exception e) {
                        try {
                            if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                    && !linea.toUpperCase().contains("WORD")
                                    && (convierteBaD(parts[parts.length - 1]) > 0
                                            && convierteBaD(parts[parts.length - 1]) <= 255)) {// Mem Var, Reg
                                for (int i = 1; i < parts.length - 1; i++) {
                                    aux += parts[i];
                                }
                                des = " " + inmHex(parts[parts.length - 2]);
                                cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010"
                                        + casosRM(aux);
                                return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                            } else if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                    && !linea.toUpperCase().contains("WORD")
                                    && (convierteBaD(parts[parts.length - 1]) >= 256
                                            && convierteBaD(parts[parts.length - 1]) <= 65535)) {// Mem Var, Reg
                                for (int i = 1; i < parts.length - 1; i++) {
                                    aux += parts[i];
                                }
                                des = " " + inmHex(parts[parts.length - 2]);
                                cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010"
                                        + casosRM(aux);
                                return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                            }
                        } catch (Exception ex) {
                            if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                    && !linea.toUpperCase().contains("WORD")
                                    && (Integer.parseInt(parts[parts.length - 1]) > 0
                                            && Integer.parseInt(parts[parts.length - 1]) <= 255)) {// Mem Var, Reg
                                for (int i = 1; i < parts.length - 1; i++) {
                                    aux += parts[i];
                                }
                                des = " " + inmHex(parts[parts.length - 2]);
                                cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010"
                                        + casosRM(aux);
                                return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                            } else if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                    && !linea.toUpperCase().contains("WORD")
                                    && (Integer.parseInt(parts[parts.length - 1]) >= 256
                                            && Integer.parseInt(parts[parts.length - 1]) <= 65535)) {// Mem Var, Reg
                                for (int i = 1; i < parts.length - 1; i++) {
                                    aux += parts[i];
                                }
                                des = " " + inmHex(parts[parts.length - 2]);
                                cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010"
                                        + casosRM(aux);
                                return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                            }
                        }
                    }
                }
                if (!Desplazamiento(linea, Cons, Simb).equals("Null") && linea.toUpperCase().contains("WORD")
                        && parts[parts.length - 1].matches(instan + "|" + bin + "|" + hex + "|" + Cons)) { // MEM
                                                                                                           // corchetes,
                                                                                                           // INS
                    try {
                        if (!Desplazamiento(linea, Cons, Simb).equals("Null") && linea.toUpperCase().contains("WORD")
                                && (convierteHaD(parts[parts.length - 1]) > 0
                                        && convierteHaD(parts[parts.length - 1]) <= 255)) {
                            for (int i = 3; i < parts.length - 1; i++) {
                                aux += parts[i];
                            }
                            des = " " + inmHex(parts[parts.length - 2]);
                            cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010" + casosRM(aux);
                            return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                        } else if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                && linea.toUpperCase().contains("WORD") && (convierteHaD(parts[parts.length - 1]) >= 256
                                        && convierteHaD(parts[parts.length - 1]) <= 65535)) {// Mem Var, Reg
                            for (int i = 3; i < parts.length - 1; i++) {
                                aux += parts[i];
                            }
                            des = " " + inmHex(parts[parts.length - 2]);
                            cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010" + casosRM(aux);
                            return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                        }
                    } catch (Exception e) {
                        try {
                            if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                    && linea.toUpperCase().contains("WORD")
                                    && (convierteBaD(parts[parts.length - 1]) > 0
                                            && convierteBaD(parts[parts.length - 1]) <= 255)) {// Mem Var, Reg
                                for (int i = 3; i < parts.length - 1; i++) {
                                    aux += parts[i];
                                }
                                des = " " + inmHex(parts[parts.length - 2]);
                                cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010"
                                        + casosRM(aux);
                                return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                            } else if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                    && linea.toUpperCase().contains("WORD")
                                    && (convierteBaD(parts[parts.length - 1]) >= 256
                                            && convierteBaD(parts[parts.length - 1]) <= 65535)) {// Mem Var, Reg
                                for (int i = 3; i < parts.length - 1; i++) {
                                    aux += parts[i];
                                }
                                des = " " + inmHex(parts[parts.length - 2]);
                                cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010"
                                        + casosRM(aux);
                                return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                            }
                        } catch (Exception ex) {
                            if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                    && linea.toUpperCase().contains("WORD")
                                    && (Integer.parseInt(parts[parts.length - 1]) > 0
                                            && Integer.parseInt(parts[parts.length - 1]) <= 255)) {// Mem Var, Reg
                                for (int i = 3; i < parts.length - 1; i++) {
                                    aux += parts[i];
                                }
                                des = " " + inmHex(parts[parts.length - 2]);
                                cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010"
                                        + casosRM(aux);
                                return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                            } else if (!Desplazamiento(linea, Cons, Simb).equals("Null")
                                    && linea.toUpperCase().contains("WORD")
                                    && (Integer.parseInt(parts[parts.length - 1]) >= 256
                                            && Integer.parseInt(parts[parts.length - 1]) <= 65535)) {// Mem Var, Reg
                                for (int i = 3; i < parts.length - 1; i++) {
                                    aux += parts[i];
                                }
                                des = " " + inmHex(parts[parts.length - 2]);
                                cad = "11000000" + casosMod(aux, Desplazamiento(linea, Cons, Simb)) + "010"
                                        + casosRM(aux);
                                return codificacionH(cad) + des + " " + inmHex(parts[parts.length - 1]);
                            }
                        }
                    }
                }
                return cad + " la instrucción no tiene ese caso";
            default:
                for (int i = 0; i < Etiq.size(); i++) {
                    if (parts[0].equalsIgnoreCase(Etiq.get(i) + ":")) {
                        return " ";
                    }
                }
                if (parts[0].equalsIgnoreCase("ENDS")
                        || parts[0].equalsIgnoreCase("CODE") && parts[1].equalsIgnoreCase("SEGMENT")) {
                    cad = " ";
                } else {
                    cad = "Pendiente de implementarr";
                    break;
                }
        }
        return cad;
    }

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

    public String convierteBaH(String digitos) {
        String nH = "";
        switch (digitos) {
            case "0000":
                nH = "0";
                break;
            case "0001":
                nH = "1";
                break;
            case "0010":
                nH = "2";
                break;
            case "0011":
                nH = "3";
                break;
            case "0100":
                nH = "4";
                break;
            case "0101":
                nH = "5";
                break;
            case "0110":
                nH = "6";
                break;
            case "0111":
                nH = "7";
                break;
            case "1000":
                nH = "8";
                break;
            case "1001":
                nH = "9";
                break;
            case "1010":
                nH = "A";
                break;
            case "1011":
                nH = "B";
                break;
            case "1100":
                nH = "C";
                break;
            case "1101":
                nH = "D";
                break;
            case "1110":
                nH = "E";
                break;
            case "1111":
                nH = "F";
                break;
        }
        return nH;
    }

    public String codificacionH(String cod) {
        String coH = "";// codificacion hexadecimal completa
        String nH = "";// numero hexadecimal
        String dd = "";// codificacion de 1 byte (2 digitos para que se vea bonito xd)
        int nD = (cod.length() / 4);// numero de digitos
        int s, f;
        s = 0;
        f = 4;
        for (int i = 0; i < nD; i++) {
            nH = cod.substring(s, f);
            dd += convierteBaH(nH);
            s += 4;
            f += 4;
            if (dd.length() == 2) {
                coH += dd + " ";
                dd = "";
            }
        }
        return coH;
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

    public String valW(String registro) {
        String w = "0";
        if (registro.matches("AX|CX|DX|BX|SP|BP|SI|DI") || registro.equalsIgnoreCase("WORD")) {
            w = "1";
        }
        return w;
    }

    public String casosMod(String mem, String desp) {
        String mod = "";
        if (desp.equals("Sind")) {
            if (mem.matches("AL|CL|DL|BL|AH|CH|DH|BH|AX|CX|DX|BX|SP|BP|SI|DI")) {
                mod = "11";
            } else {
                mod = "00";
            }
        } else if (desp.equals("d8")) {
            mod = "01";
        } else if (desp.equals("d16")) {
            mod = "10";
        } else if (mem.matches("AL|CL|DL|BL|AH|CH|DH|BH|AX|CX|DX|BX|BP|SP|SI|DI")) {
            mod = "11";
        } else if (!BuscaVar(mem).equals("")) {
            mod = "00";
        }
        return mod;
    }

    public String casosRM(String mem) {
        String rm = "";
        if (mem.matches("\\[BX\\+SI(\\+" + instan + ")*\\]|AL|AX")) {
            rm = "000";
        } else if (mem.matches("\\[BX\\+DI(\\+" + instan + ")*\\]|CL|CX")) {
            rm = "001";
        } else if (mem.matches("\\[BP\\+SI(\\+" + instan + ")*\\]|DL|DX")) {
            rm = "010";
        } else if (mem.matches("\\[BP\\+DI(\\+" + instan + ")*\\]|BL|BX")) {
            rm = "011";
        } else if (mem.matches("\\[SI(\\+" + instan + ")*\\]|AH|SP|\\[SP\\]")) {
            rm = "100";
        } else if (mem.matches("\\[DI(\\+" + instan + ")*\\]|CH|BP|\\[BP\\]")) {
            rm = "101";
        } else if (!BuscaVar(mem).equals("") || mem.matches("DH|SI|\\[SI\\]")
                || mem.matches("\\[BP\\s*\\+\\s*" + instan + "\\s*\\]")) {
            rm = "110";
        } else if (mem.matches("\\[BX(\\+" + instan + ")*\\]|BH|DI|BX|\\[BX\\]|\\[DI\\]")) {
            rm = "111";
        }
        return rm;
    }

    public String casosReg(String mem) {
        String rm = "";
        if (mem.matches("AL|AX")) {
            rm = "000";
        } else if (mem.matches("CL|CX")) {
            rm = "001";
        } else if (mem.matches("DL|DX")) {
            rm = "010";
        } else if (mem.matches("BL|BX")) {
            rm = "011";
        } else if (mem.matches("AH|SP")) {
            rm = "100";
        } else if (mem.matches("CH|BP")) {
            rm = "101";
        } else if (mem.matches("DH|SI")) {
            rm = "110";
        } else if (mem.matches("BH|DI")) {
            rm = "111";
        }
        return rm;
    }

    public String BuscaVar(String mem) {
        String cH = "";
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).equalsIgnoreCase(mem)) {
                cH = cVar.get(i);
                break;
            }
        }
        return cH;
    }

    public String inmHex(String stringN) {
        String nH = "";
        try {
            stringN = stringN.replaceAll("\\[", "");
            stringN = stringN.replaceAll("\\]", "");
            String[] v = stringN.split("\\+");
            stringN = v[(v.length - 1)];
        } catch (Exception e) {

        }
        if (stringN.matches(instan)) {// this
            int nS = Integer.parseInt(stringN);
            nH = String.format("%x", nS).toUpperCase();
        } else if (stringN.matches(bin)) {
            stringN = stringN.substring(0, stringN.length() - 1);
            while ((stringN.length() % 4) != 0) {
                stringN = "0" + stringN;
            }
            nH = convierteBaH(stringN);
        } else if (stringN.matches(hex)) {
            if (stringN.charAt(0) == '0') {
                nH = stringN.substring(1, stringN.length() - 1);
            } else {
                nH = stringN.substring(0, stringN.length() - 1);
            }
        }
        if ((nH.length() % 2) != 0) {
            nH = "0" + nH;
        }
        return nH;
    }

    public String Bdes(String tdes, String mem) {// this
        String des = "";
        if (tdes.equalsIgnoreCase("Variable")) {
            des = " " + BuscaVar(mem);
        } else if (tdes.equalsIgnoreCase("d8") || tdes.equalsIgnoreCase("d16")) {
            try {
                mem = mem.replaceAll("\\[", "");
                mem = mem.replaceAll("\\]", "");
                String[] v = mem.split("\\+");
                mem = v[(v.length - 1)];
                des = " " + inmHex(mem);
                return des;
            } catch (Exception e) {

            }
        }
        return des;
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

    public String inmWPTR(String inmed) {
        while ((inmed.length() % 4) != 0) {
            inmed = "0" + inmed;
        }
        return inmed;
    }

}
