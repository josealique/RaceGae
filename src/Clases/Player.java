package Clases;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Player {
    private int direction = 0;
    private int posX;
    private int posY;
    private Circle circle;
    private GameContainer gameContainer;
    private int points = 0;
    private boolean dead = false;
    private int lifes = 3;

    // constructor
    public Player(Circle c, int posX, int posY, GameContainer gameContainer) {
        this.circle = c;
        this.posX = posX;
        this.posY = posY;
        this.gameContainer = gameContainer;
    }

    // Getters y Setters
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getPosY() {
        return posY;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    private int score() {
        return points;
    }

    public int getScore() {
        return this.score();
    }

    public void updateScore() {
        points += 10;
    }

    public Circle getCircle() {
        return circle;
    }

    // Método render, se encarga de dibujar el jugador
    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fill(circle);
    }

    // Método update, se encarga de actualizar el estado del jugador
    public void update() {
        this.posX += direction;
        circle.setX(this.posX);
        if (this.posX <= circle.getRadius()) {
            circle.setX(circle.getRadius());
        } else if (this.posX >= gameContainer.getWidth() - circle.getRadius()) {
            circle.setX(gameContainer.getWidth() - circle.getRadius() * 2);
        }
    }

    // Método que se encarga de revisar si el jugador
    // colisiona con un obstaculo
    public boolean colission(Obstacle obstacle) {
        // Comprobamos las x y las y, tanto del obstaculo como las del jugador
        return (obstacle.getX() < circle.getX() && obstacle.getX1() < circle.getX() &&
                obstacle.getX2() > circle.getX() && obstacle.getX3() > circle.getX());
    }
}