/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import server.model.Profesor;
import server.model.Student;
import server.requestResponse.KlijentskiZahtev;
import server.requestResponse.Operacija;
import server.requestResponse.ServerskiOdgovor;

/**
 *
 * @author Korisnik
 */
public class MainClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("CLIENT: automatski se pokrene jedna nit kada se pokrene main metoda"
                +Thread.currentThread());
        
        Socket socket = new Socket("localhost", 5555);
        
        System.out.println("Konektovao sam se na server");
        
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        // ovo cudo mi sluzi da unesem na tastaturi podatke
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        boolean flag = true;
        while(flag) {
            System.out.println("\nodaberi koju operaciju saljes serveru (iskopiraj ime operacije)");
            System.out.println("1. getAllStudents");
            System.out.println("2. getAllProfesors");
            System.out.println("3. addProfesor");
            System.out.println("0. Exit");
            System.out.print("vas izbor: ");
            
            String operation = br.readLine();
            Operacija operacija = Operacija.valueOf(operation);
            // da bi ja na neki nacin reko serveru sta hocu od njega
            // ja treba da mu kazem koju operaciju / akciju / metodu da on isoristi
            
            
            KlijentskiZahtev kz = new KlijentskiZahtev();
            kz.setOperacija(operacija);
            switch(operacija) {
                case GET_ALL_STUDENTS : {
                    oos.writeObject(kz);
                    oos.flush();
                    
                    ServerskiOdgovor so = (ServerskiOdgovor) ois.readObject();
                    
                    List<Student> students = so.getResultStudents();
                    System.out.println("primio sam studente sa servera:");
                    System.out.println(students);
                    break;
                }
//                case GET_All_PROFESORS : {
//                    oos.writeObject(kz);
//                    oos.flush();
//
//                    List<Profesor> profesors = (List<Profesor>) ois.readObject();
//                    System.out.println("primio sma profesore sa servera:");
//                    System.out.println(profesors);
//                    break;
//                }
//                case "addProfesor": {
//                    oos.writeUTF("addProfesor");
//                    oos.flush();
//
//                    Profesor profesor = new Profesor();
//                    profesor.setIme("aca");
//                    profesor.setPrezime("janko");
//                    profesor.setDatumRodjenja(new Date(1995 - 1900, 10, 29));
//
//                    oos.writeObject(profesor);
//                    oos.flush();
//
//                    boolean success = ois.readBoolean();
//
//                    if(success)
//                        System.out.println("profesor je uspesno dodat");
//                    else
//                        System.out.println("GRESKA pri dodavanju profesora");
//                }
                case EXIT : {
                    oos.writeObject(kz);
                    oos.flush();
                    
                    ServerskiOdgovor so = (ServerskiOdgovor) ois.readObject();
                    
                    String message = so.getMessage();
                    System.out.println(message);

                    socket.close();
                    
                    flag = false;
                    break;
                }
            }
        }
    }
}
