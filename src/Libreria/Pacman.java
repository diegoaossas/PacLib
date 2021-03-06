package Libreria;

import java.awt.Color;
import java.io.Serializable;
import java.util.Objects;

public class Pacman implements Serializable, Cloneable
{
    private static final long serialVersionUID = 1L;
    
    public int pacmanRow = 1;
    public int pacmanCol = 1;
    public Color color = Color.YELLOW;
    public boolean powerUP = false;
    public int pos = 0;
    public int livesLeft;
    private boolean ubicados = false;
    public int puntos = 0;
    
    public Direccion direccion = Direccion.Derecha;
    public boolean moviendose = false;

    public enum Direccion
    {
        Arriba,
        Abajo,
        Izquierda,
        Derecha
    }
    
    public Pacman(int lives)
    {
        livesLeft = lives;
    }
    
    public boolean chocan(Pacman otro)
    {
        return ( (this.pacmanCol == otro.pacmanCol) && (this.pacmanRow == otro.pacmanRow) );
    }
    
    public boolean chocan(Fantasma fant)
    {
        return ( (this.pacmanCol == fant.fantasmaCol) && (this.pacmanRow == fant.fantasmaRow) );
    }
     
    @Override
    public Pacman clone() throws CloneNotSupportedException
    {
        Pacman paco = (Pacman) super.clone();
        paco.color = this.color;
        paco.livesLeft = this.livesLeft;
        paco.pacmanCol = this.pacmanCol;
        paco.pacmanRow = this.pacmanRow;
        paco.pos = this.pos;
        paco.powerUP = this.powerUP;
        paco.ubicados = this.ubicados;
        
        return paco;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + this.pacmanRow;
        hash = 67 * hash + this.pacmanCol;
        hash = 67 * hash + Objects.hashCode(this.color);
        hash = 67 * hash + (this.powerUP ? 1 : 0);
        hash = 67 * hash + this.pos;
        hash = 67 * hash + this.livesLeft;
        hash = 67 * hash + (this.ubicados ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;
        
        final Pacman other = (Pacman) obj;
        
        if (this.pacmanRow != other.pacmanRow)
            return false;
        
        if (this.pacmanCol != other.pacmanCol)
            return false;
        
        if (!Objects.equals(this.color, other.color))
            return false;
        
        if (this.powerUP != other.powerUP)
            return false;
        
        if (this.pos != other.pos)
            return false;
        
        if (this.livesLeft != other.livesLeft)
            return false;
        
        if (this.ubicados != other.ubicados)
            return false;
        
        return true;
    }

    public int getPosX(int pos)
    {
        if (pos == 0 || pos == 2)
            return 2;
        
        if (pos == 1 || pos == 3)
            return 26;

        return 1;
    }

    public int getPosY(int pos)
    {
        if (pos == 0 || pos == 1)
            return 1;
        
        if (pos == 2 || pos == 3)
            return 29;

        return 1;
    }

    public void setPos()
    {
        if (!ubicados)
        {    
            if(pos == 0 || pos == 3)
                direccion = Direccion.Derecha;
            else
                direccion = Direccion.Izquierda;
        
            pacmanCol = getPosX(pos);
            pacmanRow = getPosY(pos);
            ubicados = true;
        }
    }
    
    public boolean moveRow(int x, Cell[][] cells)
    {
        if (isCellNavigable(pacmanCol, pacmanRow + x, cells))
        {
            pacmanRow += x;
            return true;
        }
        
        return false;
    }

    public boolean moveCol(int y, Cell[][] cells)
    {
        if (isCellNavigable(pacmanCol + y, pacmanRow, cells))
        {
            pacmanCol += y;
            return true;
        }
        
        return false;
    }
    
    public boolean isCellNavigable(int column, int row, Cell[][] cells)
    {
        char type = 0;
        
        try
        {
            type = cells[row][column].getType();
        }
        catch(NullPointerException nex)
        {
            nex.printStackTrace();
            return false;
        }
        
        return (type == 'm' || type == 'n' || type == 'v' || type == 'y' || type == 'z');
    }
    
    public void comido()
    {
        moviendose = false;
        ubicados = false;
        setPos();
    }
}
