public class Builder {
    private String[] positions;
    private String player1SymbolChoice = "X";
    private String player2SymbolChoice = "O";

    public void startGame() {
        boolean playAnotherGame = true;
        while (playAnotherGame) {
            this.resetBoard();
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
            if (this.didWinGame(this.player1SymbolChoice)) {
                System.out.println("Congratulations, player X wins!");
                return true;
            } else if (this.didWinGame(this.player2SymbolChoice)) {
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
        this.printRow(0, 1, 2);
        this.printEmptyRow();
        this.printRow(3, 4, 5);
        this.printEmptyRow();
        this.printRow(6, 7, 8);
    }

    private void printEmptyRow() {
        System.out.println("-----------------");
    }

    private boolean isMatchingRow(int position1, int position2, int position3, String symbol) {
        return this.positions[position1 - 1].equals(symbol) &&
                this.positions[position2 - 1].equals(symbol) &&
                this.positions[position3 - 1].equals(symbol);
    }

    private void printRow(int position1, int position2, int position3) {
        System.out.println("  " +
                this.positions[position1] + "  |  " +
                this.positions[position2] + "  |  " +
                this.positions[position3]);
    }

    private boolean coinFlip() {
        double value = Math.round(Math.random());
        return value % 2 == 0;
    }

    private void gatherPlayerChoice(String playerSymbolChoice) {
        int position = GatherInput.gatherIntInput("Where would you like to place your " + playerSymbolChoice + "?: ", 9, 1);
        int adjustedPosition = position - 1;
        this.positions[adjustedPosition] = playerSymbolChoice;
        this.printBoard();
    }

    private boolean didWinGame(String playerSymbolChoice) {
        return this.isMatchingRow(1, 2, 3, playerSymbolChoice) ||
                this.isMatchingRow(4, 5, 6, playerSymbolChoice) ||
                this.isMatchingRow(7, 8, 9, playerSymbolChoice) ||

                this.isMatchingRow(1, 4, 7, playerSymbolChoice) ||
                this.isMatchingRow(2, 5, 8, playerSymbolChoice) ||
                this.isMatchingRow(3, 6, 9, playerSymbolChoice) ||

                this.isMatchingRow(3, 5, 7, playerSymbolChoice) ||
                this.isMatchingRow(1, 5, 9, playerSymbolChoice);
    }

    private void resetBoard() {
        this.positions = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }
}
