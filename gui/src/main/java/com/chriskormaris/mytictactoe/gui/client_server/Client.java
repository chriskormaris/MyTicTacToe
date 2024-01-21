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
		try (Socket requestSocket = new Socket(InetAddress.getByName(serverIP), serverPort);
		     ObjectOutputStream out = new ObjectOutputStream(requestSocket.getOutputStream())) {

			System.out.println("Client has started! Established connection with server at port: " + serverPort + "...");

			Move lastMove = board.getLastMove();
			out.writeInt(lastMove.getRow());
			out.flush();

			out.writeInt(lastMove.getColumn());
			out.flush();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
