package utility;

public class Constants {
	
	private Constants() { }  // Prevents instantiation.

	public static final String VERSION = "1.3.0";

    // Variables for the board values
	public static final int X = 1;
	public static final int O = -1;
	public static final int EMPTY = 0;
	
	public static final int AI_MOVE_MILLISECONDS = 250;

	public static String getIconPath(int player, String color) {
		if (player == X) {
			return "img/X/" + color + ".png";
		} else {
			return "img/O/" + color + ".png";
		}
	}
	
}
