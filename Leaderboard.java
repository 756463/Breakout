public class Leaderboard{
    //This class is used to store information needed for leaderboard for storage and sorting purposes.
    private String username;
    private long score;
    private long time;
    
    public Leaderboard(String username, long score, long time) {
        this.username = username;
        this.score = score;
        this.time = time;
    }
    
    //Connects information to other classes.
    public String getUsername(){
        return this.username;
    }
    
    public long getScore(){
        return this.score;
    }
    
    public long getTime(){
        return this.time;
    }
}