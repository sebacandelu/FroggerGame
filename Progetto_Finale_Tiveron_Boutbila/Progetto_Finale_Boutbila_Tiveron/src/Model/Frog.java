package Model;

import java.awt.*;

/**
 * La classe Frog rappresenta un oggetto rana nel gioco.
 * Estende la classe Rectangle per ereditare le caratteristiche geometriche di un rettangolo.
 */
public class Frog extends Rectangle {
	
    /**
     * Costruttore della classe Frog.
     * @param position la posizione iniziale della rana
     * @param width la larghezza della rana
     * @param height l'altezza della rana
     */
    public Frog(Position position, int width, int height) {
        super(position, width, height); // Chiama il costruttore della classe base
    }

    /**
     * Metodo per disegnare la rana.
     * @param g il contesto grafico su cui disegnare la rana
     */
    public void paint(Graphics g) {
        // Disegna il corpo della rana
        g.setColor(new Color(74, 177, 50));
        g.fillRect(position.x + 5, position.y + 10, 10, 4);
        g.fillRect(position.x, position.y + 6, 4, 4);
        g.fillRect(position.x + width - 15, position.y + 10, 10, 4);
        g.fillRect(position.x + width - 4, position.y + 6, 4, 4);

        // Disegna i contorni del corpo della rana
        g.setColor(new Color(21, 64, 12));
        g.drawRect(position.x + 5, position.y + 10, 10, 4);
        g.drawRect(position.x, position.y + 6, 4, 4);
        g.drawRect(position.x + width - 15, position.y + 10, 10, 4);
        g.drawRect(position.x + width - 4, position.y + 6, 4, 4);

        // Disegna le zampe anteriori della rana
        g.setColor(new Color(21, 64, 12));
        g.drawRect(position.x + 5, position.y + height - 10, 10, 4);
        g.drawRect(position.x + 5, position.y + height - 10, 4, 9);
        g.drawRect(position.x + width - 17, position.y + height - 10, 10, 4);
        g.drawRect(position.x + width - 7, position.y + height - 10, 4, 9);
        g.setColor(new Color(74, 177, 50));
        g.fillRect(position.x + 6, position.y + height - 9, 10, 3);
        g.fillRect(position.x + 6, position.y + height - 9, 3, 8);
        g.fillRect(position.x + width - 17, position.y + height - 9, 10, 3);
        g.fillRect(position.x + width - 6, position.y + height - 9, 3, 8);

        // Disegna il corpo ovale della rana
        g.setColor(new Color(74, 177, 50));
        g.fillOval(position.x + width / 4 + 1, position.y, width / 2, height);
        g.setColor(new Color(21, 64, 12));
        g.drawOval(position.x + width / 4 + 1, position.y, width / 2, height);

        // Disegna gli occhi della rana
        g.setColor(Color.BLACK);
        g.fillOval(position.x + width / 2 - 6, position.y + height / 2 - 10, 5, 5);
        g.fillOval(position.x + width / 2 + 2, position.y + height / 2 - 10, 5, 5);
    }
}