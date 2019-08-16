import GUI.*;
import actions.CookieClickerGame;
import actions.Data;
import client.Client;
import client.ClientConnection;
import org.junit.jupiter.api.Test;

import server.Server;
import server.ServerConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JUnitTest
{
    Server server;
    public ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();

    Client client;

    CookieClickerGame ccg;

    Gui g;

    ImageLoader il;

    Achievement newAchievement;
    AchievementCheck ac;
    AchievementSlider as;

    Upgrade up;

    Data data;

    private DataInputStream din;
    private DataOutputStream dout;

    @Test
    void testServerAndClientConfig()
    {
        server.loadXMLFile();
        assertNotNull(server.PORT_NUMBER);
        assertNotNull(server.SERVER_IP);
        assertNotNull(server.SERVER_DATA_DIR);

        client.loadXMLFile();
        assertNotNull(client.PORT_NUMBER);
        assertNotNull(client.SERVER_IP);

        server.loadLB();
        for(int i=0; i<3; i++)
        {
            assertNotNull(server.leaderboardNames[i]);
            assertNotNull(server.leaderboardScores[i]);
        }

        try
        {
            ServerSocket ss = new ServerSocket(4999);
            Socket s = new Socket("localhost", 4999);

            assertNotNull(ss);
            assertNotNull(s);

        } catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    void testConnections()
    {
        try {
            ServerSocket ss = new ServerSocket(4999);
            Socket socS;
            Socket socC = new Socket("localhost", 4999);
            socS = ss.accept();

            ServerConnection sc = new ServerConnection(socS, server, 0);
            ClientConnection cc = new ClientConnection(socC, client, "player");

            /** Connection test **/
            assertNotNull(sc);
            assertNotNull(cc);

            cc.dout = new DataOutputStream(socC.getOutputStream());
            sc.din = new DataInputStream(socS.getInputStream());
            cc.sendMyScore(10.00);
            double result = sc.din.readDouble();

            /** Client out, Server in test **/
            assertEquals(10.00, result);

            cc.din = new DataInputStream(socC.getInputStream());
            sc.dout = new DataOutputStream(socS.getOutputStream());
            sc.dout.writeUTF("Hello");

            /** Server out, Client in test **/
            assertEquals(cc.din.readUTF(), "Hello");

        } catch (IOException e) { e.printStackTrace(); }


    }

    @Test
    void testGame()
    {
        ccg = new CookieClickerGame();
        String [] args = new String []{"Player1"};
        ccg.main(args);

        assertNotNull(ccg);
    }

    @Test
    void testLoadLeaderboard()
    {
        /** Data.loadLB**/
        String s = "";
        Double d = 0.0;

        server.loadXMLFile();
        server.loadLB();
        try {
            ServerSocket ss = new ServerSocket(4999);
            Socket socS;
            Socket socC = new Socket("localhost", 4999);
            socS = ss.accept();

            ServerConnection sc = new ServerConnection(socS, server, 0);
            ClientConnection cc = new ClientConnection(socC, client, "player");

            cc.dout = new DataOutputStream(socC.getOutputStream());
            cc.din = new DataInputStream(socC.getInputStream());
            sc.dout = new DataOutputStream(socS.getOutputStream());
            sc.din = new DataInputStream(socS.getInputStream());

           cc.dout.writeUTF("s!getLB");
           assertNotNull(sc.din.readUTF());
           sc.dout.writeUTF(server.leaderboardNames[1]);
           s = cc.din.readUTF();
           sc.dout.writeDouble(server.leaderboardScores[1]);
           d = cc.din.readDouble();

            assertNotNull(s);
            assertNotNull(d);


        } catch (IOException e) { e.printStackTrace(); }


    }

    @Test
    void testGUI()
    {
        g = new Gui();

        assertNotNull(g);
        assertEquals(g.RESOLUTION_WIDTH, 1280);
        assertEquals(g.RESOLUTION_HEIGHT, 720);
    }

    @Test
    void testImageLoader()
    {
        il = new ImageLoader();

        assertNotNull(il.imgBackground);
        assertNotNull(il.imgCookie1);
        assertNotNull(il.imgCookie2);
        for(int i=0;i<il.imgUpgrades.length;i++) {assertNotNull(il.imgUpgrades[i]);}
        for(int i = 0; i<il.imgAchievements.length; i++) assertNotNull(il.imgAchievements[i]);
    }

    @Test
    void testAchievement()
    {
        il = new ImageLoader();
        newAchievement = new Achievement(0,0,il.imgAchievements[0]);
        as = new AchievementSlider(newAchievement);
        ac = new AchievementCheck();

        assertNotNull(newAchievement);
        assertNotNull(as);
        assertNotNull(ac);

        assertEquals(newAchievement.isUnlocked(), false);
        AchievementCheck.unlock(newAchievement, as);
        assertEquals(newAchievement.isUnlocked(), true);
    }

    @Test
    void testUpgrades()
    {
        il = new ImageLoader();
        up = new Upgrade("testButton", il.imgUpgrades[0], 15, 0.1);
        int result = up.setYCoord(1);

        assertNotNull(up);
        assertEquals(350, result);

        ccg = new CookieClickerGame();
        up.cpsUpgrade(9);
        assertEquals(ccg.cPS,1600000.0);
    }

}
