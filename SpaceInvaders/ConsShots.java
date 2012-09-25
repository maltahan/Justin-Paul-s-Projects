import draw.*;
import geometry.*;

class ConsShots implements IShots {
    Shot first;
    IShots rest;
    
    ConsShots(Shot first, IShots rest) {
        this.first = first;
        this.rest = rest;
    }
    
    boolean aboveOrigin() {
        return this.first.loc.y < 0;
    }
    
    public IShots move() {
        if (this.aboveOrigin()) {
            return this.rest.move();
        } else {
            return new ConsShots(this.first.move(), this.rest.move());
        }
    }
    
    public boolean draw(Canvas c) {
        return this.first.draw(c)
               &&
               this.rest.draw(c);
    }
    
    public int length() {
        return 1 + this.rest.length();
    }
    
    public boolean hitsUFO(UFO u) {
        return this.first.hitsUFO(u) || this.rest.hitsUFO(u);
    }
}