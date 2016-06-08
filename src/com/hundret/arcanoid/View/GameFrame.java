package com.hundret.arcanoid.View;

import javax.swing.*;

public class GameFrame extends JFrame {

    private String title = "Arkanoid";

    public GameFrame() {
        super();
        init();
    }

    private void init() {
        setTitle(title);
        setContentPane(new GamePanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
