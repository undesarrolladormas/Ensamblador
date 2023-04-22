package mx.uaemex.ensamblador.classes;

import mx.uaemex.ensamblador.Enums.TipoElementos;

public class Elementos {
	
    private String nombre;
    private TipoElementos tipo;
    private String estado;
    private String descrip;
    
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

    public Elementos(String nombre, TipoElementos tipo) {
        this.setNombre(nombre); 
        this.setTipo(tipo);
    }

    public TipoElementos getTipo() {
        return tipo;
    }  

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(TipoElementos tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
        return nombre;
    }
    
}
