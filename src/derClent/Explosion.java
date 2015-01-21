package derClent;


import java.awt.Color;

/**
 * Explosion class acts much like a non-moving but growing dot
 * @author Shaina
 */
public class Explosion extends Dot
{

    public boolean shrink = false;

    /**
     * Default constructor
     */
    public Explosion()
    {
    xCor = yCor = 0;
    radius = 10;
    color = Color.WHITE;
    }

    /**
     * Main constructor to set up explosion
     * @param x
     * @param y
     * @param c
     */
    public Explosion(int x, int y, Color c)
    {
    xCor = x;
    yCor = y;
    radius = 10;
    color = c;
//    Move();
    }

    /**
     * Overides to grow and not move
     */
    public void Move()
    {
        if(shrink == false)
        {
            if(radius<90)
            {
                radius++;
            }
            else
            {
                shrink = true;
            }
        }

        if(shrink == true)
        {
            radius--;
            if(radius < 1)
                dead = true;
        }

    }



    /**
     * Rteurns size of explosion
     * @return
     */
    public int getSize()
    {
        return radius;
    }

    /**
     * returns the string of the explosion - x,y - size
     * @return
     */
    public String toString()
    {
        return xCor+","+yCor+" - "+radius;
    }
    
}
