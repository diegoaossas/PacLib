package Libreria;

import java.awt.Color;
import java.io.Serializable;

public class Fantasma implements Serializable
{    
    private static final long serialVersionUID = 1L;
    
    public int fantasmaRow = 13;
    public int fantasmaCol = 11;
    public Color color = Color.RED;
    public boolean comible = false;
    public int pos = 0;
    public boolean ubicados = false;
    public Direccion direccion = Direccion.Derecha;
    
    public enum Direccion
    {
        Arriba,
        Abajo,
        Izquierda,
        Derecha
    }
}
