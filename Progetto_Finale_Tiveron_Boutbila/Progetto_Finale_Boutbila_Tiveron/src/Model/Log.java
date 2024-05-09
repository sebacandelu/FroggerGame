package Model;

import java.awt.*;

import View.Pannello;

/**
 * La classe Log rappresenta un oggetto tronco nel gioco.
 * Estende la classe MovingObject per ereditare le caratteristiche di un oggetto mobile.
 */
public class Log extends MovingObject {
 
	/**
	 * Colore del bordo del tronco
	 */
    private final Color endColour = new Color(94, 63, 24); 
    
    /**
     *  Distanza di spostamento del tronco
     */
    private static final int MOVE_DISTANCE = Pannello.SEGMENT_HEIGHT; 
    /**
     *  Ritardo di movimento del tronco
     */
    private static final int MOVE_DELAY = 1200; 
    /**
     * Costruttore della classe Log.
     * @param offsetX la posizione orizzontale iniziale del tronco
     * @param yRow la fila verticale in cui si trova il tronco
     * @param unitWidth la larghezza del tronco in unità di segmenti
     * @param isMovingLeft indica se il tronco si muove verso sinistra
     */
    public Log(int offsetX, int yRow, int unitWidth, boolean isMovingLeft) {
        super(new Position(offsetX - unitWidth * Pannello.SEGMENT_HEIGHT,
                        yRow * Pannello.SEGMENT_HEIGHT + (Pannello.SEGMENT_HEIGHT - MovingObject.OBJECT_HEIGHT) / 2),
                    unitWidth * Pannello.SEGMENT_HEIGHT, new Color(135, 91, 35),
                            isMovingLeft, MOVE_DISTANCE, MOVE_DELAY);
        isSafe = true; // Indica che il tronco è un'area sicura per il giocatore
    }

    /**
     * Metodo per disegnare il tronco.
     * @param g il contesto grafico su cui disegnare il tronco
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Chiama il metodo paint della classe base
        // Disegna le estremità del tronco
        g.setColor(moveDirection.equals(Position.LEFT) ? endColour : drawColour);
        g.fillOval(position.x - 5, position.y, 10, height);
        g.setColor(moveDirection.equals(Position.LEFT) ? drawColour : endColour);
        g.fillOval(position.x + width - 5, position.y, 10, height);
    }

    /**
     * Metodo per reimpostare il tronco alle sue condizioni iniziali.
     */
    @Override
    public void reset() {
        super.reset(); // Chiama il metodo reset della classe base
        width = getRandomSegmentLength() * Pannello.SEGMENT_HEIGHT; // Genera una nuova larghezza per il tronco
        position.x = moveDirection.equals(Position.RIGHT) ? -(getMaxLength() * Pannello.SEGMENT_HEIGHT)
                                : (Pannello.PANEL_WIDTH / Pannello.SEGMENT_HEIGHT + 1) * (Pannello.SEGMENT_HEIGHT); // Riposiziona il tronco
    }

    /**
     * Metodo statico per generare casualmente la lunghezza di un segmento di tronco.
     * @return la lunghezza del segmento di tronco generata casualmente
     */
    public static int getRandomSegmentLength() {
        return (int)(Math.random() * 4 + 1);
    }

    /**
     * Metodo statico per ottenere la lunghezza massima di un segmento di tronco.
     * @return la lunghezza massima del segmento di tronco
     */
    public static int getMaxLength() {
        return 5;
    }
}