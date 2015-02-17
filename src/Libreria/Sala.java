package Libreria;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public long idSala;
    public String nombreSala;
    public int maxjugadores;
    public ArrayList<Usuario> jugadores;
    public int jugadoresEnSala;
    
    public boolean agregarJugador(Usuario usuario)
    {
        if(jugadoresEnSala >= maxjugadores)
            return false;
        
        jugadores.add(usuario);
        jugadoresEnSala++;
        return true;
    }
    
    public boolean quitarJugador(Usuario usuario)
    {
        jugadores.remove(usuario);
        jugadoresEnSala--;
        return true;
    }
}
