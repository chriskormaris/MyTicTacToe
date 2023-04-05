package com.chriskormaris.mytictactoe.gui.settings;

import com.chriskormaris.mytictactoe.api.enumeration.AiType;
import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.enumeration.Color;
import com.chriskormaris.mytictactoe.gui.enumeration.GuiStyle;
import com.chriskormaris.mytictactoe.gui.util.GameParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class SettingsWindow extends JFrame {

	private final JComboBox<String> guiStyleDropDown;
	private final JComboBox<String> gameModeDropDown;
	private final JComboBox<String> ai1TypeDropDown;
	private final JComboBox<String> ai2TypeDropDown;
	private final JComboBox<String> ai1MaxDepthDropDown;
	private final JComboBox<String> ai2MaxDepthDropDown;
	private final JComboBox<String> player1ColorDropDown;
	private final JComboBox<String> player2ColorDropDown;
	private final JComboBox<String> playerSymbolDropDown;
	private final JTextField serverPortTextField;
	private final JTextField clientIpTextField;
	private final JTextField clientPortTextField;

	private final JButton apply;
	private final JButton cancel;

	private final Component parentComponent;
	private final GameParameters newGameParameters;

	public SettingsWindow(Component parentComponent, GameParameters newGameParameters) {
		super("Settings");

		this.parentComponent = parentComponent;
		this.newGameParameters = newGameParameters;

		int width = 460;
		int height = 550;

		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		super.setLayout(null);
		super.setSize(width, height);
		super.setLocationRelativeTo(parentComponent);
		super.setResizable(false);

		GuiStyle selectedGuiStyle = newGameParameters.getGuiStyle();
		GameMode selectedMode = newGameParameters.getGameMode();
		AiType ai1Type = newGameParameters.getAi1Type();
		AiType ai2Type = newGameParameters.getAi2Type();
		int ai1MaxDepth = newGameParameters.getAi1MaxDepth();
		int ai2MaxDepth = newGameParameters.getAi2MaxDepth();
		Color selectedPlayer1Color = newGameParameters.getPlayer1Color();
		Color selectedPlayer2Color = newGameParameters.getPlayer2Color();
		int playerSymbol = newGameParameters.getPlayerSymbol();
		int serverPort = newGameParameters.getServerPort();
		String clientIP = newGameParameters.getClientIP();
		int clientPort = newGameParameters.getClientPort();

		List<JLabel> labels = new ArrayList<>();
		List<JComponent> components = new ArrayList<>();

		JLabel guiStyleLabel = new JLabel("GUI style");
		JLabel gameModeLabel = new JLabel("Game mode");
		JLabel ai1TypeLabel = new JLabel("AI 1 type");
		JLabel ai2TypeLabel = new JLabel("AI 2 type (AI vs AI)");
		JLabel ai1MaxDepthLabel = new JLabel("AI 1 depth");
		JLabel ai2MaxDepthLabel = new JLabel("AI 2 depth (AI vs AI)");
		JLabel player1ColorLabel = new JLabel("Player 1 \"X\" color");
		JLabel player2ColorLabel = new JLabel("Player 2 \"O\" color");
		JLabel playerSymbolLabel = new JLabel("Player symbol");
		JLabel serverPortLabel = new JLabel("Server port");
		JLabel clientIpLabel = new JLabel("Client IP");
		JLabel clientPortLabel = new JLabel("Client port");

		labels.add(guiStyleLabel);
		labels.add(gameModeLabel);
		labels.add(ai1TypeLabel);
		labels.add(ai2TypeLabel);
		labels.add(ai1MaxDepthLabel);
		labels.add(ai2MaxDepthLabel);
		labels.add(player1ColorLabel);
		labels.add(player2ColorLabel);
		labels.add(playerSymbolLabel);
		labels.add(serverPortLabel);
		labels.add(clientIpLabel);
		labels.add(clientPortLabel);

		guiStyleDropDown = new JComboBox<>();
		guiStyleDropDown.addItem("Cross-Platform style");
		guiStyleDropDown.addItem("System style");
		guiStyleDropDown.addItem("Nimbus style");

		if (selectedGuiStyle == GuiStyle.CROSS_PLATFORM_STYLE) {
			guiStyleDropDown.setSelectedIndex(0);
		} else if (selectedGuiStyle == GuiStyle.SYSTEM_STYLE) {
			guiStyleDropDown.setSelectedIndex(1);
		} else if (selectedGuiStyle == GuiStyle.NIMBUS_STYLE) {
			guiStyleDropDown.setSelectedIndex(2);
		}

		gameModeDropDown = new JComboBox<>();
		gameModeDropDown.addItem("Human vs AI");
		gameModeDropDown.addItem("Human vs Human");
		gameModeDropDown.addItem("AI vs AI");
		gameModeDropDown.addItem("Client-Server");

		if (selectedMode == GameMode.HUMAN_VS_AI) {
			gameModeDropDown.setSelectedIndex(0);
		} else if (selectedMode == GameMode.HUMAN_VS_HUMAN) {
			gameModeDropDown.setSelectedIndex(1);
		} else if (selectedMode == GameMode.AI_VS_AI) {
			gameModeDropDown.setSelectedIndex(2);
		} else if (selectedMode == GameMode.CLIENT_SERVER) {
			gameModeDropDown.setSelectedIndex(3);
		}

		ai1TypeDropDown = new JComboBox<>();
		ai1TypeDropDown.addItem("Best Response AI");
		ai1TypeDropDown.addItem("Minimax AI");
		ai1TypeDropDown.addItem("Random AI");

		if (ai1Type == AiType.BEST_RESPONSE_AI) {
			ai1TypeDropDown.setSelectedIndex(0);
		} else if (ai1Type == AiType.MINIMAX_AI) {
			ai1TypeDropDown.setSelectedIndex(1);
		} else if (ai1Type == AiType.RANDOM_AI) {
			ai1TypeDropDown.setSelectedIndex(2);
		}

		ai2TypeDropDown = new JComboBox<>();
		ai2TypeDropDown.addItem("Best Response AI");
		ai2TypeDropDown.addItem("Minimax AI");
		ai2TypeDropDown.addItem("Random AI");

		if (ai2Type == AiType.BEST_RESPONSE_AI) {
			ai2TypeDropDown.setSelectedIndex(0);
		} else if (ai2Type == AiType.MINIMAX_AI) {
			ai2TypeDropDown.setSelectedIndex(1);
		} else if (ai2Type == AiType.RANDOM_AI) {
			ai2TypeDropDown.setSelectedIndex(2);
		}

		ai1MaxDepthDropDown = new JComboBox<>();
		ai1MaxDepthDropDown.addItem("1");
		ai1MaxDepthDropDown.addItem("2");
		ai1MaxDepthDropDown.addItem("3");
		ai1MaxDepthDropDown.addItem("4");
		ai1MaxDepthDropDown.addItem("5");

		ai1MaxDepthDropDown.setSelectedIndex(ai1MaxDepth - 1);

		ai2MaxDepthDropDown = new JComboBox<>();
		ai2MaxDepthDropDown.addItem("1");
		ai2MaxDepthDropDown.addItem("2");
		ai2MaxDepthDropDown.addItem("3");
		ai2MaxDepthDropDown.addItem("4");
		ai2MaxDepthDropDown.addItem("5");

		ai2MaxDepthDropDown.setSelectedIndex(ai2MaxDepth - 1);

		player1ColorDropDown = new JComboBox<>();
		player1ColorDropDown.addItem(String.valueOf(Color.BLUE));
		player1ColorDropDown.addItem(String.valueOf(Color.RED));
		player1ColorDropDown.addItem(String.valueOf(Color.BLACK));
		player1ColorDropDown.addItem(String.valueOf(Color.GREEN));
		player1ColorDropDown.addItem(String.valueOf(Color.ORANGE));
		player1ColorDropDown.addItem(String.valueOf(Color.PURPLE));
		player1ColorDropDown.addItem(String.valueOf(Color.YELLOW));

		if (selectedPlayer1Color == Color.BLUE) {
			player1ColorDropDown.setSelectedIndex(0);
		} else if (selectedPlayer1Color == Color.RED) {
			player1ColorDropDown.setSelectedIndex(1);
		} else if (selectedPlayer1Color == Color.BLACK) {
			player1ColorDropDown.setSelectedIndex(2);
		} else if (selectedPlayer1Color == Color.GREEN) {
			player1ColorDropDown.setSelectedIndex(3);
		} else if (selectedPlayer1Color == Color.ORANGE) {
			player1ColorDropDown.setSelectedIndex(4);
		} else if (selectedPlayer1Color == Color.PURPLE) {
			player1ColorDropDown.setSelectedIndex(5);
		} else if (selectedPlayer1Color == Color.YELLOW) {
			player1ColorDropDown.setSelectedIndex(6);
		}

		player2ColorDropDown = new JComboBox<>();
		player2ColorDropDown.addItem(String.valueOf(Color.BLUE));
		player2ColorDropDown.addItem(String.valueOf(Color.RED));
		player2ColorDropDown.addItem(String.valueOf(Color.BLACK));
		player2ColorDropDown.addItem(String.valueOf(Color.GREEN));
		player2ColorDropDown.addItem(String.valueOf(Color.ORANGE));
		player2ColorDropDown.addItem(String.valueOf(Color.PURPLE));
		player2ColorDropDown.addItem(String.valueOf(Color.YELLOW));

		if (selectedPlayer2Color == Color.BLUE) {
			player2ColorDropDown.setSelectedIndex(0);
		} else if (selectedPlayer2Color == Color.RED) {
			player2ColorDropDown.setSelectedIndex(1);
		} else if (selectedPlayer2Color == Color.BLACK) {
			player2ColorDropDown.setSelectedIndex(2);
		} else if (selectedPlayer2Color == Color.GREEN) {
			player2ColorDropDown.setSelectedIndex(3);
		} else if (selectedPlayer2Color == Color.ORANGE) {
			player2ColorDropDown.setSelectedIndex(4);
		} else if (selectedPlayer2Color == Color.PURPLE) {
			player2ColorDropDown.setSelectedIndex(5);
		} else if (selectedPlayer2Color == Color.YELLOW) {
			player2ColorDropDown.setSelectedIndex(6);
		}

		playerSymbolDropDown = new JComboBox<>();
		playerSymbolDropDown.addItem("X");
		playerSymbolDropDown.addItem("O");

		if (playerSymbol == Constants.X) {
			playerSymbolDropDown.setSelectedIndex(0);
		} else if (playerSymbol == Constants.O) {
			playerSymbolDropDown.setSelectedIndex(1);
		}

		serverPortTextField = new JTextField();
		serverPortTextField.setText(String.valueOf(serverPort));

		clientIpTextField = new JTextField(clientIP);
		clientIpTextField.setText(clientIP);

		clientPortTextField = new JTextField(clientPort);
		clientPortTextField.setText(String.valueOf(clientPort));

		components.add(guiStyleDropDown);
		components.add(gameModeDropDown);
		components.add(ai1TypeDropDown);
		components.add(ai2TypeDropDown);
		components.add(ai1MaxDepthDropDown);
		components.add(ai2MaxDepthDropDown);
		components.add(player1ColorDropDown);
		components.add(player2ColorDropDown);
		components.add(playerSymbolDropDown);
		components.add(serverPortTextField);
		components.add(clientIpTextField);
		components.add(clientPortTextField);

		int x = 20;
		int y = 25;
		int distance = 35;
		int w = 250;
		int h = 25;
		for (JLabel label : labels) {
			label.setBounds(x, y, w, h);
			y = y + distance;
			super.add(label);
		}

		x = 260;
		y = 25;
		w = 160;
		for (JComponent component : components) {
			component.setBounds(x, y, w, h);
			y = y + distance;
			super.add(component);
		}

		apply = new JButton("Apply");
		cancel = new JButton("Cancel");

		distance = 10;
		y = 450;
		w = 100;
		h = 30;
		EventHandler handler = new EventHandler();
		apply.setBounds((width / 2) - 110 - (distance / 2), y, w, h);
		apply.addActionListener(handler);
		cancel.setBounds((width / 2) - 10 + (distance / 2), y, w, h);
		cancel.addActionListener(handler);

		super.add(apply);
		super.add(cancel);
	}


	private class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == cancel) {
				dispose();
			} else if (event.getSource() == apply) {
				try {
					GuiStyle guiStyle = GuiStyle.valueOf(guiStyleDropDown.getSelectedItem().toString().toUpperCase()
							.replace("-", "_").replace(" ", "_"));
					GameMode gameMode = GameMode.valueOf(gameModeDropDown.getSelectedItem().toString().toUpperCase()
							.replace("-", "_").replace(" ", "_"));
					AiType ai1Type = AiType.valueOf(ai1TypeDropDown.getSelectedItem().toString().toUpperCase()
							.replace("-", "_").replace(" ", "_"));
					AiType ai2Type = AiType.valueOf(ai2TypeDropDown.getSelectedItem().toString().toUpperCase()
							.replace("-", "_").replace(" ", "_"));
					int ai1MaxDepth = ai1MaxDepthDropDown.getSelectedIndex() + 1;
					int ai2MaxDepth = ai2MaxDepthDropDown.getSelectedIndex() + 1;
					com.chriskormaris.mytictactoe.gui.enumeration.Color player1Color = Color.valueOf(
							player1ColorDropDown.getSelectedItem().toString()
					);
					com.chriskormaris.mytictactoe.gui.enumeration.Color player2Color = Color.valueOf(
							player2ColorDropDown.getSelectedItem().toString()
					);
					int playerSymbol = (playerSymbolDropDown.getSelectedIndex()) == 0 ? Constants.X : Constants.O;
					int serverPort = Integer.parseInt(serverPortTextField.getText());
					String clientIP = clientIpTextField.getText();
					int clientPort = Integer.parseInt(clientPortTextField.getText());

					if (player1Color == player2Color) {
						JOptionPane.showMessageDialog(
								parentComponent,
								"Player 1 and Player 2 cannot have the same color of symbols!",
								"ERROR",
								JOptionPane.ERROR_MESSAGE
						);
						return;
					}

					// Change game parameters based on the settings.
					newGameParameters.setGuiStyle(guiStyle);
					newGameParameters.setGameMode(gameMode);
					newGameParameters.setAi1Type(ai1Type);
					newGameParameters.setAi2Type(ai2Type);
					newGameParameters.setAi1MaxDepth(ai1MaxDepth);
					newGameParameters.setAi2MaxDepth(ai2MaxDepth);
					newGameParameters.setPlayer1Color(player1Color);
					newGameParameters.setPlayer2Color(player2Color);
					newGameParameters.setPlayerSymbol(playerSymbol);
					newGameParameters.setServerPort(serverPort);
					newGameParameters.setClientIP(clientIP);
					newGameParameters.setClientPort(clientPort);

					JOptionPane.showMessageDialog(
							parentComponent,
							"Game settings have been changed.\n" +
									"The changes will be applied in the next new game.",
							"",
							JOptionPane.INFORMATION_MESSAGE
					);
					dispose();
				} catch (Exception ex) {
					System.err.println("ERROR : " + ex.getMessage());
				}

			}  // else if.

		}  // action performed.

	}  // inner class.


}  // class end.
