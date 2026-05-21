import java.awt.*;

public class Brick extends GameObject {
    private boolean destroyed = false;
    private Color color;

    public Brick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }
    
    //Connects information to other classes.
    public boolean isDestroyed() { return destroyed; }
    public void setDestroyed(boolean destroyed) { this.destroyed = destroyed; }
    public Color getColor() { return color; }
}