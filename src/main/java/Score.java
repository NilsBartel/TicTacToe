import com.fasterxml.jackson.annotation.JsonIgnore;

public class Score {

    private int playerScore;
    private int computerScore;
    private int drawCount;





    public Score updateScore(MatchStatus status) {
        switch (status) {
            case PLAYER_WON -> playerScore++;
            case COMPUTER_WON -> computerScore++;
            case DRAW -> drawCount++;
            case NOT_STARTED, RUNNING, MATCH_ALREADY_FINISHED -> System.out.println("Match not finished!");

            default -> PrintService.getInstance().printInvalidStatus();
        }
        return this;
    }






    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }


    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }


    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }


    @JsonIgnore
    public int getRoundCounter(){
        return playerScore + computerScore + drawCount;
    }
}
