import greenfoot.*;
import java.awt.event.*; // Use events
import java.util.*; // Use util
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player1 extends Actor {
    String facing;
    int speed;
    int cheese;
    int imageStep = 0;
    Rocket1 rocket;
    
    Player1(String facing, int speed, int cheese){
        this.facing = facing;
        this.speed = speed;
        this.cheese = cheese;
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            this.rocketGo();
        } else if (Greenfoot.isKeyDown("up") && Greenfoot.isKeyDown("left")) {
            this.setLocation(this.getX() - 1, this.getY() - 1);
            this.facing = "upleft";
            if (this.imageStep == 0) {
                this.setImage("images/Hamtaro/left0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Hamtaro/left1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Hamtaro/left2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Hamtaro/left3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("up") && Greenfoot.isKeyDown("right")) {
            this.setLocation(this.getX() + 1, this.getY() - 1);
            this.facing = "upright";
            if (this.imageStep == 0) {
                this.setImage("images/Hamtaro/right0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Hamtaro/right1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Hamtaro/right2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Hamtaro/right3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("down") && Greenfoot.isKeyDown("left")) {
            this.setLocation(this.getX() - 1, this.getY() + 1);
            this.facing = "downleft";
            if (this.imageStep == 0) {
                this.setImage("images/Hamtaro/left0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Hamtaro/left1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Hamtaro/left2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Hamtaro/left3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("down") && Greenfoot.isKeyDown("right")) {
            this.setLocation(this.getX() + 1, this.getY() + 1);
            this.facing = "downright";
            if (this.imageStep == 0) {
                this.setImage("images/Hamtaro/right0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Hamtaro/right1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Hamtaro/right2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Hamtaro/right3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("up")) {
            this.setLocation(this.getX(), this.getY() - 1);    
            this.facing = "up";
            if (this.imageStep == 0) {
                this.setImage("images/Hamtaro/up0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Hamtaro/up1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Hamtaro/up2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Hamtaro/up3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("down")) {
            this.setLocation(this.getX(), this.getY() + 1);    
            this.facing = "down";
            if (this.imageStep == 0) {
                this.setImage("images/Hamtaro/down0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Hamtaro/down1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Hamtaro/down2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Hamtaro/down3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("left")) {
            this.setLocation(this.getX() - 1, this.getY());    
            this.facing = "left";  
            if (this.imageStep == 0) {
                this.setImage("images/Hamtaro/left0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Hamtaro/left1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Hamtaro/left2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Hamtaro/left3.png");
                imageStep = 0;
            }
        } else if (Greenfoot.isKeyDown("right")) {
            this.setLocation(this.getX() + 1, this.getY());    
            this.facing = "right";  
            if (this.imageStep == 0) {
                this.setImage("images/Hamtaro/right0.png");
                imageStep++;
            } else if (imageStep == 1) {
                this.setImage("images/Hamtaro/right1.png");
                imageStep++;
            } else if (imageStep == 2) {
                this.setImage("images/Hamtaro/right2.png");
                imageStep++;
            } else if (imageStep == 3) {
                this.setImage("images/Hamtaro/right3.png");
                imageStep = 0;
            }
        }
    }
    
    public void rocketGo() {
        if (this.getWorld().getObjects(Rocket1.class).size() < 4 && facing.equals("up")){
            Rocket1 r = new Rocket1("up", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket1.class).size() < 4 && facing.equals("down")){
            Rocket1 r = new Rocket1("down", this.getX(),this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket1.class).size() < 4 && facing.equals("left")){
            Rocket1 r = new Rocket1("left", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket1.class).size() < 4 && facing.equals("right")){
            Rocket1 r = new Rocket1("right", this.getX(),this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket1.class).size() < 4 && facing.equals("upright")){
            Rocket1 r = new Rocket1("upright", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket1.class).size() < 4 && facing.equals("upleft")){
            Rocket1 r = new Rocket1("upleft", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket1.class).size() < 4 && facing.equals("downright")){
            Rocket1 r = new Rocket1("downright", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
        if (this.getWorld().getObjects(Rocket1.class).size() < 4 && facing.equals("downleft")){
            Rocket1 r = new Rocket1("downleft", this.getX(), this.getY());
            this.getWorld().addObject(r, this.getX(), this.getY());
        }
    }
    
    public void loseCheese(){
        this.cheese = this.cheese - 9;
        this.spreadCheese();// 3 + (int) (Math.random() * 4) for it
        ((Counter1) this.getWorld().getObjects(Counter1.class).get(0)).subtract(5);
    }
    
    public void getCheese(){
        cheese = cheese + 3;
        ((Counter1) this.getWorld().getObjects(Counter1.class).get(0)).add(3);
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