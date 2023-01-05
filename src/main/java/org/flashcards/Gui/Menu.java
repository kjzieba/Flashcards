package org.flashcards.Gui;

import javax.swing.*;
import java.awt.*;



public class Menu extends JPanel {
    private final Initializer initializer;

    public Menu(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getCustomTitle();
        getBackButton();
        getAddButton();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Add your first repository");
        title.setFont(new Font("Arbutus", Font.PLAIN, 40));
        title.setForeground(Color.white);
        title.setBounds(160, 220, 700, 50);
        add(title);
    }

    private void getBackButton() {
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/main/resources/img/backButton.png"));
        backButton.setBounds(13, 12, 30, 30);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.GetStarted);
        });
        add(backButton);
    }

    private void getAddButton() {
        JButton addButton = new JButton();
        addButton.setIcon(new ImageIcon("src/main/resources/img/addButton.png"));
        addButton.setBounds(833, 6, 40, 40);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
        });
        add(addButton);
    }

}
