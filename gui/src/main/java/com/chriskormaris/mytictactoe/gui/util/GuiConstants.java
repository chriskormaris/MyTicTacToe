package com.chriskormaris.mytictactoe.gui.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GuiConstants {

	public static final String TITLE = "My TicTacToe";
	public static final String VERSION = "2.3.0";

	public static final String IMG_PATH = "img/";
	public static final String X_IMG_PATH = IMG_PATH + "X/";
	public static final String O_IMG_PATH = IMG_PATH + "O/";

	public static final String RULES = "Click on the buttons or press 1-9 on your keyboard to insert a new symbol.\n"
			+ "To win you must place 3 symbols in an row, horizontally, vertically or diagonally.";

}
