package Model;

import java.awt.*;

import View.Pannello;

/**
 * Rappresenta un oggetto tartaruga che si muove orizzontalmente lungo la schermata di gioco.
 */
public class Turtle extends MovingObject {

    /** La distanza che la tartaruga si muove ad ogni passo. */
    private static final int MOVE_DISTANCE = Pannello.SEGMENT_HEIGHT;

    /** Il ritardo tra i movimenti della tartaruga. */
    private static final int MOVE_DELAY = 1000;

    /** La larghezza di un segmento di unità della tartaruga. */
    private int unitWidth;

    /** Il colore del contorno della tartaruga. */
    private final Color outlineColour = new Color(30, 78, 18);

    /**
     * Crea una nuova tartaruga con l'offset specificato, posizionata nella riga yRow,
     * larghezza unitaria e direzione di movimento.
     *
     * @param offsetX L'offset orizzontale della tartaruga.
     * @param yRow La riga in cui posizionare la tartaruga.
     * @param unitWidth La larghezza unitaria della tartaruga.
     * @param isMovingLeft True se la tartaruga si sta muovendo verso sinistra, altrimenti false.
     */
    public Turtle(int offsetX, int yRow, int unitWidth, boolean isMovingLeft) {
        super(new Position(offsetX - unitWidth * Pannello.SEGMENT_HEIGHT,
                        yRow * Pannello.SEGMENT_HEIGHT + (Pannello.SEGMENT_HEIGHT - MovingObject.OBJECT_HEIGHT) / 2),
                unitWidth * Pannello.SEGMENT_HEIGHT, new Color(64, 146, 35),
                isMovingLeft, MOVE_DISTANCE, MOVE_DELAY);
        this.unitWidth = unitWidth;
        isSafe = true;
    }

    /**
     * Disegna la tartaruga sulla schermata di gioco.
     *
     * @param g Il contesto grafico su cui disegnare la tartaruga.
     */
    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < unitWidth; i++) {
            g.setColor(drawColour);
            g.fillOval(position.x + i * Pannello.SEGMENT_HEIGHT - 4, position.y + height / 2 - 4, 8, 8);
            g.setColor(outlineColour);
            g.drawOval(position.x + i * Pannello.SEGMENT_HEIGHT - 4, position.y + height / 2 - 4, 8, 8);

            g.setColor(drawColour);
            g.fillOval(position.x + i * Pannello.SEGMENT_HEIGHT + 2, position.y, Pannello.SEGMENT_HEIGHT - 4, height);
            g.setColor(outlineColour);
            g.drawOval(position.x + i * Pannello.SEGMENT_HEIGHT + 2, position.y, Pannello.SEGMENT_HEIGHT - 4, height);

            g.setColor(Color.BLACK);
            g.fillOval(position.x + i * Pannello.SEGMENT_HEIGHT - 2, position.y + height / 2 - 4, 2, 2);
            g.fillOval(position.x + i * Pannello.SEGMENT_HEIGHT - 2, position.y + height / 2, 2, 2);
        }
    }

    /**
     * Resetta lo stato della tartaruga.
     */
    @Override
    public void reset() {
        super.reset();
        unitWidth = getRandomSegmentLength();
        width = unitWidth * Pannello.SEGMENT_HEIGHT;
        position.x = moveDirection.equals(Position.RIGHT) ? -(getMaxLength() * Pannello.SEGMENT_HEIGHT)
                : (Pannello.PANEL_WIDTH / Pannello.SEGMENT_HEIGHT + 1) * (Pannello.SEGMENT_HEIGHT);
    }

    /**
     * Restituisce la lunghezza casuale di un segmento della tartaruga.
     *
     * @return La lunghezza casuale di un segmento della tartaruga.
     */
    public static int getRandomSegmentLength() {
        return (int) (Math.random() * 3 + 1);
    }

    /**
     * Restituisce la lunghezza massima della tartaruga in segmenti.
     *
     * @return La lunghezza massima della tartaruga in segmenti.
     */
    public static int getMaxLength() {
        return 3;
    }
}
