import greenfoot.*;
public class Cheese extends Actor {
    int num;
    
    Cheese(int num){
        this.num = num;
    }
    
    public void act() {
        if (this.intersects((Player1) this.getWorld().getObjects(Player1.class).get(0))) {
            ((Player1) this.getWorld().getObjects(Player1.class).get(0)).getCheese();
            this.getWorld().removeObject(this);
            return;
        } else if (this.intersects((Player2) this.getWorld().getObjects(Player2.class).get(0))) {
            ((Player2) this.getWorld().getObjects(Player2.class).get(0)).getCheese();
            this.getWorld().removeObject(this);
            return;
        }
    }   
}