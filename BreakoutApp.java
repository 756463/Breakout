import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BreakoutApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginController loginController; 
    private String currentUser;

    public BreakoutApp() {
        setTitle("Java Swing Breakout");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize Views
        LoginPanel loginPanel = new LoginPanel(this);
        this.loginController = loginPanel.getController();
        RulesPanel rulesPanel = new RulesPanel(this);
        GamePanel gamePanel = new GamePanel(this);

        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(rulesPanel, "RULES");
        mainPanel.add(gamePanel, "GAME");

        add(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");
    }
    
    //Connects BreakoutApp with RulesPanel.
    public void showRules() {
        cardLayout.show(mainPanel, "RULES");
    }

    //Shows GamePanel and starts gameplay. 
    public void startGame() {
        cardLayout.show(mainPanel, "GAME");
        // Get the GamePanel and start its controller
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof GamePanel && comp.isVisible()) {
                ((GamePanel) comp).startNewGame();
            }
        }
    }

    //Sorts and displays the leaderboard.
    public void showHighScores(long score, long finalTime) {
        //Creates GUI.
        loginController.updateHighScore(currentUser, score, finalTime);
        
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setBackground(Color.BLACK);
        
        JTextArea scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setBackground(Color.BLACK);
        scoreArea.setForeground(Color.GREEN);
        scoreArea.setFont(new Font("Plain", Font.BOLD, 16));
        
        //Gets values and sorts them.
        ArrayList<String> userID = loginController.getUserID();
        ArrayList<Long> highScores = loginController.getHighScores();
        ArrayList<Long> times = loginController.getTimes();
        
        ArrayList<Leaderboard> leaderboard = new ArrayList<>();
        for (int i = 0; i < userID.size(); i++) {
            leaderboard.add(new Leaderboard(userID.get(i), highScores.get(i), times.get(i)));
        }
        leaderboard.sort((a, b) -> Long.compare(b.getScore(), a.getScore()));
        
        //Organizes values into text.
        StringBuilder sb = new StringBuilder("\n    HIGH SCORES:\n\n");
        int rank = 1;
        for (int i = 0; i < highScores.size(); i++) {
            Leaderboard current = leaderboard.get(i);
            if(current.getScore() > 0){
                sb.append("    ").append(rank).append(". ")
                  .append(current.getUsername()).append(": ")
                  .append(current.getScore()).append(" score - ")
                  .append(String.format("%.1f", current.getTime() / 1000.0)).append(" seconds\n");
                rank++;
            }
        }
        scoreArea.setText(sb.toString());
        
        //Displays leaderboard.
        JButton playAgainBtn = new JButton("Play Again");
        playAgainBtn.setBackground(Color.BLACK);
        playAgainBtn.setForeground(Color.GREEN);
        playAgainBtn.addActionListener(e -> startGame());

        scorePanel.add(scoreArea, BorderLayout.CENTER);
        scorePanel.add(playAgainBtn, BorderLayout.SOUTH);

        mainPanel.add(scorePanel, "SCORES");
        cardLayout.show(mainPanel, "SCORES");
    }
    
    //Keeps track of current user.
    public void setCurrentUser(String username){
        this.currentUser = username;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BreakoutApp().setVisible(true);
        });
    }
}