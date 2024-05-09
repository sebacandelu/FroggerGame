package Main;



import java.util.concurrent.Semaphore;


import javax.swing.SwingUtilities;
import Controller.Controller;
import View.Finestra;

import javax.swing.SwingUtilities;
import Controller.Controller;
import View.Finestra;
import Thread.Audio;
/**
 * @author Boutbila Anass
 * @author Tiveron Sebastinao
 * 
 * @param Classe principale del programma.
 * Questa classe avvia l'applicazione e gestisce l'interfaccia grafica e la riproduzione audio.
 */

public class Main {
    
    /**
     * Metodo principale per avviare l'applicazione.
     * @param args gli argomenti della riga di comando (non utilizzati in questo caso)
     */
    public static void main(String[] args) {
        // Avvia l'interfaccia grafica nell'Event Dispatch Thread (EDT) per garantire la sicurezza delle operazioni Swing
        SwingUtilities.invokeLater(() -> {
            // Crea un semaforo per controllare l'accesso alla risorsa condivisa (la riproduzione audio)
            Semaphore semaphore = new Semaphore(1); // Solo un permesso per una sola istanza di riproduzione musicale
            
            // Crea una nuova finestra
            Finestra finestra = new Finestra();
            finestra.setLocationRelativeTo(null); // Posiziona la finestra al centro dello schermo
            
            // Crea un nuovo controller per gestire gli eventi della finestra
            Controller controller = new Controller(finestra);
            
            // Percorso del file audio da riprodurre
            String filePath = "C:\\Users\\user\\Desktop\\Progetto_Finale_Tiveron_Boutbila\\Progetto_Finale_Boutbila_Tiveron\\src\\canzone\\Frogger_Finale.wav";
            
            // Crea un nuovo oggetto Audio per gestire la riproduzione audio
            Audio audio = new Audio(filePath, semaphore);
            
            // Avvia la riproduzione audio
            audio.start();
        });
    }
}
