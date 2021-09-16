package utility;

import enumeration.AiType;
import enumeration.Color;
import enumeration.GameMode;
import enumeration.GuiStyle;

public class GameParameters {
	
	private GameParameters() { }  // Prevents instantiation.
	
	/* Default values */
	public static GuiStyle guiStyle = GuiStyle.SYSTEM_STYLE;
	public static GameMode gameMode = GameMode.HUMAN_VS_AI;
	public static AiType aiType = AiType.BEST_RESPONSE_AI;
	public static int ai1MaxDepth = 5;
	public static int ai2MaxDepth = 5;
	public static Color player1Color = Color.BLUE;
	public static Color player2Color = Color.RED;
	public static int clientServerSymbol = Constants.X;
	public static int serverPort = 4000;
	public static String clientIP = "127.0.0.1";
	public static int clientPort = 4001;

}
