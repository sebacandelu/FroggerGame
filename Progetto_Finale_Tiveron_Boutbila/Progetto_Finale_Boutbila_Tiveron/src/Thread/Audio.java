package Thread;

import java.io.File;
import java.util.concurrent.Semaphore;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;
import java.util.concurrent.Semaphore;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * La classe Audio è responsabile della riproduzione di file audio in un thread separato.
 * Estende Thread per eseguire la riproduzione audio in modo asincrono.
 */
public class Audio extends Thread {
    private String filePath; // Il percorso del file audio da riprodurre
    private volatile boolean isRunning = true; // Flag per indicare se la riproduzione è in corso
    private Semaphore semaphore; // Semaforo per la gestione dell'accesso alla risorsa condivisa

    /**
     * Costruttore della classe Audio.
     * @param filePath il percorso del file audio da riprodurre
     * @param semaphore il semaforo per il controllo dell'accesso alla risorsa condivisa
     */
    public Audio(String filePath, Semaphore semaphore) {
        this.filePath = filePath; // Imposta il percorso del file audio
        this.semaphore = semaphore; // Imposta il semaforo
    }

    /**
     * Metodo che esegue la riproduzione del file audio.
     */
    @Override
    public void run() {
        try {
            semaphore.acquire(); // Acquisisce il semaforo per l'accesso alla risorsa condivisa
            playMusic(filePath); // Avvia la riproduzione del file audio
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Rilascia il semaforo una volta terminata la riproduzione
        }
    }

    /**
     * Metodo per interrompere la riproduzione audio.
     */
    public void stopMusic() {
        isRunning = false; // Imposta il flag per indicare che la riproduzione deve essere interrotta
    }

    /**
     * Metodo per la riproduzione effettiva del file audio.
     * @param filePath il percorso del file audio da riprodurre
     */
    private void playMusic(String filePath) {
        try {
            File musicFile = new File(filePath); // Crea un oggetto File dal percorso specificato
            if (!musicFile.exists()) {
                System.out.println("File audio non trovato: " + filePath);
                return;
            }

            while (isRunning) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile); // Ottiene l'input stream del file audio
                Clip clip = AudioSystem.getClip(); // Ottiene un oggetto Clip per la riproduzione audio
                clip.open(audioInput); // Apre il file audio
                clip.start(); // Avvia la riproduzione

                // Attende la fine della riproduzione
                while (!clip.isRunning())
                    Thread.sleep(10);
                while (clip.isRunning())
                    Thread.sleep(10);

                clip.close(); // Chiude il clip audio
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
