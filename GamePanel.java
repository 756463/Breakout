import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private BreakoutApp app;
    private BreakoutController controller;

    public GamePanel(BreakoutApp app) {
        this.app = app;
        setBackground(Color.BLACK);
        setFocusable(true);
        
        // Setup input bindings (Controller updates Model via View input)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (controller != null) controller.handleInput(e.getKeyCode(), true);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (controller != null) controller.handleInput(e.getKeyCode(), false);
            }
        });
    }
    
    //Initializes game.
    public void startNewGame() {
        controller = new BreakoutController(this, app);
        requestFocusInWindow();
    }
    
    //Draws graphics for sprites.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (controller == null) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw Bricks
        for (Brick brick : controller.getBricks()) {
            if (!brick.isDestroyed()) {
                g2d.setColor(brick.getColor());
                g2d.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
                g2d.setColor(Color.BLACK);
                g2d.drawRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            }
        }

        // Draw Paddle
        Paddle paddle = controller.getPaddle();
        g2d.setColor(Color.CYAN);
        g2d.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());

        // Draw Ball
        Ball ball = controller.getBall();
        g2d.setColor(Color.WHITE);
        g2d.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

        // Draw HUD
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Plain", Font.BOLD, 14));
        long timeElapsed = controller.getElapsedTime() / 1000;
        g2d.drawString("Score: " + controller.getScore() + "    Time: " + timeElapsed + "s", 10, 20);
    }
}