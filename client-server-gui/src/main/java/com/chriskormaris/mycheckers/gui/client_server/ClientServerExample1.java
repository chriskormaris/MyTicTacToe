package com.chriskormaris.mycheckers.gui.client_server;

import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.util.GameParameters;

public class ClientServerExample1 {

	public static void main(String[] args) {
		GUI.create("My TicTacToe ClientServer 1");

		GameParameters newGameParameters = new GameParameters();
		newGameParameters.setGameMode(GameMode.CLIENT_SERVER);
		newGameParameters.setPlayerSymbol(Constants.X);
		newGameParameters.setServerPort(4000);
		newGameParameters.setClientIP("127.0.0.1");
		newGameParameters.setClientPort(4001);

		GUI.gameParameters = new GameParameters(newGameParameters);

		GUI.createClientServerNewGame();
	}

}
