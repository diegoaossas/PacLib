package Libreria;

import static Libreria.Cell.CELL;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Sala implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public long idSala;
    public String nombreSala;
    public int maxjugadores = 4;
    public ArrayList<Usuario> jugadores;
    public Cell[][] cellsMapa;
	public int tileHeight ;
	public int tileWidth;
	
	public Sala()
	{
		cargaMapa();
	}
	
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
	
    
    /**
     * Reads from the map file and create the two dimensional array
     */
    private void cargaMapa()
    {
    	String map = "src/pacmanserver/mapa.txt";
    	
        // Scanner object to read from map file
        Scanner           fileReader;
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
                }
                catch (Exception eof)
                {

                    // throw new A5FatalException("Could not read resource");
                }

                if (line == null)
                    break;

                lineList.add(line);
            }
			
			tileHeight = lineList.size();
			tileWidth = lineList.get(0).length();
			int anchoTablero = tileWidth * CELL;

			// Create the cells
			cellsMapa = new Cell[tileHeight][tileWidth];

			for (int row = 0; row < tileHeight; row++) {
				String line = lineList.get(row);

				for (int column = 0; column < tileWidth; column++) {
					char type = line.charAt(column);

					cellsMapa[row][column] = new Cell(column, row, type);
				}
			}
            
        }
        catch(FileNotFoundException e)
        {
        	System.out.println("Archivo no encontrado");
        }
	}
}
