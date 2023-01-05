package org.flashcards.Gui;

import javax.swing.*;
import java.awt.*;

public class Add extends JPanel {

    private final Initializer initializer;
    public Add(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getCustomTitle();
        getBackButton();
        getTextButton();
        getSoundButton();
        getImageButton();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Choose type of your repository");
        title.setFont(new Font("Arbutus", Font.PLAIN, 30));
        title.setForeground(Color.white);
        title.setBounds(180, 120, 700, 50);
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
            initializer.update(GUInitializer.Panel.Menu);
        });
        add(backButton);
    }

    private void getTextButton() {
        JButton addTextButton = new JButton("Text");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(132, 239, 210, 65);

        addTextButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
        });
        add(addTextButton);
    }

    private void getSoundButton() {
        JButton addButton = new JButton("Sound");
        addButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addButton.setBounds(375, 239, 210, 65);

        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
        });
        add(addButton);
    }

    private void getImageButton() {
        JButton addButton = new JButton("Image");
        addButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addButton.setBounds(618, 239, 210, 65);

        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
        });
        add(addButton);
    }
}
