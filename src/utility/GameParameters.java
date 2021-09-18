package utility;

import enumeration.AiType;
import enumeration.Color;
import enumeration.GameMode;
import enumeration.GuiStyle;

import javax.swing.*;

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
	public static int playerSymbol = Constants.X;
	public static int serverPort = 4000;
	public static String clientIP = "127.0.0.1";
	public static int clientPort = 4001;

	public static ImageIcon X_ICON = new ImageIcon(ResourceLoader.load(Constants.getIconPath(Constants.X, "Blue")));
	public static ImageIcon O_ICON = new ImageIcon(ResourceLoader.load(Constants.getIconPath(Constants.O, "Red")));

}
