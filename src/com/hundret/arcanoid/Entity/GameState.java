package com.hundret.arcanoid.Entity;

import java.awt.*;


public class GameState {

    private GameField field;

    private boolean gameStart;
    private boolean gameOver;

    private boolean pause = false;

    public static int currentScore;

    private Font titleFont = new Font("Century Gothic", Font.CENTER_BASELINE, 70);
    private Font regularFont = new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT, 20);
    private Font instructionsFont = new Font("Times New Roman", Font.CENTER_BASELINE, 14);

    private String instructionsMessage1 = "Press 'ENTER' to start game.";
    private String instructionsMessage2 = "Press 'SPACE' to launch the ball";
    private String instructionsMessage3 = "Use '<-' and '->' to controll the stick";
    private String instructionsMessage4 = "Press 'ESC' to pause/unpause the game";
    private String pauseMessage = "PAUSE";
    private String gameOverMessage = "GAME OVER!!!";
    private String scoreMessage = "Your score: ";

    private String title = "ARKANOID";

    public GameState() {}

    public void update() {
        if (field == null) field = new GameField();
        if (isGameStart() && !isPause() && !isGameOver()) {
            getField().update();
            if (gameOverConditions() || winConditions())
                gameOver = true;
        }
        if (isGameOver() && !isGameStart()) {
            resetLevel();
        }
    }

    private void resetLevel() {
        field = new GameField();
        currentScore = 0;
        gameOver = false;
        gameStart = false;
    }

    public void draw(Graphics2D g) {
        if (!isGameStart() && !isGameOver())
            drawTitleScreen(g);
        if (isPause())
            drawPauseScreen(g);
        if (isGameOver())
            drawGameOverScreen(g);
        if (isGameStart() && !isPause() && !isGameOver())
            getField().draw(g);
    }

    private void drawTitleScreen(Graphics2D g) {
        drawBlackScreen(g);
        g.setFont(titleFont);
        g.drawString(title, 70, 70);
        drawInstructions(g);
    }

    private void drawInstructions(Graphics2D g) {
        g.setFont(instructionsFont);
        g.drawString(instructionsMessage1, GameField.getBorderSize(), GameField.getFieldHeight() / 2);
        g.drawString(instructionsMessage2, GameField.getBorderSize(), GameField.getFieldHeight() / 2 + 15);
        g.drawString(instructionsMessage3, GameField.getBorderSize(), GameField.getFieldHeight() / 2 + 30);
        g.drawString(instructionsMessage4, GameField.getBorderSize(), GameField.getFieldHeight() / 2 + 45);
    }

    private void drawPauseScreen(Graphics2D g) {
        drawBlackScreen(g);
        g.setFont(regularFont);
        g.drawString(pauseMessage,
                GameField.getFieldWidth() / 2 - 40, GameField.getFieldHeight() / 2);

    }

    private void drawGameOverScreen(Graphics2D g) {
        drawBlackScreen(g);
        g.setFont(regularFont);
        g.drawString(gameOverMessage,
                        (GameField.getFieldWidth() / 2 - 60), GameField.getFieldHeight() / 2);
        g.drawString(scoreMessage + getCurrentScore(),
                        (GameField.getFieldWidth() / 2) - 60,
                            GameField.getFieldHeight() / 2 + 25);
    }

    private void drawBlackScreen(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0,0, GameField.getFieldWidth(), GameField.getFieldWidth());
        g.setColor(Color.white);
    }

    private boolean gameOverConditions() {
        return getField().ballFallsDown();
    }

    private boolean winConditions() {
        return getField().getBricks().size() == 0;
    }

    public GameField getField() {
        return field;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

}
