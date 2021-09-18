package button;

import java.awt.event.ActionEvent;

import tic_tac_toe.Board;
import gui.TicTacToeGUI;
import utility.Constants;
import utility.GameParameters;


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
		// System.out.println("Clicked button.");
		
		// List<Integer> cell = GUI.getBoardCellById(id);
		// System.out.println("button cell: [" + cell.get(0) + "]" + "[" + cell.get(1) + "]");
		
		if (aiPlayer == Constants.X) {
			setIcon(GameParameters.X_ICON);
		} else if (aiPlayer == Constants.O) {
			setIcon(GameParameters.O_ICON);
		}
		
		Board.printBoard(TicTacToeGUI.board.getGameBoard());
		
		removeActionListener(this);
	}
	
}
