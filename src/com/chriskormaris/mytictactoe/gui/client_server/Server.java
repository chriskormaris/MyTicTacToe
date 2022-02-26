package com.chriskormaris.mytictactoe.gui.client_server;

import com.chriskormaris.mytictactoe.api.board.Move;
import com.chriskormaris.mytictactoe.gui.TicTacToeGUI;
import com.chriskormaris.mytictactoe.gui.button.ClientServerButton;

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
            System.out.println("Server thread with id " + this.getId() +
                    ": Server listening at port: " + serverPort + "...");
            serverSocket = new ServerSocket(serverPort);

            while (true) {

                connection = serverSocket.accept();
                System.out.println("Server accepted connection!");
                in = new ObjectInputStream(connection.getInputStream());

                Move lastMove = (Move) in.readObject();
                int id = TicTacToeGUI.getIdByBoardCell(lastMove.getRow(), lastMove.getColumn());
                // System.out.println(id);

                int opposingPlayerSymbol = in.readInt();

                TicTacToeGUI.board.setLastPlayer(opposingPlayerSymbol);

                for (ClientServerButton button : TicTacToeGUI.clientServerButtons) {
                    if (button.id == id) {
                        // Programmatically press the com.chriskormaris.gui.button.
                        button.programmaticallyPressed = true;
                        button.doClick();
                        button.programmaticallyPressed = false;
                    }
                }
                // GUI.board.printBoard();

                if (TicTacToeGUI.board.isTerminal()) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        TicTacToeGUI.gameOver();

    }

}
