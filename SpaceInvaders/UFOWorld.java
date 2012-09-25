import colors.*;
import draw.*;
import geometry.*;

class UFOWorld extends World{
    UFO ufo;
    AUP aup;
    IShots shots;
    IUFOShots UFOShots;
    IColor BACKG = new Blue();
    int HEIGHT = 500;
    int WIDTH = 200;
    
    UFOWorld(UFO ufo, AUP aup, IShots shots, IUFOShots UFOShots) {
        this.ufo = ufo;
        this.aup = aup;
        this.shots = shots;
        this.UFOShots = UFOShots;
    }
    
    public boolean draw() {
        return this.theCanvas.drawRect(new Posn(0, 0), 
                                        this.WIDTH, 
                                        this.HEIGHT, 
                                        this.BACKG)
               &&
               this.ufo.draw(this.theCanvas)
               &&
               this.aup.draw(this.theCanvas, this.HEIGHT - 10)
               &&
               this.shots.draw(this.theCanvas)
               &&
               this.UFOShots.draw(this.theCanvas);
    }
    
    UFOWorld move() {
        return new UFOWorld(this.ufo.move(this.WIDTH, 
                                          this), 
                            this.aup, 
                            this.shots.move(), 
                            this.UFOShots.move(this));
    }
    
    public World onTick() {
        if (this.ufo.loc.y >= this.HEIGHT - 10) {
            return this.endOfWorld("You Lose!");
        } else if (this.shots.hitsUFO(this.ufo)) {
            return this.endOfWorld("You Win!");
        } else if (this.UFOShots.hitsAUP(this.aup, this)) {
            return this.endOfWorld("You Lose!");
        } else {
            return new UFOWorld(this.ufo.move(this.WIDTH, 
                                              this), 
                                this.aup, 
                                this.shots.move(), 
                                this.UFOShots.move(this));
        }
    }
    
    public UFOWorld onKeyEvent(String key) {
        if (key.equals("up") && this.shots.length() < 4) {
            return new UFOWorld(this.ufo, 
                                this.aup, 
                                new ConsShots(this.aup.fireShot(this), 
                                              this.shots), 
                                this.UFOShots);
        } else {
            return new UFOWorld(this.ufo, 
                                this.aup.move(key), 
                                this.shots, 
                                this.UFOShots);
        }
    }
    
    public static void main(String[] args) {
        new Examples().w1.bigBang(200, 500, 0.1);
    }
}