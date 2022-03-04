package com.chriskormaris.mytictactoe.gui.util;

import com.chriskormaris.mytictactoe.api.util.Constants;

public class GuiUtils {

    public static String getIconPath(int player, String color) {
        if (player == Constants.X) {
            return "img/X/" + color + ".png";
        } else if (player == Constants.O) {
            return "img/O/" + color + ".png";
        }
        return null;
    }

}
