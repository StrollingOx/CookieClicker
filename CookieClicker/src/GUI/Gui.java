package GUI;

import actions.CookieClickerGame;
import actions.Data;
import actions.MouseHandler;
import draw.*;

import javax.swing.*;

public class Gui
{
    public final int RESOLUTION_WIDTH = 1280;
    public final int RESOLUTION_HEIGHT = 720;
    public static int maxHeight, actualHeight= 0;

    JFrame jf;
    DrawMain d;
    DrawButtons db;
    DrawUpgrades du;
    DrawOptions dop;
    DrawAchievements da;
    ImageLoader il = new ImageLoader();

    public static Achievement [] achievement= new Achievement[CookieClickerGame.achievementCount];
    public static AchievementSlider [] achievementSlider= new AchievementSlider[CookieClickerGame.achievementCount];

    public static Button bUpgrades;
    public static Button bAchievements;
    public static Button bOptions;

    public static Button upgClicker;
    public static Button bCookie;

    public static Button bSave, /*bLoad,*/ bLeaderboards;

    public static Button upgButton[] = new Button [CookieClickerGame.upgCount];
    public static Upgrade upgrade[] = new Upgrade [CookieClickerGame.upgCount];

    public void createGui()
    {
        /**Init buttons + set their position**/
        bUpgrades = new Button(RESOLUTION_WIDTH/2, 0,146 + 50, 100); bUpgrades.active = true;
        bAchievements = new Button(RESOLUTION_WIDTH/2 + (146 + 50), 0,(196 + 23 + 50), 100);
        bOptions = new Button(RESOLUTION_WIDTH - 175, 0,175, 100);

        bCookie = new Button((RESOLUTION_WIDTH/4)-96, 220, 192, 192);
        upgClicker = new Button((RESOLUTION_WIDTH/4)-75, 500, 150, 50);

        //Options buttons
        bSave = new Button(((RESOLUTION_WIDTH/4)*3) - 100, 140,200,50); //Y = Y_COUNTOUR_START + CONTOUR_WIDTH - button's height
        //bLoad = new Button(((RESOLUTION_WIDTH/4)*3) - 100, 140 + 120,200,50);
        //bLeaderboards = new Button(((RESOLUTION_WIDTH/4)*3) - 100, 140 + 120 + 120,200,50);
        bLeaderboards = new Button(((RESOLUTION_WIDTH/4)*3) - 100, 140 + 120,200,50);

        /**Draw upgrades (Cursors, Grannies ect)**/
        for(int i =0; i<upgrade.length;i++)
        {
            upgButton[i] = new Button();
            upgButton[i].y = 150+(i*200)+85;
            upgButton[i].x = (RESOLUTION_WIDTH/2) + (((RESOLUTION_WIDTH/2)/3)*2) + 20;
            upgButton[i].width=170;
            upgButton[i].height=40;
        }

        //TODO: read strings from xml file maybe?
        /** Upgrade list (must have CookieClickerGame.upgCount size**/
        upgrade[0] = new Upgrade( "Cursor", il.imgUpgrades[0],  15, 0.1);
        upgrade[1] = new Upgrade( "Granny", il.imgUpgrades[1],  100, 1.0);
        upgrade[2] = new Upgrade( "Farm", il.imgUpgrades[2],  1100, 8.0);
        upgrade[3] = new Upgrade( "Mine", il.imgUpgrades[3],  12000, 47.0);
        upgrade[4] = new Upgrade( "Factory", il.imgUpgrades[4],  130000, 260.0);

        for(int i=0; i<upgrade.length;i++)
        {
            upgrade[i].setY(Upgrade.setYCoord(i));
        }

        maxHeight = (upgrade.length * (150+50)); //for upgrade scrolling

        /** Draw Achievements* */
        int temp=0;
        int yLayout = 0;
        int xLayout=0;
        for(int i = 0; i<achievement.length; i++)
        {
            achievement[i] = new Achievement((RESOLUTION_WIDTH/2)+ 7 +xLayout, 100 + 7 +yLayout ,il.imgAchievements[i]);

            temp++;
            if(temp % 12 == 0)
            {
                temp = 0;
                yLayout += 52;
            }

            xLayout = temp * 50 + temp*2;
        }

        Data.setAchievementName();
        Data.setAchievementText();

        for(int i=0; i<achievementSlider.length; i++)
        {
            achievementSlider[i] = new AchievementSlider(achievement[i]);
        }

        /** Draw main JFrame **/
        jf = new JFrame("CookieClicker - "+CookieClickerGame.playerName);
        jf.setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.addMouseWheelListener(new MouseHandler());
        jf.setResizable(false);

        db = new DrawButtons();
        db.setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        db.setVisible(true);
        db.requestFocus();
        db.addMouseListener(new MouseHandler());
        db.addMouseMotionListener(new MouseHandler());
        jf.add(db);

        du = new DrawUpgrades();
        du.setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        du.setVisible(true);
        du.requestFocus();
        du.addMouseListener(new MouseHandler());
        du.addMouseMotionListener(new MouseHandler());
        jf.add(du);

        dop = new DrawOptions();
        dop.setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        dop.setVisible(true);
        dop.requestFocus();
        dop.addMouseListener(new MouseHandler());
        dop.addMouseMotionListener(new MouseHandler());
        jf.add(dop);

        da = new DrawAchievements();
        da.setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        da.setVisible(true);
        da.requestFocus();
        da.addMouseListener(new MouseHandler());
        da.addMouseMotionListener(new MouseHandler());
        jf.add(da);

        d = new DrawMain();
        d.setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        d.setVisible(true);
        d.requestFocus();
        d.addMouseListener(new MouseHandler());
        d.addMouseMotionListener(new MouseHandler());
        d.addMouseWheelListener(new MouseHandler());
        jf.add(d);



        jf.setVisible(true);
    }
}
