package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.ai.AI;
import com.chriskormaris.mytictactoe.api.board.Move;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.util.GuiUtils;

import java.awt.event.ActionEvent;
import java.util.List;


public class HumanVsAiButton extends XOButton {

	// Empty: 0, X: 1, O: 0
	int playerSymbol;

	AI ai;

	public HumanVsAiButton(int id, int playerSymbol, AI ai, GUI gui) {
		super(id, gui);
		this.playerSymbol = playerSymbol;
		this.ai = ai;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		gui.undoItem.setEnabled(true);

		if (gui.gameParameters.getPlayerSymbol() == Constants.X) {
			setIcon(gui.XIcon);
		} else if (gui.gameParameters.getPlayerSymbol() == Constants.O) {
			setIcon(gui.OIcon);
		}

		// get cell coordinates by id
		List<Integer> cell = gui.getBoardCellById(id);

		gui.makeMove(cell.get(0), cell.get(1), playerSymbol);

		System.out.println(gui.board);

		// Check if the game is over.
		if (gui.board.isTerminal()) {
			gui.gameOver();
			return;
		} else {
			try {
				this.removeActionListener(this);
			} catch (NullPointerException ignored) {
			}
		}

		Move aiMove = this.ai.getNextMove(gui.board);

		gui.makeMove(aiMove.getRow(), aiMove.getColumn(), ai.getAiPlayer());

		int aiMoveButtonId = GuiUtils.getIdByBoardCell(aiMove.getRow(), aiMove.getColumn());

		for (HumanVsAiButton button : gui.humanVsAiButtons) {
			if (button.id == aiMoveButtonId) {
				if (gui.gameParameters.getPlayerSymbol() == Constants.X) {
					button.setIcon(gui.OIcon);
				} else if (gui.gameParameters.getPlayerSymbol() == Constants.O) {
					button.setIcon(gui.XIcon);
				}
				button.removeActionListener(button);
			}
		}

		System.out.println(gui.board);

		// Check if the game is over.
		if (gui.board.isTerminal()) {
			gui.gameOver();
		} else {
			try {
				this.removeActionListener(this);
			} catch (NullPointerException ignored) {
			}
		}
	}

}
