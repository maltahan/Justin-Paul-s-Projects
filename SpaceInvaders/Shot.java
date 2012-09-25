import colors.*;
import draw.*;
import geometry.*;

class Shot {
    Posn loc;
    IColor SHOT_COLOR = new Yellow();
    
    Shot(Posn loc) {
        this.loc = loc;
    }
    
    Shot move() {
        return new Shot(new Posn(this.loc.x, 
                                 this.loc.y - 5));
    }
    
    boolean hitsUFO(UFO u) {
        return this.distanceTo(new Posn(u.loc.x, 
                                        u.loc.y)) <= 10;
    }
      
    double distanceTo(Posn p) {
        return Math.sqrt((this.loc.x - p.x) * (this.loc.x - p.x) + 
                         (this.loc.y - p.y) * (this.loc.y - p.y));
    }    
    
    public boolean draw(Canvas c) {
        return c.drawRect(this.loc, 
                          2, 5, 
                          this.SHOT_COLOR);
    }    
}