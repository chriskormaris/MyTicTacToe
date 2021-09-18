package button;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;

import ai.AI;
import tic_tac_toe.Board;
import tic_tac_toe.Move;
import gui.TicTacToeGUI;
import utility.Constants;
import utility.GameParameters;
import utility.ResourceLoader;


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

		if (GameParameters.playerSymbol == Constants.X) {
			setIcon(GameParameters.X_ICON);
		} else if (GameParameters.playerSymbol == Constants.O) {
			setIcon(GameParameters.O_ICON);
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

		TicTacToeGUI.makeMove(aiMove.getRow(), aiMove.getColumn(), ai.getAiPlayer());

		int aiMoveButtonId = TicTacToeGUI.getIdByBoardCell(aiMove.getRow(), aiMove.getColumn());
		// System.out.println("AI Move [" + aiMove.getRow() + "]" + "[" + aiMove.getCol() +"]");

		for (HumanVsAiButton button: TicTacToeGUI.humanVsAiButtons) {
			button.ai = this.ai;
			if (button.id == aiMoveButtonId) {
				if (GameParameters.playerSymbol == Constants.X) {
					button.setIcon(GameParameters.O_ICON);
				} else if (GameParameters.playerSymbol == Constants.O) {
					button.setIcon(GameParameters.X_ICON);
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
