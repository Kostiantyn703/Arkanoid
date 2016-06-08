package com.hundret.arcanoid.View;

import com.hundret.arcanoid.Controller.GameController;
import com.hundret.arcanoid.Entity.GameState;
import com.hundret.arcanoid.Entity.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    private BufferedImage image;
    private Graphics2D g2d;

    private Thread gameThread;
    //private Timer gameTimer;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private GameState gameState;

    public GamePanel() {
        super();
        gameState = new GameState();
        setPreferredSize(new Dimension(GameField.getFieldWidth(), GameField.getFieldHeight()));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (gameThread == null) {
            gameThread = new Thread(this);
            addKeyListener(new GameController(this));
            gameThread.start();
        }
    }

    private void init() {
        image = new BufferedImage(GameField.getFieldWidth(),
                                    GameField.getFieldHeight(), BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D)image.getGraphics();
        running = true;
    }

    private void update() {
        gameState.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D)g;

        gameState.draw(g2d);
        g2d.dispose();
    }

    @Override
    public void run() {
        init();
        long start, elapsed, wait;

        while (running) {
            start = System.nanoTime();

            update();
            repaint();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            if (wait < 0) wait = 5;
            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}
