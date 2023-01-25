package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.client_server.Client;

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
		if (GUI.board.getLastPlayer() == Constants.X) {
			turn = Constants.O;
		} else if (GUI.board.getLastPlayer() == Constants.O) {
			turn = Constants.X;
		}

		if (turn != playerLetter) {
			return;
		}

		if (programmaticallyPressed) {
			turn = GUI.board.getLastPlayer();
			GUI.board.changeLastSymbolPlayed();
		}

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

		if (!programmaticallyPressed) {
			this.client = new Client(serverIP, serverPort, playerLetter);
			this.client.start();

			// check if the game is over
			if (GUI.board.isTerminal()) {
				GUI.gameOver();
			}
		}
		try {
			this.removeActionListener(this);
		} catch (NullPointerException ex) {
			// Do nothing
		}
		GUI.clientServerButtons[id] = this;
	}

}
