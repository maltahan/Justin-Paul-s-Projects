import colors.*;
import draw.*;
import geometry.*;

class UFO {
    Posn loc;
    IColor COLOR_UFO = new Green();
    
    UFO(Posn loc) {
        this.loc = loc;
    }
    
    boolean draw(Canvas c) {
        return c.drawCircle(this.loc, 10,
                            this.COLOR_UFO)
               &&
               c.drawRect(new Posn(this.loc.x - 30,
                                   this.loc.y - 2),
                                   60, 4,
                                   this.COLOR_UFO);
    }

    boolean nextShot() {
        int nextPossShot = (int) (Math.random() * 30);
        return nextPossShot <= 1;
    }

    void createShot(UFOWorld w) {
        if (this.nextShot()) {
            w.UFOShots = new ConsUFOShots(this.fireshot(w),
                                          w.UFOShots);
        }
    }

    UFOShot fireshot(UFOWorld w) {
        return new UFOShot(new Posn(this.loc.x,
                                    this.loc.y + 12));
    }

    UFO move(int maxWidth, UFOWorld w) {
        this.createShot(w);
        return new UFO(new Posn(this.nextX(maxWidth), 
                                    this.loc.y + 3));
        }
    
    int nextX(int maxWidth) {
        int nextPossX = (this.loc.x + (int) (Math.random() * 11) - 5);
        if (nextPossX < 10) {
            return 10;
        } else if (nextPossX > maxWidth - 10) {
            return maxWidth - 10;
        } else {
            return nextPossX;
        }
    }
}