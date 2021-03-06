package Libreria;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public int ID;
    public String Cuenta;
    public String Nombre;
    public String Clave;
    public Pacman paco;
    public int puntosPaco;
    public int pJugadas;
    public int pGanadas;
    public int pPerdidas;
    public int recordPuntos;
    
    public Usuario()
    {
        ID = 0;
        Cuenta = "";
        Nombre = "";
        Clave = "";
        paco = new Pacman(3);
        puntosPaco = 0;
        pJugadas = 0;
        pGanadas = 0;
        pPerdidas = 0;
        recordPuntos = 0;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.ID != other.ID)
        {
            return false;
        }
        if (!Objects.equals(this.Cuenta, other.Cuenta))
        {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre))
        {
            return false;
        }
        if (!this.paco.equals(other.paco))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + this.ID;
        hash = 97 * hash + Objects.hashCode(this.Cuenta);
        hash = 97 * hash + Objects.hashCode(this.Nombre);
        hash = 97 * hash + Objects.hashCode(this.paco);
        return hash;
    }

    @Override
    protected Usuario clone() throws CloneNotSupportedException
    {
        Usuario usu = new Usuario();
        usu.Clave = this.Clave;
        usu.Cuenta = this.Cuenta;
        usu.ID = this.ID;
        usu.Nombre = this.Nombre;
        usu.pGanadas = this.pGanadas;
        usu.pJugadas = this.pJugadas;
        usu.pPerdidas = this.pPerdidas;
        usu.paco = this.paco.clone();
        usu.puntosPaco = this.puntosPaco;

        return usu;
    }
    
    
}
