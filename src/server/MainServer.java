package server;


import server.ClientWorker.ClientThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Korisnik
 */
public class MainServer {
    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        try {
            mainServer.runServer(5555);
        } catch (Exception e) {
        }
    }
    
    public void runServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server is up and running and waiting for client request");
        
        while(true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("klijent se konektovao: " + clientSocket);
            // list of clients 
            Thread clientThread = new ClientThread(clientSocket);
            clientThread.start();
        }
    }
}
