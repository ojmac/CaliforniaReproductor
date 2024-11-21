package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.Cancion;
import modelo.Reproducir;


public class PanelPrincipal extends JPanel {

	
    private BufferedImage backgroundImage;
    private ArrayList<Cancion> canciones;
    private VentanaReproduccion reproductorPanel;
    private Reproducir reproductor;
    private Portada portada;
    private Cancion cancion;
    private VentanaPrincipal vp; 
    private boolean estado = false;
   

    
    
    
    public PanelPrincipal(VentanaPrincipal vp ) {
    	this.vp=vp;
        cancion = new Cancion();
        canciones = cancion.getCanciones();
    
       
        añadeFondo();  
       
        JPanel backgroundPanel = creaPanelPpal();
        JPanel topPanel = creaTopPanel();
        JPanel songsPanel = creaSongsPanel();
        JPanel  bottomPanel = creaBottomPanel();
       
        reproductorPanel = new VentanaReproduccion();
        reproductor = new Reproducir();
        bottomPanel.add(reproductorPanel);
        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(songsPanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);
       
        add(backgroundPanel, BorderLayout.CENTER);
     
     
        setVisible(true);
     
       
    }
    
JPanel creaPanelPpal() {
        JPanel backgroundPanel = new JPanel() {
           
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setPreferredSize(new Dimension(600, 600));
       
        return backgroundPanel;
    }
    private void añadeFondo() {
        try {
              backgroundImage = ImageIO.read(new File("images/jukebox.jpg"));
              if (backgroundImage == null) {
                    System.out.println("No se pudo cargar la imagen.");
                }
          } catch (IOException e) {
              System.out.println("Error al cargar la imagen: " + e.getMessage());
              e.printStackTrace();
          }
    }
    private JPanel creaTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
       
        JLabel titleLabel = new JLabel("California Jukebox");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
       
        JButton goToPlaylistButton = new JButton("Ir a tus Playlist");
        goToPlaylistButton.setAlignmentX(CENTER_ALIGNMENT);
        goToPlaylistButton.setFont(new Font("Arial", Font.BOLD, 14));
        goToPlaylistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                goToPlaylist();
            }
        });
       customizeButton(goToPlaylistButton);
       topPanel.add(titleLabel);
       topPanel.add(Box.createRigidArea(new Dimension(0,5)));
       topPanel.add(goToPlaylistButton);
       topPanel.add(Box.createRigidArea(new Dimension(0, 5)));
       topPanel.setOpaque(false);
       
     
       return topPanel;
    }
   
    private JPanel creaSongsPanel() {
        JPanel songsPanel = new JPanel();
            songsPanel.setLayout(new GridLayout(5, 2, 10, 10));

            for (int i = 0; i < 10; i++) {
                JButton songButton = new JButton(canciones.get(i).getTitulo());
                songButton.setFont(new Font("Arial", Font.BOLD, 20));
                songButton.setForeground(java.awt.Color.WHITE);
                songButton.setToolTipText(canciones.get(i).getDescripcion());
                final Cancion cancionSeleccionada = canciones.get(i);
           
               
                songButton.addActionListener(new ActionListener() {
                   
                    public void actionPerformed(ActionEvent e) {
                    if(!estado) {
                    vp.mostrarPanelPortada(cancionSeleccionada);
                    }else {
                        // Crear un JLabel para mostrar la información de la canción
                        String informacionCancion = 
                            "<html><b>Título:</b> " + cancionSeleccionada.getTitulo() + "<br>" +
                            "<html><b>Artista:</b> " + cancionSeleccionada.getArtista() + "<br>" +
                            "<b>Álbum:</b> " + cancionSeleccionada.getAlbum() + "<br>" +
                            "<b>Descripción:</b> " + cancionSeleccionada.getDescripcion() + "</html>";

                        // Crear una ventana de información usando un JOptionPane
                        JOptionPane.showMessageDialog(
                            songsPanel, 
                            informacionCancion, 
                            "Información de la Canción", 
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    	
                    }
                    }
                });
                customizeButton(songButton);
                songsPanel.add(songButton);
               
            }
             
            songsPanel.setOpaque(false);
         
        return songsPanel;
    }
   
    private JPanel creaBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(600, 70));
        JCheckBoxMenuItem jCheckMenu1 = new JCheckBoxMenuItem("Información Canciones");
        jCheckMenu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                estado = !estado;
                
                
               
            }
        });
       
        bottomPanel.add(jCheckMenu1);
        
       
        return bottomPanel;
    }
 
    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setForeground(java.awt.Color.WHITE);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);  
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setOpaque(false);
        
        
        
        button.setRolloverEnabled(true); 
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar el color de fondo cuando el ratón pasa sobre el botón
                button.setForeground(Color.BLUE); // Cambia el color del texto
               
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaurar el color original cuando el ratón sale
                button.setForeground(Color.WHITE); // Restaurar el color del texto
                button.setBackground(null); // Elimina el color de fondo
            }
        });
        button.setPressedIcon(null); // Elimina la imagen cuando se presiona el botón
        button.setRolloverIcon(null); // Elimina el efecto de hover
     
    }



    // Método para navegar a la pantalla de Playlist (todavía por implementar)
    private void goToPlaylist() {
    	
        vp.mostrarVentanaCrearPlayList();
      
    }
}
