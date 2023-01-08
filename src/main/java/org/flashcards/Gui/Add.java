package org.flashcards.Gui;

import org.flashcards.Gui.Components.ButtonComponents;
import org.flashcards.src.GuiApp;

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
        getSoundButton();
        getTextButton();
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

    private void getSoundButton() {
        JButton soundButton = new ButtonComponents().bigButtonComponent("Sound", 375, 239);
        soundButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
        });
        add(soundButton);
    }

    private void getTextButton() {
        JButton addTextButton = new JButton("Text");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(132, 239, 210, 65);
        addTextButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
            GuiApp.getInstance().getApp().addTxtRepo();
        });
        add(addTextButton);
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