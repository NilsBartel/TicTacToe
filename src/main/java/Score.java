import com.fasterxml.jackson.annotation.JsonIgnore;

public class Score {

    private int playerScore;
    private int computerScore;
    private int drawCount;



    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setPlayerScorePlusOne(){
        this.playerScore += 1;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public void setComputerScorePlusOne(){
        this.computerScore += 1;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }

    public void setDrawCountPlusOne(){
        this.drawCount += 1;
    }

    @JsonIgnore
    public int getRoundCounter(){
        return playerScore + computerScore + drawCount;
    }
}
