package server;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.*;
import java.io.*;

public class ServerConnection extends Thread
{
    private int connectionIndex;
    private String playerName = "unknown";
    private Double playerScore = 0.0;

    private Socket socket;
    private Server server;

    public DataInputStream din;
    public DataOutputStream dout;

    public ServerConnection(Socket socket, Server server, int connectionIndex)
    {
        super("ServerConnectionThread");
        this.socket = socket;
        this.server = server;
        this.connectionIndex = connectionIndex;
    }

    public void run()
    {
        try
        {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            /** Identify player **/
            setPlayerName(din.readUTF());
            sendStringToClient("Chat avtivated. Your name: "+playerName);
            sendStringToAllClients(playerName+" has joined the chat.");
            server.notifyServer(playerName+" is connected.");

            communicationStart();
        } catch (IOException e) { e.printStackTrace(); }
    }

    /** Start SERVER<->PLAYER communication **/
    private void communicationStart()
    {
        try
        {
            while(socket.isConnected())
            {
                /** Get message from the player **/
                while(din.available() == 0)
                {
                    try
                    {
                        Thread.sleep(1);
                    } catch (InterruptedException e) { e.printStackTrace(); }
                }
                String receivedMessage = din.readUTF();
                handleCommand(receivedMessage);
                if(receivedMessage.equals("quit")){break;}

            }
        } catch (IOException e) { server.notifyServer("Tried to send a message, but stream is closed. (user:"+playerName+")"); }
    }

    /** Handle player's command **/
    public void handleCommand(String receivedMessage)
    {
        /*Commands:
         * s!leaderboards
         * s!online
         * s!commands
         * quit
         */
        try {
            if (receivedMessage.equals("quit"))
            {
                socket.close();
                din.close();
                dout.close();
                server.connections.remove(connectionIndex);
                for (int i = connectionIndex; i < server.connections.size(); i++) {
                    server.connections.get(i).setConnectionIndex(server.connections.get(i).getConnectionIndex() - 1);
                }
                sendStringToAllClients(playerName + " has left the chat.");
                server.notifyServer(playerName + " is disconnected.");
                server.connIndex--;
            }
            else if(receivedMessage.equals("s!getLB"))
            {
                sendLeaderboardsToClient();
            }
            else
            {
                chat(receivedMessage);

                if (receivedMessage.equals("s!leaderboards"))
                {
                    sendStringToAllClients("Current leaderboard:-----------\n" +
                            "1. " + Server.leaderboardNames[0] + " with " + Server.leaderboardScores[0] + " Cookies!\n" +
                            "2. " + Server.leaderboardNames[1] + " with " + Server.leaderboardScores[1] + " Cookies!\n" +
                            "3. " + Server.leaderboardNames[2] + " with " + Server.leaderboardScores[2] + " Cookies!\n" +
                            "---------------------------------------");
                } else if (receivedMessage.equals("s!online"))
                {
                    sendStringToAllClients("Currently online:----------------------");
                    for (int i = 0; i < server.connections.size(); i++) {
                        sendStringToAllClients((i + 1) + ". " + server.connections.get(i).getPlayerName());
                    }
                    sendStringToAllClients("---------------------------------------");
                } else if (receivedMessage.equals("s!commands"))
                {
                    sendStringToAllClients("Currently online:----------------------\n"+
                            "Available commands:\n" +
                            "s!leaderboards - to show current leaderboard\n" +
                            "s!online - to show who is online\n" +
                            "s!commands - to show all commands\n"+
                            "s!check - compare your score with the leaderbord\n"+
                            "-----------------------------------------------");
                } else if (receivedMessage.equals("s!check"))
                {
                   playerScore = din.readDouble();
                   compareScore();
                }
            }
        }
        catch(IOException e) { e.printStackTrace(); }
    }

    /** Sends message to connected client**/
    private void sendStringToClient(String message)
    {
        try
        {
            dout.writeUTF(message);
            dout.flush();
        }
        catch (IOException e)
        {
            //e.printStackTrace();
        }
    }

    /** Sends message to all connected client**/
    private void sendStringToAllClients(String message)
    {
        for (int i = 0; i < server.connections.size(); i++)
        {
            ServerConnection serverConnection = server.connections.get(i);
            serverConnection.sendStringToClient("SERVER>>"+message);
        }
    }

