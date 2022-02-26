package com.chriskormaris.mytictactoe.gui.utility;

import com.chriskormaris.mytictactoe.api.utility.Constants;

public class GuiUtilities {

    public static String getIconPath(int player, String color) {
        if (player == Constants.X) {
            return "img/X/" + color + ".png";
        } else if (player == Constants.O) {
            return "img/O/" + color + ".png";
        }
        return null;
    }

}
