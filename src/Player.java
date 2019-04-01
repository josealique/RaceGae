import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Player {
     int direction = 0;
     int posX;
     int posY;
     Circle circle;
     GameContainer gameContainer;
     int points = 0;
     boolean dead = false;
     int lifes = 3;

    public Player(Circle c, int posX, int posY, GameContainer gameContainer) {
        this.circle = c;
        this.posX = posX;
        this.posY = posY;
        this.gameContainer = gameContainer;
    }

    public int score() {
        return points;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fill(circle);
    }

    public void update() {
        this.posX += direction;
        circle.setX(this.posX);
        if (this.posX <= circle.getRadius()) {
            circle.setX(circle.getRadius());
        } else if (this.posX >= gameContainer.getWidth() - circle.getRadius()) {
            circle.setX(gameContainer.getWidth() - circle.getRadius() * 2);
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean colission(Obstacle obstacle) {
        return (obstacle.x < circle.getX() && obstacle.x1 < circle.getX() &&
                        obstacle.x2 > circle.getX() && obstacle.x3 > circle.getX());
    }

    public int getScore() {
        return this.score();
    }

    public void updateScore() {
        points += 10;
    }
}