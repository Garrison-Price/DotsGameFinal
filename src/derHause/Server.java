package derHause;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This manages all of the logins and users, as well as handling the highscores.
 * Can recieve connections from up to 100 clients at a time.
 * @author Garrison
 */

public class Server extends JFrame
{
    private JTextArea displayArea;
    private DatagramSocket socket;
    private ArrayList<String> names;
    private ArrayList<InetAddress> clients;
    private ArrayList<Integer> ports;

    private JPanel mainPanel;
    private JPanel connectionPanel;
    private JPanel userPanel;
    private JScrollPane connectionPane;
    private JScrollPane userPane;
    private JTabbedPane mainTabPane;
    private JTable userTable;
    private JTextArea connectionTextArea;

    public File hsf = new File("src\\derHause\\hs.dat");
    public File uf = new File("src\\derHause\\us.dat");

    /**
     * Constructor that sets everything up
     */
    public Server()
    {
        super("Server");
        displayArea = new JTextArea();
        clients = new ArrayList<InetAddress>();
        ports = new ArrayList<Integer>();
        names = new ArrayList<String>();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        setSize(400,300);
        //setVisible(true);
        initComponents();

        try
        {
            socket = new DatagramSocket(4000);
        }
        catch(SocketException socketException)
        {
            socketException.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Initializes the components of the JFrame and puts them into a GroupLayout
     */
    private void initComponents() {

        mainPanel = new JPanel();
        mainTabPane = new JTabbedPane();
        connectionPanel = new JPanel();
        connectionPane = new JScrollPane();
        connectionTextArea = new JTextArea();
        userPanel = new JPanel();
        userPane = new JScrollPane();
        userTable = new JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        connectionTextArea.setColumns(20);
        connectionTextArea.setRows(5);
        connectionPane.setViewportView(connectionTextArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(connectionPanel);
        connectionPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(connectionPane, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(connectionPane, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabPane.addTab("Connections", connectionPanel);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "IP", "Port"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        userPane.setViewportView(userTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userPane, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userPane, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabPane.addTab("Users", userPanel);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabPane.getAccessibleContext().setAccessibleName("Connections");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    /**
     * Waits to recieve the packets from the clients
     */
    public void waitForPackets()
    {
        while(true)
        {
            try
            {

                byte data[] = new byte[100];
                DatagramPacket recievePacket = new DatagramPacket(data,data.length);

                socket.receive(recievePacket);
                String temp = new String(recievePacket.getData(), 0, recievePacket.getLength());

                if(temp.contains("loginzoeooeowwpdsddasf"))
                {
                    Login(temp.substring(temp.indexOf(":")+2), recievePacket.getAddress(),recievePacket.getPort());
                }
                else if(temp.contains("disconnectjsopjsgpjspfjosidvps[phd'dtdsrg"))
                {
                    int index = clients.indexOf(recievePacket.getAddress());
                    clients.remove(index);
                    ports.remove(index);
                    names.remove(index);
                    displayMessage("\nDisconnected - "+ recievePacket.getAddress());
                    updateTable();
                }
                else if(temp.contains("newuserpijsfdojhbzdfsjgzdfkljghbdzsflkjg"))
                {
                    newUser(temp.substring(temp.indexOf(":")+2), recievePacket.getAddress(),recievePacket.getPort());
                }
                else if(temp.contains("gimmehsoiusfdffbkjzsfgkljhjhjk"))
                {
                    sendHS(recievePacket.getAddress(),recievePacket.getPort());
                }
                else if(temp.contains("newhsoiusfdffbkjzsfgkljhjhjk"))
                {
                    addHS(temp.substring(temp.indexOf(":")+2));
                }
            }
            catch(IOException io)
            {
                displayMessage(io.toString() + "\n");
                io.printStackTrace();
            }
        }
    }

    /**
     * Updates the User Table with new info
     */
    private void updateTable()
    {
        for(int i = 0; i < clients.size()+2;i++)
        {
            if(i < clients.size())
            {
                    userTable.setValueAt(names.get(i), i, 0);
                    userTable.setValueAt(clients.get(i), i, 1);
                    userTable.setValueAt(ports.get(i), i, 2);
            }
            else
            {
                    userTable.setValueAt(null, i, 0);
                    userTable.setValueAt(null, i, 1);
                    userTable.setValueAt(null, i, 2);
            }
        }
    }

    /**
     * Displays test in the JTextArea
     * @param messageToDisplay
     */
    private void displayMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        connectionTextArea.append(messageToDisplay);
                    }
                }
        );
    }

    /**
     * Handels the Logging in of clients and sends a response to the client
     * @param info
     * @param add
     * @param prt
     */
    public void Login(String info, InetAddress add, int prt)
    {
        boolean done = false;
        try
        {
            Scanner chop = new Scanner(uf);
            while(chop.hasNextLine())
            {
                String next = chop.nextLine();
                if(next.substring(0, next.indexOf(",")).equals(info.substring(0, info.indexOf(","))))
                {
                    if(next.substring(next.indexOf(",")+1).equals(info.substring(info.indexOf(",")+1)))
                    {
                        try
                        {
                            String sendItem = "*0";
                            byte data[] = sendItem.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(data,data.length,add,prt);

                            socket.send(sendPacket);
                            done = true;
                            displayMessage("Successful Login!");
                        }
                        catch(Exception ex)
                        {
                            displayMessage("Failed Login!");
                        }
                    }
                }
            }
            if(!done)
            {
                try
                {
                    String sendItem = "*-2";
                    byte data[] = sendItem.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(data,data.length,add,prt);

                    socket.send(sendPacket);
                    displayMessage("Incorrect Username or Password!");
                }
                catch(Exception ex)
                {
                    displayMessage("Failed Login!");
                }
            }
            else
            {
                clients.add(add);
                ports.add(prt);
                names.add(info.substring(0, info.indexOf(",")));
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
            displayMessage("File Error!");
        }
        updateTable();
    }

    /**
     * Creates a new user if it is not already taken
     * Sends a response to the client
     * Logs the client in with the new username
     * @param info
     * @param add
     * @param prt
     */
    public void newUser(String info, InetAddress add, int prt)
    {

        boolean done = true;
        
        try
        {
            Scanner chop = new Scanner(uf);
            while(chop.hasNextLine())
            {
                String next = chop.nextLine();
                if(next.substring(0, next.indexOf(",")).equals(info.substring(0, info.indexOf(","))))
                {
                    done = false;
                }
            }
            chop.close();
            if(!done)
            {
                try
                {
                    String sendItem = "!-2";
                    byte data[] = sendItem.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(data,data.length,add,prt);

                    socket.send(sendPacket);
                }
                catch(Exception ex)
                {
                }
            }
            else
            {
                try
                {
                    String sendItem = "!0";
                    byte data[] = sendItem.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(data,data.length,add,prt);

                    socket.send(sendPacket);
                    done = true;
                }
                catch(Exception ex)
                {

                }
                try
                {
                    
                    Scanner chopus = new Scanner(uf);
                    ArrayList<String> lines = new ArrayList();
                    
                    while(chopus.hasNextLine())
                    {
                        String next = chopus.nextLine();
                        lines.add(next);
                    }

                    chopus.close();
                    PrintStream out = new PrintStream(new FileOutputStream(uf));
                    for(int i = 0;i < lines.size(); i++)
                    {
                        out.println(lines.get(i));
                    }
                    out.println(info);
                    out.close();
                }
                catch(Exception ez)
                {

                }
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
            displayMessage("File Error!");
        }

    }

    /**
     * Sends the highscore list to the player
     * @param add
     * @param prt
     */
    public void sendHS(InetAddress add, int prt)
    {
        String out = "";
        try
        {
            Scanner chop = new Scanner(hsf);
            while(chop.hasNextLine())
            {
                out += chop.nextLine()+"\n";
            }
        }
        catch(Exception e)
        {

        }
        out = "$"+out;
        try
        {
            byte data[] = out.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data,data.length,add,prt);

            socket.send(sendPacket);
        }
        catch(Exception ex)
        {
        }
    }

    /**
     * Checks to see if a new highscore has been achieved and re-writes the list
     * @param info
     */
    public void addHS(String info)
    {
        int score = Integer.parseInt(info.substring(0,info.indexOf(",")));
        String name = info.substring(info.indexOf(",")+1);
        int num = 1;
        boolean picked = false;
        System.out.println(info);

        try
        {

            Scanner chopus = new Scanner(hsf);
            ArrayList<String> lines = new ArrayList();

            while(chopus.hasNextLine())
            {
                String next = chopus.nextLine();
                if(score > Integer.parseInt(next.substring(next.lastIndexOf("-")+2).replaceAll(" ", "")) && !picked)
                {
                    lines.add(num+". "+name+" - "+score);
                    num++;
                    for(int i = num;i <= 5;i++)
                    {
                       
                        lines.add(num+". "+next.substring(next.indexOf(".")+2,next.indexOf("-")-1)+" - "+next.substring(next.lastIndexOf("-")+2));
                        next = chopus.nextLine();
                        num++;
                    }
                    picked = true;
                }
                else
                {
                    lines.add(next);
                    num++;
                }
            }

            chopus.close();
            PrintStream out = new PrintStream(new FileOutputStream(hsf));
            for(int i = 0;i < lines.size(); i++)
            {
                out.println(lines.get(i));
            }
            out.close();
        }
        catch(Exception ez)
        {
            System.err.println(ez);
        }


    }

    /**
     * Starts the Server
     * @param args
     */
    public static void main(String args[])
    {
        Server app = new Server();
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.waitForPackets();

    }

}
