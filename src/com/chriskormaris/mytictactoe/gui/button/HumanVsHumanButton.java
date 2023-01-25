package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;

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
		GUI.undoItem.setEnabled(true);

		int turn = Constants.EMPTY;
		if (GUI.board.getLastPlayer() == Constants.X)
			turn = Constants.O;
		else if (GUI.board.getLastPlayer() == Constants.O)
			turn = Constants.X;

		// add X or O on the board GUI
		if (turn == Constants.EMPTY) {
			setIcon(null);
		} else if (turn == Constants.X) {
			setIcon(GUI.gameParameters.getXIcon());
		} else if (turn == Constants.O) {
			setIcon(GUI.gameParameters.getOIcon());
		}

		// get cell coordinates by id
		List<Integer> cell = GUI.getBoardCellById(id);
		GUI.makeMove(cell.get(0), cell.get(1), turn);
		Board.printBoard(GUI.board.getGameBoard());

		// check if the game is over
		if (GUI.board.isTerminal())
			GUI.gameOver();

		try {
			this.removeActionListener(this);
		} catch (NullPointerException ex) {
			// Do nothing
		}

	}

}
