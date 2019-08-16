package actions;

import GUI.AchievementCheck;
import GUI.Gui;
import client.Client;

public class CookieClickerGame
{
    public static String playerName;
    public static Client player;

    public static double lifetimeCookies = 0.0;
    public static double cookies = 0.0;
    public static double cPS = 0.0; //cookies per second
    public static double cPC = 1.0; //cookies per click
    public static int upgCount = 5; //upgrade count
    public static int achievementCount = 10;
    public static int clickCount = 0;
    public static int upgClickerCost = 100;

    public static String [] leaderboardNames = new String[]{"Please refresh!", "Please refresh!", "Please refresh!"};
    public static Double [] leaderboardScores = new Double[]{0.0,0.0,0.0};

    public static void main(String [] args)
    {
        playerName = args[0];
        Gui g = new Gui();
        g.createGui();
        new CookieCounter();
        new AchievementCheck();
        Data.loadStart();
    }

    public void getClient(Client c)
    {
        this.player = c;
    }

    public void close()
    {
        System.exit(1);
    }

    /** FURTHER DEVELOPMENT **/
    //TODO: Make some 'nicer' graphic representation of upgrades owned
    //TODO: Add Achievements rewards
    //TODO: Add "reset"
    //TODO: Add "new game +"
    //TODO: Add descriptions
    //TODO: Add more 'data'
    //TODO: Stacking achievemnts slider with 'X' to close them
    //TODO: Falling cookies!
    //TODO: Golden cookies!
    //TODO: Cookie boosters!
    //TODO: Help button in OPTIONS

    /** GAME GOALS **/
    //(CHECK ✓) gra zaklada nieskonczona liczbe graczy lub gdy ze wzgledu na reguly to nie ma sensu, gre w wielu pokojach rownoczesnie (CHECK!)
    //(CHECK ✓) gra zaklada przynajmniej dwie równolegle formy komunikacji (np. gra wlasciwa plus chat)
    //(CHECK ✓) komunikacja pomiedzy klientami (graczami) odbywa sie zawsze za posrednictwem serwera
    //(CHECK ✓) jakas czesc projektu musi byc napisana w jezyku Java i bazować na polaczeniach TCP/IP (sockets)
    //(CHECK ✓) serwer jest odporny na nieprawidlowe polecenia przesylane przez klienta
    //(CHECK ✓) konfiguracja serwera w pliku XML.
    //(CHECK ✓) nalezy dostarczyc takze zbior testow jednostkowych (junit w najnowszej wersji, dodatkowy plus za selenium - gdyby projekt mial TAKZE warstwe WWW)
    //TODO:    należy przygotowac dokumentacje uwzględniajaca wszystkie w.w. aspekty.
    //(CHECK ✓) lista gier która NA PEWNO nie moze zostac wykonana w ramach zaliczenia projektu (nie jest to lista zamknieta - do ustalenia z prowadzacym), oczekuje sie projektow bardziej ambitnych: ping-pong, warcaby, szachy, statki, wisielec, wszystkie gry karciane
}
