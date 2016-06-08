package com.hundret.arcanoid.Entity;

import java.awt.*;


public class Brick {

    private final static int BRICK_WIDTH = 60, BRICK_HEIGHT = 15;
    private final static int BRICKS_SHIFT = 20;
    private final static int BORDER_SHIFT = 50;

    private Point position;
    private boolean isHit;

    public Brick() {
        setHit(false);
    }

    public void update() {
        if (isHit) setPosition(null);
    }

    public void draw(Graphics2D g) {
        if (!isHit()) {
            g.setColor(Color.green);
            g.fillRect(position.getX(), position.getY(), BRICK_WIDTH, BRICK_HEIGHT);
        }
    }

    public static int getBrickWidth() {
        return BRICK_WIDTH;
    }

    public static int getBrickHeight() {
        return BRICK_HEIGHT;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public static int getBorderShift() {
        return BORDER_SHIFT;
    }

    public static int getBricksShift() {
        return BRICKS_SHIFT;
    }
}
