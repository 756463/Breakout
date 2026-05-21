import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RulesPanel extends JPanel{
    //Initializes GUI elements.
    private JPanel rulesPanel = new JPanel();
    private JPanel line3Panel = new JPanel();
    private JLabel rules1 = new JLabel("--- HOW TO PLAY ---", SwingConstants.CENTER);
    private JLabel rules2 = new JLabel("1. Use [ A ] and [ D ] to move side to side.", SwingConstants.LEFT);
    private JLabel rules3 = new JLabel("2. Bounce the ball to break the bricks.", SwingConstants.LEFT);
    private JLabel rules4 = new JLabel("3. Break multiple bricks in a row to", SwingConstants.LEFT);
    private JLabel rules5 = new JLabel("    build up your combo multiplier.", SwingConstants.LEFT);
    private JLabel rules6 = new JLabel("4. Don't let the ball fall past the paddle.", SwingConstants.LEFT);
    private JLabel rules7 = new JLabel("[ CLICK ANYWHERE TO START ]", SwingConstants.CENTER);
    private BreakoutApp app;

    public RulesPanel(BreakoutApp app){
        this.app = app;
        
        //Sets GUI layout.
        setLayout(new GridBagLayout());
        line3Panel.setLayout(new GridBagLayout());
        rulesPanel.setLayout(new GridLayout(7, 1, 0, 10));
        line3Panel.setLayout(new GridLayout(2, 1, 0, 2));
        rulesPanel.setPreferredSize(new Dimension(300, 300));
        line3Panel.setPreferredSize(new Dimension(300, 100));
        
        //Sets GUI colour.
        setBackground(Color.BLACK);
        rulesPanel.setBackground(Color.BLACK);
        line3Panel.setBackground(Color.BLACK);
        
        rules1.setBackground(Color.BLACK);
        rules1.setForeground(Color.GREEN);
        rules2.setBackground(Color.BLACK);
        rules2.setForeground(Color.GREEN);
        rules3.setBackground(Color.BLACK);
        rules3.setForeground(Color.GREEN);
        rules6.setBackground(Color.BLACK);
        rules6.setForeground(Color.GREEN);
        rules7.setBackground(Color.BLACK);
        rules7.setForeground(Color.GREEN);
        
        rules4.setBackground(Color.BLACK);
        rules4.setForeground(Color.GREEN);
        rules5.setBackground(Color.BLACK);
        rules5.setForeground(Color.GREEN);
        
        //Adds GUI in order.
        line3Panel.add(rules4);
        line3Panel.add(rules5);
        
        rulesPanel.add(rules1);
        rulesPanel.add(rules2);
        rulesPanel.add(rules3);
        rulesPanel.add(line3Panel);
        rulesPanel.add(rules6);
        rulesPanel.add(rules7);
        
        add(rulesPanel);
        
        //Mouse click stars game.
        MouseAdapter click = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                app.startGame();
            }
        };

        this.addMouseListener(click);
        rulesPanel.addMouseListener(click);
        line3Panel.addMouseListener(click);
        rules1.addMouseListener(click);
        rules2.addMouseListener(click);
        rules3.addMouseListener(click);
        rules4.addMouseListener(click);
        rules5.addMouseListener(click);
        rules6.addMouseListener(click);
        rules7.addMouseListener(click);
    }
}