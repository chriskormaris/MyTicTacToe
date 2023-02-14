package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.ai.AI;
import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.board.Move;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;

import java.awt.event.ActionEvent;
import java.util.List;


public class HumanVsAiButton extends XOButton {

	// Empty: 0, X: 1, O: 0
	public int id;
	int playerSymbol;
	AI ai;


	public HumanVsAiButton(int id, int playerSymbol, AI ai) {
		setFocusable(false);
		this.id = id;
		this.playerSymbol = playerSymbol;
		this.addActionListener(this);
		setIcon(null);
		this.ai = ai;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		GUI.undoItem.setEnabled(true);

		if (GUI.gameParameters.getPlayerSymbol() == Constants.X) {
			setIcon(GUI.XIcon);
		} else if (GUI.gameParameters.getPlayerSymbol() == Constants.O) {
			setIcon(GUI.OIcon);
		}

		// get cell coordinates by id
		List<Integer> cell = GUI.getBoardCellById(id);

		GUI.makeMove(cell.get(0), cell.get(1), playerSymbol);

		// Check if the game is over.
		if (GUI.board.isTerminal()) {
			GUI.gameOver();
			return;
		} else {
			try {
				this.removeActionListener(this);
			} catch (NullPointerException ex) {
				// Do nothing
			}
		}

		// System.out.println(GUI.board.getLastMove());
		// Board.printBoard(GUI.board.getGameBoard());

		Move aiMove = this.ai.getNextMove(GUI.board);
		// System.out.println("AI Move [" + aiMove.getRow() + "]" + "[" + aiMove.getColumn() +"]: " + aiMove.getValue());

		GUI.makeMove(aiMove.getRow(), aiMove.getColumn(), ai.getAiPlayer());
		// System.out.println("board value: " + TicTacToeGUI.board.evaluate());

		int aiMoveButtonId = GUI.getIdByBoardCell(aiMove.getRow(), aiMove.getColumn());
		// System.out.println("AI Move [" + aiMove.getRow() + "]" + "[" + aiMove.getCol() +"]");

		for (HumanVsAiButton button : GUI.humanVsAiButtons) {
			if (button.id == aiMoveButtonId) {
				if (GUI.gameParameters.getPlayerSymbol() == Constants.X) {
					button.setIcon(GUI.OIcon);
				} else if (GUI.gameParameters.getPlayerSymbol() == Constants.O) {
					button.setIcon(GUI.XIcon);
				}
				button.removeActionListener(button);
			}
		}

		Board.printBoard(GUI.board.getGameBoard());

		// Check if the game is over.
		if (GUI.board.isTerminal()) {
			GUI.gameOver();
		} else {
			try {
				this.removeActionListener(this);
			} catch (NullPointerException ex) {
				// Do nothing
			}
		}

	}

}
