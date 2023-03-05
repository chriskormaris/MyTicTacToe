package com.chriskormaris.mytictactoe.gui.client_server;

import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.button.ClientServerButton;
import com.chriskormaris.mytictactoe.gui.util.GuiUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	int serverPort;

	public Server() {

	}

	public Server(int port) {
		this.serverPort = port;
	}

	@Override
	public void run() {

		ServerSocket serverSocket = null;
		Socket connection = null;
		ObjectInputStream in = null;
		try {
			System.out.println(
					"Server thread with id " + this.getId() + ": Server listening at port: " + serverPort + "..."
			);
			serverSocket = new ServerSocket(serverPort);

			while (true) {
				connection = serverSocket.accept();
				System.out.println("Server accepted connection!");
				in = new ObjectInputStream(connection.getInputStream());

				int lastMoveRow = in.readInt();
				int lastMoveColumn = in.readInt();
				int opposingPlayerSymbol = in.readInt();

				int id = GuiUtils.getIdByBoardCell(lastMoveRow, lastMoveColumn);

				GUI.board.setLastPlayer(opposingPlayerSymbol);

				for (ClientServerButton button : GUI.clientServerButtons) {
					if (button.id == id) {
						// Programmatically press the com.chriskormaris.gui.button.
						button.programmaticallyPressed = true;
						button.doClick();
						button.programmaticallyPressed = false;
					}
				}
				// GUI.board.printBoard();

				if (GUI.board.isTerminal()) {
					break;
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		GUI.gameOver();

	}

}