    /** Broadcasts message to other clients**/
    private void chat(String message)
    {
        //System.out.println(playerName+">>"+message);
        for (int i = 0; i < server.connections.size(); i++)
        {
            ServerConnection serverConnection = server.connections.get(i);
            if(!(serverConnection.getPlayerName()==this.playerName))
                serverConnection.sendStringToClient(this.getPlayerName()+">>"+message);
        }
    }

    /** Send leaderboard to client**/
    private void sendLeaderboardsToClient()
    {
        for(int i=0; i<server.leaderboardScores.length;i++)
        {
            try
            {
                dout.writeUTF(server.leaderboardNames[i]);
                dout.writeDouble(server.leaderboardScores[i]);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    /** Compare score with the leaderboards**/
    private void compareScore()
    {
        if(playerScore>server.leaderboardScores[0])
        {
            if(playerName.equals(server.leaderboardNames[0]))
            {
                server.leaderboardScores[0] = playerScore;
                server.leaderboardNames[0] = playerName;
                sendStringToAllClients("Congratulations! "+ playerName +" has beaten his\\her score (1st place)!");
            }
            else
            {
                server.leaderboardScores[2] = server.leaderboardScores[1];
                server.leaderboardNames[2] = server.leaderboardNames[1];
                server.leaderboardScores[1] = server.leaderboardScores[0];
                server.leaderboardNames[1] = server.leaderboardNames[0];
                server.leaderboardScores[0] = playerScore;
                server.leaderboardNames[0] = playerName;
                sendStringToAllClients("Congratulations "+ playerName +"! You are new number 1 on the leaderboards :)!");
            }
            updateLB();
        }
        else if(playerScore>server.leaderboardScores[1] && !(server.leaderboardNames[0].equals(playerName)))
        {
            if(playerName.equals(server.leaderboardNames[1]))
            {
                server.leaderboardScores[1] = playerScore;
                server.leaderboardNames[1] = playerName;
                sendStringToAllClients("Congratulations! "+ playerName +" has beaten his\\her score (2nd place)!");
            }
            else
            {
                server.leaderboardScores[2] = server.leaderboardScores[1];
                server.leaderboardNames[2] = server.leaderboardNames[1];
                server.leaderboardScores[1] = playerScore;
                server.leaderboardNames[1] = playerName;
                sendStringToAllClients("Congratulations! "+ playerName +"! You are new number 2 on the leaderboards :)!");
            }
            updateLB();
        }
        else if(playerScore>server.leaderboardScores[2] && !(server.leaderboardNames[1].equals(playerName)) && !(server.leaderboardNames[0].equals(playerName)))
        {
            if(playerName.equals(server.leaderboardNames[2]))
            {
                server.leaderboardScores[2] = playerScore;
                sendStringToAllClients("Congratulations! "+ playerName +" has beaten his\\her score (3rd place)!");
            }
            else
            {
                server.leaderboardScores[2] = playerScore;
                server.leaderboardNames[2] = playerName;
                sendStringToAllClients("Congratulations " + playerName + "! You are new number 3 on the leaderboards :)!");
            }
            updateLB();
        }
        else
        {
            sendStringToAllClients("Sorry "+playerName+". You didn't beat anyone's score :(.");
        }

    }

    /** Update leaderboards **/
    private void updateLB()
    {
        StringBuilder sLB = new StringBuilder();
        for(int i=0; i<3; i++)
        {
            sLB.append(server.leaderboardNames[i]);
            sLB.append("\n");
            sLB.append(round(server.leaderboardScores[i],2));
            sLB.append("\n");
        }

        try {
            OutputStream stream = new FileOutputStream(new File(server.SERVER_DATA_DIR));
            String s = sLB.toString();

            stream.write(s.getBytes());
            stream.close();
            server.notifyServer("Leaderboard has been updated!");

        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }

    }

    /** Setter and Getters **/
    private String getPlayerName() {
        return playerName;
    }

    private void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private int getConnectionIndex() {
        return connectionIndex;
    }

    private void setConnectionIndex(int connectionIndex) {
        this.connectionIndex = connectionIndex;
    }

    /** Rounding double to #.00 format (to nicely store leaderbord data) **/
    private static double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
