import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BreakoutController implements ActionListener {
    private GamePanel view;
    private BreakoutApp app;
    private Timer timer;
    
    // Model Data
    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean gameOver = false;
    
    private long startTime;
    private long score = 0;
    private int combo = 1;
    private boolean gameWon = false;

    public BreakoutController(GamePanel view, BreakoutApp app) {
        this.view = view;
        this.app = app;
        initGame();
    }

    //Creates sprites and starts timer.
    private void initGame() {
        paddle = new Paddle(250, 380, 80, 10);
        ball = new Ball(285, 360, 10, 10);
        bricks = new ArrayList<>();

        Color[] rowColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                bricks.add(new Brick(col * 58 + 10, row * 20 + 40, 55, 15, rowColors[row]));
            }
        }

        startTime = System.currentTimeMillis();
        timer = new Timer(10, this); // ~100 FPS
        timer.start();
    }
    
    //Detects inputs for paddle movement.
    public void handleInput(int keyCode, boolean isPressed) {
        if (keyCode == KeyEvent.VK_A) leftPressed = isPressed;
        if (keyCode == KeyEvent.VK_D) rightPressed = isPressed;
    }
    
    //Updates frames while game is running.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !gameWon) {
            updateLogic();
        }
        view.repaint(); // Tell the View to render the new Model state
    }
    
    //Calculates and applies sprite movement and changes.
    private void updateLogic() {
        // Update Paddle
        if (leftPressed && paddle.getX() > 0) {
            paddle.setX(paddle.getX() - 5);
        }
        if (rightPressed && paddle.getX() < view.getWidth() - paddle.getWidth()) {
            paddle.setX(paddle.getX() + 5);
        }

        // Update Ball
        ball.setX(ball.getX() + ball.getDx());
        ball.setY(ball.getY() + ball.getDy());

        // Wall Collisions
        if (ball.getX() <= 0 || ball.getX() >= view.getWidth() - ball.getWidth()) {
            ball.setDx(-ball.getDx());
        }
        if (ball.getY() <= 0) {
            ball.setDy(-ball.getDy());
        }
        if (ball.getY() >= view.getHeight()) {
            gameOver = true;
            timer.stop();
            long endTime = System.currentTimeMillis();
            app.showHighScores(score, endTime - startTime); // Max value means no new high score
        }

        // Paddle Collision
        Rectangle ballRect = ball.getBounds();
        if (ballRect.intersects(paddle.getBounds())) {
            ball.setDy(-Math.abs(ball.getDy())); // Bounce up
            combo = 1;
        }

        // Brick Collision
        boolean allDestroyed = true;
        for (Brick brick : bricks) {
            if (!brick.isDestroyed()) {
                allDestroyed = false;
                if (ballRect.intersects(brick.getBounds())) {
                    brick.setDestroyed(true);
                    ball.setDy(-ball.getDy());
                    score += (100 * combo); // 1st block = 100, 2nd = 200, etc.
                    combo++;
                    break; // Only hit one brick per frame
                }
            }
        }

        if (allDestroyed) {
            gameWon = true;
            timer.stop();
            long endTime = System.currentTimeMillis();
            app.showHighScores(score, endTime - startTime);
        }
    }

    // Getters for View
    public Ball getBall() { return ball; }
    public Paddle getPaddle() { return paddle; }
    public List<Brick> getBricks() { return bricks; }
    public boolean isGameOver() { return gameOver; }
    public long getElapsedTime() { 
        return gameWon ? 0 : System.currentTimeMillis() - startTime; 
    }
    
    public long getScore(){
        return this.score;
    }
}