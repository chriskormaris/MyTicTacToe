package button;

import client_server.Client;
import gui.TicTacToeGUI;
import tic_tac_toe.Board;
import utility.Constants;
import utility.GameParameters;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;


public class ClientServerButton extends XOButton implements Serializable {

    // Empty: 0, X: 1, O: 0
    public int id;
    public boolean programmaticallyPressed = false;
    String serverIP;
    int serverPort;
    Client client;
    int playerLetter;


    public ClientServerButton(int id, String serverIP, int serverPort, int playerSymbol) {
        setFocusable(false);
        this.id = id;
        this.addActionListener(this);
        setIcon(null);
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.client = new Client(serverIP, serverPort, playerSymbol);
        this.playerLetter = playerSymbol;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // GUI.undoItem.setEnabled(false);  // uncomment only if needed

        int turn = Constants.EMPTY;
        if (TicTacToeGUI.board.getLastPlayer() == Constants.X) {
            turn = Constants.O;
        } else if (TicTacToeGUI.board.getLastPlayer() == Constants.O) {
            turn = Constants.X;
        }

        if (turn != playerLetter) {
            return;
        }

        if (programmaticallyPressed) {
            turn = TicTacToeGUI.board.getLastPlayer();
            TicTacToeGUI.board.changeLastSymbolPlayed();
        }

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

        if (!programmaticallyPressed) {
            this.client = new Client(serverIP, serverPort, playerLetter);
            this.client.start();

            // check if the game is over
            if (TicTacToeGUI.board.isTerminal()) {
                TicTacToeGUI.gameOver();
            }
        }
        try {
            this.removeActionListener(this);
        } catch (NullPointerException ex) {
            // Do nothing
        }
        TicTacToeGUI.clientServerButtons[id] = this;
    }

}
