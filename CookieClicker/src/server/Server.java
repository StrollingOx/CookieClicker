package server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

public class Server
{
    //TODO: Automatically check player's stats with the leaderboard
    public static int PORT_NUMBER;
    public static String SERVER_IP;
    public static String SERVER_DATA_DIR;

    public ServerSocket serverSocket;
    public Socket socket;

    private boolean online = true;

    public static String [] leaderboardNames = new String[3];
    public static Double [] leaderboardScores = new Double[3];

    public ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
    public int connIndex;

    public static void main(String[] args)
    {
        new Server();
    }

    public Server ()
    {
        loadXMLFile();
        loadLB();

        startServer();
    }

    /** Load server configuration **/
    public static void loadXMLFile()
    {
        try
        {
            File file = new File("xml/server_config.xml");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            String key;
            while (enuKeys.hasMoreElements())
            {
                switch(key = (String) enuKeys.nextElement())
                {
                    case "port":
                        PORT_NUMBER = Integer.parseInt(properties.getProperty(key));
                        break;
                    case "ip":
                        SERVER_IP = properties.getProperty(key);
                        break;
                    case "serverdata":
                        SERVER_DATA_DIR = properties.getProperty(key);
                        break;

                }
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    /** Launches server*/
    private void startServer()
    {
        System.out.printf("SERVER IS ONLINE (PORT:"+PORT_NUMBER+")\n");

        /** Prints present leaderboard to server's terminal **/
        System.out.println("Current leaderboard:");
        for(int i=0; i<3; i++)
            System.out.println(leaderboardNames[i] +": "+leaderboardScores[i] + " Cookies baked");

        /** Connections **/
        try
        {
            serverSocket = new ServerSocket(PORT_NUMBER);
            connIndex = 0;
            while(online)
            {
                /** Wait for the connection **/
                socket = serverSocket.accept();

                System.out.printf("Estabilishing connection(Adress: "+socket.getInetAddress()+"; PORT: "+socket.getPort()+")...\n");

                /** Start connection, add to Array**/
                ServerConnection serverConnection = new ServerConnection(socket, this, connIndex);
                serverConnection.start();
                connections.add(serverConnection);
                connIndex++;
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    /** Load leaderboards to server **/
    public static void loadLB()
    {
        try {
            Scanner sc = new Scanner(new File(SERVER_DATA_DIR));
            for(int i=0;i<3;i++)
            {
                leaderboardNames[i] = sc.nextLine();
                leaderboardScores[i] = Double.parseDouble(sc.nextLine());
            }

        } catch (FileNotFoundException e) { e.printStackTrace();
        }
    }

    /** ServerConnection->Server communication **/
    public static void notifyServer(String message)
    {
        System.out.println(message);
    }
}
