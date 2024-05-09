package Model;

/**
 * Rappresenta una posizione nel piano cartesiano con coordinate intere.
 */
public class Position {

    /** Costante che rappresenta lo spostamento verso il basso. */
    public static final Position DOWN = new Position(0, 1);

    /** Costante che rappresenta lo spostamento verso l'alto. */
    public static final Position UP = new Position(0, -1);

    /** Costante che rappresenta lo spostamento verso sinistra. */
    public static final Position LEFT = new Position(-1, 0);

    /** Costante che rappresenta lo spostamento verso destra. */
    public static final Position RIGHT = new Position(1, 0);

    /** Costante che rappresenta una posizione con coordinate (0, 0). */
    public static final Position ZERO = new Position(0, 0);

    /** La coordinata x della posizione. */
    public int x;

    /** La coordinata y della posizione. */
    public int y;

    /**
     * Costruisce una nuova posizione con le coordinate specificate.
     *
     * @param x La coordinata x della posizione.
     * @param y La coordinata y della posizione.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Costruisce una nuova posizione copiando le coordinate dalla posizione specificata.
     *
     * @param positionToCopy La posizione da cui copiare le coordinate.
     */
    public Position(Position positionToCopy) {
        this.x = positionToCopy.x;
        this.y = positionToCopy.y;
    }

    /**
     * Imposta le coordinate della posizione.
     *
     * @param x La nuova coordinata x della posizione.
     * @param y La nuova coordinata y della posizione.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Aggiunge le coordinate di un'altra posizione a questa posizione.
     *
     * @param otherPosition L'altra posizione la cui coordinata deve essere aggiunta.
     */
    public void add(Position otherPosition) {
        this.x += otherPosition.x;
        this.y += otherPosition.y;
    }

    /**
     * Calcola la distanza euclidea tra questa posizione e un'altra posizione specificata.
     *
     * @param otherPosition L'altra posizione dalla quale calcolare la distanza.
     * @return La distanza euclidea tra questa posizione e l'altra posizione.
     */
    public double distanceTo(Position otherPosition) {
        return Math.sqrt(Math.pow(x - otherPosition.x, 2) + Math.pow(y - otherPosition.y, 2));
    }

    /**
     * Moltiplica le coordinate della posizione per la quantità specificata.
     *
     * @param amount La quantità per cui moltiplicare le coordinate della posizione.
     */
    public void multiply(int amount) {
        x *= amount;
        y *= amount;
    }

    /**
     * Sottrae le coordinate di un'altra posizione da questa posizione.
     *
     * @param otherPosition L'altra posizione la cui coordinata deve essere sottratta.
     */
    public void subtract(Position otherPosition) {
        this.x -= otherPosition.x;
        this.y -= otherPosition.y;
    }

    /**
     * Verifica se questa posizione è uguale a un altro oggetto specificato.
     *
     * @param o L'oggetto da confrontare con questa posizione.
     * @return true se l'oggetto specificato è uguale a questa posizione, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * Restituisce una rappresentazione testuale di questa posizione.
     *
     * @return Una stringa che rappresenta questa posizione nel formato "(x, y)".
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}