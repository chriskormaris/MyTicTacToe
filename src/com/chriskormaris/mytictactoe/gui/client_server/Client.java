package com.chriskormaris.mytictactoe.gui.client_server;

import com.chriskormaris.mytictactoe.api.board.Move;
import com.chriskormaris.mytictactoe.gui.TicTacToeGUI;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread {

    String serverIP;
    int serverPort;
    int playerSymbol;

    public Client() {

    }

    public Client(String IP, int port, int playerSymbol) {
        this.serverIP = IP;
        this.serverPort = port;
        this.playerSymbol = playerSymbol;
    }

    @Override
    public void run() {

        Socket requestSocket = null;
        ObjectOutputStream out = null;

        try {
            requestSocket = new Socket(InetAddress.getByName(serverIP), serverPort);
            System.out.println("Client has started! Established connection with server at port: " + serverPort + "...");
            out = new ObjectOutputStream(requestSocket.getOutputStream());

            Move lastMove = TicTacToeGUI.board.getLastMove();
            out.writeObject(lastMove);
            out.flush();

            out.writeInt(playerSymbol);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (requestSocket != null) {
                    requestSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
