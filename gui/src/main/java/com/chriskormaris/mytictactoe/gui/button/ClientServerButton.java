package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.client_server.Client;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;


public class ClientServerButton extends XOButton implements Serializable {

	// Empty: 0, X: 1, O: 0
	public boolean programmaticallyPressed = false;

	String serverIP;
	int serverPort;
	Client client;
	int playerSymbol;

	public ClientServerButton(int id, String serverIP, int serverPort, int playerSymbol, GUI gui) {
		super(id, gui);
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.client = new Client(serverIP, serverPort, gui.board);
		this.playerSymbol = playerSymbol;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int turn = Constants.EMPTY;
		if (gui.board.getLastPlayer() == Constants.X) {
			turn = Constants.O;
		} else if (gui.board.getLastPlayer() == Constants.O) {
			turn = Constants.X;
		}

		if (turn != playerSymbol) {
			return;
		}

		if (programmaticallyPressed) {
			turn = gui.board.getLastPlayer();
			gui.board.changeLastSymbolPlayed();
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

		if (!programmaticallyPressed) {
			this.client = new Client(serverIP, serverPort, gui.board);
			this.client.start();

			// check if the game is over
			if (gui.board.isTerminal()) {
				gui.gameOver();
			}
		}
		try {
			this.removeActionListener(this);
		} catch (NullPointerException ignored) {
		}
		gui.buttons[id] = this;
	}

}
