package draw;

import actions.CookieClickerGame;
import GUI.Gui;
import GUI.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DrawMain extends JLabel
{
    ImageLoader il = new ImageLoader();
    Gui gui = new Gui();

    DecimalFormat df = new DecimalFormat("###,###,##0");
    DecimalFormat df2 = new DecimalFormat("###,###,##0.0");
    String s;
    int textWidth;

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /** Background **/
        g.drawImage(il.imgBackground, 0, 0, null);

        /** Top pane **/
        g.setColor(new Color(43, 51, 79, 100));
        g.fillRect(0,0, gui.RESOLUTION_WIDTH, 100);

        /** Split lines **/
        g.setColor(Color.WHITE);
        g.drawLine(gui.RESOLUTION_WIDTH/2, 100, gui.RESOLUTION_WIDTH/2, gui.RESOLUTION_HEIGHT);
        g.drawLine(0, 100, gui.RESOLUTION_WIDTH, 100);

        /**Cookie count txt**/
        g.setColor(Color.ORANGE);
        g.setFont( new Font("Arial", Font.BOLD, 30));
        s = df.format(CookieClickerGame.cookies) + " Cookies";
        textWidth = g.getFontMetrics().stringWidth(s);
        g.drawString(s, gui.RESOLUTION_WIDTH/4 - textWidth/2, 62);

        /**Lifetime cookies**/
        g.setColor(Color.GRAY);
        g.setFont( new Font("Arial", Font.BOLD, 10));
        s = df.format(CookieClickerGame.lifetimeCookies) + " lifetime Cookies";
        textWidth = g.getFontMetrics().stringWidth(s);
        g.drawString(s, gui.RESOLUTION_WIDTH/4 - textWidth/2, 22);

        /**Cookie per second**/
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        s = df2.format(CookieClickerGame.cPS) + " CPS";
        g.drawString(s, 10, 88);



        repaint();
    }
}
