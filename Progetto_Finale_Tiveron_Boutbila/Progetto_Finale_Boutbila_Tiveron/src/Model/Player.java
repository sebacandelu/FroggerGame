package Model;


import java.awt.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import View.Background;
import View.Pannello;

/**
 * Questa classe rappresenta il giocatore nel gioco Frogger.
 * Gestisce le azioni e il comportamento del giocatore durante il gioco.
 */

public class Player {
 
	/** Indica se il tasto sinistro della tastiera è premuto. */
	private boolean keyLeftIsPressed;

	/** Indica se il tasto destro della tastiera è premuto. */
	private boolean keyRightIsPressed;

	/** Indica se il tasto su della tastiera è premuto. */
	private boolean keyUpIsPressed;

	/** La velocità di movimento del giocatore. */
	private final int moveRate = 5;

	/** Il numero di vite del giocatore. */
	private int lives;

	/** La rana corrente controllata dal giocatore.	 */
	private Frog currentFrog;

	/** Lo sfondo del gioco. */
	private Background background;

	/** Lista degli oggetti in movimento nel gioco.	 */
	private List<MovingObject> movingObjects;

	/** L'oggetto attaccato al giocatore. */
	private MovingObject attachedObject;

	/** La posizione dell'oggetto attaccato al giocatore. */
	private Position objectPosition;

	/** Lista delle rane nel gioco. */
	private List<Frog> frogs;

	/** Lista visuale delle vite del giocatore. */
	private List<Frog> livesVisual;

	/** Indica se il gioco è terminato. */
	private boolean gameEnded;

	/** Il punteggio del giocatore. */
	private int score;


    /**
     * Costruttore della classe Player.
     *
     * @param background    Lo sfondo del gioco.
     * @param movingObjects Gli oggetti mobili nel gioco.
     */
    public Player(Background background, List<MovingObject> movingObjects) {
        this.background = background;
        this.movingObjects = movingObjects;
        frogs = new ArrayList<>();
        livesVisual = new ArrayList<>();
        reset();
    }

    /**
     * Reimposta lo stato del giocatore.
     */
    public void reset() {
        frogs.clear();
        keyUpIsPressed = keyLeftIsPressed = keyRightIsPressed = false;
        attachedObject = null;
        lives = 5;
        score = 0;
        livesVisual.clear();
        Position startLivesPosition = new Position(background.getScoreZone().position);
        /**
         * creaiamo una frog per ogni ninfea 
         */
        for (int i=0; i< lives; i++) {	
            livesVisual.add(new Frog(new Position(startLivesPosition), Pannello.SEGMENT_HEIGHT, Pannello.SEGMENT_HEIGHT));
            startLivesPosition.x += Pannello.SEGMENT_HEIGHT;
        }
        spawnFrog();
        gameEnded = false;
    }
    /**
     * Aggiorna lo stato del giocatore.
     *
     * @param deltaTime Il tempo trascorso dall'ultimo aggiornamento.
     */

    public void update(int deltaTime) {
        if(currentFrog == null || currentFrog.position.y <= background.getEndZone().position.y) {
            keyUpIsPressed = keyLeftIsPressed = keyRightIsPressed = false;
            attachedObject = null;
            return;
        }

        if(keyUpIsPressed) {
            keyUpIsPressed = false;
            currentFrog.position.y -= Pannello.SEGMENT_HEIGHT;
            attachedObject = findCollidedMovingObject(15);
            if(attachedObject != null && !attachedObject.isSafe()) attachedObject = null;
        }
        if(keyLeftIsPressed) {
            moveWithinBounds(new Position(-moveRate,0), Pannello.PANEL_WIDTH-currentFrog.width, Pannello.PANEL_HEIGHT);
        }
        if(keyRightIsPressed) {
            moveWithinBounds(new Position(moveRate,0), Pannello.PANEL_WIDTH-currentFrog.width, Pannello.PANEL_HEIGHT);
        }
        if(attachedObject != null) {
            MovingObject checkedAttachedObject = findCollidedMovingObject(15);
            if(checkedAttachedObject != attachedObject) {
                attachedObject = checkedAttachedObject;
            }
            if(attachedObject != null) {
                objectPosition = new Position(attachedObject.position);
            }
        }
    }

    /**
     * Esegue le azioni post-aggiornamento del giocatore.
     */
    public void postUpdate() {
        if(currentFrog == null) return;

        if(attachedObject != null) {
            Position offset = new Position(attachedObject.position);
            offset.subtract(objectPosition);
            moveWithinBounds(offset, Pannello.PANEL_WIDTH-currentFrog.width, Pannello.PANEL_HEIGHT);
        }

        boolean spawnAnotherFrog = false;
        MovingObject collidedObj = findCollidedMovingObject(3);
        Rectangle collidedLily = getCollidedLily(40);
        if((collidedObj != null && !collidedObj.isSafe())
        || (collidedObj == null && isFrogInWaterArea())
        || (currentFrog.position.y == 0)) {
            spawnAnotherFrog = true;
            if(collidedLily == null || isCollidingWithAnotherFrog()) {
                frogs.remove(frogs.size() - 1);
            } else {
                score += 100;
            }
            currentFrog = null;
        }
        if(spawnAnotherFrog && lives > 0) {
            spawnFrog();
        } else if(lives == 0 && currentFrog == null) {
            gameEnded = true;
        }
    }
    
