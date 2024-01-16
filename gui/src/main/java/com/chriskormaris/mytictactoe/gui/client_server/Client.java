package com.chriskormaris.mytictactoe.gui.client_server;

import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.board.Move;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread {

	String serverIP;
	int serverPort;
	Board board;

	public Client(String IP, int port, Board board) {
		this.serverIP = IP;
		this.serverPort = port;
		this.board = board;
	}

	@Override
	public void run() {
		Socket requestSocket = null;
		ObjectOutputStream out = null;

		try {
			requestSocket = new Socket(InetAddress.getByName(serverIP), serverPort);
			System.out.println("Client has started! Established connection with server at port: " + serverPort + "...");
			out = new ObjectOutputStream(requestSocket.getOutputStream());

			Move lastMove = board.getLastMove();
			out.writeInt(lastMove.getRow());
			out.flush();

			out.writeInt(lastMove.getColumn());
			out.flush();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (requestSocket != null) {
					requestSocket.close();
				}
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

}
