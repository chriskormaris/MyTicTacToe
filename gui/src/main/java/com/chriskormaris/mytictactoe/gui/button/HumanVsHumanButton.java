package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;

import java.awt.event.ActionEvent;
import java.util.List;


public class HumanVsHumanButton extends XOButton {

	// Empty: 0, X: 1, O: 0
	public HumanVsHumanButton(int id, GUI gui) {
		super(id, gui);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		gui.undoItem.setEnabled(true);

		int turn = Constants.EMPTY;
		if (gui.board.getLastPlayer() == Constants.X) {
			turn = Constants.O;
		} else if (gui.board.getLastPlayer() == Constants.O) {
			turn = Constants.X;
		}

		// add X or O on the board GUI
		if (turn == Constants.X) {
			setIcon(gui.XIcon);
		} else if (turn == Constants.O) {
			setIcon(gui.OIcon);
		} else {
			setIcon(null);
		}

		// get cell coordinates by id
		List<Integer> cell = gui.getBoardCellById(id);
		gui.makeMove(cell.get(0), cell.get(1), turn);

		System.out.println(gui.board);

		// check if the game is over
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
