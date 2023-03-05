package com.chriskormaris.mytictactoe.gui.button;

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
	int playerSymbol;


	public ClientServerButton(int id, String serverIP, int serverPort, int playerSymbol) {
		setFocusable(false);
		this.id = id;
		this.addActionListener(this);
		setIcon(null);
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.client = new Client(serverIP, serverPort, playerSymbol);
		this.playerSymbol = playerSymbol;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int turn = Constants.EMPTY;
		if (GUI.board.getLastPlayer() == Constants.X) {
			turn = Constants.O;
		} else if (GUI.board.getLastPlayer() == Constants.O) {
			turn = Constants.X;
		}

		if (turn != playerSymbol) {
			return;
		}

		if (programmaticallyPressed) {
			turn = GUI.board.getLastPlayer();
			GUI.board.changeLastSymbolPlayed();
		}

		// add X or O on the board GUI
		if (turn == Constants.X) {
			setIcon(GUI.XIcon);
		} else if (turn == Constants.O) {
			setIcon(GUI.OIcon);
		} else {
			setIcon(null);
		}

		// get cell coordinates by id
		List<Integer> cell = GUI.getBoardCellById(id);
		GUI.makeMove(cell.get(0), cell.get(1), turn);

		System.out.println(GUI.board);

		if (!programmaticallyPressed) {
			this.client = new Client(serverIP, serverPort, playerSymbol);
			this.client.start();

			// check if the game is over
			if (GUI.board.isTerminal()) {
				GUI.gameOver();
			}
		}
		try {
			this.removeActionListener(this);
		} catch (NullPointerException ignored) {
		}
		GUI.clientServerButtons[id] = this;
	}

}
