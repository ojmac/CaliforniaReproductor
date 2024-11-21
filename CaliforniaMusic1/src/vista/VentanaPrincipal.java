package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import modelo.Cancion;

public class VentanaPrincipal extends JFrame {

	private VentanaCrearPlayList vcpl = new VentanaCrearPlayList(this);
	private boolean existeVcpl;

	public VentanaPrincipal() {
		existeVcpl = false;
		creaVentanaPpal();
	}

	private void creaVentanaPpal() {

		setTitle("California JukeBox");
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension dimension = miPantalla.getScreenSize();
		int ancho = (int) dimension.getWidth();
		int alto = (int) dimension.getHeight();
		ancho=(ancho-600)/2;
		alto =(alto-600)/2;
		setBounds(ancho,alto,600,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		mostrarPanelPrincipal();
		setVisible(true);		
	}

	public void mostrarPanelPrincipal() {
		getContentPane().removeAll(); // Eliminar panel actual
		PanelPrincipal panelPrincipal = new PanelPrincipal(this); // Crear nuevo panel
		add(panelPrincipal); // Añadir el panel al JFrame
		revalidate();
		repaint();
	}

	public void mostrarPanelPortada(Cancion cancion) {
		getContentPane().removeAll(); // Eliminar panel actual
		Portada panel = new Portada(cancion, this); // Crear nuevo panel
		add(panel); // Añadir el panel al JFrame
		revalidate();
		repaint();
	}

	public void mostrarVentanaCrearPlayList() {
	
			getContentPane().removeAll();
			VentanaCrearPlayList crearPlayListPanel = new VentanaCrearPlayList(this);
			add(crearPlayListPanel, BorderLayout.CENTER);
			this.existeVcpl = true; 
			revalidate();
			repaint();


	}
}