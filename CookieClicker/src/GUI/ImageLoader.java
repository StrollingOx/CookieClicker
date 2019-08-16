package GUI;

import actions.CookieClickerGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader
{
    public BufferedImage imgBackground;
    public BufferedImage imgUpgrades[] = new BufferedImage[CookieClickerGame.upgCount];
    public BufferedImage imgCookie1, imgCookie2;
    public BufferedImage[] imgAchievements = new BufferedImage[CookieClickerGame.achievementCount];

    public ImageLoader()
    {
        try {
            /** load background **/
            imgBackground = ImageIO.read(new File("rsc/drawable/background.jpg")); //TODO: add .png file

            /** load cookie! **/
            imgCookie1 = ImageIO.read(new File("rsc/drawable/cookie1.png"));
            imgCookie2 = ImageIO.read(new File("rsc/drawable/cookie2.png"));

            /** load achievements **/
            for(int i = 0; i < imgAchievements.length; i++)
            {
                imgAchievements[i] = ImageIO.read(new File("rsc/drawable/achiv"+(i+1)+".png"));
            }

            /** load upgrade's images **/
            for(int i=0;i<imgUpgrades.length;i++)
            {
                imgUpgrades[i] = ImageIO.read(new File("rsc/drawable/up" + (i+1) + ".png"));
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
