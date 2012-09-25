import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class raceTrack  extends World
{
    public int winner = 0;
    public MidiPlayer sound = new MidiPlayer();
    
    public raceTrack()
    {    
        // Create a new world with 20x20 cells with a cell size of 10x10 pixels.
        super(50, 50, 10);
        Player1 P1 = new Player1("up", 3, 90);
        Player2 P2 = new Player2("up", 3, 90);
        Counter Count = new Counter("Time Left: ");
        Counter1 C1 = new Counter1("Player1: ");
        Counter2 C2 = new Counter2("Player2: ");
        
        addObject(P1,5,5);
        addObject(P2,40,40);
        addObject(Count,27,2);
        addObject(C1, 10, 2);
        addObject(C2, 45, 2);
    }
    
    public void started(){
        //FIX!!!
        this.sound.playMidiFile("sounds/MMXStorm.mid");
        //FIX!!!
    }
    public void act() {
        
        if (this.winner == 1) {
            this.sound.stopMidiFile(); //make bg music stop
            this.sound.playMidiFile("sounds/Ffvictory.mid");
            addObject(new Player1Wins(), 30, 23);
            addObject(new Player2Loses(), 15, 23);
            this.winner = 4;
        } else if (this.winner == 2) {
            this.sound.stopMidiFile(); //make bg music stop
            this.sound.playMidiFile("sounds/Ffvictory.mid");
            addObject(new Player1Loses(), 31, 23);
            addObject(new Player2Wins(), 15, 23);
            this.winner = 4;
        } else if (this.winner == 4) { 
            // do nothing
        } else if (winner == 0 && ((Player1) this.getObjects(Player1.class).get(0)).cheese <= 0 && ((Player2) this.getObjects(Player2.class).get(0)).cheese <= 0) {
            this.removeObject((Player1) this.getObjects(Player1.class).get(0));
            this.removeObject((Player2) this.getObjects(Player2.class).get(0));
            this.removeObjects(this.getObjects(Rocket1.class));
            this.removeObjects(this.getObjects(Rocket2.class));
            this.removeObjects(this.getObjects(Cheese.class));
            this.removeObjects(this.getObjects(Counter1.class));
            this.removeObjects(this.getObjects(Counter2.class));
            this.removeObjects(this.getObjects(Counter.class));
            this.winner = 3;
        } else if (winner == 0 && ((Player1) this.getObjects(Player1.class).get(0)).cheese <= 0) {
            this.removeObject((Player1) this.getObjects(Player1.class).get(0));
            this.removeObject((Player2) this.getObjects(Player2.class).get(0));
            this.removeObjects(this.getObjects(Rocket1.class));
            this.removeObjects(this.getObjects(Rocket2.class));
            this.removeObjects(this.getObjects(Cheese.class));
            this.removeObjects(this.getObjects(Counter1.class));
            this.removeObjects(this.getObjects(Counter2.class));
            this.removeObjects(this.getObjects(Counter.class));
            this.winner = 2;
        } else if (winner == 0 && ((Player2) this.getObjects(Player2.class).get(0)).cheese <= 0) {
            this.removeObject((Player1) this.getObjects(Player1.class).get(0));
            this.removeObject((Player2) this.getObjects(Player2.class).get(0));
            this.removeObjects(this.getObjects(Rocket1.class));
            this.removeObjects(this.getObjects(Rocket2.class));
            this.removeObjects(this.getObjects(Cheese.class));
            this.removeObjects(this.getObjects(Counter1.class));
            this.removeObjects(this.getObjects(Counter2.class));
            this.removeObjects(this.getObjects(Counter.class));
            this.winner = 1;
        }
    }
    public void stopped(){
        //FIX!!!
        sound.stopMidiFile();
        //FIX!!!
    }
}