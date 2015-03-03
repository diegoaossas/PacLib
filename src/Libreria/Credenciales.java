package Libreria;

import java.io.Serializable;

public class Credenciales implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public String usuario = "";
    public String clave = "";
    
    public Credenciales(String usuario, String clave)
    {
        this.usuario = usuario;
        this.clave = clave;
    }
}
