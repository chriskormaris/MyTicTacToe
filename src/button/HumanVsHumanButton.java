package button;

import gui.TicTacToeGUI;
import tic_tac_toe.Board;
import utility.Constants;
import utility.GameParameters;

import java.awt.event.ActionEvent;
import java.util.List;


public class HumanVsHumanButton extends XOButton {

    // Empty: 0, X: 1, O: 0
    public int id;


    public HumanVsHumanButton(int id) {
        setFocusable(false);
        this.id = id;
        this.addActionListener(this);
        setIcon(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        TicTacToeGUI.undoItem.setEnabled(true);

        int turn = Constants.EMPTY;
        if (TicTacToeGUI.board.getLastPlayer() == Constants.X)
            turn = Constants.O;
        else if (TicTacToeGUI.board.getLastPlayer() == Constants.O)
            turn = Constants.X;

        // add X or O on the board GUI
        if (turn == Constants.EMPTY) {
            setIcon(null);
        } else if (turn == Constants.X) {
            setIcon(GameParameters.X_ICON);
        } else if (turn == Constants.O) {
            setIcon(GameParameters.O_ICON);
        }

        // get cell coordinates by id
        List<Integer> cell = TicTacToeGUI.getBoardCellById(id);
        TicTacToeGUI.makeMove(cell.get(0), cell.get(1), turn);
        Board.printBoard(TicTacToeGUI.board.getGameBoard());

        // check if the game is over
        if (TicTacToeGUI.board.isTerminal())
            TicTacToeGUI.gameOver();

        try {
            this.removeActionListener(this);
        } catch (NullPointerException ex) {
            // Do nothing
        }

    }

}
