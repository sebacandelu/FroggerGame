package View;

import javax.swing.*;


import Model.Car;
import Model.Log;
import Model.MovingObject;
import Model.Player;
import Model.Turtle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta il pannello di gioco dell'applicazione Frogger.
 * Estende la classe JPanel e implementa l'interfaccia ActionListener per gestire gli eventi del timer.
 */
public class Pannello extends JPanel implements ActionListener  {
    private static final long serialVersionUID = 1L;

    /**
     * Intervallo di tempo per il timer del gioco in millisecondi.
     */
    public static final int TIME_INTERVAL = 12;
    /**
     * Altezza del pannello di gioco.
     */
    public static final int PANEL_HEIGHT = 750;
    /**
     * Larghezza del pannello di gioco.
     */
    public static final int PANEL_WIDTH = 650;
    /**
     * Altezza di un segmento del pannello (usato per il posizionamento degli oggetti mobili).
     */
    public static final int SEGMENT_HEIGHT = Pannello.PANEL_HEIGHT / 14;

    private Background background;
    private Timer gameTimer;
    private List<MovingObject> objectList;
    private Player player;

    /**
     * Costruttore della classe Pannello.
     * Inizializza il pannello di gioco con la dimensione specificata, crea lo sfondo,
     * inizializza la lista degli oggetti mobili e il timer del gioco, quindi crea il giocatore
     * e avvia il timer.
     */
    public Pannello() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        background = new Background();
        objectList = new ArrayList<>();
        gameTimer = new Timer(TIME_INTERVAL, this);
        initMovingObjects();
        player = new Player(background, objectList);
        gameTimer.start();
    }

    /**
     * Metodo per disegnare il contenuto del pannello di gioco.
     * Disegna lo sfondo, gli oggetti mobili e il giocatore. Se la partita è terminata,
     * visualizza un messaggio di "GAME OVER".
     *
     * @param g Il contesto grafico su cui disegnare.
     */
    public void paint(Graphics g) {
        background.paint(g);
        objectList.forEach(obj -> obj.paint(g));
        player.paint(g);
        if (player.isGameEnded()) {
            drawGameOver(g);
        }
    }

    /**
     * Metodo per riavviare il gioco.
     * Resettando lo stato del giocatore e reinizializzando gli oggetti mobili.
     */
    public void restart() {
        player.reset();
        initMovingObjects();
    }

    /**
     * Gestisce gli input da tastiera durante il gioco.
     * Premendo ESC, si chiude l'applicazione; premendo R, si riavvia il gioco;
     * altrimenti, si passa l'input al giocatore per il movimento.
     *
     * @param keyCode   Il codice del tasto premuto.
     * @param isPressed Indica se il tasto è premuto o rilasciato.
     */
    public void handleInput(int keyCode, boolean isPressed) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (keyCode == KeyEvent.VK_R) {
            restart();
        } else {
            player.handleInput(keyCode, isPressed);
        }
        repaint();
    }

    /**
     * Metodo per gestire l'azione del timer del gioco.
     * Se la partita non è terminata, aggiorna lo stato del giocatore e degli oggetti mobili.
     * Se un oggetto mobile è scaduto, lo resetta. Infine, ridisegna il pannello.
     *
     * @param e L'evento dell'azione del timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!player.isGameEnded()) {
            player.update(TIME_INTERVAL);
            for (int i = 0; i < objectList.size(); i++) {
                objectList.get(i).update(TIME_INTERVAL);
                if (objectList.get(i).isExpired()) {
                    objectList.get(i).reset();
                }
            }
            player.postUpdate();
        }
        repaint();
    }

    /**
     * Metodo per inizializzare gli oggetti mobili del gioco.
     * Pulisce la lista degli oggetti mobili e inizializza tronchi, auto e tartarughe.
     */
    private void initMovingObjects() {
        objectList.clear();
        initLogs();
        initCars();
        initTurtles();
    }

    /**
     * Metodo per inizializzare i tronchi nel gioco.
     * Aggiunge tronchi alla lista degli oggetti mobili con posizioni e lunghezze casuali.
     */
    private void initLogs() {
        int middleSegment = PANEL_WIDTH / SEGMENT_HEIGHT / 2 + 1;
        objectList.add(new Log(0, 1, Log.getRandomSegmentLength(), false));
        objectList.add(new Log(-middleSegment * SEGMENT_HEIGHT, 1, Log.getRandomSegmentLength(), false));
        objectList.add(new Log(-SEGMENT_HEIGHT, 3, Log.getRandomSegmentLength(), false));
        objectList.add(new Log(-(middleSegment + 1) * SEGMENT_HEIGHT, 3, Log.getRandomSegmentLength(), false));
        objectList.add(new Log(0, 5, Log.getRandomSegmentLength(), false));
        objectList.add(new Log(-middleSegment * SEGMENT_HEIGHT, 5, Log.getRandomSegmentLength(), false));
    }

    /**
     * Metodo per inizializzare le auto nel gioco.
     * Aggiunge auto alla lista degli oggetti mobili con posizioni e lunghezze casuali.
     */
    private void initCars() {
        int middleSegment = PANEL_WIDTH / SEGMENT_HEIGHT / 2 + 1;
        objectList.add(new Car(0, 7, Car.getRandomSegmentLength(), false));
        objectList.add(new Car(-middleSegment * SEGMENT_HEIGHT, 7, Car.getRandomSegmentLength(), false));
        objectList.add(new Car(-SEGMENT_HEIGHT, 9, Car.getRandomSegmentLength(), false));
        objectList.add(new Car(-(middleSegment + 1) * SEGMENT_HEIGHT, 9, Car.getRandomSegmentLength(), false));
        objectList.add(new Car(0, 11, Car.getRandomSegmentLength(), false));
        objectList.add(new Car(-middleSegment * SEGMENT_HEIGHT, 11, Car.getRandomSegmentLength(), false));

        objectList.add(new Car(0, 8, Car.getRandomSegmentLength(), true));
        objectList.add(new Car(middleSegment * SEGMENT_HEIGHT, 8, Car.getRandomSegmentLength(), true));
        objectList.add(new Car(SEGMENT_HEIGHT, 10, Car.getRandomSegmentLength(), true));
        objectList.add(new Car((middleSegment + 1) * SEGMENT_HEIGHT, 10, Car.getRandomSegmentLength(), true));
    }

    /**
     * Metodo per inizializzare le tartarughe nel gioco.
     * Aggiunge tartarughe alla lista degli oggetti mobili con posizioni e lunghezze casuali.
     */
    private void initTurtles() {
        int middleSegment = PANEL_WIDTH / SEGMENT_HEIGHT / 2 + 1;
        objectList.add(new Turtle(0, 2, Turtle.getRandomSegmentLength(), true));
        objectList.add(new Turtle(middleSegment * SEGMENT_HEIGHT, 2, Turtle.getRandomSegmentLength(), true));
        objectList.add(new Turtle(SEGMENT_HEIGHT, 4, Turtle.getRandomSegmentLength(), true));
        objectList.add(new Turtle((middleSegment + 1) * SEGMENT_HEIGHT, 4, Turtle.getRandomSegmentLength(), true));
    }

    /**
     * Metodo per disegnare il messaggio di "PARTITA CONCLUSA" quando la partita è terminata.
     *
     * @param g Il contesto grafico su cui disegnare.
     */
    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, PANEL_HEIGHT / 2 - 20, PANEL_WIDTH, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String gameOverMessage = "PARTITA CONCLUSA! Premi R per rigiocare";
        int strWidth = g.getFontMetrics().stringWidth(gameOverMessage);
        g.drawString(gameOverMessage, PANEL_WIDTH / 2 - strWidth / 2, PANEL_HEIGHT / 2 + 10);
        
    }
}