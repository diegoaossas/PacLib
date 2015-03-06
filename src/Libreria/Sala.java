package Libreria;

import static Libreria.Cell.CELL;
import java.awt.Color;
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

    public long idSala = 0;
    public String nombreSala = "";
    public String capitan = "";
    public boolean empezado = false;
    public int maxjugadores = 4;
    public Jugadores jugadores = new Jugadores();
    public Cell[][] cellsMapa = null;
    public int tileHeight = 0;
    public int tileWidth = 0;
    public int pelletsRestantes = 0;
    
    public Fantasma fant1 = new Fantasma();
    public Fantasma fant2 = new Fantasma();
    public Fantasma fant3 = new Fantasma();
    public Fantasma fant4 = new Fantasma();

    public Sala()
    {
        fant2.color = Color.BLUE;
        fant2.fantasmaCol = fant1.fantasmaCol + 1;
        fant3.color = Color.PINK;
        fant3.fantasmaCol = fant2.fantasmaCol + 1;
        fant4.color = Color.GREEN;
        fant4.fantasmaCol = fant3.fantasmaCol + 1;
        
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
        if (!Objects.equals(this.jugadores, other.jugadores))
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
        if (!Objects.equals(this.fant1, other.fant1))
        {
            return false;
        }
        if (!Objects.equals(this.fant2, other.fant2))
        {
            return false;
        }
        if (!Objects.equals(this.fant3, other.fant3))
        {
            return false;
        }
        if (!Objects.equals(this.fant4, other.fant4))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 83 * hash + (int) (this.idSala ^ (this.idSala >>> 32));
        hash = 83 * hash + Objects.hashCode(this.nombreSala);
        hash = 83 * hash + Objects.hashCode(this.capitan);
        hash = 83 * hash + (this.empezado ? 1 : 0);
        hash = 83 * hash + this.maxjugadores;
        hash = 83 * hash + Objects.hashCode(this.jugadores);
        hash = 83 * hash + Arrays.deepHashCode(this.cellsMapa);
        hash = 83 * hash + this.tileHeight;
        hash = 83 * hash + this.tileWidth;
        hash = 83 * hash + Objects.hashCode(this.fant1);
        hash = 83 * hash + Objects.hashCode(this.fant2);
        hash = 83 * hash + Objects.hashCode(this.fant3);
        hash = 83 * hash + Objects.hashCode(this.fant4);
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

    private void cargaMapa()
    {
        String map = "src/pacmanserver/mapa.txt";

        Scanner fileReader;
        ArrayList<String> lineList = new ArrayList<String>();

        try
        {
            fileReader = new Scanner(new File(map));

            while (true)
            {
                String line = null;

                try
                {
                    line = fileReader.nextLine();
                }
                catch (Exception eof){}

                if (line == null)
                    break;

                lineList.add(line);
            }

            tileHeight = lineList.size();
            tileWidth = lineList.get(0).length();
            int anchoTablero = tileWidth * CELL;

            cellsMapa = new Cell[tileHeight][tileWidth];

            for (int row = 0; row < tileHeight; row++)
            {
                String line = lineList.get(row);

                for (int column = 0; column < tileWidth; column++)
                {
                    char type = line.charAt(column);
                    
                    if(type == 'n' ||type == 'm')
                        pelletsRestantes++;
                    
                    cellsMapa[row][column] = new Cell(column, row, type);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Archivo no encontrado");
        }
    }

    @Override
    public Sala clone() throws CloneNotSupportedException
    {
        Sala sala = (Sala) super.clone();
        
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
        sala.fant1 = this.fant1.clone();
        sala.fant2 = this.fant2.clone();
        sala.fant3 = this.fant3.clone();
        sala.fant4 = this.fant4.clone();
        
        return sala;
    }
    
    public boolean compruebaColision(Pacman pacman)
    {
        if (pacman.chocan(fant1))
        {
            if(pacman.powerUP)
                fant1.volverACasa();
            return true;
        }
        
        if (pacman.chocan(fant2))
        {
            if(pacman.powerUP)
                fant2.volverACasa();
            return true;
        }
        
        if (pacman.chocan(fant3))
        {
            if(pacman.powerUP)
                fant3.volverACasa();
            return true;
        }
        
        if (pacman.chocan(fant4))
        {
            if(pacman.powerUP)
                fant4.volverACasa();
            return true;
        }

        return false;
    }
}
