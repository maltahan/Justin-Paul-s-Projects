import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player2Loses here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player2Loses  extends Actor
{
    private int imageStep = 0;
    /**
     * Act - do whatever the Player2Loses wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (this.imageStep == 0) {
                this.setImage("images/Cappy/cry0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/cry0.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/cry1.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/cry1.png");
                imageStep = 0;
            }
    }    
}
