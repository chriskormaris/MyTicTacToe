package com.chriskormaris.mytictactoe.gui.client_server;

import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.button.ClientServerButton;
import com.chriskormaris.mytictactoe.gui.button.XOButton;
import com.chriskormaris.mytictactoe.gui.util.GuiUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	int serverPort;
	GUI gui;
	int playerSymbol;

	public Server(int port, GUI gui, int playerSymbol) {
		this.serverPort = port;
		this.gui = gui;
		this.playerSymbol = playerSymbol;
	}

	@Override
	public void run() {
		int opposingPlayerSymbol = this.playerSymbol == Constants.X ? Constants.O : Constants.X;

		try (ServerSocket serverSocket = new ServerSocket(serverPort);
		     Socket connection = serverSocket.accept();
		     ObjectInputStream in = new ObjectInputStream(connection.getInputStream())) {

			System.out.println(
					"Server thread with id " + this.getId() + ": Server listening at port: " + serverPort + "..."
			);

			do {
				System.out.println("Server accepted connection!");

				int lastMoveRow = in.readInt();
				int lastMoveColumn = in.readInt();

				int id = GuiUtils.getIdByBoardCell(lastMoveRow, lastMoveColumn);

				gui.board.setLastPlayer(opposingPlayerSymbol);

				for (XOButton button : gui.buttons) {
					ClientServerButton clientServerButton = (ClientServerButton) button;
					if (button.getId() == id) {
						clientServerButton.programmaticallyPressed = true;
						clientServerButton.doClick();
						clientServerButton.programmaticallyPressed = false;
					}
				}
				// gui.board.printBoard();

			} while (!gui.board.isTerminal());

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		if (gui.board.getLastPlayer() != playerSymbol) {
			gui.gameOver();
		}
	}

}
