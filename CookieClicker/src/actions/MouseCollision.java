package actions;

import GUI.Achievement;
import GUI.Button;

public class MouseCollision
{
    int mouseX, mouseY, x, y, width, height;

    /* True - if the mouse is inside button frame
    * False - if it is not :) */
    public boolean inside(int mouseX, int mouseY, Button btn)
    {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.x = btn.getX();
        this.y = btn.getY();
        this.height = btn.getHeight();
        this.width = btn.getWidth();

        if(     this.mouseX >= this.x &&
                this.mouseX <= this.x + this.width &&
                this.mouseY > this.y &&
                this.mouseY <= this.y + this.height)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean inside(int mouseX, int mouseY, Achievement achievement)
    {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.x = achievement.getX();
        this.y = achievement.getY();
        this.height = achievement.getHeight();
        this.width = achievement.getWidth();

        if(     this.mouseX >= this.x &&
                this.mouseX <= this.x + this.width &&
                this.mouseY > this.y &&
                this.mouseY <= this.y + this.height)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
