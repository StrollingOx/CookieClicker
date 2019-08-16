package actions;

import java.util.Timer;
import java.util.TimerTask;

public class CookieCounter
{
    Timer count;

    /** Every (0.001 second) add (0.001x total cPS) to cookies and lifetimeCookies ***/
    public CookieCounter()
    {
        count = new Timer();
        count.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                CookieClickerGame.cookies += CookieClickerGame.cPS/1000;
                CookieClickerGame.lifetimeCookies += CookieClickerGame.cPS/1000;
            }
        },0,1);
    }
}
