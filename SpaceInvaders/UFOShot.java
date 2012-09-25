import colors.*;
import draw.*;
import geometry.*;

class UFOShot {
    Posn loc;
    IColor SHOT_COLOR = new Yellow();
    
    UFOShot(Posn loc) {
        this.loc = loc;
    }
    
    UFOShot move() {
        return new UFOShot(new Posn(this.loc.x, 
                                    this.loc.y + 5));
    }
    
    boolean between(int lft, int x, int width) {
        return lft <= x && x <= lft + width;
    }
    
    public boolean hitsAUP(AUP a, UFOWorld w) {
        return this.between(a.loc, this.loc.x, 30)
               &&
               this.between(w.HEIGHT - 10, this.loc.y + 5, 10);
    } 

    public boolean draw(Canvas c) {
        return c.drawRect(this.loc, 2, 5, this.SHOT_COLOR);
    }
}