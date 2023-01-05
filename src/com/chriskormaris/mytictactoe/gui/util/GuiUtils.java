package com.chriskormaris.mytictactoe.gui.util;

import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.enumeration.Color;

public class GuiUtils {

	public static String getIconPath(int player, Color color) {
		String stringColor = String.valueOf(color).charAt(0)
				+ String.valueOf(color).toLowerCase().substring(1);
		if (player == Constants.X) {
			return GuiConstants.X_IMG_PATH + stringColor + ".png";
		} else if (player == Constants.O) {
			return GuiConstants.O_IMG_PATH + stringColor + ".png";
		}
		return null;
	}

}
