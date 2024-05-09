package Model;

/**
 * La classe ActionTimer rappresenta un timer per il conteggio del tempo.
 * È utilizzato per tracciare il tempo rimanente per un'azione specifica.
 */
public class ActionTimer {
   
	/**
	 * Il tempo iniziale per l'azione
	 */
    private int startTime;  
    /**
     * Il tempo rimanente per l'azione
     */
    private int timeRemaining; 
    
    /**
     * Indica se l'azione è stata attivata
     */
    private boolean triggered; 

    /**
     * Costruttore della classe ActionTimer.
     * @param startTime il tempo iniziale per l'azione
     */
    public ActionTimer(int startTime) {
        this.startTime = startTime; // Imposta il tempo iniziale
        reset(); // Resetta il timer all'avvio
    }

    /**
     * Metodo per aggiornare il timer.
     * Sottrae il tempo trascorso dal tempo iniziale e imposta lo stato dell'azione se il tempo è scaduto.
     * @param deltaTime il tempo trascorso dall'ultimo aggiornamento
     */
    public void update(int deltaTime) {
        timeRemaining -= deltaTime; // Sottrae il tempo trascorso dal tempo rimanente
        if(timeRemaining <= 0) { // Se il tempo rimanente è inferiore o uguale a zero
            triggered = true; // Imposta lo stato dell'azione come attivata
        }
    }

    /**
     * Metodo per controllare se l'azione è stata attivata.
     * @return true se l'azione è stata attivata, false altrimenti
     */
    public boolean isTriggered() {
        return triggered; // Restituisce lo stato dell'azione
    }

    /**
     * Metodo per impostare il timer a un nuovo valore di tempo.
     * @param time il nuovo tempo per l'azione
     */
    public void setTimer(int time) {
        startTime = time; // Imposta il nuovo tempo iniziale
        reset(); // Resetta il timer
    }

    /**
     * Metodo per ripristinare il timer al suo stato iniziale.
     * Imposta il tempo rimanente al tempo iniziale e reimposta lo stato dell'azione.
     */
    public void reset() {
        timeRemaining = startTime; // Imposta il tempo rimanente al tempo iniziale
        triggered = false; // Reimposta lo stato dell'azione come non attivata
    }
}