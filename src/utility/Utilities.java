package utility;
/*
 * Athens 2021
 *
 * Created on : 2021-09-18
 * Author     : Christos Kormaris
 */

public class Utilities {

    public static String getIconPath(int player, String color) {
        if (player == Constants.X) {
            return "img/X/" + color + ".png";
        } else if (player == Constants.O) {
            return "img/O/" + color + ".png";
        }
        return null;
    }

}
