package com.sofka;

import com.sofka.ui.MainUI;
import javax.swing.*;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createGUI() throws IOException {
        MainUI ui = new MainUI();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}