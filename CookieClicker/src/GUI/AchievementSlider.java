package GUI;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class AchievementSlider
{
    Gui gui = new Gui();
    int X = 0, y;
    int width = 200;
    int heigth = 50;
    boolean visible = false;
    BufferedImage img;
    String txt;

    public AchievementSlider(Achievement achievement)
    {
        this.y = gui.RESOLUTION_HEIGHT;
        this.img = achievement.getImg();
        this.txt = achievement.getTxt();
    }

    public static void slideIn(AchievementSlider achievementSlider)
    {
        Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask()
        {
            int temp =0;
            @Override
            public void run()
            {
                if(temp<88)
                {
                    achievementSlider.y -=1;
                    temp++;
                }
                else
                {
                    slideOut(achievementSlider);
                    try {time.cancel();} catch (Exception e){ e.printStackTrace();}
                }
            }
        },0, 60);
    }

    public static void slideOut(AchievementSlider achievementSlider)
    {
        Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask()
        {
            int temp = 88;
            @Override
            public void run()
            {
                if(temp>0)
                {
                    achievementSlider.y +=1;
                    temp--;
                }
                else
                {
                    achievementSlider.setVisible(false);
                    try {time.cancel();} catch (Exception e){ e.printStackTrace();}
                }
            }
        },2000, 60);
    }
    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
