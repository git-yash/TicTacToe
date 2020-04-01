public class Builder {
    private String player1SymbolChoice = "X";
    private String player2SymbolChoice = "O";
    private Positions positions = new Positions();

    public void startGame() {
        boolean playAnotherGame = true;
        while (playAnotherGame) {
            this.positions.resetBoard();
            this.printBoard();

            boolean isPlayer1Winner = this.coinFlip();
            String winner = isPlayer1Winner ? "Player X" : "Player O";
            String firstPlayerSymbol = isPlayer1Winner ? this.player1SymbolChoice : this.player2SymbolChoice;
            String secondPlayerSymbol = !isPlayer1Winner ? this.player1SymbolChoice : this.player2SymbolChoice;
            System.out.println(winner + " goes first due to a coin flip.");

            for (int i = 1; i <= 5; i++) {
                this.gatherPlayerChoice(firstPlayerSymbol);
                if (this.isGameFinished(i)) {
                    break;
                }
                this.gatherPlayerChoice(secondPlayerSymbol);
                if (this.isGameFinished(i)) {
                    break;
                }
            }
            playAnotherGame = GatherInput.gatherBooleanInput("Would you like to play another game?");
        }
    }

    private boolean isGameFinished(int turn) {
        if (turn >= 3 && turn < 5) {
            if (this.positions.didWinGame(this.player1SymbolChoice)) {
                System.out.println("Congratulations, player X wins!");
                return true;
            } else if (this.positions.didWinGame(this.player2SymbolChoice)) {
                System.out.println("Congratulations, player O wins!");
                return true;
            }
        } else if (turn == 5) {
            System.out.println("That's a draw");
            return true;
        }
        return false;
    }

    private void printBoard() {
        this.positions.printRow(0, 1, 2);
        this.printEmptyRow();
        this.positions.printRow(3, 4, 5);
        this.printEmptyRow();
        this.positions.printRow(6, 7, 8);
    }

    private void printEmptyRow() {
        System.out.println("-----------------");
    }

    private boolean coinFlip() {
        double value = Math.round(Math.random());
        return value % 2 == 0;
    }

    private void gatherPlayerChoice(String playerSymbolChoice) {
        int position = GatherInput.gatherIntInput("Where would you like to place your " + playerSymbolChoice + "?: ", 9, 1);
        int adjustedPosition = position - 1;
        this.positions.setPosition(adjustedPosition, playerSymbolChoice);
        this.printBoard();
    }
}
