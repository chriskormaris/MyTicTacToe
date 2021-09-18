package gui;

import enumeration.AiType;
import enumeration.Color;
import enumeration.GameMode;
import enumeration.GuiStyle;
import utility.Constants;
import utility.GameParameters;

public class exampleClientServer2 {

    static int playerSymbol = Constants.O;
    static int serverPort = 4001;
    static String clientIP = "127.0.0.1";
    static int clientPort = 4000;

    public exampleClientServer2() {

    }

    public static void main(String[] args) {
        GameParameters.guiStyle = GuiStyle.SYSTEM_STYLE;
        GameParameters.gameMode = GameMode.CLIENT_SERVER;
        GameParameters.aiType = AiType.BEST_RESPONSE_AI;
        GameParameters.ai1MaxDepth = 5;
        GameParameters.ai2MaxDepth = 5;
        GameParameters.player1Color = Color.BLUE;
        GameParameters.player2Color = Color.RED;
        GameParameters.playerSymbol = playerSymbol;
        GameParameters.serverPort = serverPort;
        GameParameters.clientIP = clientIP;
        GameParameters.clientPort = clientPort;

        @SuppressWarnings("unused")
        TicTacToeGUI gui = new TicTacToeGUI("My TicTacToe ClientServer 2");

        TicTacToeGUI.createClientServerNewGame();
    }

}
