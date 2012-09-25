import greenfoot.*;
public class Rocket1 extends Actor {
    String facing;
    int x;
    int y;
    
    Rocket1(String facing, int x, int y){
        this.facing = facing;
        this.x = x;
        this.y = y;
    }
    
    public void act(){
         if (this.facing == "upright") {
            this.setImage("images/vroom vroom rocket/rocketUPright.png");
            this.y = this.y - 3;
            this.x = this.x + 3;
            
        }
        if (this.facing == "upleft") {
            this.setImage("images/vroom vroom rocket/rocketUPleft.png");
            this.y = this.y - 3;
            this.x = this.x - 3;
            
        }
        if (this.facing == "downright") {
            this.setImage("images/vroom vroom rocket/rocketDOWNright.png");
            this.y = this.y + 3;
            this.x = this.x + 3;
            
        }
        if (this.facing == "downleft") {
            this.setImage("images/vroom vroom rocket/rocketDOWNleft.png");
            this.y = this.y + 3;
            this.x = this.x - 3;
            
        }
        if (this.facing == "up") {
            this.setImage("images/vroom vroom rocket/rocketUP.png");
            this.y = this.y - 3;
            
        }
        
        if (this.facing == "down"){ 
            this.setImage("images/vroom vroom rocket/rocketDOWN.png");
            this.y = this.y + 3; 
            
        }
        
        if (this.facing == "left") {
            this.setImage("images/vroom vroom rocket/rocketLEFT.png");
            this.x = this.x - 3;
            
        }
        
        if (this.facing == "right") {
            this.setImage("images/vroom vroom rocket/rocketRIGHT.png");
            this.x = this.x + 3;
            
        }
        
        if (this.getX() == 0 || this.getX() == 49 ||
           this.getY() == 0 || this.getY() == 49) {
               this.getWorld().removeObject(this);
               return;
        }
        
        if (this.intersects((Player2) this.getWorld().getObjects(Player2.class).get(0))) {
            this.hit((Player2) this.getWorld().getObjects(Player2.class).get(0));
            return;
        }
        
        this.setLocation(this.x, this.y);
    }
    
    public void hit(Player2 p) {
        p.loseCheese();
        this.getWorld().removeObject(this);
    }
}
    