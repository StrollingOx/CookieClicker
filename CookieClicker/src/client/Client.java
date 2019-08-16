package client;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

public class Client
{
    //TODO: Logging/Signing in
    public static int PORT_NUMBER;
    public static String SERVER_IP;

    public Socket client;
    public static ClientConnection clientConnection;
    private Scanner cmd;

    String playerName;

    public static void main(String[] args)
    {
        new Client();
    }

    public Client()
    {
        loadXMLFile();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter you nickname");
        playerName = sc.nextLine();
        System.out.println("Please, wait for the game and chat to activate...");

        /** Commands **/
        System.out.println("Available commands:\n" +
                "s!leaderboards - to show current leaderboard\n" +
                "s!online - to show who is online\n"+
                "s!commands - to show all commands\n"+
                "s!check - send Your score to server! Check if You are the best!!!\n"+
                "'quit' to quit the game and disconnect from the server");

        try
        {
            client = new Socket(SERVER_IP, PORT_NUMBER);
            clientConnection = new ClientConnection(client,this, playerName);
            clientConnection.start();

            listenForInput();
        }
        catch (UnknownHostException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void listenForInput()
    {

        cmd = new Scanner(System.in);
        while(client.isConnected())
        {
            while(!cmd.hasNextLine())
            {
                try
                {
                    Thread.sleep(1);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            String clientMessage = cmd.nextLine();
            if (clientMessage.toLowerCase().equals("quit"))
            {
                clientConnection.sendStringToServer(clientMessage);
                clientConnection.close();
                break;
            }
            else if(clientMessage.equals("s!check"))
            {
                clientConnection.sendStringToServer(clientMessage);
                clientConnection.sendMyScore(round(clientConnection.theGame.lifetimeCookies, 2));
            }
            else { clientConnection.sendStringToServer(clientMessage); }
        }
    }

    /** Load server's port and ip **/
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

                }
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    /** Rounding double to #.00 format (used to send better data to server) **/
    private static double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
