package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaPlayList extends JPanel{

    private String string;
    private PlayLists playList;
    private VentanaPrincipal principal;
   
    
    public VentanaPlayList(VentanaPrincipal principal,PlayLists playList) {
    	this.principal=principal;
    	this.playList=playList;
    	
        // Configuración del panel principal
        setLayout(new BorderLayout());

        // Crear paneles
        JPanel panelMedio = añadirPanelMedio();
        crearBotonesCanciones(panelMedio);
        JPanel panelSuperior = añadirPanelSuperior(panelMedio);
        JPanel panelInferior = añadirPanelInferior();

        // Configuración del JScrollPane para el panelMedio
        JScrollPane scrollPane = new JScrollPane(panelMedio);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Añadir paneles al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        
    }
   public void crearBotonesCanciones(JPanel panelMedio) {
    	if(playList.getCanciones().size()>0) {
    		for(int i = 0;i<playList.getCanciones().size();i++) {
    			JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    			JButton cancion = new JButton(playList.getCanciones().get(i).getTitulo());
                cancion.setPreferredSize(new Dimension(400, 50));
                itemPanel.add(cancion);
                panelMedio.add(itemPanel);
    		}
    		
    		panelMedio.revalidate();
            panelMedio.repaint();
    	}
    	
    }

    public JPanel añadirPanelMedio() {
        JPanel panelMedio = new JPanel();
        panelMedio.setLayout(new BoxLayout(panelMedio, BoxLayout.Y_AXIS));
        panelMedio.setBackground(Color.YELLOW);
        return panelMedio;
    }

    public JPanel añadirPanelSuperior(JPanel panelMedio) {
      
    	 JPanel panelSuperior = new JPanel(new BorderLayout());
    	    panelSuperior.setPreferredSize(new Dimension(600, 150));
    	    panelSuperior.setBackground(Color.BLACK);

    	    // Subpanel para los botones "Volver" y "Añadir Cancion" en la primera fila
    	    JPanel topPanel = new JPanel(new BorderLayout());
    	    topPanel.setBackground(Color.BLACK);

    	    JButton volverButton = new JButton("Volver");
    	    volverButton.setPreferredSize(new Dimension(200, 50));
    	    topPanel.add(volverButton, BorderLayout.WEST);

    	    // Añadir el subpanel en la parte superior del panel superior
    	    panelSuperior.add(topPanel, BorderLayout.NORTH);

    	    // Botón "Playlist" en la fila inferior, centrado
    	    JButton playlistButton = new JButton(playList.getNombre());
    	    playlistButton.setPreferredSize(new Dimension(600, 100));
    	    panelSuperior.add(playlistButton, BorderLayout.CENTER);

    	    // Acción para el botón "Volver"
    	    volverButton.addActionListener(e -> {
            	this.setVisible(false);
            	VentanaCrearPlayList ventanaCrearPlayList = new VentanaCrearPlayList(this.principal);
            	principal.add(ventanaCrearPlayList);
            });


    	    return panelSuperior;
    	}
    

    public JPanel añadirPanelInferior() {
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(Color.BLUE);
        panelInferior.setPreferredSize(new Dimension(600, 100));
        panelInferior.add(new Label("Panel 3 - Solo una fila"));
        return panelInferior;
    }
    

    
    
    
}

