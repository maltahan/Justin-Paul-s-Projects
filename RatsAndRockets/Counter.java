import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Color;
import java.awt.Graphics;

/**
 * Counter that displays a number with a text prefix.
 * 
 * @author Michael Kolling
 * @version 1.0.2
 */
public class Counter extends Actor
{
    private static final Color textColor = new Color(224, 177, 14);
    
    private int value = 200;
    private int target = 0;
    private String text;
    private int stringLength;

    public Counter()
    {
        this("");
    }

    public Counter(String prefix)
    {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);
        updateImage();
    }
    
    public void act() {
        if (value > target) {
            value--;
            updateImage();
        } else if (value <= target && 
                   ((Player1) this.getWorld().getObjects(Player1.class).get(0)).cheese >
                   ((Player2) this.getWorld().getObjects(Player2.class).get(0)).cheese) {
                       ((Player2) this.getWorld().getObjects(Player2.class).get(0)).cheese = 0;
        } else if (value <= target && 
                   ((Player1) this.getWorld().getObjects(Player1.class).get(0)).cheese <
                   ((Player2) this.getWorld().getObjects(Player2.class).get(0)).cheese) {
                       ((Player1) this.getWorld().getObjects(Player1.class).get(0)).cheese = 0;
        }
    }

    public void add(int score)
    {
        target += score;
    }

    public void subtract(int score)
    {
        target -= score;
    }

    public int getValue()
    {
        return value;
    }

    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        if (value > target){
            image.drawString(text + value, 1, 12);
        } else {
            image.drawString("Overtime", 1, 12);
        }
    }
}