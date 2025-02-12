/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.requestResponse;

import java.io.Serializable;
import server.model.Model;

/**
 *
 * @author Korisnik
 */
public class KlijentskiZahtev implements Serializable {
    private Operacija operacija;
    private Model parametar; // na ovaj nacin mogu da prosledim
    // i studenta i profeseroa preko klijentskog zahteva

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(Operacija operacija, Model parametar ) {
        this.operacija = operacija;
        this.parametar = parametar;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    public Model getParametar() {
        return parametar;
    }

    public void setParametar(Model parametar) {
        this.parametar = parametar;
    }
    
    
    
}
