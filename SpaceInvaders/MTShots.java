import draw.*;

class MTShots implements IShots {
    
    MTShots() {
    }
    
    public IShots move() {
        return this;
    }
    
    public boolean draw(Canvas c) {
        return true;
    }
    
    public int length() {
        return 0;
    }
    
    public boolean hitsUFO(UFO u) {
        return false;
    }
}