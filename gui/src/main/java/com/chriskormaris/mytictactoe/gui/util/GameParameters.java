package com.chriskormaris.mytictactoe.gui.util;

import com.chriskormaris.mytictactoe.api.enumeration.AiType;
import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.enumeration.Color;
import com.chriskormaris.mytictactoe.gui.enumeration.GuiStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameParameters {

	private GuiStyle guiStyle;
	private GameMode gameMode;
	private AiType ai1Type;
	private AiType ai2Type;
	private int ai1MaxDepth;
	private int ai2MaxDepth;
	private Color player1Color;
	private Color player2Color;
	private int playerSymbol;
	private int serverPort;
	private String clientIP;
	private int clientPort;

	// Default constructor
	public GameParameters() {
		this.guiStyle = GuiStyle.CROSS_PLATFORM;
		this.gameMode = GameMode.HUMAN_VS_AI;
		this.ai1Type = AiType.BEST_RESPONSE_AI;
		this.ai2Type = AiType.BEST_RESPONSE_AI;
		this.ai1MaxDepth = Constants.DEFAULT_MAX_DEPTH;
		this.ai2MaxDepth = Constants.DEFAULT_MAX_DEPTH;
		this.player1Color = Color.BLUE;
		this.player2Color = Color.RED;
		this.playerSymbol = Constants.X;
		this.serverPort = GuiConstants.SERVER_PORT;
		this.clientIP = GuiConstants.LOCALHOST;
		this.clientPort = GuiConstants.CLIENT_PORT;
	}

	public GameParameters(GameParameters otherGameParameters) {
		this.guiStyle = otherGameParameters.guiStyle;
		this.gameMode = otherGameParameters.gameMode;
		this.ai1Type = otherGameParameters.ai1Type;
		this.ai2Type = otherGameParameters.ai2Type;
		this.ai1MaxDepth = otherGameParameters.ai1MaxDepth;
		this.ai2MaxDepth = otherGameParameters.ai2MaxDepth;
		this.player1Color = otherGameParameters.player1Color;
		this.player2Color = otherGameParameters.player2Color;
		this.playerSymbol = otherGameParameters.playerSymbol;
		this.serverPort = otherGameParameters.serverPort;
		this.clientIP = otherGameParameters.clientIP;
		this.clientPort = otherGameParameters.clientPort;
	}

}
