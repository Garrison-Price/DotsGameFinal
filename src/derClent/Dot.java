package derClent;


import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



/**
 * The dot class used for all dots in the game
 * @author Shaina
 */
public class Dot
{
    public int xCor;
    public int yCor;
    public Color color;
    public int xSpeed;
    public int ySpeed;
    public int xDir;
    public int yDir;
    public int radius;
    public DotsArray dotArray;
    public boolean dead = false;
    public Rectangle bounds;

    /**
     * Default constructor - not used often
     */
    public Dot()
    {
        xCor = 0;
        yCor = 0;
        color = Color.WHITE;
        radius = 10;
        bounds = new Rectangle(xCor-radius,yCor-radius,radius,radius);

    }

    /**
     * Main constructor used to set up the dot and get it going
     * @param x
     * @param y
     * @param xSpeed
     * @param ySpeed
     * @param rad
     * @param dA
     */
    public Dot(int x, int y, int xSpeed, int ySpeed, int rad, DotsArray dA)
    {
        xCor = x;
        this.xSpeed = xSpeed;
        yCor = y;
        this.ySpeed = ySpeed;
        xDir =xSpeed/Math.abs(xSpeed);
        yDir =ySpeed/Math.abs(ySpeed);
        color = randomColor();
        radius = rad;
        dotArray = dA;
        bounds = new Rectangle(xCor-radius,yCor-radius,radius,radius);
        

    }



    /*
     * This method makes the dot explode if it is touching an
     * explosion (known by calling hitsExplosion)
     * in the explosion array stored in the DotsArray.
     */
    public void Explode(DotsArray array)
    {

        ArrayList<Explosion> one = array.getExplosionList();

            for (int exp = 0; exp < one.size(); exp++)
            {
                if (this.hitsExplosion(one.get(exp)))
                {
                    
                    Explosion ex = new Explosion(xCor, yCor, color);
                    array.addExplosionList(ex);
                    die(dotArray);
                }
            }
    }

    /**
     * Called to stop the dot in its tracks - at the end of each level
     * @param array
     */
    public void die(DotsArray array)
    {
        array.removeDot(array.indexOf(this));
        dead = true;
        xCor = -100;
        yCor = -100;
        xSpeed = 0;
        ySpeed = 0;
    }

    /* Tests if the dot is touching an explosion,
     * returns true or false
     */
    public boolean hitsExplosion(Explosion other)
    {
        int distance = (int)(Math.pow((xCor-other.getX()),2)+Math.pow((yCor-other.getY()),2));
        //System.out.println(distance);
        if(distance<Math.pow(other.getSize()*.5,2))
        {
            return true;
        }

    return false;
    }

    /* returns the x coordinate of the dot
     */
    public int getX()
    {
        return xCor;
    }

    /* returns the y coordinate of the dot
     */
    public int getY()
    {
        return yCor;
    }

    /* returns the color of the dot
     */
    public Color getColor()
    {
        return color;
    }

    /* creates a random color for each new dot
     */
    public Color randomColor()
    {
        int x = (int)(Math.random()*256);
        int y = (int)(Math.random()*256);
        int z = (int)(Math.random()*256);
        Color random = new Color(x,y,z);
        return random;
    }

    

    


    
    /* moves the dot, making it change direction if it hits a wall
     */
    public void Move()
    {
        
//dot hits left boundry
        if(xCor <= 20)
        {
          xDir = 1;
        }
//dot hits right boundry
        else if(xCor >= 760)
        {
          xDir = -1;
        }
//dot hits top boundry
        else if(yCor < 20)
        {
            yDir = 1;
        }
//dot hits bottom boundry
        else if(yCor > 560)
        {
            yDir = -1;
        }

            xCor+=xSpeed*xDir;
            yCor+=ySpeed*yDir;
            //System.out.println(xCor+","+yCor);

            Explode(dotArray);
        
    }

}
