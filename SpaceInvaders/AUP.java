import colors.*;
import draw.*;
import geometry.*;

class AUP {
    int loc; // x-coordinate of AUP
    IColor AUP_COLOR = new Red();
    
    AUP(int loc) {
        this.loc = loc;
    }
    
    Shot fireShot(UFOWorld w) {
        return new Shot(new Posn(this.loc + 14, 
                                 w.HEIGHT - 20));
    }
    
    public AUP move(String key) {
        if (key.equals("left")) {
            return new AUP(this.loc - 3);
        } else if (key.equals("right")) {
            return new AUP(this.loc + 3);
        } else {
            return this;
        }
    }
    
    public boolean draw(Canvas c, int yLoc) {
        return c.drawRect(new Posn(this.loc, 
                                   yLoc), 
                                   30, 10, 
                                   this.AUP_COLOR)
               &&
               c.drawRect(new Posn(this.loc + 10, 
                                   yLoc - 5), 
                                   10, 5, 
                                   this.AUP_COLOR);
    }
}