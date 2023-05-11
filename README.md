# Ensamblador

![Ensamblador](Preview_01.png)

## Compilación y ejecución
Requiere de `maven` para compilar el proyecto.

```bash
mvn clean compile assembly:single
java -jar target/Ensamblador-X.X.X-SNAPSHOT-jar-with-dependencies.jar
```

## Instrucciones consideradas

### Saltos y loops
1. `JGE`
2. `JNA`
3. `JNC`
4. `JNZ`
5. `JZ`
6. `LOOPNZ`

### Instrucciones sin operandos
1. `CMPSW`
2. `CWD`
3. `POPF`
4. `PUSHA`
5. `AAS`
6. `CBW`

### Instrucciones con un operando
1. `NEG`
2. `PUSH`
3. `NOT`
10. `DIV`

### Instrucciones con dos operandos
11. TODO: `SAR`
12. `TEST`
13. `ADC`
14. `LES`


