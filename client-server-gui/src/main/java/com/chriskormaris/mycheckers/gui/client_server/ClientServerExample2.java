package com.chriskormaris.mycheckers.gui.client_server;

import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.util.GameParameters;

public class ClientServerExample2 {

	public static void main(String[] args) {
		GUI gui = new GUI("My TicTacToe ClientServer 2");

		gui.newGameParameters.setGameMode(GameMode.CLIENT_SERVER);
		gui.newGameParameters.setPlayerSymbol(Constants.O);
		gui.newGameParameters.setServerPort(4001);
		gui.newGameParameters.setClientIP("127.0.0.1");
		gui.newGameParameters.setClientPort(4000);

		gui.gameParameters = new GameParameters(gui.newGameParameters);
		gui.newGameParameters = new GameParameters(gui.newGameParameters);

		gui.createClientServerNewGame();
	}

}
