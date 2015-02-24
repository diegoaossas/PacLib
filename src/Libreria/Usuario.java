package Libreria;

import java.io.Serializable;

public class Usuario implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public int ID;
    public String Cuenta;
    public String Nombre;
    public String Clave;
    public Pacman paco;
    public int pJugadas = 0;
    public int pGanadas = 0;
    public int pPerdidas = 0;
}
