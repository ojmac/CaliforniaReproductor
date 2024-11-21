package vista;


import modelo.Cancion;
import modelo.Reproducir;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaReproduccion extends JPanel {
   
        private Reproducir reproductor;
        private JButton playButton;
        private JButton pauseButton;
        private JButton stopButton;
        private JProgressBar progressBar;
        private JLabel songLabel;
       
        public VentanaReproduccion() {
            reproductor = new Reproducir();
        }
       
        public VentanaReproduccion( String cancion) {
            reproductor = new Reproducir();
           
           
           
            setLayout(new FlowLayout());
           
           
            playButton = new JButton("Play");
            pauseButton = new JButton("Pause");
            stopButton = new JButton("Stop");
           
            songLabel = new JLabel("Reproduciendo: " + cancion);
            songLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            songLabel.setHorizontalAlignment(JLabel.CENTER);
           
            playButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!reproductor.isPlaying()) {
                        reproductor.reproducirCancion(cancion);
                    } else {
                        reproductor.continuarCancion();
                    }
                }
            });


     
            pauseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reproductor.pausarCancion();
                }
            });


            stopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reproductor.detenerCancion();
                    songLabel.setText("Reproducción detenida");                 }
            });


           
            progressBar = new JProgressBar(0, 100);
            progressBar.setPreferredSize(new Dimension(300, 30));
           
           
            add(playButton);
            add(pauseButton);
            add(stopButton);
            add(progressBar);
            add(songLabel);


           
            setVisible(true);
        }
        private void startProgressBarUpdate() {
            Timer timer = new Timer(1000, e -> {
                if (reproductor.isPlaying()) {
                    long current = reproductor.getClip().getMicrosecondPosition();
                    long total = reproductor.getClip().getMicrosecondLength();
                    int progress = (int) ((current / (double) total) * 100);
                    progressBar.setValue(progress);
                }
            });
            timer.start();
        }
     
        public void updateProgress(int progress) {
         
            progressBar.setValue(progress);
        }


        public static void main(String[] args) {
            new VentanaReproduccion();
        }
   
   
}



