


public final class Difficulty {
    private Difficulty() {
    }

    public static Position returnMove(Board board, int difficulty) {

        switch (Main.getDifficulty()){
            case EASY -> {
                return ComputerMoveService.easyComputerMove(board);
            }
            case MEDIUM -> {
                return ComputerMoveService.mediumComputerMove(board, difficulty);
            }
            case IMPOSSIBLE -> {
                return ComputerMoveService.impossibleComputerMove(board);
            }
            default -> {throw new IllegalStateException("Unexpected value: " + difficulty);}
        }
    }
}
