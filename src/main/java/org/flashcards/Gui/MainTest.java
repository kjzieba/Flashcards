package org.flashcards.Gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.net.*;

public class MainTest {

    private JComponent ui = null;
    JButton button1;
    JButton button2;

    MainTest() {
        try {
            initUI();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    public void initUI() throws MalformedURLException {
        if (ui!=null) return;

        ui = new JPanel(new BorderLayout(4,4));
        ui.setBorder(new EmptyBorder(4,4,4,4));

        button1 = new JButton("button 1", new ImageIcon(
                new URL("https://i.stack.imgur.com/in9g1.png")));
        button2 = new JButton("button 2", new ImageIcon(
                new URL("https://i.stack.imgur.com/wCF8S.png")));
        ui.add(button1);
        // Yep. One button can indeed be added to another...
        button1.add(button2);
    }

    public JComponent getUI() {
        return ui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (Exception useDefault) {
//                }
                MainTest o = new MainTest();

                JFrame f = new JFrame(o.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                f.setContentPane(o.getUI());
                f.pack();
                f.setMinimumSize(f.getSize());

                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}