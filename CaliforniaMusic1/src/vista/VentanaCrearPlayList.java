package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaCrearPlayList extends JPanel {
	private VentanaPrincipal ventanaPrincipal;
    private String string;
    private static ArrayList<PlayLists> listaPlayLists = new ArrayList<>();
    
    public VentanaCrearPlayList(VentanaPrincipal ventanaPrincipal) {

    	this.ventanaPrincipal = ventanaPrincipal;
        // Inicialización de las listas
         

        // Configuración del panel principal
        setLayout(new BorderLayout());

        // Crear paneles
        JPanel panelMedio = añadirPanelMedio();
        leerPlayList(panelMedio);
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
    
    public void leerPlayList(JPanel panelMedio) {

    	if(listaPlayLists.size()>0) {
    		
    		for(int i = 0;i<listaPlayLists.size();i++) {
                JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                PlayLists playList = listaPlayLists.get(i);
                playList.setText(playList.getNombre());
                
                playList.setPreferredSize(new Dimension(395, 50));
                playList.addActionListener(e -> {
                	this.setVisible(false);
                	VentanaPlayList ventanaPlayList = new VentanaPlayList(this.ventanaPrincipal,playList);
                	this.ventanaPrincipal.add(ventanaPlayList);
                });
                itemPanel.add(playList);
                JButton eliminar = new JButton("Eliminar");
                eliminar.setPreferredSize(new Dimension(85, 50));
                itemPanel.add(eliminar);
                eliminar.addActionListener(e -> {
                    panelMedio.remove(itemPanel); 
                    listaPlayLists.remove(playList); 
                    panelMedio.revalidate(); 
                    panelMedio.repaint();    
                });
                JButton editar = new JButton("Editar");
                editar.setPreferredSize(new Dimension(80, 50));
                itemPanel.add(editar);
                editar.addActionListener(e -> {
                	EditarPlayList v = new EditarPlayList(playList, ventanaPrincipal);
                	ventanaPrincipal.getContentPane().removeAll();
                	ventanaPrincipal.add(v);
                	ventanaPrincipal.revalidate();
                	ventanaPrincipal.repaint();   
                });
                
                panelMedio.add(itemPanel);
                panelMedio.revalidate();
                panelMedio.repaint();
    			
    		}
    	}

    }


    public JPanel añadirPanelMedio() {
        JPanel panelMedio = new JPanel();
        panelMedio.setLayout(new BoxLayout(panelMedio, BoxLayout.Y_AXIS));
        panelMedio.setBackground(Color.GREEN);
        return panelMedio;
    }

    public JPanel añadirPanelSuperior(JPanel panelMedio) {
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSuperior.setPreferredSize(new Dimension(600, 150));
        panelSuperior.setBackground(Color.BLACK);

        JButton volverButton = new JButton("Volver");
        volverButton.setPreferredSize(new Dimension(100, 50));
        panelSuperior.add(volverButton);
        
        volverButton.addActionListener(e -> {
          this.setVisible(false);
          PanelPrincipal panelPrincipal = new PanelPrincipal(this.ventanaPrincipal);
          this.ventanaPrincipal.add(panelPrincipal);
           
        });

        JButton playlistButton = new JButton("Playlist");
        playlistButton.setPreferredSize(new Dimension(600, 100));
        playlistButton.addActionListener(e -> {
//            this.string = JOptionPane.showInputDialog("Introduzca el nombre de tu Playlist");
        	PlayLists playList = new PlayLists();
        	listaPlayLists.add(playList);
        	EditarPlayList v = new EditarPlayList(playList, ventanaPrincipal);
        	ventanaPrincipal.getContentPane().removeAll();
        	ventanaPrincipal.add(v);
        	ventanaPrincipal.revalidate();
        	ventanaPrincipal.repaint();
//            creaBotonPlayList(string, panelMedio,playList);
        });
        panelSuperior.add(playlistButton);

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
