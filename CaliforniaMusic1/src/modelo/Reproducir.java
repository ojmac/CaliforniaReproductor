package modelo;


import java.io.File;
import java.io.Serializable;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;


public class Reproducir implements Serializable {


    private Clip clip;
    private AudioInputStream audioStream;
    private String archivo;
    private Timer timer;
    private long clipTimePosition;
    private boolean isPlaying;


    public Reproducir() {
        this.isPlaying = false;
    }


    // Método para cargar y reproducir la canción
    public void reproducirCancion(String archivo) {
        detenerCancion();
        try {
           


            File audioFile = new File(archivo);
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);


            clip.start();
            isPlaying = true;


            startTimer();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void pausarCancion() {
        if (clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
            isPlaying = false;
        }
    }


    public void continuarCancion() {
        if (!clip.isRunning() && clipTimePosition > 0) {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
            isPlaying = true;
        }
    }


    public void detenerCancion() {
   


        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            clip.flush();
            clipTimePosition = 0;
            isPlaying = false;
        }


    }


    public boolean isPlaying() {
        return isPlaying;
    }


    private void startTimer() {
        timer = new Timer(1000, e -> {
            updateProgressBar();
        });
        timer.start();
    }


    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }


    private void updateProgressBar() {
        if (clip != null && clip.isRunning()) {
            long microseconds = clip.getMicrosecondPosition();
            long duration = clip.getMicrosecondLength();


            int progress = (int) ((microseconds / (double) duration) * 100);


        }
    }


    public Clip getClip() {
        return clip;
    }


    public void setClip(Clip clip) {
        this.clip = clip;
    }


    public AudioInputStream getAudioStream() {
        return audioStream;
    }


    public void setAudioStream(AudioInputStream audioStream) {
        this.audioStream = audioStream;
    }


    public String getArchivo() {
        return archivo;
    }


    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }


    public Timer getTimer() {
        return timer;
    }


    public void setTimer(Timer timer) {
        this.timer = timer;
    }


    public long getClipTimePosition() {
        return clipTimePosition;
    }


    public void setClipTimePosition(long clipTimePosition) {
        this.clipTimePosition = clipTimePosition;
    }


    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }


}



