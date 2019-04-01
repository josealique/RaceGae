import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

public class Obstacle {
    int x;
    int x1;
    int x2;
    int x3;
    float y;
    World world;

    public Obstacle(int x, int x1, int x2, int x3, float y){
        this.x = x;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y = y;
    }

    public void render(Graphics g){
       Line line = new Line(x,y,x1,y);
       Line line2 = new Line(x2,y,x3,y);
       g.setColor(Color.white);
       g.draw(line);
       g.draw(line2);
    }

    public void update() {
        this.y += world.getSpeed();
    }

    public void setWorld(World world) {
        this.world = world;
    }
}