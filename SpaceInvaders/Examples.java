import draw.*;
import geometry.*;
import tester.*;

class Examples {
    UFO ufo1 = new UFO(new Posn(88, 11));
    UFO ufo2 = new UFO(new Posn(88, 14));
    UFO ufo3 = new UFO(new Posn(88, 389));
    UFO ufo4 = new UFO(new Posn(88, 390));
    Shot shot1= new Shot(new Posn(88, 17));
    Shot shot2 = new Shot(new Posn(88, 12));
    AUP a = new AUP(85);
    AUP a1 = new AUP(150);
    UFO u = new UFO(new Posn(100, 5));
    Shot s = new Shot(new Posn(112, 380));
    IShots le = new MTShots();
    IShots ls = new ConsShots(s, new MTShots());
    UFOShot UFOShot1 = new UFOShot(new Posn(100, 112));
    IUFOShots MTUFOShot1 = new MTUFOShots();
    IUFOShots UFOShots1 = new ConsUFOShots(UFOShot1, MTUFOShot1);
    UFOWorld w1 = new UFOWorld(u, a, le, MTUFOShot1);
    UFOWorld w2 = new UFOWorld(u, a, ls, UFOShots1);
    IShots shots1 = new ConsShots(s, new ConsShots(shot1, new ConsShots(shot2, le)));
    
     public static void main(String[] args) {
         Tester.run(new Examples());
     }

    void testMove(Tester t) {
        t.checkExpect(shot1.move(), shot2);
        t.checkExpect(UFOShot1.move(), new UFOShot(new Posn(100, 117)));
        t.checkExpect(a.move("left"), new AUP(82));
        t.checkExpect(a.move("right"), new AUP(88));
    }
    
    void checkDrawing() {
        Canvas c = new Canvas(200, 500);
        c.show();
        this.ufo1.draw(c);
        this.shot1.draw(c);
        this.ls.draw(c);
        this.UFOShots1.draw(c);
        this.a1.draw(c, 490);
    }        
}