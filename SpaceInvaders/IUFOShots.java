import draw.*;

interface IUFOShots {
    boolean draw(Canvas c);
    IUFOShots move(UFOWorld w);
    boolean hitsAUP(AUP a, UFOWorld w);
}