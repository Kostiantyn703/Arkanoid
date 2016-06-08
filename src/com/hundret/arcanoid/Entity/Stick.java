package com.hundret.arcanoid.Entity;

import java.awt.*;

public class Stick {

    private final static int STICK_WIDTH = 100, STICK_HEIGHT = 20;

    private static Point position;
    private int dx = 5;
    private int xTemp;

    private boolean left;
    private boolean right;

    public Stick() {}

    public void draw(Graphics2D g) {
        g.setColor(Color.orange);
        g.fillRect(position.getX(), position.getY(), STICK_WIDTH, STICK_HEIGHT);
    }

    public void update() {
        xTemp = position.getX();
        if (left) {
            if (xTemp == GameField.getBorderSize()) {
                position.setX(GameField.getBorderSize());
            } else {
                xTemp -= dx;
                position.setX(xTemp);
            }
        } else if (right) {
            if (xTemp + STICK_WIDTH == GameField.getFieldWidth() - GameField.getBorderSize()) {
                position.setX(GameField.getFieldWidth() - GameField.getBorderSize() - STICK_WIDTH);
            } else {
                xTemp += dx;
                position.setX(xTemp);
            }
        }
    }

    public static int getStickWidth() {
        return STICK_WIDTH;
    }

    public static int getStickHeight() {
        return STICK_HEIGHT;
    }

    public static Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
