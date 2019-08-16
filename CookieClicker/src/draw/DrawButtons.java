package draw;

import GUI.Gui;
import GUI.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class DrawButtons extends JLabel
{

    Gui gui = new Gui();
    ImageLoader il = new ImageLoader();

    int textWidth;

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /** Cookie button**/
        if(Gui.bCookie.isActive())
        {
            g.drawImage(il.imgCookie2, Gui.bCookie.getX()+15, Gui.bCookie.getY() + 15, Gui.bCookie.getWidth() -30, Gui.bCookie.getHeight() - 30, null);
        }
        else
        {
            g.drawImage(il.imgCookie1, Gui.bCookie.getX(), Gui.bCookie.getY(), Gui.bCookie.getWidth(), Gui.bCookie.getHeight(), null);
        }

        /** Buttons menu layout **/
        g.setFont(new Font("Arial", Font.BOLD, 32));

        /** Button Upgrades **/
        if(Gui.bUpgrades.isHover())
        {
            g.setColor(new Color(84, 84, 84, 150));
            g.fillRect(Gui.bUpgrades.getX(), Gui.bUpgrades.getY(), Gui.bUpgrades.getWidth(), Gui.bUpgrades.getHeight());
        }
        else
        {
            g.setColor(Color.WHITE);
        }

        if(Gui.bUpgrades.isActive())
        {
            g.setColor(new Color(22, 135, 128));
            g.fillRect(Gui.bUpgrades.getX(), Gui.bUpgrades.getY(), Gui.bUpgrades.getWidth(), Gui.bUpgrades.getHeight());
        }

        g.setColor(Color.WHITE);
        g.drawString("Upgrades", gui.RESOLUTION_WIDTH / 2 + 25, 60);

        /** Button Achievements **/
        if(Gui.bAchievements.isHover())
        {
            g.setColor(new Color(84, 84, 84, 150));
            g.fillRect(Gui.bAchievements.getX(), Gui.bAchievements.getY(), Gui.bAchievements.getWidth(), Gui.bAchievements.getHeight());
        }
        else
        {
            g.setColor(Color.WHITE);
        }

        if(Gui.bAchievements.isActive())
        {
            g.setColor(new Color(22, 135, 128));
            g.fillRect(Gui.bAchievements.getX(), Gui.bAchievements.getY(), Gui.bAchievements.getWidth(), Gui.bAchievements.getHeight());
        }

        g.setColor(Color.WHITE);
        textWidth = g.getFontMetrics().stringWidth("Achievements");
        g.drawString("Achievements", 3 * (gui.RESOLUTION_WIDTH / 4) - (textWidth/2) + 10, 60);

        /** Button Options **/
        if(Gui.bOptions.isHover())
        {
            g.setColor(new Color(84, 84, 84, 150));
            g.fillRect(Gui.bOptions.getX(), Gui.bOptions.getY(), Gui.bOptions.getWidth(), Gui.bOptions.getHeight());
        }
        else
        {
            g.setColor(Color.WHITE);
        }

        if(Gui.bOptions.isActive())
        {
            g.setColor(new Color(22, 135, 128));
            g.fillRect(Gui.bOptions.getX(), Gui.bOptions.getY(), Gui.bOptions.getWidth(), Gui.bOptions.getHeight());
        }

        g.setColor(Color.WHITE);
        textWidth = g.getFontMetrics().stringWidth("Options");
        g.drawString("Options", gui.RESOLUTION_WIDTH - textWidth - 33, 60);

        repaint();
    }


}
