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
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public int ID;
    public String Cuenta;
    public String Nombre;
    public int pJugadas;
    public int pGanadas;
    public int pPerdidas;
}
