import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {





    @Test
    public void drawTest() {
        Score score = new Score();

        score.updateScore(MatchStatus.DRAW);

        assertEquals(1, score.getDrawCount());
    }

    @Test
    public void playerWonTest() {
        Score score = new Score();

        score.updateScore(MatchStatus.PLAYER_WON);

        assertEquals(1, score.getPlayerScore());
    }

    @Test
    public void computerWonTest() {
        Score score = new Score();

        score.updateScore(MatchStatus.COMPUTER_WON);

        assertEquals(1, score.getComputerScore());
    }




}