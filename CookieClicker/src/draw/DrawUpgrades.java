package draw;

import actions.CookieClickerGame;
import GUI.Gui;
import GUI.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DrawUpgrades extends JLabel
{
    Gui gui = new Gui();
    ImageLoader il = new ImageLoader();
    int textWidth;
    DecimalFormat df = new DecimalFormat("###,###,###");
    DecimalFormat df2 = new DecimalFormat("###,###,###.0");

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /** upgrades layout **/
        if(Gui.bUpgrades.isActive())
        {
            for(int i = 0; i< Gui.upgrade.length; i++)
            {
                if(Gui.upgrade[i].getY()>100)
                {

                    g.setColor(new Color(43, 61, 79, 150));
                    //g.drawImage(img.png, Gui.upgrade[i].getX() + 1, Gui.upgrade[i].getY(),null);
                    g.fillRect(Gui.upgrade[i].getX() + 1, Gui.upgrade[i].getY(), Gui.upgrade[i].getWidth(), Gui.upgrade[i].getHeight());

                    g.setColor(new Color(255, 255, 255, 150));
                    g.drawLine((gui.RESOLUTION_WIDTH / 2) + 1, Gui.upgrade[i].getY(), gui.RESOLUTION_WIDTH, Gui.upgrade[i].getY());
                    g.drawLine((gui.RESOLUTION_WIDTH / 2) + 1, Gui.upgrade[i].getY() + 151, gui.RESOLUTION_WIDTH, Gui.upgrade[i].getY() + 151);

                    g.drawLine((gui.RESOLUTION_WIDTH / 2) + ((gui.RESOLUTION_WIDTH / 2) / 3), Gui.upgrade[i].getY(), (gui.RESOLUTION_WIDTH / 2) + ((gui.RESOLUTION_WIDTH / 2) / 3), Gui.upgrade[i].getY() + 150);
                    g.drawLine((gui.RESOLUTION_WIDTH / 2) + (((gui.RESOLUTION_WIDTH / 2) / 3) * 2), Gui.upgrade[i].getY(), (gui.RESOLUTION_WIDTH / 2) + (((gui.RESOLUTION_WIDTH / 2) / 3) * 2), Gui.upgrade[i].getY() + 150);


                    g.setColor(Color.WHITE);

                    /** Upgrades count **/
                    g.setFont(new Font("Arial", Font.PLAIN, 28));
                    textWidth = g.getFontMetrics().stringWidth(Integer.toString(Gui.upgrade[i].getCount()));
                    g.drawString(Integer.toString(Gui.upgrade[i].getCount()), (gui.RESOLUTION_WIDTH / 2) + (((gui.RESOLUTION_WIDTH / 2) / 3) / 2) - (textWidth / 2), Gui.upgrade[i].getY() + 50);

                    /** Upgrades name **/
                    g.setFont(new Font("Arial", Font.PLAIN, 25));
                    textWidth = g.getFontMetrics().stringWidth(Gui.upgrade[i].getName());
                    g.drawString(Gui.upgrade[i].getName(), (gui.RESOLUTION_WIDTH / 2) + (((gui.RESOLUTION_WIDTH / 2) / 3) / 2) - (textWidth / 2), Gui.upgrade[i].getY() + 90);

                    /** Upgrades cPS **/
                    g.setColor(Color.cyan);
                    g.setFont(new Font("Arial", Font.ITALIC, 18));
                    textWidth = g.getFontMetrics().stringWidth("+ " + Gui.upgrade[i].getcPS() + " cPS");
                    g.drawString("+ " + Gui.upgrade[i].getcPS() + " cPS", (gui.RESOLUTION_WIDTH / 2) + (((gui.RESOLUTION_WIDTH / 2) / 3) / 2) - (textWidth / 2) - 5, Gui.upgrade[i].getY() + 120);
                    g.setColor(Color.WHITE);

                    /** Upgrades img **/
                    g.drawImage(Gui.upgrade[i].getImage(), (gui.RESOLUTION_WIDTH / 2) + ((gui.RESOLUTION_WIDTH / 2) / 3)+1, Gui.upgrade[i].getY()+1, null);

                    /** Upgrades cost **/
                    String s = df.format(Gui.upgrade[i].getCost());
                    g.setFont(new Font("Arial", Font.PLAIN, 18));
                    textWidth = g.getFontMetrics().stringWidth(s + " Cookies");
                    g.drawString(s + " Cookies", (int) ((gui.RESOLUTION_WIDTH / 2) + ((gui.RESOLUTION_WIDTH / 2) * (5.0 / 6.0)) - textWidth / 2), Gui.upgrade[i].getY() + 55);

                    /** Upgrades buttons **/
                    g.setColor(new Color(255, 255, 255, 75));
                    g.drawRect(Gui.upgButton[i].getX(), Gui.upgButton[i].getY(), Gui.upgButton[i].getWidth(), Gui.upgButton[i].getHeight());

                    if (Gui.upgButton[i].isHover()) {
                        g.setColor(new Color(84, 84, 84, 150));
                        g.fillRect(Gui.upgButton[i].getX(), Gui.upgButton[i].getY(), Gui.upgButton[i].getWidth(), Gui.upgButton[i].getHeight());
                    }

                    g.setColor(Color.WHITE);
                    textWidth = g.getFontMetrics().stringWidth("Upgrade");
                    g.drawString("Upgrade", (int) ((gui.RESOLUTION_WIDTH / 2) + ((gui.RESOLUTION_WIDTH / 2) * (5.0 / 6.0)) - textWidth / 2), Gui.upgrade[i].getY() + 113);
                }
            }
        }

        /** Upgrade Clicker **/
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.setColor(new Color(255, 255, 255, 75));
        g.drawRect(Gui.upgClicker.getX(), Gui.upgClicker.getY(), Gui.upgClicker.getWidth(), Gui.upgClicker.getHeight());

        if(Gui.upgClicker.isHover())
        {
            g.setColor(new Color(84, 84, 84, 150));
            g.fillRect(Gui.upgClicker.getX(), Gui.upgClicker.getY(), Gui.upgClicker.getWidth(), Gui.upgClicker.getHeight());
        }
        else
        {
            g.setColor(Color.WHITE);
        }

        g.setColor(Color.WHITE);
        textWidth = g.getFontMetrics().stringWidth("Upgrade");
        g.drawString("Upgrade", (gui.RESOLUTION_WIDTH/4) - (textWidth/2), 533);

        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if(CookieClickerGame.cookies >= CookieClickerGame.upgClickerCost)
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.RED);
        String s = "Cost: "+df.format(CookieClickerGame.upgClickerCost) + " Cookies";
        textWidth = g.getFontMetrics().stringWidth(s);
        g.drawString(s, (gui.RESOLUTION_WIDTH/4) - (textWidth/2), 492);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.ORANGE);
        s = df2.format(CookieClickerGame.cPC)+" Cookies per click";
        textWidth = g.getFontMetrics().stringWidth(s);
        g.drawString(s,gui.RESOLUTION_WIDTH/4 - textWidth/2, 620);

        repaint();
    }
}
