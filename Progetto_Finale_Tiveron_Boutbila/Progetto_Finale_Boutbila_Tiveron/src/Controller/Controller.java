package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import View.Finestra;

/**
 * La classe Controller gestisce gli eventi di input dall'utente.
 * Implementa ActionListener per gestire eventi generati da componenti Swing e KeyListener per gestire gli eventi della tastiera.
 */
public class Controller implements ActionListener, KeyListener {
    private Finestra f; // La finestra dell'applicazione

    /**
     * Costruttore della classe Controller.
     * @param f la finestra dell'applicazione a cui è associato il controller
     */
    public Controller(Finestra f) {
        this.f = f; // Imposta la finestra
        f.addKeyListener(this); // Aggiunge il controller come ascoltatore degli eventi della tastiera alla finestra
    }
    
    /**
     * Gestisce l'evento di pressione di un tasto sulla tastiera.
     * @param e l'evento associato alla pressione del tasto
     */
    @Override
    public void keyPressed(KeyEvent e) {
        f.getGamePanel().handleInput(e.getKeyCode(), true); // Passa l'evento di pressione del tasto al pannello di gioco
    }

    /**
     * Gestisce l'evento di rilascio di un tasto sulla tastiera.
     * @param e l'evento associato al rilascio del tasto
     */
    @Override
    public void keyReleased(KeyEvent e) {
        f.getGamePanel().handleInput(e.getKeyCode(), false); // Passa l'evento di rilascio del tasto al pannello di gioco
    }
 
    /**
     * Metodo non utilizzato ma richiesto dall'interfaccia KeyListener.
     * @param e l'evento associato alla pressione di un tasto
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Metodo non utilizzato ma richiesto dall'interfaccia ActionListener.
     * @param e l'evento associato all'azione
     */
    @Override
    public void actionPerformed(ActionEvent e) {}
}