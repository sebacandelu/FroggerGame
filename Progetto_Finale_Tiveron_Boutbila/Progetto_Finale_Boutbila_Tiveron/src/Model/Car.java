package Model;

import java.awt.*;

import View.Pannello;

/**
 * Questa classe rappresenta un oggetto auto nel gioco Frogger.
 * Estende la classe MovingObject e implementa il comportamento specifico delle auto.
 */
public class Car extends MovingObject {
	/**
	 * Array di colori rappresentanti i colori delle macchine.
	 */
	private static final Color[] CAR_COLOURS = { Color.CYAN, new Color(27, 57, 167), Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN };
	/**
	 * La distanza di movimento delle macchine.
	 */
	private static final int MOVE_DISTANCE = 2;
	/**
	 * Il ritardo tra i movimenti delle macchine.
	 */
	private static final int MOVE_DELAY = 20;
	
	/**
	 * Larghezza in unità della macchina.
	 */
    private int unitWidth;
    
    /**
     * Costruttore della classe Car.
     * Crea un oggetto auto con le specifiche date.
     *
     * @param offsetX       La coordinata x iniziale dell'auto.
     * @param yRow          La riga in cui si trova l'auto.
     * @param unitWidth     La larghezza dell'auto in segmenti.
     * @param isMovingLeft  Indica se l'auto si muove verso sinistra.
     */
    public Car(int offsetX, int yRow, int unitWidth, boolean isMovingLeft) {
        super(new Position(offsetX+(isMovingLeft?1:-1)*unitWidth*Pannello.SEGMENT_HEIGHT,
        yRow*Pannello.SEGMENT_HEIGHT+(Pannello.SEGMENT_HEIGHT-MovingObject.OBJECT_HEIGHT)/2),
        unitWidth * Pannello.SEGMENT_HEIGHT, CAR_COLOURS[(int)(Math.random()*CAR_COLOURS.length)],
        isMovingLeft, MOVE_DISTANCE, MOVE_DELAY);
        this.unitWidth = unitWidth;
    }

    /**
     * Metodo per disegnare l'auto.
     * Disegna l'auto con un aspetto diverso a seconda della sua larghezza.
     *
     * @param g Il contesto grafico su cui disegnare l'auto.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        if(unitWidth < 3) {

        	int windowFrontX = moveDirection.equals(Position.LEFT) ? position.x + 20 : position.x + width - 30;
            g.fillRect(windowFrontX, position.y+5, 10, height-10);
            int windowBackX = moveDirection.equals(Position.RIGHT) ? position.x + 10 : position.x + width - 15;
            g.fillRect(windowBackX, position.y+5, 5, height-10);
        } else {

        	int windowFrontX = moveDirection.equals(Position.LEFT) ? position.x + 10 : position.x + width - 20;
            g.fillRect(windowFrontX, position.y+5, 10, height-10);
            int wheelBackX = moveDirection.equals(Position.RIGHT) ? position.x + 10 : position.x + width - 20;
            g.setColor(new Color(52, 49, 49));
            g.fillRect(wheelBackX, position.y-3, 10, 6);
            g.fillRect(wheelBackX, position.y+height-3, 10, 6);
            g.fillRect(windowFrontX, position.y-3, 10, 3);
            g.fillRect(windowFrontX, position.y+height, 10, 3);
            int cabinBackX = moveDirection.equals(Position.LEFT) ? position.x + 25 : position.x + width - 35;
            g.drawLine(cabinBackX, position.y+1, cabinBackX, position.y+height-1);
        }
    }

    /**
     * Metodo per reimpostare l'auto quando raggiunge un'estremità del pannello.
     * Riesegue l'inizializzazione dell'auto con una nuova larghezza e posizione.
     */
    @Override
    public void reset() {
        super.reset();
        unitWidth = getRandomSegmentLength();
        width = unitWidth * Pannello.SEGMENT_HEIGHT;
        position.x = moveDirection.equals(Position.RIGHT) ? -(getMaxLength() * Pannello.SEGMENT_HEIGHT)
                :(Pannello.PANEL_WIDTH/Pannello.SEGMENT_HEIGHT+1)*(Pannello.SEGMENT_HEIGHT);
        drawColour = CAR_COLOURS[(int)(Math.random()*CAR_COLOURS.length)];
    }

    /**
     * Metodo statico per ottenere una lunghezza casuale per un segmento.
     *
     * @return Una lunghezza casuale per un segmento.
     */
    public static int getRandomSegmentLength() {
        return (int)(Math.random()*2)+2;
    }

    /**
     * Metodo statico per ottenere la lunghezza massima di un segmento.
     *
     * @return La lunghezza massima di un segmento.
     */
    public static int getMaxLength() {
        return 3;
    }
}

