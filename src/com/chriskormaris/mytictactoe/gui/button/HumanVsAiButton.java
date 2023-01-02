package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.ai.AI;
import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.board.Move;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.TicTacToeGUI;

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
		TicTacToeGUI.undoItem.setEnabled(true);

		if (TicTacToeGUI.gameParameters.getPlayerSymbol() == Constants.X) {
			setIcon(TicTacToeGUI.gameParameters.getXIcon());
		} else if (TicTacToeGUI.gameParameters.getPlayerSymbol() == Constants.O) {
			setIcon(TicTacToeGUI.gameParameters.getOIcon());
		}

		// get cell coordinates by id
		List<Integer> cell = TicTacToeGUI.getBoardCellById(id);

		TicTacToeGUI.makeMove(cell.get(0), cell.get(1), playerSymbol);

		// Check if the game is over.
		if (TicTacToeGUI.board.isTerminal()) {
			TicTacToeGUI.gameOver();
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

		Move aiMove = this.ai.getNextMove(TicTacToeGUI.board);
		// System.out.println("AI Move [" + aiMove.getRow() + "]" + "[" + aiMove.getColumn() +"]: " + aiMove.getValue());

		TicTacToeGUI.makeMove(aiMove.getRow(), aiMove.getColumn(), ai.getAiPlayer());
		// System.out.println("board value: " + TicTacToeGUI.board.evaluate());

		int aiMoveButtonId = TicTacToeGUI.getIdByBoardCell(aiMove.getRow(), aiMove.getColumn());
		// System.out.println("AI Move [" + aiMove.getRow() + "]" + "[" + aiMove.getCol() +"]");

		for (HumanVsAiButton button : TicTacToeGUI.humanVsAiButtons) {
			if (button.id == aiMoveButtonId) {
				if (TicTacToeGUI.gameParameters.getPlayerSymbol() == Constants.X) {
					button.setIcon(TicTacToeGUI.gameParameters.getOIcon());
				} else if (TicTacToeGUI.gameParameters.getPlayerSymbol() == Constants.O) {
					button.setIcon(TicTacToeGUI.gameParameters.getXIcon());
				}
				button.removeActionListener(button);
			}
		}

		Board.printBoard(TicTacToeGUI.board.getGameBoard());

		// Check if the game is over.
		if (TicTacToeGUI.board.isTerminal()) {
			TicTacToeGUI.gameOver();
		} else {
			try {
				this.removeActionListener(this);
			} catch (NullPointerException ex) {
				// Do nothing
			}
		}

	}

}
