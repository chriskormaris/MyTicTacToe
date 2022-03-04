package com.chriskormaris.mytictactoe.gui;

import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;

public class exampleClientServer {

    public static void main(String[] args) {
        TicTacToeGUI.gameParameters.setGameMode(GameMode.CLIENT_SERVER);
        TicTacToeGUI.gameParameters.setPlayerSymbol(Constants.X);
        TicTacToeGUI.gameParameters.setServerPort(4000);
        TicTacToeGUI.gameParameters.setClientIP("127.0.0.1");
        TicTacToeGUI.gameParameters.setClientPort(4001);

        @SuppressWarnings("unused")
        TicTacToeGUI gui = new TicTacToeGUI("My TicTacToe ClientServer 1");

        TicTacToeGUI.createClientServerNewGame();
    }

}
