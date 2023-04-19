package com.chriskormaris.mytictactoe.api.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

	// Variables for the board values
	public static final int X = 1;
	public static final int O = -1;
	public static final int EMPTY = 0;

	public static final int DEFAULT_MAX_DEPTH = 3;

	public static final int AI_MOVE_MILLISECONDS = 250;

	public static final String LOCALHOST = "127.0.0.1";
	public static final int SERVER_PORT = 4000;
	public static final int CLIENT_PORT = 4001;

}
