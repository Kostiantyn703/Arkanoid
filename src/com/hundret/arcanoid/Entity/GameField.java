package com.hundret.arcanoid.Entity;

import java.awt.*;
import java.util.ArrayList;

public class GameField {

    private final static int FIELD_WIDTH = 640, FIELD_HEIGHT = 480;
    private final static int BORDER_SIZE = 10;
    private final static int BRICKS_COLS = 7, BRICKS_ROWS = 3;

    private Stick racket;
    private Ball ball;
    private ArrayList<Brick> bricks;

    public GameField() {
        initLevel();
    }

    private void initLevel() {
        racket = new Stick();
        ball = new Ball();
        bricks = new ArrayList<>();
        setLevel();
    }

    private void setLevel() {
        int tempVal = FIELD_HEIGHT - Stick.getStickHeight() * 3;
        racket.setPosition(new Point((FIELD_WIDTH / 2) - (Stick.getStickWidth() / 2), tempVal));
        ball.setPosition(new Point((FIELD_WIDTH / 2), tempVal - Ball.getRADIUS()));
        setBricks();
    }

    public void draw(Graphics2D g) {
        drawBorders(g);
        racket.draw(g);
        ball.draw(g);
        drawBricks(g);
    }

    private void drawBorders(Graphics2D g) {
        g.fillRect(0, 0, FIELD_WIDTH, BORDER_SIZE);
        g.fillRect(0, 0, BORDER_SIZE, FIELD_HEIGHT);
        g.fillRect(FIELD_WIDTH - BORDER_SIZE, 0, FIELD_WIDTH, FIELD_HEIGHT);
    }

    private void drawBricks(Graphics2D g) {
        for (int i = 0; i < bricks.size(); i++)
            bricks.get(i).draw(g);
    }

    public void update() {
        racket.update();
        ball.update();
        for (int i = 0; i < bricks.size(); i++)
            bricks.get(i).update();
        ballHitBrick(bricks);
    }

    private void ballHitBrick(ArrayList<Brick> bricks) {
        for (int i = 0; i < bricks.size(); i++) {
            if (checkBrickXPositionToHit(bricks.get(i)) &&
                    checkBrickYPostiotionToHit(bricks.get(i)) &&
                        !bricks.get(i).isHit()) {
                bricks.get(i).setHit(true);
                bricks.remove(i);
                changeBallDirection();
                GameState.currentScore += 10;
            }
        }
    }

    private void changeBallDirection() {
        if (ball.getBd() == BallDirection.UP_LEFT) ball.setBd(BallDirection.DOWN_LEFT);
        else if (ball.getBd() == BallDirection.UP_RIGHT) ball.setBd(BallDirection.DOWN_RIGHT);
        else if (ball.getBd() == BallDirection.DOWN_LEFT) ball.setBd(BallDirection.UP_LEFT);
        else if (ball.getBd() == BallDirection.DOWN_RIGHT) ball.setBd(BallDirection.UP_RIGHT);
    }

    private boolean checkBrickXPositionToHit(Brick b) {
        return ball.getPosition().getX() >= b.getPosition().getX() &&
                ball.getPosition().getX() <= b.getPosition().getX() + b.getBrickWidth();
    }

    private boolean checkBrickYPostiotionToHit(Brick b) {
        return ball.getPosition().getY() >= b.getPosition().getY() &&
                ball.getPosition().getY() <= b.getPosition().getY() + b.getBrickHeight();
    }

    private void setBricks() {
        initBricks();
        int count = 0;
        for (int i = 0; i < BRICKS_ROWS; i++) {
            for (int j = 0; j < BRICKS_COLS; j++) {
                bricks.get(count++).setPosition(
                            new Point(Brick.getBorderShift() + (brickDistanceHorizontal() * j),
                                        Brick.getBorderShift() + (brickDistanceVertical() * i)));
            }
        }
    }

    private int brickDistanceHorizontal() {
        return Brick.getBrickWidth() + Brick.getBricksShift();
    }

    private int brickDistanceVertical() {
        return Brick.getBrickHeight() + Brick.getBricksShift();
    }

    private void initBricks() {
        for (int i = 0; i < BRICKS_ROWS * BRICKS_COLS; i++)
            bricks.add(new Brick());
    }

    public boolean ballFallsDown() {
        return ball.getPosition().getY() > FIELD_HEIGHT;
    }

    public Ball getBall() {
        return ball;
    }

    public Stick getRacket() {
        return racket;
    }

    public static int getBorderSize() {
        return BORDER_SIZE;
    }

    public static int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public static int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }
}
