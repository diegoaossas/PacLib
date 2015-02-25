package Libreria;

import java.io.Serializable;
import javafx.scene.paint.Color;

public class Pacman implements Serializable
{
    public int pacmanRow = 1;
    public int pacmanCol = 1;
    public int color = 0;
    public int puntos = 0;
    public boolean powerUP = false;
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
        if (pos == 0 || pos == 2)
        {
            return 2;
        }
        if (pos == 1 || pos == 3)
        {
            return 26;
        }

        return 1;
    }

    public int getPosY(int pos)
    {
        if (pos == 0 || pos == 1)
        {
            return 1;
        }
        if (pos == 2 || pos == 3)
        {
            return 29;
        }

        return 1;
    }

    public void setPos()
    {
        if (!ubicados)
        {
            pacmanCol = getPosX(pos);
            pacmanRow = getPosY(pos);
            ubicados = true;
        }
    }
}
