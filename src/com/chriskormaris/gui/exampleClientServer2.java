package com.chriskormaris.gui;

import com.chriskormaris.api.enumeration.GameMode;
import com.chriskormaris.api.utility.Constants;

public class exampleClientServer2 {

    public static void main(String[] args) {
        TicTacToeGUI.gameParameters.setGameMode(GameMode.CLIENT_SERVER);
        TicTacToeGUI.gameParameters.setPlayerSymbol(Constants.O);
        TicTacToeGUI.gameParameters.setServerPort(4001);
        TicTacToeGUI.gameParameters.setClientIP("127.0.0.1");
        TicTacToeGUI.gameParameters.setClientPort(4000);

        @SuppressWarnings("unused")
        TicTacToeGUI gui = new TicTacToeGUI("My TicTacToe ClientServer 2");

        TicTacToeGUI.createClientServerNewGame();
    }

}
