package Clases;

import org.newdawn.slick.Graphics;
import java.util.*;

public class World {
    private List<Obstacle> obstacles = new ArrayList<>();
    private Player player;
    private int width;
    private int height;
    private float speed = 1;

    // Constructor
    World(List<Obstacle> obstacles, Player player, int width, int height) {
        this.obstacles = obstacles;
        this.player = player;
        this.width = width;
        this.height = height;
        this.init();
    }

    // Getters y Setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void init() {
        int y1 = -20;
        this.obstacles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            obstacles.add(createObstacle(y1));
            y1 += 90;
        }
    }

    // Método que se encarga de actualizar el mundo
    // con sus respectivos obstaculos y el jugador
    public void update() {
        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle o = obstacles.get(i);
            o.update();
            if (o.getY() >= player.getPosY() - player.getCircle().radius) {
                if (player.colission(o)) {
                    player.updateScore();
                } else {
                    player.setDead(true);
                }
            }
            if (o.getY() >= player.getPosY() - player.getCircle().radius) {
                obstacles.remove(o);
                Obstacle obstacle = createObstacle(-20);
                obstacle.setWorld(this);
                obstacles.add(obstacle);
            }
        }
    }

    // Método render, se encargará de pintar los obstaculos en el mundo
    public void render(Graphics g) {
        for (Obstacle o : obstacles) {
            o.render(g);
        }
    }

    // Método que se encarga de crear los obstaculos en el mundo
    private Obstacle createObstacle(int y) {
        int x1 = 0;
        int x2 = (int) (Math.random() * width) / 2;
        int x3 = x2 + 100;
        int x4 = width;
        Obstacle obstacle = new Obstacle(x1, x2, x3, x4, y);
        obstacle.setWorld(this);
        return obstacle;
    }

    // Actualizamos la velocidad
    public void updateSpeed() {
        speed += 0.25;
    }
}