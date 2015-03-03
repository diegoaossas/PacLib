package Libreria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Cell implements Serializable, Cloneable
{
    private static final long serialVersionUID = 1L;

    public final static int CELL = 18;
    public char type;
    protected int x, y;

    public Cell(int x, int y, char type)
    {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void setSeparacion(int x, int y)
    {
        this.x += x;
        this.y += y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public char getType()
    {
        return type;
    }

    public void drawBackground(Graphics g)
    {
        int xBase;
        int yBase;

        switch (type)
        {
            
            case 'q':
                g.setColor(Color.WHITE);
                g.fillRect(x * CELL, y * CELL + CELL / 2 - 10, CELL, 3);
                break;
   
            case 'k':
            case 'e':
                g.setColor(Color.BLUE);
                g.fillRect(x * CELL, y * CELL + CELL / 2 - 1, CELL, 3);
                break;

            case 'l':
            case 'f':
                g.setColor(Color.BLUE);
                g.fillRect(x * CELL + CELL / 2 - 1, y * CELL, 3, CELL);
                break;

            case 'b':
            case 'h':
                xBase = x * CELL - CELL / 2;
                yBase = y * CELL + CELL / 2;
                drawCorner(g, xBase, yBase);
                break;

            case 'a':
            case 'g':
            case 'p':
                xBase = x * CELL + CELL / 2;
                yBase = y * CELL + CELL / 2;
                drawCorner(g, xBase, yBase);
                break;

            case 'd':
            case 'j':
            case 'o':
                xBase = x * CELL - CELL / 2;
                yBase = y * CELL - CELL / 2;
                drawCorner(g, xBase, yBase);
                break;

            case 'c':
            case 'i':
                xBase = x * CELL + CELL / 2;
                yBase = y * CELL - CELL / 2;
                drawCorner(g, xBase, yBase);
                break;

            case 'm':
                g.setColor(Color.WHITE);
                g.fillRect(x * CELL + CELL / 2 - 1, y * CELL + CELL / 2 - 1, 3, 3);
                break;

            case 'n':
                g.setColor(Color.PINK);
                g.fillOval(x * CELL + CELL / 2 - 7, y * CELL + CELL / 2 - 7, 13, 13);
                break;

            default:
                break;
        }
    }

    private void drawCorner(Graphics g, int xBase, int yBase)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle oldClip = g.getClipBounds();

        g2.setClip(x * CELL, y * CELL, CELL, CELL);
        g2.setColor(Color.BLUE);

        Shape oval = new Ellipse2D.Double(xBase, yBase, CELL, CELL);

        g2.setStroke(new BasicStroke(3));
        g2.draw(oval);
        g2.setClip(oldClip);
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Cell clone() throws CloneNotSupportedException
    {
        Cell cell = (Cell) super.clone();
        cell.type = this.type;
        cell.x = this.x;
        cell.y = this.y;
        
        return cell;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o)
    {        
        if (o == null)
            return false;
        
        if(getClass() != o.getClass())
            return false;
        
        Cell other = (Cell) o;
        
        if(other.type != this.type)
            return false;
        if(other.x != this.x)
            return false;
        if(other.y != this.y)
            return false;
        
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + this.type;
        hash = 53 * hash + this.x;
        hash = 53 * hash + this.y;
        return hash;
    }

}
