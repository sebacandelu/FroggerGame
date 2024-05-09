package View;

import javax.swing.JFrame;

/**
 * Questa classe rappresenta la finestra principale dell'applicazione Frogger.
 * Estende la classe JFrame e contiene un pannello di gioco (Pannello).
 */
public class Finestra extends JFrame {
    private static final long serialVersionUID = 1L;
    private Pannello gamePanel;

    /**
     * Costruttore della classe Finestra.
     * Inizializza la finestra con titolo "Frogger", imposta la chiusura dell'applicazione
     * quando la finestra viene chiusa, e rende la finestra non ridimensionabile.
     * Crea un nuovo oggetto Pannello e lo imposta come contenuto della finestra.
     * Infine, imposta le dimensioni minime della finestra e la rende visibile.
     */
    public Finestra() {
        setTitle("Frogger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new Pannello();
        setContentPane(gamePanel);

        pack();
        setVisible(true);
    }

    /**
     * Metodo per ottenere il pannello di gioco associato a questa finestra.
     *
     * @return Il pannello di gioco (Pannello).
     */
    public Pannello getGamePanel() {
        return gamePanel;
    }
}
