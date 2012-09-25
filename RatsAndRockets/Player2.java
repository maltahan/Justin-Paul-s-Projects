import greenfoot.*;
import java.awt.event.*; // Use events
import java.util.*; // Use util
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player2 extends Actor {
    String facing;
    int speed;
    int cheese;
    int imageStep = 0;
    Rocket2 rocket;
    
    Player2(String facing, int speed, int cheese){
        this.facing = facing;
        this.speed = speed;
        this.cheese = cheese;
    }
    
   public void act() {
        if (Greenfoot.isKeyDown("space")) {
            this.rocketGo();
        } else if (Greenfoot.isKeyDown("w") && Greenfoot.isKeyDown("a")) {
            this.setLocation(this.getX() - 1, this.getY() - 1);
            this.facing = "upleft";
            if (this.imageStep == 0) {
                this.setImage("images/Cappy/left0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/left0.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/left1.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/left1.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("w") && Greenfoot.isKeyDown("d")) {
            this.setLocation(this.getX() + 1, this.getY() - 1);
            this.facing = "upright";
            if (this.imageStep == 0) {
                this.setImage("images/Cappy/right0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/right0.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/right1.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/right1.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("s") && Greenfoot.isKeyDown("a")) {
            this.setLocation(this.getX() - 1, this.getY() + 1);
            this.facing = "downleft";
            if (this.imageStep == 0) {
                this.setImage("images/Cappy/left0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/left0.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/left1.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/left1.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("s") && Greenfoot.isKeyDown("d")) {
            this.setLocation(this.getX() + 1, this.getY() + 1);
            this.facing = "downright";
            if (this.imageStep == 0) {
                this.setImage("images/Cappy/right0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/right0.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/right1.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/right1.png");
                imageStep = 0;
            }
        
        } else if (Greenfoot.isKeyDown("w")) {
            this.setLocation(this.getX(), this.getY() - 1);    
            this.facing = "up";
            if (this.imageStep == 0) {
                this.setImage("images/Cappy/up0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/up1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/up2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/up3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("s")) {
            this.setLocation(this.getX(), this.getY() + 1);    
            this.facing = "down";
            if (this.imageStep == 0) {
                this.setImage("images/Cappy/down0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/down1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/down2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/down3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("a")) {
            this.setLocation(this.getX() - 1, this.getY());    
            this.facing = "left";    
            if (this.imageStep == 0) {
                this.setImage("images/Cappy/left0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/left0.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/left1.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/left1.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("d")) {
            this.setLocation(this.getX() + 1, this.getY());    
            this.facing = "right";   
            if (this.imageStep == 0) {
                this.setImage("images/Cappy/right0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Cappy/right0.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Cappy/right1.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Cappy/right1.png");
                imageStep = 0;
            }
        }
    }
    
    public void rocketGo() {
        if (this.getWorld().getObjects(Rocket2.class).size() < 4 && facing.equals("up")){
            Rocket2 r = new Rocket2("up", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket2.class).size() < 4 && facing.equals("down")){
            Rocket2 r = new Rocket2("down", this.getX(),this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket2.class).size() < 4 && facing.equals("left")){
            Rocket2 r = new Rocket2("left", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket2.class).size() < 4 && facing.equals("right")){
            Rocket2 r = new Rocket2("right", this.getX(),this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket2.class).size() < 4 && facing.equals("upright")){
            Rocket2 r = new Rocket2("upright", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket2.class).size() < 4 && facing.equals("upleft")){
            Rocket2 r = new Rocket2("upleft", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket2.class).size() < 4 && facing.equals("downright")){
            Rocket2 r = new Rocket2("downright", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket2.class).size() < 4 && facing.equals("downleft")){
            Rocket2 r = new Rocket2("downleft", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
    }
    
    public void loseCheese(){
        this.cheese = this.cheese - 9;
        this.spreadCheese();// 3 + (int) (Math.random() * 4) for it
          ((Counter2) this.getWorld().getObjects(Counter2.class).get(0)).subtract(5);
    }
    
    public void getCheese(){
        cheese = cheese + 3;
    }
    
    public void spreadCheese() {//int x for it
        Cheese c1 = new Cheese(1);
        this.getWorld().addObject(c1,
                                  this.getX() + (int) (25 * (Math.random() * 2 - 1)),
                                  this.getY() + (int) (25 * (Math.random() * 2 - 1)));
        Cheese c2 = new Cheese(1);
        this.getWorld().addObject(c2,
                                  this.getX() + (int) (25 * (Math.random() * 2 - 1)),
                                  this.getY() + (int) (25 * (Math.random() * 2 - 1)));
        Cheese c3 = new Cheese(1);
        this.getWorld().addObject(c3,
                                  this.getX() + (int) (25 * (Math.random() * 2 - 1)),
                                  this.getY() + (int) (25 * (Math.random() * 2 - 1)));
        
    }
}