package gui;

import enumeration.GameMode;
import utility.Constants;

public class exampleClientServer2 {

    private static int playerSymbol = Constants.O;
    private static int serverPort = 4001;
    private static String clientIP = "127.0.0.1";
    private static int clientPort = 4000;

    public static void main(String[] args) {
        TicTacToeGUI.gameParameters.setGameMode(GameMode.CLIENT_SERVER);
        TicTacToeGUI.gameParameters.setPlayerSymbol(playerSymbol);
        TicTacToeGUI.gameParameters.setServerPort(serverPort);
        TicTacToeGUI.gameParameters.setClientIP(clientIP);
        TicTacToeGUI.gameParameters.setClientPort(clientPort);

        @SuppressWarnings("unused")
        TicTacToeGUI gui = new TicTacToeGUI("My TicTacToe ClientServer 2");

        TicTacToeGUI.createClientServerNewGame();
    }

}
