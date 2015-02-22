package Libreria;

import java.io.Serializable;


public class Pacman implements Serializable{
    public int pacmanRow = 1;
	public int pacmanCol = 1;
	public int pos = 0;
	public int livesLeft;
	private boolean ubicados = false;
	
	private int x;
	private int y;
	
	public Pacman(int lives)
	{
        livesLeft = lives;
    }
	
	public void setSeparacion(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getPosX(int pos)
	{
		if(pos == 0)
			return 2;
		if(pos == 1)
			return 25;
		
		return 1;
	}
	
	public int getPosY(int pos)
	{
		if(pos == 0)
			return 1;
		if(pos == 1)
			return 1;
		
		return 1;
	}
	
	public void setPos()
	{
		if(!ubicados)
		{
			pacmanCol = getPosX(pos);
			pacmanRow = getPosY(pos);
			ubicados = true;
		}
	}
}
