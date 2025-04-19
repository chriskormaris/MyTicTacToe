package com.chriskormaris.mytictactoe.gui.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GuiConstants {

	public static final String TITLE = "My Tic-Tac-Toe";
	public static final String VERSION = "2.4.2";

	public static final long AI_MOVE_DELAY_MILLISECONDS = 100L;

	public static final String LOCALHOST = "127.0.0.1";
	public static final int SERVER_PORT = 4000;
	public static final int CLIENT_PORT = 4001;

	public static final String IMG_PATH = "img/";
	public static final String X_IMG_PATH = IMG_PATH + "X/";
	public static final String O_IMG_PATH = IMG_PATH + "O/";

	public static final String RULES = "Click on the buttons or press 1-9 on your keyboard to insert a new symbol.\n"
			+ "To win you must place 3 symbols in an row, horizontally, vertically or diagonally.";

}
