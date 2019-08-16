package actions;

import java.awt.event.*;

import GUI.Button;
import GUI.Gui;
import GUI.Upgrade;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener
{

    Gui gui = new Gui();
    MouseCollision mc = new MouseCollision();

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        /** Upgrades button **/
        if(mc.inside(e.getX(), e.getY(), Gui.bUpgrades))
        {
            Button.setActive(Gui.bUpgrades);
        }

        /** Achievements button **/
        if(mc.inside(e.getX(), e.getY(), Gui.bAchievements))
        {
            Button.setActive(Gui.bAchievements);
        }

        /** Options button **/
        if(mc.inside(e.getX(), e.getY(), Gui.bOptions))
        {
            Button.setActive(Gui.bOptions);
        }

        /** Upgrades button **/
        if(mc.inside(e.getX(), e.getY(), Gui.bLeaderboards) && Gui.bOptions.isActive())
        {
            Data.loadLB(CookieClickerGame.leaderboardNames, CookieClickerGame.leaderboardScores);
        }

        /**Cookie Button**/
        if(mc.inside(e.getX(), e.getY(), Gui.bCookie))
        {
            Gui.bCookie.setActive(true);
            CookieClickerGame.cookies += CookieClickerGame.cPC;
            CookieClickerGame.lifetimeCookies += CookieClickerGame.cPC;
            CookieClickerGame.clickCount ++;
        }

        /** Upgrade (cPS items) buttons**/
        for(int i=0; i<Gui.upgrade.length;i++)
        {
            if(mc.inside(e.getX(),e.getY(),Gui.upgButton[i]))
            {
                if(CookieClickerGame.cookies>= Gui.upgrade[i].getCost())
                {
                    CookieClickerGame.cookies-= Gui.upgrade[i].getCost();
                    int count = Gui.upgrade[i].getCount();
                    Gui.upgrade[i].setCount(count+=1); //increase upgrade count
                    int cst = Gui.upgrade[i].getCost();
                    double newCost = Math.floor(cst += 0.15*cst);
                    Gui.upgrade[i].setCost((int)newCost); //increase upgrade cost
                    Upgrade.cpsUpgrade(i);
                }
            }
        }


        /** Upgrade (cPC) Clicker**/
        if(mc.inside(e.getX(), e.getY(), Gui.upgClicker))
        {
            if(CookieClickerGame.cookies >= CookieClickerGame.upgClickerCost)
            {
                CookieClickerGame.cookies -= CookieClickerGame.upgClickerCost;

                CookieClickerGame.upgClickerCost*=5.0; //increase clicker upgrade cost
                CookieClickerGame.cPC += CookieClickerGame.cPC; //increase coockies per click
            }
        }

        /** Save button **/
        if(mc.inside(e.getX(),e.getY(),Gui.bSave))
        {
            Data.saveData();
        }

//        /** Load button **/
//        if(mc.inside(e.getX(),e.getY(),Gui.bLoad))
//        {
//            Data.loadManual();
//        }
    }


    @Override
    public void mouseReleased(MouseEvent e)
    {
        /**Cookie Button**/
        if(Gui.bCookie.isActive())
        {
            Gui.bCookie.setActive(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        /** Upgrades button **/
        if (mc.inside(e.getX(), e.getY(), Gui.bUpgrades))
        {
            Gui.bUpgrades.setHover(true);
        }
        else
        {
            Gui.bUpgrades.setHover(false);
        }

        /** Achievements button **/
        if (mc.inside(e.getX(), e.getY(), Gui.bAchievements))
        {
            Gui.bAchievements.setHover(true);
        }
        else
        {
            Gui.bAchievements.setHover(false);
        }

        /** Options button **/
        if (mc.inside(e.getX(), e.getY(), Gui.bOptions))
        {
            Gui.bOptions.setHover(true);
        }
        else
        {
            Gui.bOptions.setHover(false);
        }

        /** Leaderboards button **/
        if (mc.inside(e.getX(), e.getY(), Gui.bLeaderboards))
        {
            Gui.bLeaderboards.setHover(true);
        }
        else
        {
            Gui.bLeaderboards.setHover(false);
        }

        /** Upgrades button **/
        for(int i = 0; i< Gui.upgButton.length; i++)
        {
            if(mc.inside(e.getX(),e.getY(), Gui.upgButton[i]))
            {
                Gui.upgButton[i].setHover(true);
            }
            else
            {
                Gui.upgButton[i].setHover(false);
            }
        }

        /** Upgrade Clicker**/
        if(mc.inside(e.getX(), e.getY(), Gui.upgClicker))
        {
            Gui.upgClicker.setHover(true);
        }
        else
        {
            Gui.upgClicker.setHover(false);
        }

        /** Save button **/
        if(mc.inside(e.getX(), e.getY(), Gui.bSave))
        {
            Gui.bSave.setHover(true);
        }
        else
        {
            Gui.bSave.setHover(false);
        }

//        /** Load button **/
//        if(mc.inside(e.getX(), e.getY(), Gui.bLoad))
//        {
//            Gui.bLoad.setHover(true);
//        }
//        else
//        {
//            Gui.bLoad.setHover(false);
//        }

        /** Leaderboards button **/
        if(mc.inside(e.getX(), e.getY(), Gui.bLeaderboards))
        {
            Gui.bLeaderboards.setHover(true);
        }
        else
        {
            Gui.bLeaderboards.setHover(false);
        }

        /** Achievements **/
        for(int i = 0; i< CookieClickerGame.achievementCount; i++)
        {
            if(mc.inside(e.getX(), e.getY(), Gui.achievement[i]))
            {
                Gui.achievement[i].setHover(true);
            }
            else {
                Gui.achievement[i].setHover(false);
            }
        }

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if(Gui.bUpgrades.isActive())
        {
            if(e.getX()>gui.RESOLUTION_WIDTH/2)
            {
                /** Scroll up**/
                if(e.getWheelRotation()==1)
                {
                    Gui.actualHeight-=20;

                    if(Gui.actualHeight>= -(Gui.maxHeight - 550))
                    {
                        for(int i = 0; i< Gui.upgrade.length; i++)
                        {
                            int y1 = Gui.upgrade[i].getY() - 20;
                            int y2 = Gui.upgButton[i].getY() -20;

                            Gui.upgrade[i].setY(y1);
                            Gui.upgButton[i].setY(y2);
                        }
                    }
                    else
                    {
                        Gui.actualHeight = -(Gui.maxHeight - 550);
                    }
                }

                /** Scroll down**/
                if(e.getWheelRotation()==-1)
                {
                    Gui.actualHeight+=20;

                    if(Gui.actualHeight<=0)
                    {
                        for(int i = 0; i< Gui.upgrade.length; i++)
                        {
                            int y1 = Gui.upgrade[i].getY() + 20;
                            int y2 = Gui.upgButton[i].getY() + 20;

                            Gui.upgrade[i].setY(y1);
                            Gui.upgButton[i].setY(y2);
                        }
                    }
                    else
                    {
                        Gui.actualHeight=0;
                    }
                }

            }
        }
    }
}
