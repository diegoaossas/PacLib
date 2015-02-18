package Libreria;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public long idSala;
    public String nombreSala;
    public int maxjugadores = 4;
    public ArrayList<Usuario> jugadores;
    
    public boolean agregarJugador(Usuario usuario)
    {
        if(jugadores.size() >= maxjugadores)
            return false;
        
        jugadores.add(usuario);
        return true;
    }
    
    public boolean quitarJugador(Usuario usuario)
    {
        boolean eliminado = jugadores.remove(usuario);
        System.out.println("Sala:quitarJugador() -> " + usuario.Cuenta + "("+usuario.hashCode()+") -> " + eliminado);
        return eliminado;
    }
}
