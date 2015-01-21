/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package derClent;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is the client, acts as a container and handler for the game
 * @author Garrison
 */
public class Client extends JFrame
{

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public StartBoard sb;

    public MenuBoard mb;

    public Gameboard gb;

    public boolean namd;
    public boolean logd;

    public boolean timed;

    private DatagramSocket socket;

    public String currName;

    /**
     * Default constructor that sets up the client
     */
    public Client()
    {
        super("Dots!");

        setSize(WIDTH,HEIGHT);
        sb = new StartBoard(WIDTH,HEIGHT,this);
        this.add(sb);
        setVisible(true);
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try
                {
                    String message = "disconnectjsopjsgpjspfjosidvps[phd'dtdsrg";
                    byte data[] = message.getBytes();

                    //172.28.90.227
                    DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName("127.0.0.1"),4000);

                    socket.send(sendPacket);
                }
                catch(IOException io)
                {
                    io.printStackTrace();
                }
            }
        });
    }

    /**
     * Called by StartPanel, requests a login from the server and continues if nothing is wrong
     * @param name
     * @param pass
     * @return
     */
    public int Login(String name, String pass)
    {
        //do login if error then return error...
        //else change to menuBoard!
        try
        {
            //connecthere
            //
            try
            {
                String message = "loginzoeooeowwpdsddasf:"+" "+name+","+pass;
                byte data[] = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName("127.0.0.1"),4000);

                socket.send(sendPacket);
            }
            catch(IOException io)
            {
                io.printStackTrace();
            }


            //return checkUser()
            //will return -2 for invalid user
            //stops there
            //
            //OR
            //
            //proceeeds to login and changes board to menu board! and return 0

            Timer wait = new Timer();
            timed = false;
            wait.schedule(new RemindTask(), 2000);
            while(!timed)
            {
                
            }
            //if(checkUser() == 0)
            //{
                mb = new MenuBoard(WIDTH,HEIGHT,this);
                this.remove(sb);
                this.add(mb);
                setVisible(false);
                setVisible(true);
                currName = name;
            //}
            return checkUser();
        }
        catch(Exception e)
        {
            //return -1 for connection fail
            return -1;
        }
    }

    /**
     * Called by startboard, asks server to create new user
     * @param name
     * @param pass
     * @return
     */
    public int newUser(String name, String pass)
    {
        //try to connect

        try
        {
            //connecthere
            //

            try
            {
                String message = "newuserpijsfdojhbzdfsjgzdfkljghbdzsflkjg:"+" "+name+","+pass;
                byte data[] = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName("127.0.0.1"),4000);

                socket.send(sendPacket);
            }
            catch(IOException io)
            {
                io.printStackTrace();
            }
            //return checkName()
            //will return -2 for a name that has been used!
            //stops there
            //
            //OR
            //
            //proceeeds to login and changes board to menu board! and return 0

            Timer wait = new Timer();
            timed = false;
            wait.schedule(new RemindTask(), 2000);
            while(!timed)
            {

            }
            //if(checkNamed() == 0)
            //{
                Login(name,pass);
            //}

            return checkNamed();
        }
        catch(Exception e)
        {
            //return -1 for connection fail
            return -1;
        }
    }

    /**
     * Called to start playing the game
     */
    public void play()
    {
        try
        {
            gb = new Gameboard(WIDTH,HEIGHT,this);
            this.remove(mb);
            mb.setVisible(false);
            add(gb);
            setVisible(false);
            setVisible(true);
        }
        catch(Exception e)
        {
            
        }
    }

    /**
     * Sends the score to the server to check for highscores
     * @param score
     */
    public void sendScore(int score)
    {
        try
        {
            //connecthere
            //

            try
            {
                String message = "newhsoiusfdffbkjzsfgkljhjhjk:"+" "+score+","+currName;
                byte data[] = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName("127.0.0.1"),4000);

                socket.send(sendPacket);
            }
            catch(IOException io)
            {
                io.printStackTrace();
            }
            //return checkName()
            //will return -2 for a name that has been used!
            //stops there
            //
            //OR
            //
            //proceeeds to login and changes board to menu board! and return 0

        }
        catch(Exception e)
        {
            //return -1 for connection fail
        }
    }

    /**
     * Checks info sent by the server
     * @return
     */
    public int checkUser()
    {
        if(logd)
        {
            return 0;
        }
        else
        {
            return -2;
        }
    }

    /**
     * Checks info sent by server
     * @return
     */
    public int checkNamed()
    {
        if(namd)
        {
            return 0;
        }
        else
        {
            return -2;
        }
    }

    /**
     * Requests to see the highscore list from the server
     */
    public void scores()
    {
        try
        {
            //connecthere
            //

            try
            {
                String message = "gimmehsoiusfdffbkjzsfgkljhjhjk";
                byte data[] = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName("127.0.0.1"),4000);

                socket.send(sendPacket);
            }
            catch(IOException io)
            {
                io.printStackTrace();
            }
            //return checkName()
            //will return -2 for a name that has been used!
            //stops there
            //
            //OR
            //
            //proceeeds to login and changes board to menu board! and return 0

        }
        catch(Exception e)
        {
            //return -1 for connection fail
        }
    }

    /**
     * Custom Timer Task
     */
    class RemindTask extends TimerTask
    {
        public void run()
        {
            timed = true;
        }
    }

    /**
     * Waits for server responses
     */
    private void waitForInfo()
    {
        while(true)
        {
            try
            {
                byte data[] = new byte[100];
                DatagramPacket receivePacket = new DatagramPacket(data,data.length);

                socket.receive(receivePacket);

                String temp = new String(receivePacket.getData(), 0, receivePacket.getLength());
                if(temp.contains("*"))
                {
                    if(Integer.parseInt(temp.substring(temp.indexOf("*")+1)) == 0)
                        logd = true;
                    else
                        logd = false;
                }
                else if(temp.contains("!"))
                {
                    if(Integer.parseInt(temp.substring(temp.indexOf("!")+1)) == 0)
                        namd = true;
                    else
                        namd = false;
                }
                else if(temp.contains("$"))
                {
                    JOptionPane.showMessageDialog(new JFrame(), temp.substring(temp.indexOf("$")+1));
                }
            }
            catch(IOException exception)
            {
                exception.printStackTrace();
            }
        }
    }

    

    /**
     * Starts the client
     * @param args
     */
    public static void main(String args[])
    {
        Client run = new Client();
        run.setDefaultCloseOperation(EXIT_ON_CLOSE);
        run.waitForInfo();
    }
}
