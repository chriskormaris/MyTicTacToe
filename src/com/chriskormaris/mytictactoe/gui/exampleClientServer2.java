package com.chriskormaris.mytictactoe.gui;

import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;

public class exampleClientServer2 {

	public static void main(String[] args) {
		GUI.gameParameters.setGameMode(GameMode.CLIENT_SERVER);
		GUI.gameParameters.setPlayerSymbol(Constants.O);
		GUI.gameParameters.setServerPort(4001);
		GUI.gameParameters.setClientIP("127.0.0.1");
		GUI.gameParameters.setClientPort(4000);

		@SuppressWarnings("unused")
		GUI gui = new GUI("My TicTacToe ClientServer 2");

		GUI.createClientServerNewGame();
	}

}
