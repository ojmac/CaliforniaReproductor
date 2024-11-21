package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
	
	public Playlist(String nombre, ArrayList<Cancion> canciones) {
		super();
		this.nombre = nombre;
		this.canciones = canciones;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
	}


	
	



	
}
