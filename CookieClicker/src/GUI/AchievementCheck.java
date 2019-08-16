package GUI;

import actions.CookieClickerGame;

import java.util.Timer;
import java.util.TimerTask;

public class AchievementCheck
{
    Timer time;

    public AchievementCheck()
    {
        time = new Timer();
        time.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                /** Achievement 1**/
                /*
                 * Get ur first cookie :).
                 */
                if(!Gui.achievement[0].isUnlocked())
                {
                    if(CookieClickerGame.cookies >= 1)
                    {
                        unlock(Gui.achievement[0], Gui.achievementSlider[0]);
                    }
                }

                /** Achievement 2**/
                /*
                 * Unlocked by HAVING 10 000 cookies.
                 */
                if(!Gui.achievement[1].isUnlocked())
                {
                    if(CookieClickerGame.cookies >= 50000)
                    {
                        unlock(Gui.achievement[1], Gui.achievementSlider[1]);
                    }
                }

                /** Achievement 3**/
                /*
                 * Unlocked by HAVING 100 000 cookies.
                 */
                if(!Gui.achievement[2].isUnlocked())
                {
                    if(CookieClickerGame.cookies >= 100000)
                    {
                        unlock(Gui.achievement[2], Gui.achievementSlider[2]);
                    }
                }

                /** Achievement 4**/
                /*
                 * Unlocked by HAVING 1 000 000 cookies (lifetime).
                 */
                if(!Gui.achievement[3].isUnlocked())
                {
                    if(CookieClickerGame.lifetimeCookies >= 1000000)
                    {
                        unlock(Gui.achievement[3], Gui.achievementSlider[3]);
                    }
                }

                /** Achievement 5**/
                /*
                 * Unlocked by HAVING 2 500 000 cookies (lifetime).
                 */
                if(!Gui.achievement[4].isUnlocked())
                {
                    if(CookieClickerGame.lifetimeCookies >= 2500000)
                    {
                        unlock(Gui.achievement[4], Gui.achievementSlider[4]);
                    }
                }

                /** Achievement 6**/
                /*
                 * Unlocked by HAVING 10 000 000 cookies (lifetime).
                 */
                if(!Gui.achievement[5].isUnlocked())
                {
                    if(CookieClickerGame.cookies >= 10000000)
                    {
                        unlock(Gui.achievement[5], Gui.achievementSlider[5]);
                    }
                }

                /** Achievement 7**/
                /*
                 * Unlocked by clicking on cookie for 666 times.
                 */
                if(!Gui.achievement[6].isUnlocked())
                {
                    if(CookieClickerGame.clickCount >= 666)
                    {
                        unlock(Gui.achievement[6], Gui.achievementSlider[6]);
                    }
                }

                /** Achievement 8**/
                /*
                 * Unlocked by clicking on cookie for 1000 times.
                 */
                if(!Gui.achievement[7].isUnlocked())
                {
                    if(CookieClickerGame.cookies >= 1000)
                    {
                        unlock(Gui.achievement[7], Gui.achievementSlider[7]);
                    }
                }

                /** Achievement 9**/
                /*
                 * Unlocked by having over 500 CookiesPerSecond.
                 */
                if(!Gui.achievement[8].isUnlocked())
                {
                    if(CookieClickerGame.cPS >= 2500)
                    {
                        unlock(Gui.achievement[8], Gui.achievementSlider[8]);
                    }
                }

                /** Achievement 10**/
                /*
                 * Unlocked by having over 100000 CookiesPerSecond.
                 */
                if(!Gui.achievement[9].isUnlocked())
                {
                    if(CookieClickerGame.cookies >= 8000)
                    {
                        unlock(Gui.achievement[9], Gui.achievementSlider[9]);
                    }
                }
            }
        }, 1000, 1000);
    }

    public static void unlock(Achievement achievement, AchievementSlider slider)
    {
        achievement.setUnlocked(true);
        slider.setVisible(true);
        AchievementSlider.slideIn(slider);
    }
}
