# Ensamblador

![Ensamblador](Preview_01.png)

## Compilación y ejecución
Requiere de `maven` para compilar el proyecto.

```bash
# Primero copiamos las dependencias necesarias 
# Esto solo se requiere una vez, a menos que las dependencias cambien
mvn dependency:copy-dependencies

# Ahora empaquetamos
mvn package
```

Tambien es posible realizar todo el proceso anterior en una sola linea de comando
```bash
mvn dependency:copy-dependencies package
# Notese que las dependencias se copiaran cada vez que se empaquete,
# por lo que solo recomiendo esta opcion si es la primera vez que se 
# construye el proyecto
```

## Instrucciones consideradas

### Saltos y loops
1. `JGE` Listo
2. `JNA` Listo
3. `JNC` Listo
4. `JNZ` Listo
5. `JZ` Listo
6. `LOOPNZ` Verificar direccion

### Instrucciones sin operandos
1. `CMPSW` Listo
2. `CWD` Listo
3. `POPF` Listo
4. `PUSHA` Listo
5. `AAS` Listo
6. `CBW` Listo

### Instrucciones con un operando
1. `NEG` Listo
2. `PUSH`
3. `NOT` Listo
4. `DIV` Listo

### Instrucciones con dos operandos
1. `SAR`
2. `TEST`
3. `ADC` Listo
4. `LES` Listo


