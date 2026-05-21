import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Scanner;

public class LoginController {
    private JTextField userField;
    private JPasswordField passField;
    private JLabel status;
    private ArrayList<String> userID = new ArrayList<>();
    private ArrayList<String> userPass = new ArrayList<>();
    private ArrayList<Long> highScores = new ArrayList<>();
    private ArrayList<Long> times = new ArrayList<>();
    private BreakoutApp app;
    
    public LoginController(JTextField userField, JPasswordField passField, JLabel status, BreakoutApp app){
        //Import variables
        this.userField = userField;
        this.passField = passField;
        this.status = status;
        this.app = app;
        
        //Load user info
        Scanner userIn = Prompt.getInputScannerPromptless("UserInfo.txt");
        while(userIn.hasNextLine()){
            String current = userIn.nextLine();
            String username = "";
            String password = "";
            String score = "";
            String time = "";
            
            int level = 0;
            for(int i = 0; i < current.length(); i++){
                if(current.charAt(i) == ','){
                    level++;
                    continue;
                }
                else if(level == 0){
                    username += current.charAt(i);
                }
                else if(level == 1){
                    password += current.charAt(i);
                }
                else if(level == 2){
                    score += current.charAt(i);
                }
                else{
                    time += current.charAt(i);
                }
            }
            this.userID.add(username);
            this.userPass.add(password);
            this.highScores.add(Long.parseLong(score));
            this.times.add(Long.parseLong(time));
        }
        userIn.close();
    }
    
    // For this example, ignore validating user info
    // You must modify the following to make it work.
    public void loginUser(){
        //first, ensure that user types something in the fields
        String username = userField.getText();
        String password = new String(passField.getPassword());
        if(username.isEmpty() || password.isEmpty()){
            this.status.setText("Please enter username and password");
            return;
        }
        
        //if login is successful, then change the 'status' to "Login successful"
        if(this.userID.contains(username)){
            if((this.userPass.get(this.userID.indexOf(username))).equals(password)){
                this.status.setText("Login successful");
                this.app.setCurrentUser(username); 
                this.app.showRules();
            } 
            else{
                this.status.setText("Wrong password");
            }
        }
        else{
            this.status.setText("User not found");
        }
    }
    
    //registe the user by adding the user info to corresponding ArrayList
    public void registerUser(){
        //First, get user info
        String username = this.userField.getText();
        String password = new String(this.passField.getPassword());
        
        //only if it is not empty, add the item to the ArrayList and change the 
        //status to "Register successful"
        if(!username.isEmpty() && !password.isEmpty()){
            if(this.userID.contains(username)){
                this.status.setText("Username taken");
                return;
            }
            this.userID.add(username);
            this.userPass.add(password);
            this.highScores.add(0L);
            this.times.add(0L);
            Prompt.getPrintWriterPromptless("UserInfo.txt", username + "," + password + ",0,0", true);
            this.status.setText("Register successful");
        }
        else{
            this.status.setText("Please enter username and password");
        }
    }
    
    //Checks if user achieved a new high score.
    public void updateHighScore(String username, long newScore, long newTime){
        if(this.userID.contains(username)){ 
            if(newScore > this.highScores.get(this.userID.indexOf(username))){
                this.highScores.set(this.userID.indexOf(username), newScore);
                this.times.set(this.userID.indexOf(username), newTime);
                saveUserInfo();
            }
        }
    }
    
    //Writes username, password, score, and time on text file.
    public void saveUserInfo(){
        String output = "";
        for(int i = 0; i < userID.size(); i++){
            output += this.userID.get(i) + "," + this.userPass.get(i) + "," + this.highScores.get(i) + "," + this.times.get(i);
            if(i != userID.size() -1){
                output += "\n";
            }
        }
        Prompt.getPrintWriterPromptless("UserInfo.txt", output, false);
    }
    
    //Connects information to other classes.
    public ArrayList<String> getUserID(){
        return this.userID;
    }
    
    public ArrayList<Long> getHighScores(){
        return this.highScores;
    }
    
    public ArrayList<Long> getTimes(){
        return this.times;
    }
}