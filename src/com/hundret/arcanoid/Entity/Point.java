package com.hundret.arcanoid.Entity;


public class Point {

    private int x, y;

    public Point(int xVal, int yVal) {
        x = xVal;
        y = yVal;
    }

    public void setCoordinates(int xVal, int yVal) {
        x = xVal;
        y = yVal;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
