/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package derClent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Displayes the menu for the game
 * @author Garrison
 */
public class MenuBoard extends JPanel
{

    public int height;
    public int width;
    public Client container;
    public MenuBoard tis = this;


    /**
     * Constructor for creating the panel
     * @param w
     * @param h
     * @param fra
     */
    public MenuBoard(int w, int h, Client fra)
    {
        height = h;
        width = w;
        container = fra;
        setVisible(true);

        this.addMouseListener(new SuperMouseListener());

    }


    /**
     * Custom mouse listener
     */
    class SuperMouseListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {

            int x = e.getX();
            int y = e.getY();

            if(x > 305 && y > 250 && x < 415 && y < 300)
            {
                container.play();
                tis.setVisible(false);
                container.remove(tis);
            }

            if(x > 255 && y > 350 && x < 455 && y < 400)
            {
                container.scores();
            }
        }
    }

    /**
     * Paints our buttons and graphics
     * @param window
     */
    public void paint(Graphics window)
    {

        window.setColor(Color.black);
        window.setFont(new Font("KIDTYPEPAINT",Font.BOLD,48));
        window.drawString("Menu", 275, 150);
        window.setFont(new Font("KIDTYPEPAINT",Font.BOLD,24));

        window.setColor(Color.CYAN);
        window.fill3DRect(305, 250, 110, 50, true);
        window.setColor(Color.black);
        window.drawString("Play", 320, 280);


        window.setColor(Color.ORANGE);
        window.fill3DRect(255, 350, 200, 50, true);
        window.setColor(Color.black);
        window.drawString("High Scores", 260, 388);


    }


}
