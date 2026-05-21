import java.awt.*;

public abstract class GameObject {
    protected int x, y, width, height;

    public GameObject(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }
    
    //Finds bounds for object collision.
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    ////Connects information to other classes.
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}