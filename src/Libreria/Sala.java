/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria;

import java.io.Serializable;

/**
 *
 * @author Diego
 */
public class Sala implements Serializable{
    public long idSala;
    public String nombreSala;
    
    public String getNombre()
    {
        return this.nombreSala;
    }

    public long getID() {
        return this.idSala;
    }
}
