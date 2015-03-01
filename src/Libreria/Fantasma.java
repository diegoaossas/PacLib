/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Diego
 */
public class Fantasma implements Serializable
{    
    private static final long serialVersionUID = 1L;
    
    public int pacmanRow = 1;
    public int pacmanCol = 1;
    public Color color = Color.RED;
    public boolean comible = false;
    public int pos = 0;
    public boolean ubicados = false;
    public Direccion direccion = Direccion.Derecha;
    
    public enum Direccion
    { Arriba, Abajo, Izquierda, Derecha }
}
