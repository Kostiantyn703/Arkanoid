package com.hundret.arcanoid.Entity;

import java.awt.*;

public class Ball {

    private final static int RADIUS = 10;
    private final static int DIAMETER = RADIUS * 2;

    private Point position;
    private int dx = 1, dy = 3;
    private int xTemp, yTemp;
    private BallDirection bd = BallDirection.NO_DIRECTION;

    private boolean flying;

    public Ball() {
        flying = false;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(position.getX() - RADIUS, position.getY() - RADIUS, DIAMETER, DIAMETER);
    }

    public void update() {
        if (!isFlying()) {
            position.setX(Stick.getPosition().getX() + Stick.getStickWidth() / 2);
        } else {
            xTemp = position.getX();
            yTemp = position.getY();
            flight();
            position.setCoordinates(xTemp, yTemp);
            bordersCollision();
            stickCollision();
        }
    }

    private void flight() {
        switch (bd) {
            case UP_RIGHT:
                xTemp += dx;
                yTemp -= dy;
                break;
            case UP_LEFT:
                xTemp -= dx;
                yTemp -= dy;
                break;
            case DOWN_RIGHT:
                xTemp += dx;
                yTemp += dy;
                break;
            case DOWN_LEFT:
                xTemp -= dx;
                yTemp += dy;
                break;
        }
    }

    private void bordersCollision() {
        //left border
        if (bd == BallDirection.UP_LEFT && position.getX() <= GameField.getBorderSize()) {
            getPosition().setX(GameField.getBorderSize() + RADIUS);
            setBd(BallDirection.UP_RIGHT);
        }
        if (bd == BallDirection.DOWN_LEFT && position.getX() <= GameField.getBorderSize()) {
            getPosition().setX(GameField.getBorderSize() + RADIUS);
            setBd(BallDirection.DOWN_RIGHT);
        }
        //up border
        if (bd == BallDirection.UP_RIGHT && position.getY() <= GameField.getBorderSize()) {
            getPosition().setY(GameField.getBorderSize() + RADIUS);
            setBd(BallDirection.DOWN_RIGHT);
        }
        if (bd == BallDirection.UP_LEFT && position.getY() <= GameField.getBorderSize()) {
            getPosition().setY(GameField.getBorderSize() + RADIUS);
            setBd(BallDirection.DOWN_LEFT);
        }
        //right border
        if (bd == BallDirection.UP_RIGHT &&
                position.getX() >= GameField.getFieldWidth() - GameField.getBorderSize()) {
            getPosition().setX(GameField.getFieldWidth() - GameField.getBorderSize() - RADIUS);
            setBd(BallDirection.UP_LEFT);
        }
        if (bd == BallDirection.DOWN_RIGHT &&
                position.getX() >= GameField.getFieldWidth() - GameField.getBorderSize()) {
            getPosition().setX(GameField.getFieldWidth() - GameField.getBorderSize() - RADIUS);
            setBd(BallDirection.DOWN_LEFT);
        }
    }

    private void stickCollision() {
        if (checkBallDirectionForStickCollision() && checkLeftSideStickPosition()) {
            //getPosition().setY(Stick.getPosition().getY());
            setBd(BallDirection.UP_LEFT);
            setDx(2);
        }
        if (checkBallDirectionForStickCollision() && checkMiddleLeftStickPosition()) {
            //getPosition().setY(Stick.getPosition().getY());
            setBd(BallDirection.UP_LEFT);
            setDx(1);
        }
        if (checkBallDirectionForStickCollision() && checkMiddleRightStickPosition()) {
            //getPosition().setY(Stick.getPosition().getY());
            setBd(BallDirection.UP_RIGHT);
            setDx(1);
        }
        if (checkBallDirectionForStickCollision() && checkRightSideStickPosition()) {
            //getPosition().setY(Stick.getPosition().getY());
            setBd(BallDirection.UP_RIGHT);
            setDx(2);
        }
    }

    private boolean checkLeftSideStickPosition() {
        return checkYPosForStickCollision() &&
                (position.getX() >= Stick.getPosition().getX() &&
                        position.getX() <= Stick.getPosition().getX() + (Stick.getStickWidth() / 4));
    }

    private boolean checkMiddleLeftStickPosition() {
        return checkYPosForStickCollision() &&
                (position.getX() >= Stick.getPosition().getX() + (Stick.getStickWidth() / 4) &&
                        position.getX() <= Stick.getPosition().getX() + (Stick.getStickWidth() / 2));
    }

    private boolean checkMiddleRightStickPosition() {
        return  checkYPosForStickCollision() &&
                    (position.getX() >= Stick.getPosition().getX() + (Stick.getStickWidth() / 2) &&
                        position.getX() <= Stick.getPosition().getX() +
                                Stick.getStickWidth() - (Stick.getStickWidth() / 4));
    }

    private boolean checkRightSideStickPosition() {
        return  checkYPosForStickCollision() &&
                (position.getX() >= Stick.getPosition().getX() +
                        Stick.getStickWidth() - (Stick.getStickWidth() / 4) &&
                            position.getX() <= Stick.getPosition().getX() + Stick.getStickWidth());
    }

    private boolean checkBallDirectionForStickCollision() {
        return bd == BallDirection.DOWN_LEFT || bd == BallDirection.DOWN_RIGHT;
    }

    private boolean checkYPosForStickCollision() {
        return position.getY() >= Stick.getPosition().getY() - RADIUS / 2;
    }

    public static int getRADIUS() {
        return RADIUS;
    }

    public static int getDIAMETER() {
        return DIAMETER;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public BallDirection getBd() {
        return bd;
    }

    public void setBd(BallDirection bd) {
        this.bd = bd;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }
}
