package Model;

import java.awt.*;

import View.Pannello;

/**
 * La classe MovingObject rappresenta un oggetto mobile nel gioco Frogger.
 * Estende la classe Rectangle per ereditare le caratteristiche geometriche di un rettangolo.
 */
public class MovingObject extends Rectangle {

    /** Altezza predefinita degli oggetti mobili */
    public static final int OBJECT_HEIGHT = Pannello.SEGMENT_HEIGHT * 4 / 5;

    /** Direzione di movimento dell'oggetto */
    protected Position moveDirection;

    /** Timer per il movimento dell'oggetto */
    private ActionTimer moveTimer;

    /** Distanza di spostamento dell'oggetto */
    private int moveDistance;

    /** Colore di disegno dell'oggetto */
    protected Color drawColour;

    /** Indica se l'oggetto è scaduto */
    private boolean isExpired;

    /** Indica se l'oggetto è una zona sicura */
    protected boolean isSafe;

    /**
     * Costruttore della classe MovingObject.
     * @param position la posizione iniziale dell'oggetto
     * @param width la larghezza dell'oggetto
     * @param drawColour il colore di disegno dell'oggetto
     * @param isMovingLeft indica se l'oggetto si muove verso sinistra
     * @param moveDistance la distanza di spostamento dell'oggetto
     * @param moveDelay il ritardo di movimento dell'oggetto
     */
    public MovingObject(Position position, int width, Color drawColour, boolean isMovingLeft, int moveDistance, int moveDelay) {
        super(position, width, OBJECT_HEIGHT);
        this.moveDirection = isMovingLeft ? Position.LEFT : Position.RIGHT;
        moveTimer = new ActionTimer(moveDelay);
        this.moveDistance = moveDistance;
        this.drawColour = drawColour;
        isExpired = false;
        isSafe = false;
    }

    /**
     * Metodo per aggiornare lo stato dell'oggetto mobile.
     * @param deltaTime il tempo trascorso dall'ultimo aggiornamento
     */
    public void update(int deltaTime) {
        moveTimer.update(deltaTime);
        if(moveTimer.isTriggered()) {
            moveTimer.reset();
            Position translation = new Position(moveDirection);
            translation.multiply(moveDistance);
            position.add(translation);

            // Verifica se l'oggetto è uscito dallo schermo
            if((moveDirection.equals(Position.LEFT) && position.x < -width)
                || (moveDirection.equals(Position.RIGHT) && position.x > Pannello.PANEL_WIDTH)) {
                isExpired = true;
            }
        }
    }

    /**
     * Metodo per disegnare l'oggetto mobile.
     * @param g il contesto grafico su cui disegnare l'oggetto
     */
    public void paint(Graphics g) {
        g.setColor(drawColour);
        g.fillRect(position.x, position.y, width, height);
    }

    /**
     * Metodo per reimpostare lo stato dell'oggetto alle condizioni iniziali.
     */
    public void reset() {
        isExpired = false;
    }

    /**
     * Metodo per verificare se l'oggetto è scaduto.
     * @return true se l'oggetto è scaduto, altrimenti false
     */
    public boolean isExpired() {
        return isExpired;
    }

    /**
     * Metodo per verificare se l'oggetto è una zona sicura.
     * @return true se l'oggetto è una zona sicura, altrimenti false
     */
    public boolean isSafe() {
        return isSafe;
    }
}