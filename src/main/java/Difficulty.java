public class Difficulty {


    public static Position returnMove(Board board) {

        switch (Main.getDifficulty()){
            case EASY -> {
                return ComputerMoveService.easyComputerMove(board);
            }
            case MEDIUM -> {
                return ComputerMoveService.mediumComputerMove(board);
            }
            case IMPOSSIBLE -> {
                return ComputerMoveService.impossibleComputerMove(board);
            }
        }

        return null;
    }
}
