package derClent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;


/**
 *
 * @author Shaina
 */
public class Gameboard extends JPanel implements Runnable
{
    public int height;
    public int width;
    public Color color;
    public DotsArray dotArray;
    public ArrayList<Dot> dots;
    public ArrayList<Explosion> exp;
    private int now = 0;
    public boolean isClicked;
    public int score;
    public Client container;
    private double lastTime;
    private int fpsCount = 0;
    private double fps = 0;
    private double fpsAdd;

    /**
     * Default constructor - not used often
     */
    public Gameboard()
    {
        height=width=0;
        color = Color.gray;
        dotArray = new DotsArray();
        dots = dotArray.getDotList();
        exp = dotArray.getExplosionList();
        now = 1;
        new Thread(this).start();
        GarbageCollector tim = new GarbageCollector();
        this.addMouseListener(new SuperMouseListener());
        score = 0;
        lastTime = 0;
    }


    /**
     * simple constructor - not used often
     * @param w
     * @param h
     */
    public Gameboard(int w, int h)
    {
        height = h;
        width = w;
        color = Color.gray;
        setBackground(Color.GRAY);
        dotArray = new DotsArray();
        dots = dotArray.getDotList();
        exp = dotArray.getExplosionList();
        now = 1;
        new Thread(this).start();
        GarbageCollector jay = new GarbageCollector();
        this.addMouseListener(new SuperMouseListener());
        dotArray.setLevel(1);
        dotArray.setPerc(40);
        dotArray.setList();
        lastTime = 0;
    }

    /**
     * Main constructor used to set everything up and get things rolling
     * @param w
     * @param h
     * @param fra
     */
    public Gameboard(int w, int h, Client fra)
    {
        container = fra;
        height = h;
        width = w;
        color = Color.gray;
        setBackground(Color.GRAY);
        dotArray = new DotsArray();
        dots = dotArray.getDotList();
        exp = dotArray.getExplosionList();
        now = 1;

        new Thread(this).start();
        GarbageCollector jay = new GarbageCollector();
        this.addMouseListener(new SuperMouseListener());
        dotArray.setLevel(1);
        dotArray.setPerc(40);
        dotArray.setList();
        lastTime = 0;
    }

    /**
     * Custom Mouse Listener
     */
    class SuperMouseListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if(!isClicked)
            {
                int x = e.getX();
                int y = e.getY();

                Explosion ex = new Explosion(x,y,Color.WHITE);
                dotArray.addExplosionList(ex);
                exp.add(ex);
                dotArray.addExplosionList(ex);
                isClicked = true;
            }
        }
    }

    /**
     * returns height of panel
     * @return
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * returns width of panel
     * @return
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * sets the score of the game
     * @param x
     */
    public void setScore(int x)
    {
        score = x;
    }

    /**
     * returns the score
     * @return
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Figures out what the score is and sets it
     * @param temp
     */
    public void figureoutscore(int temp)
    {
        setScore(temp);
    }

    /**
     * Checks the explosion list to see if any exist and that we have already placed one
     */
    public void checkExpList()
    {
        int x = dotArray.getExplosionList().size();

        for(int i = 0; i<x; i++)
        {
            if(dotArray.getExplosionList().get(i).getSize()<=0)
            {
                dotArray.removeExplosion(i);
                i--;
            }
            
            x = dotArray.getExplosionList().size();
        }

        if(isClicked && x==0)
        {
            //something here that shows the score of the player...maybe a pop up window...or something

            //and then that method calls changeLevel...which will change the level of the game, and
            changeLevel(score);
            //then start it over on a new level...with the same score...good luck with that
        }
    }

    /**
     * Changes the level or ends game and prompts to look at high scores
     * @param previous
     */
    public void changeLevel(int previous)
    {
        
            int level = dotArray.getLevel();
            dotArray.setLevel(++level);
        if(level != 11)
        {

            int percent = dotArray.getPerc();
            dotArray.setPerc(percent+5);

            dotArray.setList();
            isClicked = false;
            setVisible(true);
        }
        else
        {
            container.sendScore(score);
            JOptionPane.showMessageDialog(new JFrame(), "Check the high scores to see if you got one!");
            container.add(container.mb);
            container.mb.setVisible(true);
            container.setVisible(false);
            container.setVisible(true);
            container.remove(this);
            this.setVisible(false);
        }
    }

    /**
     * Updates the painting of the panel
     * @param window
     */
    public void update(Graphics window)
    {
        //checkExpList();
        paint(window);
    }

    /**
     * paints all of our dots and explosions and the score
     * @param window
     */
    public void paint(Graphics window)
    {

        window.clearRect(0, 0, width, height);
        System.gc();
        
        for(int i = 0;i<dotArray.getExplosionList().size();i++)
        {
            window.setColor(dotArray.getExplosionList().get(i).getColor());
            window.fillOval(dotArray.getExplosionList().get(i).getX()-(dotArray.getExplosionList().get(i).getSize()/2),dotArray.getExplosionList().get(i).getY()-(dotArray.getExplosionList().get(i).getSize()/2), dotArray.getExplosionList().get(i).getSize(), dotArray.getExplosionList().get(i).getSize());
        }
        for(int i = 0; i < dotArray.getDotList().size();i++)
        {
            window.setColor(dotArray.getDotList().get(i).getColor());
            window.fillOval(dotArray.getDotList().get(i).getX()-5, dotArray.getDotList().get(i).getY()-5, 10, 10);
        }
        window.setColor(Color.black);
        window.setFont(new Font("TAHOMA",Font.BOLD,18));
        window.drawString("Points: " + getScore() ,40,height-50);
        window.drawString("FPS: " + getFPS() ,width-200,height-50);
    }

    public double getFPS()
    {
        
        if(fpsCount == 0)
        {
            fps = 15;
            fpsCount++;
        }
        else if(fpsCount>10)
        {
            fps = fpsAdd/10;
            lastTime = System.currentTimeMillis();
            //System.out.println(fps);
            fps= 1000/fps;
            fpsAdd=0;
            fpsCount = 1;
            return fps;
        }
        else
        {
            fpsCount++;
            fpsAdd+=System.currentTimeMillis()-lastTime;
            lastTime = System.currentTimeMillis();
        }
        return fps;
    }

    /**
     * called automatically to update the painting, the dots array, and to check explosions
     */
    public void run()
    {
   	try
   	{
            while(this.isVisible())
            {
                dotArray.update();
                checkExpList();
                figureoutscore(dotArray.getScore());
                repaint();
                Thread.currentThread().sleep(28);
            }
        }
        catch(Exception e)
        {

        }
    }
}
