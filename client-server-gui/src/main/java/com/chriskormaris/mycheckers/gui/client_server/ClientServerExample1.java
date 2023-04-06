package com.chriskormaris.mycheckers.gui.client_server;

import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.util.GameParameters;

public class ClientServerExample1 {

	public static void main(String[] args) {
		GUI gui = new GUI("My TicTacToe ClientServer 1");

		gui.newGameParameters.setGameMode(GameMode.CLIENT_SERVER);
		gui.newGameParameters.setPlayerSymbol(Constants.X);
		gui.newGameParameters.setServerPort(4000);
		gui.newGameParameters.setClientIP("127.0.0.1");
		gui.newGameParameters.setClientPort(4001);

		gui.gameParameters = new GameParameters(gui.newGameParameters);

		gui.createClientServerNewGame();
	}

}
