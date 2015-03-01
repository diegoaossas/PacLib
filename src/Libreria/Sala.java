package Libreria;

import static Libreria.Cell.CELL;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Sala implements Serializable, Cloneable
{

    private static final long serialVersionUID = 1L;

    public long idSala;
    public String nombreSala;
    public String capitan;
    public boolean empezado = false;
    public int maxjugadores = 4;
    public Jugadores jugadores;
    public Cell[][] cellsMapa;
    public int tileHeight;
    public int tileWidth;
    
    public Fantasma fanti;

    public Sala()
    {
        cargaMapa();
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
        final Sala other = (Sala) obj;
        if (this.idSala != other.idSala)
        {
            return false;
        }
        if (!Objects.equals(this.nombreSala, other.nombreSala))
        {
            return false;
        }
        if (!Objects.equals(this.capitan, other.capitan))
        {
            return false;
        }
        if (this.empezado != other.empezado)
        {
            return false;
        }
        if (this.maxjugadores != other.maxjugadores)
        {
            return false;
        }
        if (!this.jugadores.equals(other.jugadores))
        {
            return false;
        }
        if (!Arrays.deepEquals(this.cellsMapa, other.cellsMapa))
        {
            return false;
        }
        if (this.tileHeight != other.tileHeight)
        {
            return false;
        }
        if (this.tileWidth != other.tileWidth)
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 31 * hash + (int) (this.idSala ^ (this.idSala >>> 32));
        hash = 31 * hash + Objects.hashCode(this.nombreSala);
        hash = 31 * hash + Objects.hashCode(this.capitan);
        hash = 31 * hash + (this.empezado ? 1 : 0);
        hash = 31 * hash + this.maxjugadores;
        hash = 31 * hash + this.jugadores.hashCode();
        hash = 31 * hash + Arrays.deepHashCode(this.cellsMapa);
        hash = 31 * hash + this.tileHeight;
        hash = 31 * hash + this.tileWidth;
        
        return hash;
    }
    
    public boolean agregarJugador(Usuario usuario)
    {
        if (jugadores.size() >= maxjugadores)
            return false;

        jugadores.add(usuario);
        return true;
    }

    public boolean quitarJugador(Usuario usuario)
    {
        return jugadores.remove(usuario);
    }

    /**
     * Reads from the map file and create the two dimensional array
     */
    private void cargaMapa()
    {
        String map = "src/pacmanserver/mapa.txt";

        // Scanner object to read from map file
        Scanner fileReader;
        ArrayList<String> lineList = new ArrayList<String>();

        // Attempt to load the maze map file
        try
        {
            fileReader = new Scanner(new File(map));

            while (true)
            {
                String line = null;

                try
                {
                    line = fileReader.nextLine();
                } catch (Exception eof)
                {

                    // throw new A5FatalException("Could not read resource");
                }

                if (line == null)
                {
                    break;
                }

                lineList.add(line);
            }

            tileHeight = lineList.size();
            tileWidth = lineList.get(0).length();
            int anchoTablero = tileWidth * CELL;

            // Create the cells
            cellsMapa = new Cell[tileHeight][tileWidth];

            for (int row = 0; row < tileHeight; row++)
            {
                String line = lineList.get(row);

                for (int column = 0; column < tileWidth; column++)
                {
                    char type = line.charAt(column);

                    cellsMapa[row][column] = new Cell(column, row, type);
                }
            }

        } catch (FileNotFoundException e)
        {
            System.out.println("Archivo no encontrado");
        }
    }

    @Override
    public Sala clone() throws CloneNotSupportedException
    {
        Sala sala = new Sala();
        sala.capitan = this.capitan;
        
        sala.cellsMapa = new Cell[tileHeight][tileWidth];
        for(int i = 0; i<tileHeight; i++)
        {
            for(int j = 0; j < tileWidth; j++)
            {
                sala.cellsMapa[i][j] = this.cellsMapa[i][j].clone();
            }
        }
        
        sala.empezado = this.empezado;
        sala.idSala = this.idSala;
        
        sala.jugadores = this.jugadores.clone();
        
        sala.maxjugadores = this.maxjugadores;
        sala.nombreSala = this.nombreSala;
        sala.tileHeight = this.tileHeight;
        sala.tileWidth = this.tileWidth;
        
        return sala;
    }
    
    
}
