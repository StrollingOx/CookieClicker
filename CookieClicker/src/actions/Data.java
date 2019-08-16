package actions;

import GUI.Gui;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


public class Data {

    File folder = new File("data");
    File file = new File("data/"+CookieClickerGame.playerName+"_save.txt");
    StringBuilder sb = new StringBuilder();

    /** Save player data **/
    public static void saveData()
    {
        Data data = new Data();

        /**Create**/
        if(!data.folder.exists())
        {
            System.out.println("GAME>> Directory not found. Creating directory...");
            data.folder.mkdir();
            System.out.println("GAME>> Directory succesfully created!");
        }
        else
        {
            System.out.println("GAME>> Directory already exists.");
        }

        if(!data.file.exists())
        {
            try {
                data.file.createNewFile();
                System.out.println("GAME>> Save file created!");
            } catch (IOException e) { e.printStackTrace(); }
        }
        else
        {
            System.out.println("GAME>> Save file already exists. Overwriting...");
        }

        /**Save**/
        data.sb.append(CookieClickerGame.lifetimeCookies);
        data.sb.append("\n");
        data.sb.append(CookieClickerGame.cookies);
        data.sb.append("\n");
        data.sb.append(CookieClickerGame.cPS);
        data.sb.append("\n");
        data.sb.append(CookieClickerGame.cPC);
        data.sb.append("\n");
        data.sb.append(CookieClickerGame.upgClickerCost);
        data.sb.append("\n");

        for(int i=0; i< Gui.upgrade.length; i++)
        {
            data.sb.append(Gui.upgrade[i].getCount());
            data.sb.append("\n");
            data.sb.append(Gui.upgrade[i].getCost());
            data.sb.append("\n");
        }

        data.sb.append(CookieClickerGame.clickCount);
        data.sb.append("\n");

        for(int i = 0; i< CookieClickerGame.achievementCount; i++)
        {
            if(Gui.achievement[i].isUnlocked())
            {
                data.sb.append("1");
                data.sb.append("\n");
            }
            else
            {
                data.sb.append("0");
                data.sb.append("\n");
            }
        }

        try {
            OutputStream stream = new FileOutputStream(data.file);
            String s = data.sb.toString();

            stream.write(s.getBytes());
            stream.close();

            System.out.println("GAME>> Game saved!");
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }

//        /**save Leaderboard**/
//        saveLB();
    }

    /** OLD: manually choose savefile**/
    public static void loadManual() {
        JFileChooser chooser = new JFileChooser();

        int temp = chooser.showOpenDialog(null);
        if (temp == JFileChooser.APPROVE_OPTION) {
            String s = chooser.getSelectedFile().getAbsolutePath();
            if (s.endsWith("_save.txt")) {
                File file = new File(s);
                loadData(file);
            } else {
                System.out.println("GAME>> Error occured while loading file!");
            }
        }
    }

    /** Load savefile**/
    public static void loadStart()
    {
        File savefile = new File("data/"+CookieClickerGame.playerName+"_save.txt");
        loadData(savefile);
    }

    /** This is actually loadStart(), but thise method is also used in old 'loadManual'**/
    private static void loadData(File file)
    {
        if (file.exists())
        {
            try {
                Scanner sc = new Scanner(file);

                CookieClickerGame.lifetimeCookies = Double.parseDouble(sc.nextLine());
                CookieClickerGame.cookies = Double.parseDouble(sc.nextLine());
                CookieClickerGame.cPS = Double.parseDouble(sc.nextLine());
                CookieClickerGame.cPC = Double.parseDouble(sc.nextLine());
                CookieClickerGame.upgClickerCost = Integer.parseInt(sc.nextLine());

                for (int i = 0; i < Gui.upgrade.length; i++)
                {
                    Gui.upgrade[i].setCount(Integer.parseInt(sc.nextLine()));
                    Gui.upgrade[i].setCost(Integer.parseInt(sc.nextLine()));
                }

                CookieClickerGame.clickCount = Integer.parseInt(sc.nextLine());

                for (int i = 0; i < CookieClickerGame.achievementCount; i++)
                {
                    if (sc.nextLine().equals("1")) {
                        Gui.achievement[i].setUnlocked(true);
                    } else {
                        Gui.achievement[i].setUnlocked(false);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("GAME>> Savefile not found.");
        }
    }

    /** Set achievement's description**/
    public static void setAchievementText()
    {
        File achText = new File("rsc/values/achievements_description.txt");
        if(achText!=null)
        {
            try
            {
                Scanner sc = new Scanner(achText);
                for(int i = 0; i< CookieClickerGame.achievementCount; i++)
                {
                    Gui.achievement[i].setTxt(sc.nextLine());
                }
            } catch (FileNotFoundException e) { e.printStackTrace(); }
        }
    }

    /** Set achievement's names**/
    public static void setAchievementName()
    {
        File achName = new File("rsc/values/achievements_names.txt");
        if(achName!=null)
        {
            try
            {
                Scanner sc = new Scanner(achName);
                for(int i = 0; i< CookieClickerGame.achievementCount; i++)
                {
                    Gui.achievement[i].setName(sc.nextLine());
                }
            } catch (FileNotFoundException e) { e.printStackTrace(); }
        }
    }

    /** Load leaderboards (from server) **/
    public static void loadLB(String [] s, Double [] d)
    {
        CookieClickerGame.player.clientConnection.getLeaderboardsFromServer(s, d);
    }

//    /** Send your score to server **/
//    public static void saveLB()
//    {
//        String [] s = new String[3];
//        Double [] d = new Double[3];
//        loadLB(s, d);
//        if(CookieClickerGame.lifetimeCookies>d[0])
//        {
//            if(CookieClickerGame.playerName.equals(s[0]))
//            {
//                d[0] = CookieClickerGame.lifetimeCookies;
//                s[0] = CookieClickerGame.playerName;
//            }
//            else
//                {
//                d[2] = d[1];
//                s[2] = s[1];
//                d[1] = d[0];
//                s[1] = s[0];
//                d[0] = CookieClickerGame.lifetimeCookies;
//                s[0] = CookieClickerGame.playerName;
//            }
//        }
//        else if(CookieClickerGame.lifetimeCookies>d[1])
//        {
//            if(CookieClickerGame.playerName.equals(s[1]))
//            {
//                d[1] = CookieClickerGame.lifetimeCookies;
//                s[1] = CookieClickerGame.playerName;
//            }
//            else
//                {
//                d[2] = d[1];
//                s[2] = s[1];
//                d[1] = CookieClickerGame.lifetimeCookies;
//                s[1] = CookieClickerGame.playerName;
//            }
//        }
//        else if(CookieClickerGame.lifetimeCookies>d[2])
//        {
//            d[2] = CookieClickerGame.lifetimeCookies;
//            s[2] = CookieClickerGame.playerName;
//        }
//
//
//        StringBuilder sLB = new StringBuilder();
//        for(int i=0; i<3; i++)
//        {
//            sLB.append(s[i]);
//            sLB.append("\n");
//            sLB.append(round(d[i],2));
//            sLB.append("\n");
//        }
//
//        try {
//            OutputStream stream = new FileOutputStream(new File("data/leaderboards.txt"));
//            String s2 = sLB.toString();
//
//            stream.write(s2.getBytes());
//            stream.close();
//
//        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
//
//
//
//    }
//
//    public static double round(double value, int places)
//    {
//        if (places < 0) throw new IllegalArgumentException();
//
//        BigDecimal bd = new BigDecimal(value);
//        bd = bd.setScale(places, RoundingMode.HALF_UP);
//        return bd.doubleValue();
//    }
}
