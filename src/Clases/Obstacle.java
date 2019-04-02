package Clases;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

public class Obstacle {
    private int x;
    private int x1;
    private int x2;
    private int x3;
    private float y;
    private World world;

    // Constructor
    Obstacle(int x, int x1, int x2, int x3, float y) {
        this.x = x;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y = y;
    }

    // Getters y Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX3() {
        return x3;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    // Método render, se encarga de dibujar los obstaculos
    public void render(Graphics g) {
        Line line = new Line(x, y, x1, y);
        Line line2 = new Line(x2, y, x3, y);
        g.setColor(Color.white);
        g.draw(line);
        g.draw(line2);
    }

    // Método update, se encarga de actualizar los obstaculos
    public void update() {
        this.y += world.getSpeed();
    }
}