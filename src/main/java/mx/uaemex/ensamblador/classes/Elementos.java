package mx.uaemex.ensamblador.classes;

public class Elementos {
	
    private String nombre;
    private String tipo;

    public Elementos(String nombre, String tipo) {
        this.setNombre(nombre); 
        this.setTipo(tipo);
    }

    public String getTipo() {
        return tipo;
    }  

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
        return nombre;
    }
    
}
