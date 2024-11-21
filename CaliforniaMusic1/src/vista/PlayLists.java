package vista;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;

import modelo.Cancion;

public class PlayLists extends JButton {
		
		private String nombre;
		private ArrayList<String> cancionesS = new ArrayList();
		private ArrayList<Cancion> canciones = new ArrayList();
	
		public PlayLists() {
		    
		    this.setText(nombre); // Establece el texto en el botón actual (this)
		    this.setPreferredSize(new Dimension(400, 50)); // Configura el tamaño directamente en este botón
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public ArrayList<String> getCancionesS() {
			return cancionesS;
		}

		public void setCancionesS(ArrayList<String> cancionesS) {
			this.cancionesS = cancionesS;
		}

		public ArrayList<Cancion> getCanciones() {
			return canciones;
		}

		public void setCanciones(ArrayList<Cancion> canciones) {
			this.canciones = canciones;
		}

//		public void eliminarCancion(String nombreCancion) {
//		    
//			for(Cancion cancion : canciones) {
//				if(cancion.getTitulo().equalsIgnoreCase(nombreCancion));
//				canciones.remove(cancion);
//			}
//		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
