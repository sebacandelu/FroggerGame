package View;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Model.Rectangle;
import Model.Position;

/**
 * Questa classe rappresenta lo sfondo del gioco Frogger.
 * Contiene diverse zone e oggetti grafici, come la strada, l'acqua, le ninfee, ecc.
 */
public class Background {
  
    private Rectangle endZone;
    private Rectangle middleZone;
    private Rectangle startZone;
    private Rectangle roadZone;
    private Rectangle waterZone;
    private Rectangle scoreZone;
    private List<Rectangle> lilies;

    /**
     * Costruttore della classe Background.
     * Inizializza le diverse zone e gli oggetti grafici che compongono lo sfondo del gioco.
     */
    public Background() {
        endZone = new Rectangle(new Position(0,0),Pannello.PANEL_WIDTH, Pannello.SEGMENT_HEIGHT);
        middleZone = new Rectangle(new Position(0,6*Pannello.SEGMENT_HEIGHT),Pannello.PANEL_WIDTH,Pannello.SEGMENT_HEIGHT);
        startZone = new Rectangle(new Position(0,12*Pannello.SEGMENT_HEIGHT),Pannello.PANEL_WIDTH,Pannello.SEGMENT_HEIGHT);
        roadZone = new Rectangle(new Position(0,7*Pannello.SEGMENT_HEIGHT),Pannello.PANEL_WIDTH,5*Pannello.SEGMENT_HEIGHT);
        waterZone = new Rectangle(new Position(0,Pannello.SEGMENT_HEIGHT),Pannello.PANEL_WIDTH,5*Pannello.SEGMENT_HEIGHT);
        scoreZone = new Rectangle(new Position(0,13*Pannello.SEGMENT_HEIGHT), Pannello.PANEL_WIDTH, Pannello.PANEL_HEIGHT-Pannello.SEGMENT_HEIGHT*12);

        lilies = new ArrayList<>();
        int totalWidth = Pannello.SEGMENT_HEIGHT; // Larghezza delle singola ninfea
        int startX = (Pannello.PANEL_WIDTH - totalWidth) / 2; // Calcolo della coordinata x iniziale
        lilies.add(new Rectangle(startX,endZone.getPosition().y, Pannello.SEGMENT_HEIGHT, Pannello.SEGMENT_HEIGHT));
      
        for (int i = 2; i < 6; i+=2) {
            lilies.add(new Rectangle(startX - (totalWidth*i),endZone.getPosition().y, Pannello.SEGMENT_HEIGHT, Pannello.SEGMENT_HEIGHT));
        }
        for (int i = 2; i < 6; i+=2) {
            lilies.add(new Rectangle(startX + (totalWidth*i),endZone.getPosition().y, Pannello.SEGMENT_HEIGHT, Pannello.SEGMENT_HEIGHT));
        }
    }

    /**
     * Metodo per disegnare lo sfondo del gioco.
     * Disegna la strada, l'acqua, le ninfee e le altre zone del gioco.
     *
     * @param g Il contesto grafico su cui disegnare lo sfondo.
     */
    public void paint(Graphics g) {
        g.setColor(new Color(108, 186, 88));
        g.fillRect(middleZone.getPosition().x,middleZone.getPosition().y,middleZone.getWidth(),middleZone.getHeight());
        g.fillRect(startZone.getPosition().x,startZone.getPosition().y,startZone.getWidth(),startZone.getHeight());

        g.setColor(Color.BLACK);
        g.fillRect(roadZone.getPosition().x, roadZone.getPosition().y, roadZone.getWidth(), roadZone.getHeight());
        g.fillRect(scoreZone.getPosition().x, scoreZone.getPosition().y, scoreZone.getWidth(), scoreZone.getHeight());
        g.setColor(Color.WHITE);
        for(int y = roadZone.getPosition().y+Pannello.SEGMENT_HEIGHT; y < startZone.getPosition().y; y+= Pannello.SEGMENT_HEIGHT) {
            for(int x = 0; x < Pannello.PANEL_WIDTH; x+= 20) {
                g.fillRect(x,y,10,4);
            }
        }

        g.setColor(Color.CYAN);
        g.fillRect(waterZone.getPosition().x, waterZone.getPosition().y, waterZone.getWidth(), waterZone.getHeight());
        g.fillRect(endZone.getPosition().x,endZone.getPosition().y,endZone.getWidth(),endZone.getHeight());

        g.setColor(new Color(108, 186, 88));
        for(Rectangle lily : lilies) {
            g.fillArc(lily.getPosition().x, lily.getPosition().y, lily.getWidth(), lily.getHeight(), 180, 330);
        }
    }

    /**
     * Metodo per ottenere la zona dell'acqua.
     *
     * @return La zona dell'acqua.
     */
    public Rectangle getWaterZone() {
        return waterZone;
    }

    /**
     * Metodo per ottenere la zona finale.
     *
     * @return La zona finale.
     */
    public Rectangle getEndZone() {
        return endZone;
    }

    /**
     * Metodo per ottenere la lista delle ninfee.
     *
     * @return La lista delle ninfee.
     */
    public List<Rectangle> getLilies() {
        return lilies;
    }

    /**
     * Metodo per ottenere la zona del punteggio.
     *
     * @return La zona del punteggio.
     */
    public Rectangle getScoreZone() {
        return scoreZone;
    }   
}

