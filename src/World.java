import org.newdawn.slick.Graphics;
import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Obstacle> obstacles = new ArrayList<>();
    private Player player;
    private int width;
    private int height;
    float speed = 1;

    public World(List<Obstacle> obstacles, Player player, int width, int height) {
        this.obstacles = obstacles;
        this.player = player;
        this.width = width;
        this.height = height;
        this.init();
    }

    public float getSpeed() {
        return speed;
    }

    public void init(){
        int y1 = -20;
        this.obstacles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            obstacles.add(createObstacle(y1));
            y1 += 90;
        }
    }

    public void update(){
        for (int i = obstacles.size() -1; i >= 0; i--) {
            Obstacle o = obstacles.get(i);
            o.update();
            if (o.y >= player.posY - player.circle.radius){
                if (player.colission(o)) {
                    player.updateScore();
                } else {
                    player.dead = true;
                }
            }
            if (o.y >= player.posY - player.circle.radius){
                obstacles.remove(o);
                Obstacle obstacle = createObstacle(-20);
                obstacle.setWorld(this);
                obstacles.add(obstacle);
            }
        }
    }

    public void render(Graphics g){
        for (Obstacle o : obstacles) {
            o.render(g);
        }
    }

    private Obstacle createObstacle(int y){
        int x1 = 0;
        int x2 = (int) (Math.random() * width) / 2;
        int x3 = x2 + 100;
        int x4 = width;
        Obstacle obstacle = new Obstacle(x1,x2,x3,x4,y);
        obstacle.setWorld(this);
        return obstacle;
    }

    public void updateSpeed(){
        speed += 0.25;
        System.out.println(speed);
    }
}
