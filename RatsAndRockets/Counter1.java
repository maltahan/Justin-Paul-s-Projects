import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Color;
import java.awt.Graphics;

/**
 * Counter that displays a number with a text prefix.
 * 
 * @author Michael Kolling
 * @version 1.0.2
 */
public class Counter1 extends Actor
{
    private static final Color textColor = new Color(224, 177, 14);
    
    private int value = 90;
    private int target = 0;
    private String text;
    private int stringLength;

    public Counter1()
    {
        this("");
    }

    public Counter1(String prefix)
    {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);
        updateImage();
    }
    
    public void act() {
        this.value = ((Player1) this.getWorld().getObjects(Player1.class).get(0)).cheese; 
        updateImage();
    }

    public void add(int score)
    {
        value = value + score;
    }

    public void subtract(int score)
    {
        value = value - score;
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
        image.drawString(text + value, 1, 12);
    }
}