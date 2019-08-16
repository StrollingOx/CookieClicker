package draw;

import actions.CookieClickerGame;
import GUI.Gui;
import GUI.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class DrawAchievements extends JLabel
{

    Gui gui = new Gui();
    ImageLoader il = new ImageLoader();

    int textWidth;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /** draw achievments **/
        if(Gui.bAchievements.isActive())
        {
            for(int i = 0; i< CookieClickerGame.achievementCount; i++)
            {
                g.drawImage(Gui.achievement[i].getImg(), Gui.achievement[i].getX(), Gui.achievement[i].getY(), null);
                if(!Gui.achievement[i].isUnlocked())
                {
                    g.setColor(new Color(0,0,0,150));
                    g.fillRect(Gui.achievement[i].getX(),Gui.achievement[i].getY(),Gui.achievement[i].getWidth(),Gui.achievement[i].getHeight());
                }

                if(Gui.achievement[i].isHover())
                {
                    g.setColor(Color.WHITE);
                    g.drawRect(Gui.achievement[i].getX(),Gui.achievement[i].getY(),Gui.achievement[i].getWidth(),Gui.achievement[i].getHeight()-1);

                    g.setFont(new Font("Arial,", Font.PLAIN, 20));

                    String s = Gui.achievement[i].getName();
                    textWidth = g.getFontMetrics().stringWidth(s);
                    g.drawString(s,(int)(gui.RESOLUTION_WIDTH/2 + gui.RESOLUTION_WIDTH/4 - textWidth/2), 590);

                    s = Gui.achievement[i].getTxt();
                    textWidth = g.getFontMetrics().stringWidth(s);
                    g.drawString(s,(int)(gui.RESOLUTION_WIDTH/2 + gui.RESOLUTION_WIDTH/4 - textWidth/2), 645);

                    g.setColor(new Color(255,255,255,75));
                    g.drawLine((int)(gui.RESOLUTION_WIDTH/2 + gui.RESOLUTION_WIDTH/4 - textWidth/4),610,(int)(gui.RESOLUTION_WIDTH/2 + gui.RESOLUTION_WIDTH/4 + textWidth/4), 610);
                }
            }
            g.setColor(new Color(255,255,255,75));
            g.drawLine((gui.RESOLUTION_WIDTH/2 +1),550, gui.RESOLUTION_WIDTH, 550);
        }

        /** draw slider **/
        for(int i=0; i< Gui.achievementSlider.length; i++)
        {
            if(Gui.achievementSlider[i].isVisible())
            {
                g.setColor(Color.WHITE);
                g.drawRect(Gui.achievementSlider[i].getX(),Gui.achievementSlider[i].getY(),Gui.achievementSlider[i].getWidth()+30,Gui.achievementSlider[i].getHeigth());
                g.drawImage(Gui.achievementSlider[i].getImg(), Gui.achievementSlider[i].getX()+5, Gui.achievementSlider[i].getY()+5, null);
                g.setFont(new Font("Arial", Font.PLAIN, 11));
                g.drawString(Gui.achievementSlider[i].getTxt(), Gui.achievementSlider[i].getX()+60, Gui.achievementSlider[i].getY()+30);
            }
        }
    }
}
