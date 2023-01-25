package com.chriskormaris.mytictactoe.gui;

import com.chriskormaris.mytictactoe.api.enumeration.AiType;
import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.enumeration.Color;
import com.chriskormaris.mytictactoe.gui.enumeration.GuiStyle;
import com.chriskormaris.mytictactoe.gui.util.GameParameters;
import com.chriskormaris.mytictactoe.gui.util.GuiUtils;
import com.chriskormaris.mytictactoe.gui.util.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsWindow extends JFrame {

	public static int width = 460;
	public static int height = 515;
	private final JComboBox<String> gui_style_drop_down;
	private final JComboBox<String> game_mode_drop_down;
	private final JComboBox<String> ai_type_drop_down;
	private final JComboBox<String> ai1_max_depth_drop_down;
	private final JComboBox<String> ai2_max_depth_drop_down;
	private final JComboBox<String> player1_color_drop_down;
	private final JComboBox<String> player2_color_drop_down;
	private final JComboBox<String> player_symbol_drop_down;
	private final JTextField server_port_text_field;
	private final JTextField client_ip_text_field;
	private final JTextField client_port_text_field;
	private final JButton apply;
	private final JButton cancel;


	public SettingsWindow(Component parentComponent) {
		super("Settings");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setSize(width, height);
		setLocationRelativeTo(parentComponent);
		setResizable(false);

		EventHandler handler = new EventHandler();

		GuiStyle selectedGuiStyle = GUI.gameParameters.getGuiStyle();
		GameMode selectedMode = GUI.gameParameters.getGameMode();
		AiType aiType = GUI.gameParameters.getAiType();
		int maxDepth1 = GUI.gameParameters.getAi1MaxDepth();
		int maxDepth2 = GUI.gameParameters.getAi2MaxDepth();
		Color selectedPlayer1Color = GUI.gameParameters.getPlayer1Color();
		Color selectedPlayer2Color = GUI.gameParameters.getPlayer2Color();
		int playerSymbol = GUI.gameParameters.getPlayerSymbol();
		int serverPort = GUI.gameParameters.getServerPort();
		String clientIP = GUI.gameParameters.getClientIP();
		int clientPort = GUI.gameParameters.getClientPort();

		JLabel guiStyleLabel = new JLabel("GUI style");
		JLabel gameModeLabel = new JLabel("Game mode");
		JLabel aiTypeLabel = new JLabel("AI type");
		JLabel ai1MaxDepthLabel = new JLabel("AI 1 depth");
		JLabel ai2MaxDepthLabel = new JLabel("AI 2 depth (AiVsAi)");
		JLabel player1ColorLabel = new JLabel("Player 1 \"X\" color");
		JLabel player2ColorLabel = new JLabel("Player 2 \"O\" color");
		JLabel playerSymbolLabel = new JLabel("Player symbol");
		JLabel serverPortLabel = new JLabel("Server port");
		JLabel clientIpLabel = new JLabel("Client IP");
		JLabel clientPortLabel = new JLabel("Client port");

		add(guiStyleLabel);
		add(gameModeLabel);
		add(aiTypeLabel);
		add(ai1MaxDepthLabel);
		add(ai2MaxDepthLabel);
		add(player1ColorLabel);
		add(player2ColorLabel);
		add(playerSymbolLabel);
		add(serverPortLabel);
		add(clientIpLabel);
		add(clientPortLabel);

		gui_style_drop_down = new JComboBox<>();
		gui_style_drop_down.addItem("System style");
		gui_style_drop_down.addItem("Cross-Platform style");
		gui_style_drop_down.addItem("Nimbus style");

		if (selectedGuiStyle == GuiStyle.SYSTEM_STYLE) {
			gui_style_drop_down.setSelectedIndex(0);
		} else if (selectedGuiStyle == GuiStyle.CROSS_PLATFORM_STYLE) {
			gui_style_drop_down.setSelectedIndex(1);
		} else if (selectedGuiStyle == GuiStyle.NIMBUS_STYLE) {
			gui_style_drop_down.setSelectedIndex(2);
		}

		game_mode_drop_down = new JComboBox<>();
		game_mode_drop_down.addItem("Human Vs AI");
		game_mode_drop_down.addItem("Human Vs Human");
		game_mode_drop_down.addItem("AI Vs AI");
		game_mode_drop_down.addItem("Client-Server");

		if (selectedMode == GameMode.HUMAN_VS_AI) {
			game_mode_drop_down.setSelectedIndex(0);
		} else if (selectedMode == GameMode.HUMAN_VS_HUMAN) {
			game_mode_drop_down.setSelectedIndex(1);
		} else if (selectedMode == GameMode.AI_VS_AI) {
			game_mode_drop_down.setSelectedIndex(2);
		} else if (selectedMode == GameMode.CLIENT_SERVER) {
			game_mode_drop_down.setSelectedIndex(3);
		}

		ai_type_drop_down = new JComboBox<>();
		ai_type_drop_down.addItem("Best Response AI");
		ai_type_drop_down.addItem("Minimax AI");
		ai_type_drop_down.addItem("Random AI");

		if (aiType == AiType.BEST_RESPONSE_AI) {
			ai_type_drop_down.setSelectedIndex(0);
		} else if (aiType == AiType.MINIMAX_AI) {
			ai_type_drop_down.setSelectedIndex(1);
		} else if (aiType == AiType.RANDOM_AI) {
			ai_type_drop_down.setSelectedIndex(2);
		}

		ai1_max_depth_drop_down = new JComboBox<>();
		ai1_max_depth_drop_down.addItem("1");
		ai1_max_depth_drop_down.addItem("2");
		ai1_max_depth_drop_down.addItem("3");
		ai1_max_depth_drop_down.addItem("4");
		ai1_max_depth_drop_down.addItem("5");

		ai1_max_depth_drop_down.setSelectedIndex(maxDepth1 - 1);

		ai2_max_depth_drop_down = new JComboBox<>();
		ai2_max_depth_drop_down.addItem("1");
		ai2_max_depth_drop_down.addItem("2");
		ai2_max_depth_drop_down.addItem("3");
		ai2_max_depth_drop_down.addItem("4");
		ai2_max_depth_drop_down.addItem("5");

		ai2_max_depth_drop_down.setSelectedIndex(maxDepth2 - 1);

		player1_color_drop_down = new JComboBox<>();
		player1_color_drop_down.addItem(String.valueOf(Color.BLUE));
		player1_color_drop_down.addItem(String.valueOf(Color.RED));
		player1_color_drop_down.addItem(String.valueOf(Color.BLACK));
		player1_color_drop_down.addItem(String.valueOf(Color.GREEN));
		player1_color_drop_down.addItem(String.valueOf(Color.ORANGE));
		player1_color_drop_down.addItem(String.valueOf(Color.PURPLE));
		player1_color_drop_down.addItem(String.valueOf(Color.YELLOW));

		if (selectedPlayer1Color == Color.BLUE) {
			player1_color_drop_down.setSelectedIndex(0);
		} else if (selectedPlayer1Color == Color.RED) {
			player1_color_drop_down.setSelectedIndex(1);
		} else if (selectedPlayer1Color == Color.BLACK) {
			player1_color_drop_down.setSelectedIndex(2);
		} else if (selectedPlayer1Color == Color.GREEN) {
			player1_color_drop_down.setSelectedIndex(3);
		} else if (selectedPlayer1Color == Color.ORANGE) {
			player1_color_drop_down.setSelectedIndex(4);
		} else if (selectedPlayer1Color == Color.PURPLE) {
			player1_color_drop_down.setSelectedIndex(5);
		} else if (selectedPlayer1Color == Color.YELLOW) {
			player1_color_drop_down.setSelectedIndex(6);
		}

		player2_color_drop_down = new JComboBox<>();
		player2_color_drop_down.addItem(String.valueOf(Color.BLUE));
		player2_color_drop_down.addItem(String.valueOf(Color.RED));
		player2_color_drop_down.addItem(String.valueOf(Color.BLACK));
		player2_color_drop_down.addItem(String.valueOf(Color.GREEN));
		player2_color_drop_down.addItem(String.valueOf(Color.ORANGE));
		player2_color_drop_down.addItem(String.valueOf(Color.PURPLE));
		player2_color_drop_down.addItem(String.valueOf(Color.YELLOW));

		if (selectedPlayer2Color == Color.BLUE) {
			player2_color_drop_down.setSelectedIndex(0);
		} else if (selectedPlayer2Color == Color.RED) {
			player2_color_drop_down.setSelectedIndex(1);
		} else if (selectedPlayer2Color == Color.BLACK) {
			player2_color_drop_down.setSelectedIndex(2);
		} else if (selectedPlayer2Color == Color.GREEN) {
			player2_color_drop_down.setSelectedIndex(3);
		} else if (selectedPlayer2Color == Color.ORANGE) {
			player2_color_drop_down.setSelectedIndex(4);
		} else if (selectedPlayer2Color == Color.PURPLE) {
			player2_color_drop_down.setSelectedIndex(5);
		} else if (selectedPlayer2Color == Color.YELLOW) {
			player2_color_drop_down.setSelectedIndex(6);
		}

		player_symbol_drop_down = new JComboBox<>();
		player_symbol_drop_down.addItem("X");
		player_symbol_drop_down.addItem("O");

		if (playerSymbol == Constants.X) {
			player_symbol_drop_down.setSelectedIndex(0);
		} else if (playerSymbol == Constants.O) {
			player_symbol_drop_down.setSelectedIndex(1);
		}

		server_port_text_field = new JTextField();
		server_port_text_field.setText(serverPort + "");

		client_ip_text_field = new JTextField(clientIP);
		client_ip_text_field.setText(clientIP);

		client_port_text_field = new JTextField(clientPort);
		client_port_text_field.setText(clientPort + "");

		add(gui_style_drop_down);
		add(game_mode_drop_down);
		add(ai_type_drop_down);
		add(ai1_max_depth_drop_down);
		add(ai2_max_depth_drop_down);
		add(player1_color_drop_down);
		add(player2_color_drop_down);
		add(player_symbol_drop_down);
		add(server_port_text_field);
		add(client_ip_text_field);
		add(client_port_text_field);

		guiStyleLabel.setBounds(20, 25, 250, 25);
		gameModeLabel.setBounds(20, 60, 250, 25);
		aiTypeLabel.setBounds(20, 95, 250, 25);
		ai1MaxDepthLabel.setBounds(20, 130, 250, 25);
		ai2MaxDepthLabel.setBounds(20, 165, 250, 25);
		player1ColorLabel.setBounds(20, 200, 250, 25);
		player2ColorLabel.setBounds(20, 235, 250, 25);
		playerSymbolLabel.setBounds(20, 270, 250, 25);
		serverPortLabel.setBounds(20, 305, 250, 25);
		clientIpLabel.setBounds(20, 340, 250, 25);
		clientPortLabel.setBounds(20, 375, 250, 25);

		gui_style_drop_down.setBounds(260, 25, 160, 25);
		game_mode_drop_down.setBounds(260, 60, 160, 25);
		ai_type_drop_down.setBounds(260, 95, 160, 25);
		ai1_max_depth_drop_down.setBounds(260, 130, 160, 25);
		ai2_max_depth_drop_down.setBounds(260, 165, 160, 25);
		player1_color_drop_down.setBounds(260, 200, 160, 25);
		player2_color_drop_down.setBounds(260, 235, 160, 25);
		player_symbol_drop_down.setBounds(260, 270, 160, 25);
		server_port_text_field.setBounds(260, 305, 160, 25);
		client_ip_text_field.setBounds(260, 340, 160, 25);
		client_port_text_field.setBounds(260, 375, 160, 25);

		apply = new JButton("Apply");
		cancel = new JButton("Cancel");
		add(apply);
		add(cancel);

		int distance = 10;
		apply.setBounds((width / 2) - 110 - (distance / 2), 415, 100, 30);
		apply.addActionListener(handler);
		cancel.setBounds((width / 2) - 10 + (distance / 2), 415, 100, 30);
		cancel.addActionListener(handler);
	}


	private class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			if (ev.getSource() == cancel) {
				dispose();
			} else if (ev.getSource() == apply) {
				try {
					GuiStyle guiStyle = GuiStyle.valueOf(gui_style_drop_down.getSelectedItem().toString()
							.toUpperCase().replace("-", "_").replace(" ", "_"));
					GameMode gameMode = GameMode.valueOf(game_mode_drop_down.getSelectedItem().toString()
							.toUpperCase().replace("-", "_").replace(" ", "_"));
					AiType aiType = AiType.valueOf(ai_type_drop_down.getSelectedItem().toString()
							.toUpperCase().replace("-", "_").replace(" ", "_"));
					int ai1MaxDepth = ai1_max_depth_drop_down.getSelectedIndex() + 1;
					int ai2MaxDepth = ai2_max_depth_drop_down.getSelectedIndex() + 1;
					Color player1Color = Color.valueOf(player1_color_drop_down.getSelectedItem().toString());
					Color player2Color = Color.valueOf(player2_color_drop_down.getSelectedItem().toString());
					int playerSymbol = (player_symbol_drop_down.getSelectedIndex()) == 0 ? Constants.X : Constants.O;
					int serverPort = Integer.parseInt(server_port_text_field.getText());
					String clientIP = client_ip_text_field.getText();
					int clientPort = Integer.parseInt(client_port_text_field.getText());

					if (player1Color == player2Color) {
						JOptionPane.showMessageDialog(null,
								"Player 1 and Player 2 cannot have the same color of symbols!",
								"ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}

					ImageIcon XIcon = new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
							Constants.X,
							player1Color
					)));
					ImageIcon OIcon = new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
							Constants.O,
							player2Color
					)));

					// Change game parameters based on the settings.
					GUI.newGameParameters = new GameParameters(
							guiStyle,
							gameMode,
							aiType,
							ai1MaxDepth,
							ai2MaxDepth,
							player1Color,
							player2Color,
							playerSymbol,
							serverPort,
							clientIP,
							clientPort,
							XIcon,
							OIcon
					);

					JOptionPane.showMessageDialog(
							null,
							"Game settings have been changed.\n" +
									"The changes will be applied in the next new game.",
							"",
							JOptionPane.INFORMATION_MESSAGE
					);
					dispose();
				} catch (Exception e) {
					System.err.println("ERROR : " + e.getMessage());
				}

			}  // else if.

		}  // action performed.

	}  // inner class.


}  // class end.
