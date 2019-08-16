package client;

import actions.CookieClickerGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread {
    private Socket socket;
    private String playerName;
    private Client client;

    public DataInputStream din;
    public DataOutputStream dout;
    protected CookieClickerGame theGame;

    public ClientConnection(Socket socket, Client client, String playerName) {
        this.socket = socket;
        this.playerName = playerName;
        this.client = client;
    }

    /** Send message to server (methods used for chat and for commands)**/
    protected void sendStringToServer(String clientMessage) {
        try {
            dout.writeUTF(clientMessage);
            dout.flush();
        } catch (IOException e) {
            System.out.println("(tried to send data but failed)Connection lost");
            //e.printStackTrace();
           //close();
        }
    }

    /** Get leaderboards data from the server **/
    public void getLeaderboardsFromServer(String []s, Double[] d)
    {
        sendStringToServer("s!getLB");
        for(int i=0; i<3; i++)
        {
            try {
                s[i] = din.readUTF();
                d[i] = din.readDouble();
            } catch (IOException e)
            {
                /* e.printStackTrace();*/
                System.out.println("(tried to fetch data but failed) Connection lost");
                for(int j =0; j<3; j++)
                {
                    s[j] = "ERR404";
                    d[j] = 0.0;
                }
                break;
            }
        }

    }

    /** Send your score to server **/
    public void sendMyScore(Double score)
    {
        try { dout.writeDouble(score); } catch (IOException e) { e.printStackTrace(); }
    }
    public void run()
    {
        try
        {
            /** Start the game **/
            String [] launchArgs = new String[1];
            launchArgs[0] = playerName;
            theGame = new CookieClickerGame();
            theGame.main(launchArgs);
            theGame.getClient(this.client);


            /** Prepare chat **/
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            sendStringToServer(playerName);
        }
        catch (IOException e) { e.printStackTrace(); }
        while (socket.isConnected())
        {
            try
            {
                while (din.available() == 0)
                {
                    Thread.sleep(1);
                }
                String serverReply = din.readUTF();
                System.out.println(serverReply);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        close();
    }

    protected void close()
    {
        try
        {
            theGame.close();
            din.close();
            dout.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
