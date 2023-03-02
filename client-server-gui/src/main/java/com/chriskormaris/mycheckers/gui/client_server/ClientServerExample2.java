package com.chriskormaris.mycheckers.gui.client_server;

import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.util.GameParameters;

public class ClientServerExample2 {

	public static void main(String[] args) {
		GUI.create("My TicTacToe ClientServer 2");

		GUI.newGameParameters.setGameMode(GameMode.CLIENT_SERVER);
		GUI.newGameParameters.setPlayerSymbol(Constants.O);
		GUI.newGameParameters.setServerPort(4001);
		GUI.newGameParameters.setClientIP("127.0.0.1");
		GUI.newGameParameters.setClientPort(4000);

		GUI.gameParameters = new GameParameters(GUI.newGameParameters);

		GUI.createClientServerNewGame();
	}

}
