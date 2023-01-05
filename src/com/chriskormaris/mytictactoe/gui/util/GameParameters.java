package com.chriskormaris.mytictactoe.gui.util;

import com.chriskormaris.mytictactoe.api.enumeration.AiType;
import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.enumeration.Color;
import com.chriskormaris.mytictactoe.gui.enumeration.GuiStyle;

import javax.swing.*;

public class GameParameters {

	private GuiStyle guiStyle;
	private GameMode gameMode;
	private AiType aiType;
	private int ai1MaxDepth;
	private int ai2MaxDepth;
	private Color player1Color;
	private Color player2Color;
	private int playerSymbol;
	private int serverPort;
	private String clientIP;
	private int clientPort;
	private ImageIcon XIcon;
	private ImageIcon OIcon;

	// Default constructor
	public GameParameters() {
		this.guiStyle = GuiStyle.SYSTEM_STYLE;
		this.gameMode = GameMode.HUMAN_VS_AI;
		this.aiType = AiType.BEST_RESPONSE_AI;
		this.ai1MaxDepth = 3;
		this.ai2MaxDepth = 3;
		this.player1Color = Color.BLUE;
		this.player2Color = Color.RED;
		this.playerSymbol = Constants.X;
		this.serverPort = Constants.SERVER_PORT;
		this.clientIP = Constants.LOCALHOST;
		this.clientPort = Constants.CLIENT_PORT;
		this.XIcon = new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(Constants.X, Color.BLUE)));
		this.OIcon = new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(Constants.O, Color.RED)));
	}

	public GameParameters(GameParameters otherGameParameters) {
		this.guiStyle = otherGameParameters.guiStyle;
		this.gameMode = otherGameParameters.gameMode;
		this.aiType = otherGameParameters.aiType;
		this.ai1MaxDepth = otherGameParameters.ai1MaxDepth;
		this.ai2MaxDepth = otherGameParameters.ai2MaxDepth;
		this.player1Color = otherGameParameters.player1Color;
		this.player2Color = otherGameParameters.player2Color;
		this.playerSymbol = otherGameParameters.playerSymbol;
		this.serverPort = otherGameParameters.serverPort;
		this.clientIP = otherGameParameters.clientIP;
		this.clientPort = otherGameParameters.clientPort;
		this.XIcon = otherGameParameters.XIcon;
		this.OIcon = otherGameParameters.OIcon;
	}

	public GameParameters(
			GuiStyle guiStyle,
			GameMode gameMode,
			AiType aiType,
			int ai1MaxDepth,
			int ai2MaxDepth,
			Color player1Color,
			Color player2Color,
			int playerSymbol,
			int serverPort,
			String clientIP,
			int clientPort,
			ImageIcon XIcon,
			ImageIcon OIcon
	) {
		this.guiStyle = guiStyle;
		this.gameMode = gameMode;
		this.aiType = aiType;
		this.ai1MaxDepth = ai1MaxDepth;
		this.ai2MaxDepth = ai2MaxDepth;
		this.player1Color = player1Color;
		this.player2Color = player2Color;
		this.playerSymbol = playerSymbol;
		this.serverPort = serverPort;
		this.clientIP = clientIP;
		this.clientPort = clientPort;
		this.XIcon = XIcon;
		this.OIcon = OIcon;
	}

	public GuiStyle getGuiStyle() {
		return guiStyle;
	}

	public void setGuiStyle(GuiStyle guiStyle) {
		this.guiStyle = guiStyle;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public AiType getAiType() {
		return aiType;
	}

	public void setAiType(AiType aiType) {
		this.aiType = aiType;
	}

	public int getAi1MaxDepth() {
		return ai1MaxDepth;
	}

	public void setAi1MaxDepth(int ai1MaxDepth) {
		this.ai1MaxDepth = ai1MaxDepth;
	}

	public int getAi2MaxDepth() {
		return ai2MaxDepth;
	}

	public void setAi2MaxDepth(int ai2MaxDepth) {
		this.ai2MaxDepth = ai2MaxDepth;
	}

	public Color getPlayer1Color() {
		return player1Color;
	}

	public void setPlayer1Color(Color player1Color) {
		this.player1Color = player1Color;
	}

	public Color getPlayer2Color() {
		return player2Color;
	}

	public void setPlayer2Color(Color player2Color) {
		this.player2Color = player2Color;
	}

	public int getPlayerSymbol() {
		return playerSymbol;
	}

	public void setPlayerSymbol(int playerSymbol) {
		this.playerSymbol = playerSymbol;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

	public ImageIcon getXIcon() {
		return XIcon;
	}

	public void setXIcon(ImageIcon XIcon) {
		this.XIcon = XIcon;
	}

	public ImageIcon getOIcon() {
		return OIcon;
	}

	public void setOIcon(ImageIcon OIcon) {
		this.OIcon = OIcon;
	}

}
