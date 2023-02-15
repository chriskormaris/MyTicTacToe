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
import com.chriskormaris.mytictactoe.gui.util.GameParameters;
import com.chriskormaris.mytictactoe.gui.util.GuiConstants;
import com.chriskormaris.mytictactoe.gui.util.GuiUtils;
import com.chriskormaris.mytictactoe.gui.util.ResourceLoader;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.chriskormaris.mytictactoe.gui.util.GuiConstants.TITLE;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GUI {

	public static GameParameters gameParameters;
	public static GameParameters newGameParameters;

	public static ImageIcon XIcon;
	public static ImageIcon OIcon;

	private static JFrame frame;

	private static JPanel panel;
	private static GridLayout layout;

	public static HumanVsHumanButton[] humanVsHumanButtons;
	public static HumanVsAiButton[] humanVsAiButtons;
	public static AiVsAiButton[] aiVsAiButtons;
	public static ClientServerButton[] clientServerButtons;

	public static Board board;

	// Menu bars and items
	private static JMenuBar menuBar;
	public static JMenuItem undoItem;
	public static JMenuItem redoItem;

	// These Stack objects are used for the "Undo" and "Redo" functionalities.
	private static Stack<Board> undoBoards;
	private static Stack<Board> redoBoards;

	public static void create(String title) {
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

		frame = new JFrame();

		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
		int y = (int) (screenSize.getHeight() - frame.getHeight()) / 2;
		frame.setLocation(x, y);

		frame.addKeyListener(gameKeyListener);
		frame.addKeyListener(undoRedoKeyListener);
	}

	private static final KeyListener gameKeyListener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			// System.out.println("keyTyped = " + KeyEvent.getKeyText(e.getKeyCode()));
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// System.out.println("keyPressed = " + KeyEvent.getKeyText(e.getKeyCode()));
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
			// System.out.println("keyReleased = " + KeyEvent.getKeyText(e.getKeyCode()));
		}
	};

	private static final KeyListener undoRedoKeyListener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			// System.out.println("keyTyped = " + KeyEvent.getKeyText(e.getKeyCode()));
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
			// System.out.println("keyReleased = " + KeyEvent.getKeyText(e.getKeyCode()));
		}
	};

	private static void configureGuiStyle() {
		try {
			if (gameParameters.getGuiStyle() == GuiStyle.SYSTEM_STYLE) {
				// Option 1
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} else if (gameParameters.getGuiStyle() == GuiStyle.CROSS_PLATFORM_STYLE) {
				// Option 2
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
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


	private static void addMenus() {
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
			gameParameters = new GameParameters(newGameParameters);

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

			Board.printBoard(board.getGameBoard());
		});

		undoItem.addActionListener(e -> undo());

		redoItem.addActionListener(e -> redo());

		settingsItem.addActionListener(e -> {
			SettingsWindow settings = new SettingsWindow(frame);
			settings.setVisible(true);
		});

		exitItem.addActionListener(e -> System.exit(0));

		howToPlayItem.addActionListener(
				e -> JOptionPane.showMessageDialog(
						frame,
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
			JOptionPane.showMessageDialog(frame, label, "About", JOptionPane.INFORMATION_MESSAGE);
		});

		fileMenu.add(newGameItem);
		fileMenu.add(undoItem);
		fileMenu.add(redoItem);
		fileMenu.add(settingsItem);
		fileMenu.add(exitItem);

		frame.setJMenuBar(menuBar);
	}


	public static void undo() {
		if (!undoBoards.isEmpty()) {
			System.out.println("Undo is pressed!");

			// System.out.println("undo");
			// This is the "undo" implementation for Human VS Human mode.
			if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
				try {
					redoBoards.push(new Board(board));
					board = undoBoards.pop();

					for (HumanVsHumanButton button : humanVsHumanButtons) {
						List<Integer> cell = GUI.getBoardCellById(button.id);
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

					Board.printBoard(board.getGameBoard());

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
						List<Integer> cell = GUI.getBoardCellById(button.id);
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

					Board.printBoard(board.getGameBoard());

				} catch (ArrayIndexOutOfBoundsException ex) {
					System.err.println("No move has been made yet!");
					System.err.flush();
				}
			}
		}
	}


	public static void redo() {
		if (!redoBoards.isEmpty()) {
			System.out.println("Redo is pressed!");

			// System.out.println("undo");
			// This is the "redo" implementation for Human VS Human mode.
			if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
				try {

					undoBoards.push(new Board(board));
					board = new Board(redoBoards.pop());

					for (HumanVsHumanButton button : humanVsHumanButtons) {
						List<Integer> cell = GUI.getBoardCellById(button.id);
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

					Board.printBoard(board.getGameBoard());

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
						List<Integer> cell = GUI.getBoardCellById(button.id);
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

					Board.printBoard(board.getGameBoard());

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
	 * f(0, 0) = 0 = 0*3 + 0
	 * f(0, 1) = 0 = 0*3 + 1
	 * f(0, 2) = 0 = 0*3 + 2
	 * f(1, 0) = 0 = 1*3 + 0
	 * f(1, 1) = 0 = 1*3 + 1
	 * f(1, 2) = 0 = 1*3 + 2
	 * f(2, 0) = 0 = 2*3 + 0
	 * f(2, 1) = 0 = 2*3 + 1
	 * f(2, 2) = 0 = 2*3 + 2
	 */
	public static List<Integer> getBoardCellById(int id) {
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

	public static int getIdByBoardCell(int row, int col) {
		return row * 3 + col;
	}

	public static void createHumanVsAiNewGame() {
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
			frame.remove(panel);
			frame.revalidate();
			frame.repaint();
		}

		panel = new JPanel();
		frame.add(panel);

		layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		board = new Board();

		humanVsAiButtons = new HumanVsAiButton[9];

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int i = 0; i < 9; i++) {
			humanVsAiButtons[i] = new HumanVsAiButton(i, gameParameters.getPlayerSymbol(), ai);
			panel.add(humanVsAiButtons[i]);
		}

		frame.setFocusable(true);

		frame.setVisible(true);

		if (gameParameters.getPlayerSymbol() == Constants.O) {
			Move aiMove = ai.getNextMove(board);
			makeMove(aiMove.getRow(), aiMove.getColumn(), Constants.X);

			int aiMoveButtonId = GUI.getIdByBoardCell(aiMove.getRow(), aiMove.getColumn());
			// System.out.print("AI Move [" + aiMove.getRow() + "]" + "[" + aiMove.getColumn() +"]: ");
			// System.out.println(aiMove.getValue());

			for (HumanVsAiButton button : GUI.humanVsAiButtons) {
				if (button.id == aiMoveButtonId) {
					button.setIcon(XIcon);
					button.removeActionListener(button);
				}
			}

		}
	}


	public static void createHumanVsHumanNewGame() {
		configureGuiStyle();

		if (menuBar == null) {
			addMenus();
		}

		if (panel != null) {
			frame.remove(panel);
			frame.revalidate();
			frame.repaint();
		}

		panel = new JPanel();
		frame.add(panel);

		layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		board = new Board();

		humanVsHumanButtons = new HumanVsHumanButton[9];

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int id = 0; id < 9; id++) {
			humanVsHumanButtons[id] = new HumanVsHumanButton(id);
			panel.add(humanVsHumanButtons[id]);
		}

		frame.setFocusable(true);

		frame.setVisible(true);
	}


	public static void createAiVsAiNewGame() {
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
			frame.remove(panel);
			frame.revalidate();
			frame.repaint();
		}

		panel = new JPanel();
		frame.add(panel);

		layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		board = new Board();

		aiVsAiButtons = new AiVsAiButton[9];

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int i = 0; i < 9; i++) {
			aiVsAiButtons[i] = new AiVsAiButton(i);
			panel.add(aiVsAiButtons[i]);
		}

		frame.setVisible(true);

		playAiVsAi(ai1, ai2);
	}


	public static void createClientServerNewGame() {
		configureGuiStyle();

		if (menuBar == null) {
			addMenus();
		}

		Server server = new Server(gameParameters.getServerPort());
		server.start();

		if (panel != null) {
			frame.remove(panel);
			frame.revalidate();
			frame.repaint();
		}

		panel = new JPanel();
		frame.add(panel);

		layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		board = new Board();

		clientServerButtons = new ClientServerButton[9];

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int i = 0; i < 9; i++) {
			clientServerButtons[i] = new ClientServerButton(i, gameParameters.getClientIP(),
					gameParameters.getClientPort(), gameParameters.getPlayerSymbol());
			panel.add(clientServerButtons[i]);
		}

		frame.setFocusable(true);

		frame.setVisible(true);
	}

	private static void aiVsAiMove(AI ai) {
		Move aiMove = ai.getNextMove(board);

		makeMove(aiMove.getRow(), aiMove.getColumn(), ai.getAiPlayer());

		int ai_button_id = getIdByBoardCell(aiMove.getRow(), aiMove.getColumn());
		for (AiVsAiButton button : aiVsAiButtons) {
			if (button.id == ai_button_id) {
				button.aiPlayer = ai.getAiPlayer();
				button.doClick();
			}
		}
	}


	private static void playAiVsAi(AI ai1, AI ai2) {
		while (!GUI.board.isTerminal()) {

			// AI 1 Move
			aiVsAiMove(ai1);

			// Sleep ms
			try {
				Thread.sleep(Constants.AI_MOVE_MILLISECONDS);
				frame.paint(frame.getGraphics());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (!GUI.board.isTerminal()) {
				// AI 2 Move
				aiVsAiMove(ai2);
			}

			// Sleep ms
			try {
				Thread.sleep(Constants.AI_MOVE_MILLISECONDS);
				frame.paint(frame.getGraphics());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		gameOver();
	}


	public static void gameOver() {
		// System.out.println("Game Over function called!");

		String message = "";
		if (board.getWinner() == Constants.X) {
			System.out.println("Player 1 \"X\" wins!");
			message = "Player 1 \"X\" wins!\nPlay again?";
		} else if (board.getWinner() == Constants.O) {
			System.out.println("Player 2 \"O\" wins!");
			message = "Player 2 \"O\" wins!\nPlay again?";
		} else if (Board.isGameBoardFull(board.getGameBoard()) && board.getWinner() == Constants.EMPTY) {
			System.out.println("It is a draw!");
			message = "It is a draw!\nPlay again?";
		}
		int input = JOptionPane.showConfirmDialog(frame, message, "Game Over", JOptionPane.YES_NO_OPTION);
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
	public static void makeMove(int row, int col, int player) {
		if ((player == Constants.X && gameParameters.getGameMode() == GameMode.HUMAN_VS_AI)
				|| (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN)) {
			undoBoards.push(new Board(board));
		}

		board.getGameBoard()[row][col] = player;
		// System.out.println("previous move: " + board.getLastMove());
		board.setLastMove(new Move(row, col));
		// System.out.println("this move: " + board.getLastMove());
		board.setLastPlayer(player);

		redoBoards.clear();
		redoItem.setEnabled(false);
	}

	public static void main(String[] args) {
		// Here, you can change the game parameters, before running the application.
		// You can also change them later, from the Settings window.
		/*
		gameParameters = new GameParameters();
		gameParameters.setGuiStyle(GuiStyle.SYSTEM_STYLE);
		gameParameters.setGameMode(GameMode.HUMAN_VS_AI);
		gameParameters.setGameMode(GameMode.AI_VS_AI);
		gameParameters.setAi1Type(AiType.BEST_RESPONSE_AI);
		gameParameters.setAi1MaxDepth(4);
		gameParameters.setAi2MaxDepth(4);
		gameParameters.setPlayer1Color(Color.BLUE);
		gameParameters.setPlayer2Color(Color.RED);
		gameParameters.setPlayerSymbol(Constants.X);
		gameParameters.setServerPort(4000);
		gameParameters.setClientIP("127.0.0.1");
		gameParameters.setClientPort(4001);
		*/

		create(TITLE);

		if (gameParameters.getGameMode() == GameMode.HUMAN_VS_AI) {
			createHumanVsAiNewGame();
		} else if (gameParameters.getGameMode() == GameMode.HUMAN_VS_HUMAN) {
			createHumanVsHumanNewGame();
		} else if (gameParameters.getGameMode() == GameMode.AI_VS_AI) {
			createAiVsAiNewGame();
		} else if (gameParameters.getGameMode() == GameMode.CLIENT_SERVER) {
			createClientServerNewGame();
		}
	}

}
