package com.chriskormaris.mytictactoe.gui.client_server;

import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;

public class ClientServerExample1 {

	public static void main(String[] args) {
		GUI.gameParameters.setGameMode(GameMode.CLIENT_SERVER);
		GUI.gameParameters.setPlayerSymbol(Constants.X);
		GUI.gameParameters.setServerPort(4000);
		GUI.gameParameters.setClientIP("127.0.0.1");
		GUI.gameParameters.setClientPort(4001);

		GUI.create("My TicTacToe ClientServer 1");

		GUI.createClientServerNewGame();
	}

}
