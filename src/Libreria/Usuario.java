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
    public String Clave;
    public int pJugadas = 0;
    public int pGanadas = 0;
    public int pPerdidas = 0;
}
