package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Cancion;

public class Portada extends JPanel {

	//private Cancion cancion = new Cancion();
	 private VentanaPrincipal principal;

	public Portada(Cancion c, VentanaPrincipal vp) {
		
		principal = vp;
		
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 600)); 

        
        
        // Panel superior: Botón "Volver"
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.green);
        topPanel.setPreferredSize(new Dimension(600, 100)); 
        
        JButton botonVolver = new JButton("VOLVER");
        botonVolver.setFont(new Font("Arial", Font.BOLD, 14));
        botonVolver.addActionListener(e -> principal.mostrarPanelPrincipal());
        topPanel.add(botonVolver);
        
        
       
        

      
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                System.out.println("Botón VOLVER presionado");
   
            }
        });

     
        //**********************************************************************************************************************
        
      
        // Panel central: 
        
        //Imagen de la canción y botón "Añadir"
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.blue);
        centerPanel.setPreferredSize(new Dimension(600, 400));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Cargar la imagen de la canción
        ImageIcon songImageIcon = new ImageIcon(c.getCaratula()); // Ruta de la imagen
        Image scaledImage = songImageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel songImageLabel = new JLabel(new ImageIcon(scaledImage));
        songImageLabel.setPreferredSize(new Dimension(300, 300)); 
        songImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para los botones "AÑADIR" y "Random"
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Espacio horizontal de 20 píxeles
        botonesPanel.setBackground(Color.blue);
        botonesPanel.setMaximumSize(new Dimension(300, 50)); // Fijamos el tamaño máximo al ancho de la imagen

        JButton botonAdd = new JButton("AÑADIR");
        botonAdd.setPreferredSize(new Dimension(80, 40)); // Ajuste de tamaño para ocupar la mitad del ancho aprox.
        JButton botonRandom = new JButton();
        ImageIcon randomIcon = new ImageIcon("./images/random.png");
        Image imagenRandom = randomIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        botonRandom.setIcon(new ImageIcon(imagenRandom));
        botonRandom.setPreferredSize(new Dimension(40, 40));
        botonRandom.setBorderPainted(false); 
        botonRandom.setContentAreaFilled(false);


        // Añadir funcionalidad a los botones
        botonAdd.addActionListener(e -> System.out.println("Botón AÑADIR presionado"));
        botonRandom.addActionListener(e -> System.out.println("Botón Random presionado"));

        // Agregar los botones al panel
        botonesPanel.add(botonAdd);
        botonesPanel.add(botonRandom);

        // Agregar componentes al panel central
        centerPanel.add(songImageLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre la imagen y los botones
        centerPanel.add(botonesPanel); // Añadir el panel de botones en la parte central
                  
        
        //**********************************************************************************************************************

        
     // Panel inferior: Controles de reproducción
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.green);
        bottomPanel.setPreferredSize(new Dimension(600, 100));
        
        // Botones de control
        JButton botonPlay = new JButton("Play");
        JButton botonPause = new JButton("Pause");
        JButton botonStop = new JButton("Stop");
        
        // Añadir los botones al panel inferior
        bottomPanel.add(botonPlay);
        bottomPanel.add(botonPause);
        bottomPanel.add(botonStop);

        // Funcionalidad de los botones de control
        botonPlay.addActionListener(e -> System.out.println("Reproducción iniciada"));
        botonPause.addActionListener(e -> System.out.println("Reproducción en pausa"));
        botonStop.addActionListener(e -> System.out.println("Reproducción detenida"));
             
        //Reproductor reproductor = new Reproductor();
        

//********************************************************************************************************************************


        // Agregar los paneles a la Portada
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        //add(reproductor, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
        
        
    }
	
    
}

