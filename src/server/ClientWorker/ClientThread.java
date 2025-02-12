/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.ClientWorker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.Model;
import server.model.Student;
import server.requestResponse.KlijentskiZahtev;
import server.requestResponse.ServerskiOdgovor;
import server.service.StudentService;

/**
 *
 * @author Korisnik
 */
public class ClientThread extends Thread {
    
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private StudentService studentService;
    
    public ClientThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        ois = new ObjectInputStream(clientSocket.getInputStream());
        oos = new ObjectOutputStream(clientSocket.getOutputStream());
        studentService = new StudentService();
    }

    @Override
    public void run() {
        boolean flag = true;
        try {
            while(flag) { // beskonacna komunikacija sa klijentom dok klijent ne raskine komunikaciju
                
                // read request
                KlijentskiZahtev kz = (KlijentskiZahtev) ois.readObject();

                ServerskiOdgovor so = new ServerskiOdgovor();
                // do something
                switch (kz.getOperacija()) {
                    case LOGIN:
                        // 
                        break;
                    case INSERT_STUDENT:
                        // 
                        break;
                    case GET_ALL_STUDENTS:
                        List<Student> students = studentService.getAllStudents();
                        so.setMessage("vracam listu studenata");
                        so.setSuccess(true);
                        so.setResultStudents(students);
                        break;
                    case EXIT:
                        so.setMessage("Goodbye");
                        flag = false;
                        System.out.println("diskonektovao se klijent "+ clientSocket);
                        break;
                    default:
                        throw new AssertionError();
                }
                // send response
                oos.writeObject(so);
                oos.flush();
            }
            try {
                wait(1000);
            } catch (InterruptedException ex) {
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
