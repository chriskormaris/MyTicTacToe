package com.chriskormaris.mytictactoe.gui;

import com.chriskormaris.mytictactoe.api.ai.AI;
import com.chriskormaris.mytictactoe.api.ai.BestResponseAI;
import com.chriskormaris.mytictactoe.api.ai.MinimaxAI;
import com.chriskormaris.mytictactoe.api.ai.RandomChoiceAI;
import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.board.Move;
import com.chriskormaris.mytictactoe.api.enumeration.AiType;
import com.chriskormaris.mytictactoe.api.enumeration.GameMode;
import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.button.AiVsAiButton;
import com.chriskormaris.mytictactoe.gui.button.ClientServerButton;
import com.chriskormaris.mytictactoe.gui.button.HumanVsAiButton;
import com.chriskormaris.mytictactoe.gui.button.HumanVsHumanButton;
import com.chriskormaris.mytictactoe.gui.client_server.Server;
import com.chriskormaris.mytictactoe.gui.enumeration.Color;
import com.chriskormaris.mytictactoe.gui.enumeration.GuiStyle;
import com.chriskormaris.mytictactoe.gui.settings.SettingsWindow;
import com.chriskormaris.mytictactoe.gui.util.GameParameters;
import com.chriskormaris.mytictactoe.gui.util.GuiConstants;
import com.chriskormaris.mytictactoe.gui.util.GuiUtils;
import com.chriskormaris.mytictactoe.gui.util.ResourceLoader;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class GUI extends JFrame {

	public GameParameters gameParameters;
	public GameParameters newGameParameters;

	public ImageIcon XIcon;
	public ImageIcon OIcon;

	JPanel panel;
	GridLayout layout;

	public HumanVsHumanButton[] humanVsHumanButtons;
	public HumanVsAiButton[] humanVsAiButtons;
	public AiVsAiButton[] aiVsAiButtons;
	public ClientServerButton[] clientServerButtons;

	public Board board;

	// Menu bars and items
	JMenuBar menuBar;

	public JMenuItem undoItem;
	public JMenuItem redoItem;

	// These Stack objects are used for the "undo" and "redo" functionalities.
	final Stack<Board> undoBoards;
	final Stack<Board> redoBoards;

	KeyListener gameKeyListener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			String button = KeyEvent.getKeyText(e.getKeyCode());

			for (int i = 0; i < 9; i++) {
				if (button.equals(i + 1 + "")) {
					if (gameParameters.getGameMode() == GameMode.HUMAN_VS_AI) {
						humanVsAiButtons[i].doClick();
					}
					if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
						humanVsHumanButtons[i].doClick();
					}
					if (gameParameters.getGameMode() == GameMode.CLIENT_SERVER) {
						clientServerButtons[i].doClick();
					}
					break;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	};

	KeyListener undoRedoKeyListener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) && (e.getKeyCode() == KeyEvent.VK_Z)) {
				undo();
			} else if (((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) && (e.getKeyCode() == KeyEvent.VK_Y)) {
				redo();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	};

	public GUI() {
		this(GuiConstants.TITLE);
	}

	public GUI(String title) {
		super(title);

		gameParameters = new GameParameters();
		newGameParameters = new GameParameters(gameParameters);

		XIcon = new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
				Constants.X,
				Color.BLUE
		)));
		OIcon = new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
				Constants.O,
				Color.RED
		)));

		undoBoards = new Stack<>();
		redoBoards = new Stack<>();

		configureGuiStyle();

		super.setSize(500, 500);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setTitle(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (screenSize.getWidth() - super.getWidth()) / 2;
		int y = (int) (screenSize.getHeight() - super.getHeight()) / 2;
		super.setLocation(x, y);

		super.addKeyListener(gameKeyListener);
		super.addKeyListener(undoRedoKeyListener);
	}

	private void configureGuiStyle() {
		try {
			if (gameParameters.getGuiStyle() == GuiStyle.CROSS_PLATFORM_STYLE) {
				// Option 1
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} else if (gameParameters.getGuiStyle() == GuiStyle.SYSTEM_STYLE) {
				// Option 2
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} else if (gameParameters.getGuiStyle() == GuiStyle.NIMBUS_STYLE) {
				// Option 3
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			}
		} catch (Exception ex1) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
	}

	private void addMenus() {
		// Adding the menu bar
		menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem newGameItem = new JMenuItem("New Game");
		undoItem = new JMenuItem("Undo    Ctrl+Z");
		redoItem = new JMenuItem("Redo    Ctrl+Y");
		JMenuItem settingsItem = new JMenuItem("Settings");
		JMenuItem exitItem = new JMenuItem("Exit");

		undoItem.setEnabled(false);
		redoItem.setEnabled(false);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem howToPlayItem = new JMenuItem("How to Play");
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(howToPlayItem);
		helpMenu.add(aboutItem);

		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		newGameItem.addActionListener(e -> {
			XIcon = new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
					Constants.X,
					gameParameters.getPlayer1Color()
			)));
			OIcon = new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
					Constants.O,
					gameParameters.getPlayer2Color()
			)));

			if (gameParameters.getGameMode() == GameMode.HUMAN_VS_AI) {
				createHumanVsAiNewGame();
			} else if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
				createHumanVsHumanNewGame();
			} else if (gameParameters.getGameMode() == GameMode.AI_VS_AI) {
				createAiVsAiNewGame();
			} else if (gameParameters.getGameMode() == GameMode.CLIENT_SERVER) {
				createClientServerNewGame();
			}

			undoBoards.clear();
			redoBoards.clear();

			undoItem.setEnabled(false);
			redoItem.setEnabled(false);

			System.out.println(board);
		});

		undoItem.addActionListener(e -> undo());

		redoItem.addActionListener(e -> redo());

		settingsItem.addActionListener(e -> {
			SettingsWindow settings = new SettingsWindow(this, gameParameters, newGameParameters);
			settings.setVisible(true);
		});

		exitItem.addActionListener(e -> System.exit(0));

		howToPlayItem.addActionListener(
				e -> JOptionPane.showMessageDialog(
						this,
						GuiConstants.RULES,
						"How to Play",
						JOptionPane.INFORMATION_MESSAGE
				)
		);

		aboutItem.addActionListener(e -> {
			JLabel label = new JLabel(
					"<html>© Created by: Christos Kormaris<br>"
							+ "Version " + GuiConstants.VERSION + "</html>"
			);
			JOptionPane.showMessageDialog(this, label, "About", JOptionPane.INFORMATION_MESSAGE);
		});

		fileMenu.add(newGameItem);
		fileMenu.add(undoItem);
		fileMenu.add(redoItem);
		fileMenu.add(settingsItem);
		fileMenu.add(exitItem);

		super.setJMenuBar(menuBar);
	}

	public void undo() {
		if (!undoBoards.isEmpty()) {
			System.out.println("Undo is pressed!");

			// This is the "undo" implementation for Human VS Human mode.
			if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
				try {
					redoBoards.push(new Board(board));
					board = undoBoards.pop();

					for (HumanVsHumanButton button : humanVsHumanButtons) {
						List<Integer> cell = getBoardCellById(button.id);
						if (board.getGameBoard()[cell.get(0)][cell.get(1)] == Constants.X) {
							button.setIcon(new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
									Constants.X,
									gameParameters.getPlayer1Color()
							))));
							button.doClick();
						} else if (board.getGameBoard()[cell.get(0)][cell.get(1)] == Constants.O) {
							button.setIcon(new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
									Constants.O,
									gameParameters.getPlayer2Color()
							))));
							button.doClick();
						} else {
							button.setIcon(null);
							if (button.getActionListeners().length == 0)
								button.addActionListener(button);
						}
					}

					if (undoBoards.isEmpty()) {
						undoItem.setEnabled(false);
					}

					redoItem.setEnabled(true);

					System.out.println(board);
				} catch (ArrayIndexOutOfBoundsException ex) {
					System.err.println("No move has been made yet!");
					System.err.flush();
				}
			}

			// This is the "undo" implementation for Human VS AI mode.
			else if (gameParameters.getGameMode() == GameMode.HUMAN_VS_AI) {
				try {
					redoBoards.push(new Board(board));
					board = undoBoards.pop();

					for (HumanVsAiButton button : humanVsAiButtons) {
						List<Integer> cell = getBoardCellById(button.id);
						if (board.getGameBoard()[cell.get(0)][cell.get(1)] == Constants.X) {
							button.setIcon(new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
									Constants.X,
									gameParameters.getPlayer1Color()
							))));
							button.doClick();
						} else if (board.getGameBoard()[cell.get(0)][cell.get(1)] == Constants.O) {
							button.setIcon(new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
									Constants.O,
									gameParameters.getPlayer2Color()
							))));
							button.doClick();
						} else {
							button.setIcon(null);
							if (button.getActionListeners().length == 0)
								button.addActionListener(button);
						}
					}

					if (undoBoards.isEmpty()) {
						undoItem.setEnabled(false);
					}

					redoItem.setEnabled(true);

					System.out.println(board);
				} catch (ArrayIndexOutOfBoundsException ex) {
					System.err.println("No move has been made yet!");
					System.err.flush();
				}
			}
		}
	}

	public void redo() {
		if (!redoBoards.isEmpty()) {
			System.out.println("Redo is pressed!");

			// This is the "redo" implementation for Human VS Human mode.
			if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
				try {

					undoBoards.push(new Board(board));
					board = new Board(redoBoards.pop());

					for (HumanVsHumanButton button : humanVsHumanButtons) {
						List<Integer> cell = getBoardCellById(button.id);
						if (board.getGameBoard()[cell.get(0)][cell.get(1)] == Constants.X) {
							button.setIcon(new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
									Constants.X,
									gameParameters.getPlayer1Color()
							))));
							for (ActionListener actionListener : button.getActionListeners()) {
								button.removeActionListener(actionListener);
							}
						} else if (board.getGameBoard()[cell.get(0)][cell.get(1)] == Constants.O) {
							button.setIcon(new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
									Constants.O,
									gameParameters.getPlayer2Color()
							))));
							for (ActionListener actionListener : button.getActionListeners()) {
								button.removeActionListener(actionListener);
							}
						} else {
							button.setIcon(null);
							if (button.getActionListeners().length == 0)
								button.addActionListener(button);
						}
					}

					if (redoBoards.isEmpty()) {
						redoItem.setEnabled(false);
					}

					undoItem.setEnabled(true);

					System.out.println(board);

					boolean isGameOver = board.isTerminal();
					if (isGameOver) {
						gameOver();
					}

				} catch (ArrayIndexOutOfBoundsException ex) {
					System.err.println("No move has been made yet!");
					System.err.flush();
				}
			} else if (gameParameters.getGameMode() == GameMode.HUMAN_VS_AI) {

				try {
					undoBoards.push(new Board(board));
					board = new Board(redoBoards.pop());

					for (HumanVsAiButton button : humanVsAiButtons) {
						List<Integer> cell = getBoardCellById(button.id);
						if (board.getGameBoard()[cell.get(0)][cell.get(1)] == Constants.X) {
							button.setIcon(new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
									Constants.X,
									gameParameters.getPlayer1Color()
							))));
							for (ActionListener actionListener : button.getActionListeners()) {
								button.removeActionListener(actionListener);
							}
						} else if (board.getGameBoard()[cell.get(0)][cell.get(1)] == Constants.O) {
							button.setIcon(new ImageIcon(ResourceLoader.load(GuiUtils.getIconPath(
									Constants.O,
									gameParameters.getPlayer2Color()
							))));
							for (ActionListener actionListener : button.getActionListeners()) {
								button.removeActionListener(actionListener);
							}
						} else {
							button.setIcon(null);
							if (button.getActionListeners().length == 0)
								button.addActionListener(button);
						}
					}

					if (redoBoards.isEmpty()) {
						redoItem.setEnabled(false);
					}

					undoItem.setEnabled(true);

					System.out.println(board);

					boolean isGameOver = board.isTerminal();
					if (isGameOver) {
						gameOver();
					}

				} catch (ArrayIndexOutOfBoundsException ex) {
					System.err.println("No move has been made yet!");
					System.err.flush();
				}
			}
		}
	}

	/* This method returns a pair of 2 integers from 0-2, given an id from 0-8.
	 * We need something like a function, namely f, that contains the following points:
	 * f(0, 0) = 0 = 0 * 3 + 0
	 * f(0, 1) = 0 = 0 * 3 + 1
	 * f(0, 2) = 0 = 0 * 3 + 2
	 * f(1, 0) = 0 = 1 * 3 + 0
	 * f(1, 1) = 0 = 1 * 3 + 1
	 * f(1, 2) = 0 = 1 * 3 + 2
	 * f(2, 0) = 0 = 2 * 3 + 0
	 * f(2, 1) = 0 = 2 * 3 + 1
	 * f(2, 2) = 0 = 2 * 3 + 2 */
	public List<Integer> getBoardCellById(int id) {
		List<Integer> cell = new ArrayList<>();
		int i = 0, j = 0;
		if (id / 3 == 0) {  // we know that i=0
			i = 0;
			j = id % 3;
		} else if (id / 3 == 1) {  // we know that i=1
			i = 1;
			j = id % 3;
		} else if (id / 3 == 2) {  // we know that i=2
			i = 2;
			j = id % 3;
		}
		cell.add(i);
		cell.add(j);

		return cell;
	}

	public void createHumanVsAiNewGame() {
		gameParameters = new GameParameters(newGameParameters);

		configureGuiStyle();

		if (menuBar == null) {
			addMenus();
		}

		int aiPlayerSymbol = (gameParameters.getPlayerSymbol() == Constants.X) ? Constants.O : Constants.X;

		AI ai;
		if (gameParameters.getAi1Type() == AiType.BEST_RESPONSE_AI) {
			ai = new BestResponseAI(aiPlayerSymbol);
		} else if (gameParameters.getAi1Type() == AiType.MINIMAX_AI) {
			ai = new MinimaxAI(gameParameters.getAi1MaxDepth(), aiPlayerSymbol);
		} else {
			ai = new RandomChoiceAI(aiPlayerSymbol);
		}

		if (panel != null) {
			super.remove(panel);
			super.revalidate();
			super.repaint();
		}

		panel = new JPanel();
		super.add(panel);

		layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		board = new Board();

		humanVsAiButtons = new HumanVsAiButton[9];

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int i = 0; i < 9; i++) {
			humanVsAiButtons[i] = new HumanVsAiButton(i, gameParameters.getPlayerSymbol(), ai, this);
			panel.add(humanVsAiButtons[i]);
		}

		super.setFocusable(true);

		super.setVisible(true);

		if (gameParameters.getPlayerSymbol() == Constants.O) {
			Move aiMove = ai.getNextMove(board);
			makeMove(aiMove.getRow(), aiMove.getColumn(), Constants.X);

			int aiMoveButtonId = GuiUtils.getIdByBoardCell(aiMove.getRow(), aiMove.getColumn());

			for (HumanVsAiButton button : humanVsAiButtons) {
				if (button.id == aiMoveButtonId) {
					button.setIcon(XIcon);
					button.removeActionListener(button);
				}
			}
		}
	}

	public void createHumanVsHumanNewGame() {
		gameParameters = new GameParameters(newGameParameters);

		configureGuiStyle();

		if (menuBar == null) {
			addMenus();
		}

		if (panel != null) {
			super.remove(panel);
			super.revalidate();
			super.repaint();
		}

		panel = new JPanel();
		super.add(panel);

		layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		board = new Board();

		humanVsHumanButtons = new HumanVsHumanButton[9];

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int id = 0; id < 9; id++) {
			humanVsHumanButtons[id] = new HumanVsHumanButton(id, this);
			panel.add(humanVsHumanButtons[id]);
		}

		super.setFocusable(true);

		super.setVisible(true);
	}

	public void createAiVsAiNewGame() {
		gameParameters = new GameParameters(newGameParameters);

		configureGuiStyle();

		if (menuBar == null) {
			addMenus();
		}

		AI ai1;
		if (gameParameters.getAi1Type() == AiType.BEST_RESPONSE_AI) {
			ai1 = new BestResponseAI(Constants.X);
		} else if (gameParameters.getAi1Type() == AiType.MINIMAX_AI) {
			ai1 = new MinimaxAI(gameParameters.getAi1MaxDepth(), Constants.X);
		} else {
			ai1 = new RandomChoiceAI(Constants.X);
		}

		AI ai2;
		if (gameParameters.getAi2Type() == AiType.BEST_RESPONSE_AI) {
			ai2 = new BestResponseAI(Constants.O);
		} else if (gameParameters.getAi2Type() == AiType.MINIMAX_AI) {
			ai2 = new MinimaxAI(gameParameters.getAi2MaxDepth(), Constants.O);
		} else {
			ai2 = new RandomChoiceAI(Constants.O);
		}

		if (panel != null) {
			super.remove(panel);
			super.revalidate();
			super.repaint();
		}

		panel = new JPanel();
		super.add(panel);

		layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		board = new Board();

		aiVsAiButtons = new AiVsAiButton[9];

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int i = 0; i < 9; i++) {
			aiVsAiButtons[i] = new AiVsAiButton(i, this);
			panel.add(aiVsAiButtons[i]);
		}

		super.setVisible(true);

		playAiVsAi(ai1, ai2);
	}

	public void createClientServerNewGame() {
		gameParameters = new GameParameters(newGameParameters);

		configureGuiStyle();

		if (menuBar == null) {
			addMenus();
		}

		Server server = new Server(gameParameters.getServerPort(), this);
		server.start();

		if (panel != null) {
			super.remove(panel);
			super.revalidate();
			super.repaint();
		}

		panel = new JPanel();
		super.add(panel);

		layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		board = new Board();

		clientServerButtons = new ClientServerButton[9];

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int i = 0; i < 9; i++) {
			clientServerButtons[i] = new ClientServerButton(
					i,
					gameParameters.getClientIP(),
					gameParameters.getClientPort(),
					gameParameters.getPlayerSymbol(),
					this
			);
			panel.add(clientServerButtons[i]);
		}

		super.setFocusable(true);

		super.setVisible(true);
	}

	private void aiVsAiMove(AI ai) {
		Move aiMove = ai.getNextMove(board);

		makeMove(aiMove.getRow(), aiMove.getColumn(), ai.getAiPlayer());

		int ai_button_id = GuiUtils.getIdByBoardCell(aiMove.getRow(), aiMove.getColumn());
		for (AiVsAiButton button : aiVsAiButtons) {
			if (button.id == ai_button_id) {
				button.aiPlayer = ai.getAiPlayer();
				button.doClick();
			}
		}
	}

	private void playAiVsAi(AI ai1, AI ai2) {
		while (!board.isTerminal()) {

			// AI 1 Move
			aiVsAiMove(ai1);

			// Sleep ms
			try {
				Thread.sleep(Constants.AI_MOVE_MILLISECONDS);
				super.paint(super.getGraphics());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (!board.isTerminal()) {
				// AI 2 Move
				aiVsAiMove(ai2);
			}

			// Sleep ms
			try {
				Thread.sleep(Constants.AI_MOVE_MILLISECONDS);
				super.paint(super.getGraphics());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		gameOver();
	}

	public void gameOver() {
		String message;
		if (board.getWinner() == Constants.X) {
			System.out.println("Player 1 \"X\" wins!");
			message = "Player 1 \"X\" wins!\nPlay again?";
		} else if (board.getWinner() == Constants.O) {
			System.out.println("Player 2 \"O\" wins!");
			message = "Player 2 \"O\" wins!\nPlay again?";
		} else if (Board.isGameBoardFull(board.getGameBoard()) && board.getWinner() == Constants.EMPTY) {
			System.out.println("It is a draw!");
			message = "It is a draw!\nPlay again?";
		} else {
			return;
		}
		int input = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);
		if (input == JOptionPane.OK_OPTION) {
			undoBoards.clear();
			redoBoards.clear();
			if (gameParameters.getGameMode() == GameMode.HUMAN_VS_AI) {
				createHumanVsAiNewGame();
			} else if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
				createHumanVsHumanNewGame();
			} else if (gameParameters.getGameMode() == GameMode.AI_VS_AI) {
				createAiVsAiNewGame();
			} else if (gameParameters.getGameMode() == GameMode.CLIENT_SERVER) {
				createClientServerNewGame();
			}
		} else if (input == JOptionPane.NO_OPTION || input == JOptionPane.CLOSED_OPTION) {
			if (gameParameters.getGameMode() == GameMode.HUMAN_VS_AI) {
				for (HumanVsAiButton button : humanVsAiButtons) {
					button.removeActionListener(button);
				}
			} else if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
				for (HumanVsHumanButton button : humanVsHumanButtons) {
					button.removeActionListener(button);
				}
			} else if (gameParameters.getGameMode() == GameMode.CLIENT_SERVER) {
				for (ClientServerButton button : clientServerButtons) {
					button.removeActionListener(button);
				}
			}
		}
	}

	// Make a move; it places a symbol on the board
	public void makeMove(int row, int col, int player) {
		if ((player == Constants.X && gameParameters.getGameMode() == GameMode.HUMAN_VS_AI)
				|| (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN)) {
			undoBoards.push(new Board(board));
		}

		board.getGameBoard()[row][col] = player;
		board.setLastMove(new Move(row, col));
		board.setLastPlayer(player);

		redoBoards.clear();
		redoItem.setEnabled(false);
	}

	public static void main(String[] args) {
		GUI gui = new GUI();

		// Here, you can change the game parameters, before running the application.
		// You can also change them later, from the Settings window.
		/*
		gui.newGameParameters = new GameParameters();
		gui.newGameParameters.setGuiStyle(GuiStyle.SYSTEM_STYLE);
		gui.newGameParameters.setGameMode(GameMode.HUMAN_VS_AI);
		gui.newGameParameters.setGameMode(GameMode.AI_VS_AI);
		gui.newGameParameters.setAi1Type(AiType.BEST_RESPONSE_AI);
		gui.newGameParameters.setAi1MaxDepth(4);
		gui.newGameParameters.setAi2MaxDepth(4);
		gui.newGameParameters.setPlayer1Color(Color.BLUE);
		gui.newGameParameters.setPlayer2Color(Color.RED);
		gui.newGameParameters.setPlayerSymbol(Constants.X);
		gui.newGameParameters.setServerPort(4000);
		gui.newGameParameters.setClientIP("127.0.0.1");
		gui.newGameParameters.setClientPort(4001);
		*/

		if (gui.newGameParameters.getGameMode() == GameMode.HUMAN_VS_AI) {
			gui.createHumanVsAiNewGame();
		} else if (gui.newGameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
			gui.createHumanVsHumanNewGame();
		} else if (gui.newGameParameters.getGameMode() == GameMode.AI_VS_AI) {
			gui.createAiVsAiNewGame();
		} else if (gui.newGameParameters.getGameMode() == GameMode.CLIENT_SERVER) {
			gui.createClientServerNewGame();
		}
	}

}
