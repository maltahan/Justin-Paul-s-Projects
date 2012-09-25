import draw.*;

class MTUFOShots implements IUFOShots {
    
    MTUFOShots() {
    }
    
    public IUFOShots move(UFOWorld w) {
        return this;
    }
    
    public boolean draw(Canvas c) {
        return true;
    }
    
    public boolean hitsAUP(AUP a, UFOWorld w) {
        return false;
    }
}