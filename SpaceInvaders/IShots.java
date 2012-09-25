import draw.*;

interface IShots {
    boolean draw(Canvas c);
    IShots move();
    int length();
    boolean hitsUFO(UFO u);
}