package Libreria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Jugadores implements Iterable<Usuario>, Serializable, Cloneable
{
    private static final long serialVersionUID = 1L;
    public ArrayList<Usuario> lista;
    
    public Jugadores()
    {
        lista = new ArrayList<>();
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.lista);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;
        
        final Jugadores other = (Jugadores) obj;
        
        if (!this.lista.equals(other.lista))
            return false;
        
        return true;
    }

    public int size()
    {
        return lista.size();
    }

    public boolean add(Usuario e)
    {
        return lista.add(e);
    }

    public boolean remove(Usuario e)
    {
        for(Usuario usu : lista)
        {
            if(e.equals(usu))
                return lista.remove(usu);
        }
        
        return false;
    }

    public Usuario get(int i)
    {
        return lista.get(i);
    }

    @Override
    public Iterator<Usuario> iterator()
    {
        return lista.iterator();
    }    

    @Override
    public Jugadores clone() throws CloneNotSupportedException
    {
        Jugadores clone = (Jugadores)super.clone();
        clone.lista = new ArrayList<>(lista.size());
        
        for(Usuario usu : lista)
        {
            clone.lista.add(usu.clone());
        }
        
        return clone;
    }

}
