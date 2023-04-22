package mx.uaemex.ensamblador.Enums;

public enum TipoElementos {
    PSEUDOINSTRUCCION("PSEUDOINSTRUCCION", "stack segment|"
            + "data segment|"
            + "code segment|"
            + "byte(\\s+)PTR|"
            + "word(\\s+)PTR|"
            + "dup(\\s*)\\(.*\\)|"
            + "db|"
            + "dw|"
            + "ends"),
    INSTRUCCION("INSTRUCCION", "(?i)CMPSW|"
    		+ "CWD|"
    		+ "POPF|"
    		+ "PUSHA|"
    		+ "AAS|"
    		+ "CBW|"
    		+ "NEG|"
    		+ "PUSH|"
    		+ "NOT|"
    		+ "DIV|"
    		+ "SAR|"
    		+ "TEST|"
    		+ "ADC|"
    		+ "LES|"
    		+ "JNZ|"
    		+ "JZ|"
    		+ "LOOPNZ|"
    		+ "JGE|"
    		+ "JNA|"
    		+ "JNC|"
    		),
    REGISTRO("REGISTRO", "(?i)AX|"
            + "AH|"
            + "AL|"
            + "BX|"
            + "BH|"
            + "BL|"
            + "CX|"
            + "CH|"
            + "CL|"
            + "DX|"
            + "DH|"
            + "DL|"),
    CONSTANTE_DECIMAL("CONSTANTE NUMERICA DECIMAL","(?i)([0-9])+d|([0-9])+"),
    CONSTANTE_HEXADECIMAL("CONSTANTE NUMERICA HEXADECIMAL","(?i)0([0-F])*h"),
    CONSTANTE_BINARIA("CONSTANTE NUMERICA BINARIA","(?i)([0-1])+b"),
    CONSTANTE_CARACTER("CONSTANTE CARACTER","(?i)[\"'](.*)[\"']"),
    SIMBOLO("SIMBOLO","(?i)\\w+"),
    NO_INDENTIFICADO("ELEMENTO NO IDENTIFICADO",".*");
    

    private String cadena;
    private String condicion;

    private TipoElementos(String cadena, String condicion) {
        this.cadena = cadena;
        this.condicion = condicion;

    }

    public String getCadena() {
        return cadena;
    }

    public String getCondicion() {
        return condicion;
    }

    @Override
    public String toString() {
        return cadena;
    }
}
