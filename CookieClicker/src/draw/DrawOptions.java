package draw;

import GUI.Gui;
import actions.CookieClickerGame;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DrawOptions extends JLabel
{
    Gui gui = new Gui();
    int textWidth;
    DecimalFormat df = new DecimalFormat("###,###,###");

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        if(Gui.bOptions.isActive())
        {
            /** Save **/
            g.setColor(new Color(43,61,79,150));

            g.fillRect((gui.RESOLUTION_WIDTH/2) +1,110,gui.RESOLUTION_WIDTH, 110 );

            g.setColor(new Color(255, 255, 255, 150));
            g.drawLine((gui.RESOLUTION_WIDTH/2) +1, 110, gui.RESOLUTION_WIDTH, 110);
            g.drawLine((gui.RESOLUTION_WIDTH/2) +1, 110 + 110, gui.RESOLUTION_WIDTH, 110 + 110);

            g.drawRect(Gui.bSave.getX(), Gui.bSave.getY(), Gui.bSave.getWidth(), Gui.bSave.getHeight());
            if(Gui.bSave.isHover())
            {
                g.setColor(new Color(84, 84, 84, 150));
                g.fillRect(Gui.bSave.getX()+1, Gui.bSave.getY()+1, Gui.bSave.getWidth()-2, Gui.bSave.getHeight()-2);
            }
            else
            {
                g.setColor(Color.WHITE);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN,25));
            textWidth = g.getFontMetrics().stringWidth("Save");
            g.drawString("Save", ((gui.RESOLUTION_WIDTH/4)*3) - textWidth/2,175);

            /**Leaderboards**/

            g.setColor(new Color(43,61,79,150));

            g.fillRect((gui.RESOLUTION_WIDTH/2) +1,230,gui.RESOLUTION_WIDTH, 110 );

            g.setColor(new Color(255, 255, 255, 150));
            g.drawLine((gui.RESOLUTION_WIDTH/2) +1, 230, gui.RESOLUTION_WIDTH, 230);
            g.drawLine((gui.RESOLUTION_WIDTH/2) +1, 230 + 110, gui.RESOLUTION_WIDTH, 230 + 110);

            g.drawRect(Gui.bLeaderboards.getX(), Gui.bLeaderboards.getY(), Gui.bLeaderboards.getWidth(), Gui.bLeaderboards.getHeight());
            if(Gui.bLeaderboards.isHover())
            {
                g.setColor(new Color(84, 84, 84, 150));
                g.fillRect(Gui.bLeaderboards.getX()+1, Gui.bLeaderboards.getY()+1, Gui.bLeaderboards.getWidth()-2, Gui.bLeaderboards.getHeight()-2);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Consolas", Font.PLAIN,18));
                textWidth = g.getFontMetrics().stringWidth("<- click to refresh");
                g.drawString("<- click to refresh", gui.RESOLUTION_WIDTH - textWidth - 25,175+120);

                //DRAW LEADERBOARDS
                g.setFont(new Font("Arial", Font.PLAIN,20));

                g.setColor(Color.YELLOW);
                textWidth = g.getFontMetrics().stringWidth(CookieClickerGame.leaderboardNames[0]+": "+df.format(CookieClickerGame.leaderboardScores[0])+" Cookies");
                g.drawString(CookieClickerGame.leaderboardNames[0]+": "+df.format(CookieClickerGame.leaderboardScores[0])+" Cookies", ((gui.RESOLUTION_WIDTH/4)*3) - textWidth/2,450);

                g.setColor(Color.GRAY);
                textWidth = g.getFontMetrics().stringWidth(CookieClickerGame.leaderboardNames[1]+": "+df.format(CookieClickerGame.leaderboardScores[1])+" Cookies");
                g.drawString(CookieClickerGame.leaderboardNames[1]+": "+df.format(CookieClickerGame.leaderboardScores[1])+" Cookies", ((gui.RESOLUTION_WIDTH/4)*3) - textWidth/2,500);

                g.setColor(new Color(150, 75, 0));
                textWidth = g.getFontMetrics().stringWidth(CookieClickerGame.leaderboardNames[2]+": "+df.format(CookieClickerGame.leaderboardScores[2])+" Cookies");
                g.drawString(CookieClickerGame.leaderboardNames[2]+": "+df.format(CookieClickerGame.leaderboardScores[2])+" Cookies", ((gui.RESOLUTION_WIDTH/4)*3) - textWidth/2,550);
            }
            else
            {
                g.setColor(Color.WHITE);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN,25));
            textWidth = g.getFontMetrics().stringWidth("Leaderboards");
            g.drawString("Leaderboards", ((gui.RESOLUTION_WIDTH/4)*3) - textWidth/2,175+120);

//            /**Load**/
//
//            g.setColor(new Color(43,61,79,150));
//
//            g.fillRect((gui.RESOLUTION_WIDTH/2) +1,350,gui.RESOLUTION_WIDTH, 110 );
//
//            g.setColor(new Color(255, 255, 255, 150));
//            g.drawLine((gui.RESOLUTION_WIDTH/2) +1, 350, gui.RESOLUTION_WIDTH, 350);
//            g.drawLine((gui.RESOLUTION_WIDTH/2) +1, 350 + 110, gui.RESOLUTION_WIDTH, 350 + 110);
//
//            g.drawRect(Gui.bLoad.getX(), Gui.bLoad.getY(), Gui.bLoad.getWidth(), Gui.bLoad.getHeight());
//            if(Gui.bLoad.isHover())
//            {
//                g.setColor(new Color(84, 84, 84, 150));
//                g.fillRect(Gui.bLoad.getX()+1, Gui.bLoad.getY()+1, Gui.bLoad.getWidth()-2, Gui.bLoad.getHeight()-2);
//            }
//            else
//            {
//                g.setColor(Color.WHITE);
//            }
//
//            g.setColor(Color.WHITE);
//            g.setFont(new Font("Arial", Font.PLAIN,25));
//            textWidth = g.getFontMetrics().stringWidth("Load");
//            g.drawString("Load", ((gui.RESOLUTION_WIDTH/4)*3) - textWidth/2,175+240);

        }
    }
}