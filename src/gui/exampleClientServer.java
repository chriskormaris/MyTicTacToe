package gui;

import enumeration.AiType;
import enumeration.Color;
import enumeration.GameMode;
import enumeration.GuiStyle;
import utility.Constants;
import utility.GameParameters;

public class exampleClientServer {

    private static int playerSymbol = Constants.X;
    private static int serverPort = 4000;
    private static String clientIP = "127.0.0.1";
    private static int clientPort = 4001;

    public static void main(String[] args) {
        TicTacToeGUI.gameParameters.setGameMode(GameMode.CLIENT_SERVER);
        TicTacToeGUI.gameParameters.setPlayerSymbol(playerSymbol);
        TicTacToeGUI.gameParameters.setServerPort(serverPort);
        TicTacToeGUI.gameParameters.setClientIP(clientIP);
        TicTacToeGUI.gameParameters.setClientPort(clientPort);

        @SuppressWarnings("unused")
        TicTacToeGUI gui = new TicTacToeGUI("My TicTacToe ClientServer 1");

        TicTacToeGUI.createClientServerNewGame();
    }

}
