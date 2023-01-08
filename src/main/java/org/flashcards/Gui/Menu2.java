package org.flashcards.Gui;

import org.flashcards.src.GuiApp;

import javax.swing.*;
import java.awt.*;


public class Menu2 extends JPanel {
    private final Initializer initializer;

    public Menu2(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getCustomTitle();
        getBackButton();
        getAddButton();
        getAllRepos();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Your repositories");
        title.setFont(new Font("Arbutus", Font.PLAIN, 40));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        title.setBounds(130, 160, 700, 50);
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

    private void getAllRepos() {
        JButton print = new JButton("Print all repos");
        print.setFont(new Font("Arbutus", Font.PLAIN, 16));
        print.setHorizontalAlignment(JTextField.CENTER);
        print.setBounds(375, 260, 210, 65);
        print.addActionListener(e -> {
            GuiApp.getInstance().getApp().print();
        });
        add(print);
    }

}
