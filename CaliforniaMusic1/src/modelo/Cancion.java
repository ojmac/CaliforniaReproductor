package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Cancion {

	private String titulo;
	private String artista;
	private String album;
	private String archivo;
	private String caratula;
	private String descripcion;
	private ArrayList<Cancion> canciones;
	
	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
	}
	public Cancion(String titulo, String artista, String album, String archivo, String caratula, String descripcion) {
		super();
		this.titulo = titulo;
		this.artista = artista;
		this.album = album;
		this.archivo = archivo;
		this.caratula = caratula;
		this.descripcion =descripcion;
		
	}
	public Cancion() {
		canciones = new ArrayList<>();
		cargarCanciones();
	}
	
    private void cargarCanciones() {
        
        canciones.add(new Cancion("California Dreamin", " The Mamas & the Papas", "California Dreamin'", "archivosCanciones/c1.wav", "images/car1.jpg","Una de las canciones más icónicas sobre California, que habla sobre el deseo de escapar al clima cálido del estado."));    
        canciones.add(new Cancion("Hotel California", "Eagles", "Hotel California", "archivosCanciones/c2.wav", "images/car2.wav", "Esta clásica canción de rock pinta una imagen surrealista de una experiencia en un misterioso hotel en California."));
        canciones.add(new Cancion("California Love", "2Pac ft. Dr. Dre", " All Eyez on Me", "archivosCanciones/c3.wav", "images/car3.jpg","Un himno del hip-hop que celebra la vida en California, especialmente en Los Ángeles."));
        canciones.add(new Cancion("California Girls", "The Beach Boys", "Summer Days (and Summer Nights!!)", "archivosCanciones/c4.wav", "images/car4.jpg", "Un homenaje a las chicas de California y el estilo de vida playero del estado."));
        canciones.add(new Cancion("Going to California" , "Led Zeppelin", "Led Zeppelin IV", "archivosCanciones/c5.wav", "images/car5.jpg", "Esta canción melancólica trata sobre un viaje a California en busca de una vida mejor y libertad."));
        canciones.add(new Cancion("Dani California", "Red Hot Chili Peppers", "Californication", "archivosCanciones/c6.wav", "images/car6.jpg", "Una historia sobre una chica llamada Dani y su vida en el estado dorado."));
        canciones.add(new Cancion("California" , "Phantom Planet", "California", "archivosCanciones/c7.wav", "images/car7.jpg", "Famosa por ser la canción de apertura de la serie The O.C., esta canción captura la esencia del deseo de estar en California.\r\n"));
        canciones.add(new Cancion("California Waiting", "Kings of Leon", "Youth And Young Manhood", "archivosCanciones/c8.wav", "images/car8.jpg", "Esta canción expresa impaciencia y anticipación, con California como símbolo de nuevas oportunidades."));
        canciones.add(new Cancion("California" , "Joni Mitchell", "Azul", "archivosCanciones/c9.wav", "images/car9.jpg", "Un reflejo poético sobre regresar a California después de viajar por el mundo."));
        canciones.add(new Cancion("California" , " Grimes", "Art Angels", "archivosCanciones/c10.wav", "images/car10.jpg","Un tema más reciente que habla sobre las dificultades y el encanto de la vida en el estado."));
       
       
    }
	
	
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String  getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getCaratula() {
		return caratula;
	}
	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cancion cancion = (Cancion) obj;
        return Objects.equals(titulo, cancion.titulo);
        // Puedes agregar más atributos para la comparación si es necesario
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
        // Asegúrate de incluir los mismos atributos que en equals
    }


	
	
}
