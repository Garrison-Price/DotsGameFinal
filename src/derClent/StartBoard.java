/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package derClent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Panel that draws and handles the First Panel shown
 * @author Garrison
 */
public class StartBoard extends JPanel
{

    public int height;
    public int width;
    public Color color;
    public boolean clicked;
    public boolean clicked2;
    public JFrame loginFrame;
    public JFrame newUserFrame;
    public Client container;
    public JTextField nameField1;
    public JTextField nameField2;
    public JPasswordField passField1;
    public JPasswordField passField2;

    /**
     * Constructor to setup the JPanel
     * @param w
     * @param h
     * @param fra
     */
    public StartBoard(int w, int h, Client fra)
    {
        height = h;
        width = w;

        container = fra;

        //initComp();
        setVisible(true);
        clicked = true;
        clicked2 = true;

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

            if(x > 150 && y > 300 && x < 260 && y < 350)
            {
                clicked = false;
                Login();
            }

            if(x > 500 && y > 300 && x < 680 && y < 350)
            {
                clicked2 = false;
                newUser();
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
        window.setFont(new Font("KIDTYPEPAINT",Font.BOLD,72));
        window.drawString("Dots!", 275, 150);

        
        window.setFont(new Font("KIDTYPEPAINT",Font.BOLD,24));
        window.setColor(Color.CYAN);
        window.fill3DRect(150, 300, 110, 50, clicked);
        window.setColor(Color.black);
        window.drawString("Login", 155, 333);


        window.setColor(Color.ORANGE);
        window.fill3DRect(500, 300, 180, 50, clicked2);
        window.setColor(Color.black);
        window.drawString("New User", 505, 333);

        if(clicked == false)
        {
            
        }
        
    }

    /**
     * Called when login button is pressed
     */
    public void Login()
    {
        loginFrame = new JFrame();
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initLogin(loginFrame);
    }

    /**
     * Creates the login frame used to enter username and password
     * @param frame
     */
    public void initLogin(final JFrame frame)
    {
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        nameField1 = new javax.swing.JTextField();
        passField1 = new javax.swing.JPasswordField();
        JButton jButton1 = new javax.swing.JButton();



        frame.setLocation(500, frame.getY());


        jLabel1.setFont(new java.awt.Font("KidTYPEPaint", 0, 48)); 
        jLabel1.setText("Dots");

        jLabel2.setText("Username:");

        jLabel3.setText("Password:");

        passField1.setText("password");

        jButton1.setText("Submit!");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                String pass = "";
                for(int i = 0;i<passField1.getPassword().length;i++)
                    pass += passField1.getPassword()[i];
                int test = container.Login(nameField1.getText(),pass);
                if(test == -1)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Connection Failure!");
                }
                else if(test == -2)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Bad Username or Password");
                }
                else
                {
                    frame.setVisible(false);
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(nameField1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(passField1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton1)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
    }

    /**
     * Called when new user button is pressed
     */
    public void newUser()
    {
        newUserFrame = new JFrame();
        newUserFrame.setVisible(true);
        newUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initNewUser(newUserFrame);
    }

    /**
     * Creates the new user frame to enter new username and new password
     * @param frame
     */
    public void initNewUser(final JFrame frame)
    {
        JPanel jPanel1 = new javax.swing.JPanel();
        nameField2 = new javax.swing.JTextField();
        passField2 = new javax.swing.JPasswordField();
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JButton jButton1 = new javax.swing.JButton();
        JLabel jLabel3 = new javax.swing.JLabel();


        frame.setLocation(500, frame.getY());

        passField2.setText("password");

        jLabel1.setText("New Username");

        jLabel2.setText("New Password");

        jButton1.setText("Submit");

        

        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                String pass = "";
                for(int i = 0;i<passField2.getPassword().length;i++)
                    pass += passField2.getPassword()[i];
                int test = container.newUser(nameField2.getText(),pass);
                if(test == -1)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Connection Failure!");
                }
                else if(test == -2)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Name Already Taken!");
                }
                else
                {
                    frame.setVisible(false);
                }
            }
        });

        jLabel3.setFont(new java.awt.Font("KidTYPEPaint", 0, 48)); // NOI18N
        jLabel3.setText("Dots");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameField2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(passField2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(102, 102, 102))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel3)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
    }

}