    /**
     * Gestisce l'input del giocatore.
     *
     * @param keyCode    Il codice della tastiera dell'input.
     * @param isPressed  Indica se il tasto è premuto o rilasciato.
     */

    public void handleInput(int keyCode, boolean isPressed) {
        if(keyCode == KeyEvent.VK_LEFT) {
            keyLeftIsPressed = isPressed;
        } else if(keyCode == KeyEvent.VK_RIGHT) {
            keyRightIsPressed = isPressed;
        } else if(keyCode == KeyEvent.VK_UP && isPressed) {
            keyUpIsPressed = true;
        }
    }
    
    /**
     * Disegna il giocatore.
     *
     * @param g Il contesto grafico su cui disegnare il giocatore.
     */

    public void paint(Graphics g) {
        frogs.forEach(f -> f.paint(g));
        livesVisual.forEach(f -> f.paint(g));
        drawScore(g);
    }
    
    /**
     * Verifica se la partita è terminata.
     *
     * @return true se la partita è terminata, false altrimenti.
     */

    public boolean isGameEnded() {
        return gameEnded;
    }

    /**
     * Sposta il giocatore all'interno dei limiti specificati.
     *
     * @param translationVector Il vettore di traslazione per lo spostamento.
     * @param maxX              La coordinata x massima consentita.
     * @param maxY              La coordinata y massima consentita.
     */
    private void moveWithinBounds(Position translationVector, int maxX, int maxY) {
        int newX = currentFrog.position.x+translationVector.x;
        int newY = currentFrog.position.y+translationVector.y;
        if(newX < 0) newX = 0;
        else if(newX > maxX) newX = maxX;
        if(newY < 0) newY = 0;
        else if(newY > maxY) newY = maxY;
        currentFrog.position.setPosition(newX, newY);
    }
    
    /**
     * Trova un oggetto mobile con cui il giocatore sta collidendo.
     *
     * @param minimumPercent La percentuale minima di sovrapposizione richiesta per la collisione.
     * @return L'oggetto mobile con cui il giocatore sta collidendo, null altrimenti.
     */

    private MovingObject findCollidedMovingObject(double minimumPercent) {
        if(currentFrog == null) return null;

        for(MovingObject movingObject : movingObjects) {
            if(currentFrog.isIntersecting(movingObject)) {
                if(currentFrog.getOverlapPercent(movingObject) >= minimumPercent) {
                    return movingObject;
                }
            }
        }
        return null;
    }
    
    /**
     * Genera una nuova rana quando quella attuale muore.
     */
    private void spawnFrog() {
        lives--;
        livesVisual.remove(livesVisual.size()-1);
        Frog newFrog = new Frog(new Position((Pannello.PANEL_WIDTH/Pannello.SEGMENT_HEIGHT /2)*Pannello.SEGMENT_HEIGHT, 12*Pannello.SEGMENT_HEIGHT),
                Pannello.SEGMENT_HEIGHT, Pannello.SEGMENT_HEIGHT);
        frogs.add(newFrog);
        currentFrog = newFrog;
    }

    /**
     * Verifica se la rana è nell'area dell'acqua.
     *
     * @return true se la rana è nell'area dell'acqua, false altrimenti.
     */
    private boolean isFrogInWaterArea() {
        return (currentFrog.position.y >= background.getWaterZone().position.y &&
                currentFrog.position.y <= background.getWaterZone().position.y+background.getWaterZone().height-5);
    }
    
    /**
     * Trova una ninfea con cui la rana sta collidendo.
     *
     * @param minimumCollision La percentuale minima di sovrapposizione richiesta per la collisione.
     * @return La ninfea con cui la rana sta collidendo, null altrimenti.
     */

    private Rectangle getCollidedLily(double minimumCollision) {
        for(Rectangle lily : background.getLilies()) {
            if(lily.getOverlapPercent(currentFrog) >= minimumCollision) {
                return lily;
            }
        }
        return null;
    }

    /**
     * Verifica se la rana sta collidendo con un'altra rana.
     *
     * @return true se la rana sta collidendo con la rana
     * 
     */
    private boolean isCollidingWithAnotherFrog() {
        for(Frog f : frogs) {
            if(f != currentFrog && f.isIntersecting(currentFrog)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Disegna il punteggio del giocatore sul contesto grafico specificato.
     *
     * @param g Il contesto grafico su cui disegnare il punteggio del giocatore.
     */
    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String scoreStr = score + " :Score";
        int strWidth = g.getFontMetrics().stringWidth(scoreStr);
        g.drawString(scoreStr, Pannello.PANEL_WIDTH-strWidth-15, background.getScoreZone().position.y+30);
    }
}