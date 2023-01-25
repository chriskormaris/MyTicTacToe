package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;

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
			setIcon(GUI.gameParameters.getXIcon());
		} else if (aiPlayer == Constants.O) {
			setIcon(GUI.gameParameters.getOIcon());
		}

		Board.printBoard(GUI.board.getGameBoard());

		removeActionListener(this);
	}

}
