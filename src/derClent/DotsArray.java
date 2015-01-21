package derClent;

import java.util.ArrayList;

/**
 * This holds all of the dots and explosions along with other information
 * @author Shaina
 */
public class DotsArray
{

    public ArrayList<Dot> dotlist;
    public ArrayList<Explosion> explosionlist;
    public ArrayList<Integer> removed = new ArrayList();
    public int level;
    public int percent;
    public int score;

    /**
     * Default constructor - not used often
     */
    public DotsArray()
    {
        dotlist = new ArrayList();
        explosionlist = new ArrayList();
        level = 1;
        percent = 50;
    }

    /**
     * Main constructor to set up the DotList
     * @param lev
     * @param perc
     */
    public DotsArray(int lev, int perc)
    {
        dotlist = new ArrayList();
        explosionlist = new ArrayList();
        level = lev;
        percent = perc;
    }

    /* set the percentage of dots to hit needed to pass a level
     */
    public void setPerc(int perc)
    {
        percent = perc;
    }

    /* set the level number, which determines the number of dots within the level
     */
    public void setLevel(int lev)
    {
        level = lev;
    }

    /* sets the scors of the user
     */
    public void setScore(int x)
    {
        score = x;
    }

    /* creates the list of dots at the beginning of each level,
     * giving them a random x and y coordinate and a random speed.
     * A random color is assigned to the dot in its constructor
     */
    public void setList()
    {
        for(int d = 0; d < dotlist.size();d++)
            dotlist.get(d).die(this);
        dotlist.clear();
        for(int x = 0; x < explosionlist.size();x++)
            explosionlist.get(x).die(this);
        explosionlist.clear();
        for (int i = 0; i < (11-level) * 10; i++)
        {
            Dot dot = new Dot(getRandomX(), getRandomY(), (int)(Math.random()*4)+2, (int)(Math.random()*4)+2, 10,this);

            dotlist.add(i,dot);
        }
    }

    /* returns a random x coordinate
     */
    public int getRandomX()
    {
        int x = 0;
        x = (int) (Math.random() * 800);
        return x;
    }

    /* returns a random y coordinate
     */
    public int getRandomY()
    {
        int y = 0;
        y = (int) (Math.random() * 600);
        return y;
    }

    /* adds the explosion given to the list
     */
    public ArrayList<Explosion> addExplosionList(Explosion other)
    {
        explosionlist.add(other);
        other.Move();
        return explosionlist;
    }

    /* returns the dot list
     */
    public ArrayList<Dot> getDotList()
    {
        return dotlist;
    }

        /* returns the explosion list
     */
    public ArrayList<Explosion> getExplosionList()
    {
        return explosionlist;
    }

     /* removes a dot from the list
     */
    public void removeDot(int position)
    {
        setScore(score+1);
        dotlist.remove(position);
        removed.add(position);
    }

    /**
     * removes explosions from the arraylist
     * @param position
     */
    public void removeExplosion(int position)
    {
        explosionlist.remove(position);
    }

      /* returns the level
     */
    public int getLevel()
    {
        return level;
    }

        /* returns the percent
     */
    public int getPerc()
    {
        return percent;
    }

        /* returns the score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Returns the index of the given dot
     * @param d
     * @return
     */
    public int indexOf(Dot d)
    {
        for(int i = 0;i<dotlist.size();i++)
        {
            if(dotlist.get(i).equals(d))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Updates all of the dots and explosions
     */
    public void update()
    {
        for(int i = 0; i< explosionlist.size();i++)
        {
            if(!explosionlist.get(i).dead)
            {
                explosionlist.get(i).Move();
            }
        }
        for(int i = 0; i< dotlist.size();i++)
        {
            if(!removed.contains(i))
            {
                if(!dotlist.get(i).dead)
                {
                    dotlist.get(i).Move();
                }

            }
        }
        removed.clear();
    }

}
