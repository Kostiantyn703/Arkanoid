package com.hundret.arcanoid.Controller;

import com.hundret.arcanoid.Entity.BallDirection;
import com.hundret.arcanoid.View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameController implements KeyListener {

    private GamePanel gamePane;

    public GameController(GamePanel gp) {
        gamePane = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                gamePane.getGameState().getField().getRacket().setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                gamePane.getGameState().getField().getRacket().setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                if (!gamePane.getGameState().getField().getBall().isFlying()) {
                    gamePane.getGameState().getField().getBall().setFlying(true);
                    gamePane.getGameState().getField().getBall().setBd(BallDirection.UP_RIGHT);
                }
                break;
            case KeyEvent.VK_ENTER:
                if (gamePane.getGameState().isGameOver())
                    gamePane.getGameState().setGameStart(false);
                else
                    gamePane.getGameState().setGameStart(true);
                break;
            case KeyEvent.VK_ESCAPE:
                if (gamePane.getGameState().isGameStart() && !gamePane.getGameState().isGameOver()) {
                    if (!gamePane.getGameState().isPause())
                        gamePane.getGameState().setPause(true);
                    else
                    gamePane.getGameState().setPause(false);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                gamePane.getGameState().getField().getRacket().setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                gamePane.getGameState().getField().getRacket().setRight(false);
                break;
        }
    }
}
