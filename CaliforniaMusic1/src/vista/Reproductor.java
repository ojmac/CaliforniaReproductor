package vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Reproductor extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JButton botonPlay;
    private JButton botonPause;
    private JButton botonStop;
    private JButton botonForward;
    private JButton botonRewind;
    private JProgressBar progressBar;
    private Timer timer;

    public Reproductor() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setPreferredSize(new Dimension(600, 100));

        // Crear botones de control
        botonPlay = new JButton();
        botonPause = new JButton();
        botonStop = new JButton();
        botonForward = new JButton(">>");
        botonRewind = new JButton("<<");
           
        
        // Ajustar estilo de los botones
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        botonPlay.setFont(buttonFont);
        botonPause.setFont(buttonFont);
        botonStop.setFont(buttonFont);
        botonForward.setFont(buttonFont);
        botonRewind.setFont(buttonFont);

        // Establecer el tamaño de los botones
        botonPlay.setPreferredSize(new Dimension(50, 50));
        botonPause.setPreferredSize(new Dimension(50, 50));
        botonStop.setPreferredSize(new Dimension(50, 50));

     // Establecer los iconos para los botones y ajustar su tamaño
        int buttonSize = 50; // Tamaño del botón (ajustado a 50x50)
        ImageIcon playIcon = new ImageIcon(new ImageIcon("./images/play.png").getImage().getScaledInstance(buttonSize, buttonSize, java.awt.Image.SCALE_SMOOTH));
        ImageIcon pauseIcon = new ImageIcon(new ImageIcon("./images/pause.png").getImage().getScaledInstance(buttonSize, buttonSize, java.awt.Image.SCALE_SMOOTH));
        ImageIcon stopIcon = new ImageIcon(new ImageIcon("./images/stop.png").getImage().getScaledInstance(buttonSize, buttonSize, java.awt.Image.SCALE_SMOOTH));
        botonPlay.setIcon(playIcon);
        botonPause.setIcon(pauseIcon);
        botonStop.setIcon(stopIcon);

        // Hacer los botones invisibles (sin bordes y sin fondo)
        botonPlay.setContentAreaFilled(false);
        botonPlay.setBorderPainted(false);
        botonPlay.setFocusPainted(false);
        
        botonPause.setContentAreaFilled(false);
        botonPause.setBorderPainted(false);
        botonPause.setFocusPainted(false);
        
        botonStop.setContentAreaFilled(false);
        botonStop.setBorderPainted(false);
        botonStop.setFocusPainted(false);
 
        
        // Crear barra de progreso
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(400, 20));
        progressBar.setStringPainted(true);

        // Agregar funcionalidad a los botones
        botonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startProgress();
                System.out.println("Reproducción iniciada");
            }
        });

        botonPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseProgress();
                System.out.println("Reproducción en pausa");
            }
        });

        botonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopProgress();
                System.out.println("Reproducción detenida");
            }
        });

        botonForward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Avanzar canción");
            }
        });

        botonRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retroceder canción");
            }
        });

        // Agregar componentes al panel
        add(botonRewind);
        add(botonPlay);
        add(botonPause);
        add(botonStop);
        add(botonForward);
        add(progressBar);
    }

    // Métodos para controlar la barra de progreso
    private void startProgress() {
        if (timer == null || !timer.isRunning()) {
            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int value = progressBar.getValue();
                    if (value < 100) {
                        progressBar.setValue(value + 1);
                    } else {
                        timer.stop();
                    }
                }
            });
            timer.start();
        }
    }

    private void pauseProgress() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void stopProgress() {
        if (timer != null) {
            timer.stop();
            progressBar.setValue(0);
        }
    }
}
