package GUI;

import actions.CookieClickerGame;

import java.awt.image.BufferedImage;

public class Upgrade
{
    Gui gui = new Gui();

    int x,y;
    int width = gui.RESOLUTION_WIDTH/2;
    int height = 150;
    String name;
    BufferedImage image;
    int count;
    int cost;
    double cPS;


    public Upgrade(String name, BufferedImage image, int cost, double cPS) {
        this.x = gui.RESOLUTION_WIDTH/2;
        this.name = name;
        this.image = image;
        this.count = 0;
        this.cost = cost;
        this.cPS = cPS;
    }

    /** Function returns Y position for new upgrades (Beacuse Upgrade layout is scrollable)**/
    public static int setYCoord(int index)
    {
        return 150 + index * 200;
    }

    /** This method adds add cPS points per upgrade (many cases for future development) **/
    public static void cpsUpgrade(int index)
    {
        switch(index)
        {
            case 0:
                CookieClickerGame.cPS +=0.1;
                break;
            case 1:
                CookieClickerGame.cPS+=1.0;
                break;
            case 2:
                CookieClickerGame.cPS+=8.0;
                break;
            case 3:
                CookieClickerGame.cPS+=47.0;
                break;
            case 4:
                CookieClickerGame.cPS+=260.0;
                break;
            case 5:
                CookieClickerGame.cPS+=1400.0;
                break;
            case 6:
                CookieClickerGame.cPS+=7800.0;
                break;
            case 7:
                CookieClickerGame.cPS+=44000.0;
                break;
            case 8:
                CookieClickerGame.cPS+=260000.0;
                break;
            case 9:
                CookieClickerGame.cPS+=1600000.0;
                break;
            case 10:
                CookieClickerGame.cPS+=10000000.0;
                break;
            case 11:
                CookieClickerGame.cPS+=65000000.0;
                break;
            case 12:
                CookieClickerGame.cPS+=430000000.0;
                break;
            case 13:
                CookieClickerGame.cPS+=2900000000.0;
                break;
            case 14:
                CookieClickerGame.cPS+=21000000000.0;
                break;
            case 15:
                CookieClickerGame.cPS+=150000000000.0;
                break;
        }
    }

    /** Setters and Getters **/
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getcPS() {
        return cPS;
    }

    public void setcPS(double cPS) {
        this.cPS = cPS;
    }
}
