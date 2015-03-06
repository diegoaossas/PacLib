package Libreria;

import java.awt.Color;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fantasma implements Serializable, Cloneable
{    
    private static final long serialVersionUID = 1L;
    
    public int fantasmaRow = 13;
    public int fantasmaCol = 11;
    public Color color = Color.RED;
    public Direccion direccion = Direccion.Derecha;
    private boolean aCasa = false;

    public transient Thread movimiento;
    public boolean intMovimiento = false;
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + this.fantasmaRow;
        hash = 97 * hash + this.fantasmaCol;
        hash = 97 * hash + Objects.hashCode(this.color);
        hash = 97 * hash + Objects.hashCode(this.direccion);
        return hash;
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
        final Fantasma other = (Fantasma) obj;
        if (this.fantasmaRow != other.fantasmaRow)
        {
            return false;
        }
        if (this.fantasmaCol != other.fantasmaCol)
        {
            return false;
        }
        if (!Objects.equals(this.color, other.color))
        {
            return false;
        }
        if (this.direccion != other.direccion)
        {
            return false;
        }
        return true;
    }

    @Override
    public Fantasma clone() throws CloneNotSupportedException
    {
        Fantasma fanti = new Fantasma();
        fanti.color = this.color;
        fanti.direccion = this.direccion;
        fanti.fantasmaCol = this.fantasmaCol;
        fanti.fantasmaRow = this.fantasmaRow;
        
        return fanti;
    }
    
    public enum Direccion
    {
        Arriba,
        Abajo,
        Izquierda,
        Derecha
    }
      
    public void llevarACasa()
    {
        fantasmaCol = 11;
        fantasmaRow = 13;
    }    
    
    public void volverACasa()
    {
        aCasa = true;
    }
    
    public boolean isCellNavigable(int column, int row, Cell[][] cells)
    {
        if(aCasa)
        {
            llevarACasa();
            aCasa = false;
            return true;
        }
        
        char type = cells[row][column].getType();
        
        return (type == 'm' || type == 'n' || type == 'v' || type == 'y' || type == 'z');
    }
    
    public boolean caminoArriba(Cell[][] cells)
    {
        return(isCellNavigable(fantasmaCol, fantasmaRow-1, cells));
    }   
    
    public boolean caminoAbajo(Cell[][] cells)
    {
        return(isCellNavigable(fantasmaCol, fantasmaRow+1, cells));
    }   
    
    public boolean caminoIzquierda(Cell[][] cells)
    {
        return(isCellNavigable(fantasmaCol-1, fantasmaRow , cells));
    }   
    
    public boolean caminoDerecha(Cell[][] cells)
    {
        return(isCellNavigable(fantasmaCol+1, fantasmaRow, cells));
    }
    
    public int cantidadCaminos(Cell[][] cells)
    {
        int Cant = 0;
        
        if(caminoArriba(cells))
            Cant++;
        if(caminoAbajo(cells))
            Cant++;
        if(caminoIzquierda(cells))
            Cant++;
        if(caminoDerecha(cells))
            Cant++;
        
        return Cant;
    }
    
    public void decideCamino(Cell[][] cells)
    {
        Random rand = new Random();
        boolean decidio = false;
        
        if(aCasa)
        {
            llevarACasa();
            aCasa = false;
            return;
        }
        
        if(direccion == Direccion.Arriba)
        {
            if(caminoArriba(cells))
            {
                fantasmaRow--;
                return;
            }
        }
        else if(direccion == Direccion.Abajo)
        {
            if(caminoAbajo(cells))
            {
                fantasmaRow++;
                return;
            }
        }
        else if(direccion == Direccion.Izquierda)
        {
            if(caminoIzquierda(cells))
            {
                fantasmaCol--;
                return;
            }
        }
        else if(direccion == Direccion.Derecha)
        {
            if(caminoDerecha(cells))
            {
                fantasmaCol++;
                return;
            }
        }
        
        while(true)
        {
            int rand_ = rand.nextInt(4);
            
            switch(rand_)
            {
                case 0:
                    if(caminoDerecha(cells))
                    {
                        direccion = Direccion.Derecha;
                        fantasmaCol++;
                        decidio = true;
                    }
                    break;
                case 1:
                    if(caminoIzquierda(cells))
                    {
                        direccion = Direccion.Izquierda;
                        fantasmaCol--;
                        decidio = true;
                    }
                    break;
                case 2:
                    if(caminoArriba(cells))
                    {
                        direccion = Direccion.Arriba;
                        fantasmaRow--;
                        decidio = true;
                    }
                    break;
                case 3:
                    if(caminoAbajo(cells))
                    {
                        direccion = Direccion.Abajo;
                        fantasmaRow++;
                        decidio = true;
                    }
                    break;
            }
            
            if(decidio)
                break;
        }
    }
    
    public void caminoUnico(Cell[][] cells)
    {
        if(aCasa)
        {
            llevarACasa();
            aCasa = false;
            return;
        }
        
        if (caminoDerecha(cells))
        {
            fantasmaCol--;
            return;
        }

        if (caminoIzquierda(cells))
        {
            fantasmaCol++;
            return;
        }

        if (caminoArriba(cells))
        {
            fantasmaRow--;
            return;
        }

        if (caminoAbajo(cells))
        {
            fantasmaRow++;
            return;
        }

    }
    
    public void opciones(Cell[][] cellsMapa)
    {
        movimiento = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    if(intMovimiento)
                    {
                        Thread.currentThread().interrupt();
                        intMovimiento = false;
                        break;
                    }
                    
                    try
                    {
                        Thread.sleep(300);
                    }
                    catch (InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                        intMovimiento = false;
                        break;
                    }
                    
                    switch (direccion)
                    {
                        case Arriba:
                            if (cantidadCaminos(cellsMapa) > 1)
                            {
                                decideCamino(cellsMapa);
                            }
                            else
                            {
                                caminoUnico(cellsMapa);
                            }
                            break;

                        case Abajo:
                            if (cantidadCaminos(cellsMapa) > 1)
                            {
                                decideCamino(cellsMapa);
                            }
                            else
                            {
                                caminoUnico(cellsMapa);
                            }
                            break;

                        case Izquierda:
                            if (cantidadCaminos(cellsMapa) > 1)
                            {
                                decideCamino(cellsMapa);
                            }
                            else
                            {
                                caminoUnico(cellsMapa);
                            }

                            if ((cellsMapa[fantasmaRow][fantasmaCol].getType() == 'z'))
                            {
                                fantasmaCol = 27;
                            }
                            break;

                        case Derecha:
                            if (cantidadCaminos(cellsMapa) > 1)
                            {
                                decideCamino(cellsMapa);
                            } else
                            {
                                caminoUnico(cellsMapa);
                            }

                            if ((cellsMapa[fantasmaRow][fantasmaCol].getType() == 'y'))
                            {
                                fantasmaCol = 0;
                            }
                            break;
                    }
                }
            }
        });


        movimiento.start();
        
    }
}
