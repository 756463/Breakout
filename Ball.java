public class Ball extends GameObject {
    //The speed of the ball
    private int dx = 3;
    private int dy = -3;
    
    //Connects information to other classes.
    public Ball(int x, int y, int width, int height) { super(x, y, width, height); }
    public int getDx() { return dx; }
    public void setDx(int dx) { this.dx = dx; }
    public int getDy() { return dy; }
    public void setDy(int dy) { this.dy = dy; }
}