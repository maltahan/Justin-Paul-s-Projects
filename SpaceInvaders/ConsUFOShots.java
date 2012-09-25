import draw.*;
import geometry.*;

class ConsUFOShots implements IUFOShots {
    UFOShot first;
    IUFOShots rest;
    
    ConsUFOShots(UFOShot first, IUFOShots rest) {
        this.first = first;
        this.rest = rest;
    }
    
    boolean belowHeight(UFOWorld w) {
        return this.first.loc.y > w.HEIGHT;
    }
    
    public IUFOShots move(UFOWorld w) {
        if (this.belowHeight(w)) {
            return this.rest.move(w);
        } else {
            return new ConsUFOShots(this.first.move(), 
                                    this.rest.move(w));
        }
    }
    
    public boolean draw(Canvas c) {
        return this.first.draw(c)
               &&
               this.rest.draw(c);
    }
    
    public boolean hitsAUP(AUP a, UFOWorld w) {
        return this.first.hitsAUP(a, w) 
               || 
               this.rest.hitsAUP(a, w);
    }
}