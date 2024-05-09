package Model;

/**
 * Rappresenta un rettangolo con posizione, larghezza e altezza definite.
 */
public class Rectangle {

    /** La posizione del rettangolo. */
    protected Position position;

    /** La larghezza del rettangolo. */
    protected int width;

    /** L'altezza del rettangolo. */
    protected int height;

    /**
     * Costruisce un nuovo rettangolo con la posizione, la larghezza e l'altezza specificate.
     *
     * @param position La posizione del rettangolo.
     * @param width La larghezza del rettangolo.
     * @param height L'altezza del rettangolo.
     */
    public Rectangle(Position position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    /**
     * Costruisce un nuovo rettangolo con la posizione e le dimensioni specificate.
     *
     * @param x La coordinata x della posizione del rettangolo.
     * @param y La coordinata y della posizione del rettangolo.
     * @param width La larghezza del rettangolo.
     * @param height L'altezza del rettangolo.
     */
    public Rectangle(int x, int y, int width, int height) {
        this(new Position(x, y), width, height);
    }

    /**
     * Restituisce l'altezza del rettangolo.
     *
     * @return L'altezza del rettangolo.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Restituisce la larghezza del rettangolo.
     *
     * @return La larghezza del rettangolo.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Restituisce la posizione del rettangolo.
     *
     * @return La posizione del rettangolo.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Restituisce il centro del rettangolo.
     *
     * @return Il centro del rettangolo.
     */
    public Position getCentre() {
        return new Position(position.x + width / 2, position.y + height / 2);
    }

    /**
     * Verifica se una posizione specificata è all'interno del rettangolo.
     *
     * @param targetPosition La posizione da verificare.
     * @return true se la posizione è all'interno del rettangolo, altrimenti false.
     */
    public boolean isPositionInside(Position targetPosition) {
        return targetPosition.x >= position.x && targetPosition.y >= position.y
                && targetPosition.x < position.x + width && targetPosition.y < position.y + height;
    }

    /**
     * Verifica se questo rettangolo interseca un altro rettangolo specificato.
     *
     * @param otherRectangle L'altro rettangolo con cui verificare l'intersezione.
     * @return true se i rettangoli si intersecano, altrimenti false.
     */
    public boolean isIntersecting(Rectangle otherRectangle) {
        if (position.y + height < otherRectangle.position.y) return false;
        if (position.y > otherRectangle.position.y + otherRectangle.height) return false;
        if (position.x + width < otherRectangle.position.x) return false;
        if (position.x > otherRectangle.position.x + otherRectangle.width) return false;
        return true;
    }

    /**
     * Calcola la percentuale di sovrapposizione tra questo rettangolo e un altro rettangolo specificato.
     *
     * @param otherRectangle L'altro rettangolo con cui calcolare la sovrapposizione.
     * @return La percentuale di sovrapposizione tra i due rettangoli.
     */
    public double getOverlapPercent(Rectangle otherRectangle) {
        double SI = Math.max(0, Math.min(position.x + width, otherRectangle.position.x + otherRectangle.width)
                - Math.max(position.x, otherRectangle.position.x))
                * Math.max(0, Math.min(position.y + height, otherRectangle.position.y + otherRectangle.height)
                - Math.max(position.y, otherRectangle.position.y));
        double SU = (width * height) + (otherRectangle.width * otherRectangle.height) - SI;
        return SI / SU * 100;
    }
}
