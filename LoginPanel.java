import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel{
    private JPanel loginPanel = new JPanel();
    private JPanel userPanel = new JPanel();
    private JPanel passPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel title = new JLabel("BREAKOUT!", SwingConstants.CENTER);
    private JLabel username = new JLabel("Username:");
    private JLabel password = new JLabel("Password:");
    private JTextField userField = new JTextField("Enter username", 10);
    private JPasswordField passField = new JPasswordField(10);
    private JLabel status = new JLabel("Login or Register", SwingConstants.CENTER);
    private LoginController controller;

    public LoginPanel(BreakoutApp app){
        this.controller = new LoginController(userField, passField, status, app);
        setLayout(new GridBagLayout());
        
        loginPanel.setLayout(new GridLayout(5, 1, 0, 11));
        userPanel.setLayout(new GridLayout(1, 2, -112, 0));
        passPanel.setLayout(new GridLayout(1, 2, -112, 0));
        buttonPanel.setLayout(new GridLayout(1, 2, 22, 0));
        
        loginPanel.setPreferredSize(new Dimension(300, 225));
        userPanel.setPreferredSize(new Dimension(300, 45));
        passPanel.setPreferredSize(new Dimension(300, 45));
        buttonPanel.setPreferredSize(new Dimension(300, 45));
        
        //add the components in the following order:[this part is already done]
        title.setFont(new Font("Plain", Font.BOLD, 45));
        title.setForeground(Color.RED);
        
        username.setForeground(Color.GREEN);
        password.setForeground(Color.GREEN);
        userField.setBackground(Color.BLACK);
        passField.setBackground(Color.BLACK);
        userField.setForeground(Color.GREEN);
        passField.setForeground(Color.GREEN);
        
        userPanel.add(username);
        userPanel.add(userField);
        passPanel.add(password);
        passPanel.add(passField);
        
        loginPanel.add(title);
        loginPanel.add(userPanel);
        loginPanel.add(passPanel);
        
        //create a login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.GREEN);
        //create a register button
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.GREEN);
        
        //add the buttons and status to the pane
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        loginPanel.add(buttonPanel);
        
        status.setForeground(Color.GREEN);
        loginPanel.add(status);
        
        setBackground(Color.BLACK);
        loginPanel.setBackground(Color.BLACK);
        userPanel.setBackground(Color.BLACK);
        passPanel.setBackground(Color.BLACK);
        buttonPanel.setBackground(Color.BLACK);
        add(loginPanel);
        
        //add action listener to the buttons
        //when login button is clicked , call "loginUser()"
        loginButton.addActionListener(e -> controller.loginUser());
        
        //when register button is clicked, call "registerUser()"
        registerButton.addActionListener(e -> controller.registerUser());
    }
    
    //Connects information to other classes.
    public LoginController getController(){
        return this.controller;
    }
}