package com.chriskormaris.gui.button;

import com.chriskormaris.api.tic_tac_toe.Board;
import com.chriskormaris.api.utility.Constants;
import com.chriskormaris.gui.TicTacToeGUI;

import java.awt.event.ActionEvent;


public class AiVsAiButton extends XOButton {

    // Empty: 0, X: 1, O: 0
    public int id;
    public int aiPlayer;


    public AiVsAiButton(int id) {
        setFocusable(false);
        this.id = id;
        this.addActionListener(this);
        setIcon(null);
        this.aiPlayer = Constants.EMPTY;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("Clicked com.chriskormaris.gui.button.");

        // List<Integer> cell = GUI.getBoardCellById(id);
        // System.out.println("com.chriskormaris.gui.button cell: [" + cell.get(0) + "]" + "[" + cell.get(1) + "]");

        if (aiPlayer == Constants.X) {
            setIcon(TicTacToeGUI.gameParameters.getXIcon());
        } else if (aiPlayer == Constants.O) {
            setIcon(TicTacToeGUI.gameParameters.getOIcon());
        }

        Board.printBoard(TicTacToeGUI.board.getGameBoard());

        removeActionListener(this);
    }

}
