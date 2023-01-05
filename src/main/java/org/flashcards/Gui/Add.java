package org.flashcards.Gui;

import org.flashcards.Gui.Components.ButtonComponents;

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
        JButton backButton = new ButtonComponents().backButtonComponent(13,12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Menu);
        });
        add(backButton);
    }

    private void getTextButton() {
        JButton textButton = new ButtonComponents().bigButtonComponent("Text", 132, 239);
        textButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
        });
        add(textButton);
    }

    private void getSoundButton() {
        JButton soundButton = new ButtonComponents().bigButtonComponent("Sound", 375, 239);
        soundButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
        });
        add(soundButton);
    }

    private void getImageButton() {
        JButton imageButton = new ButtonComponents().bigButtonComponent("Image", 618, 239);
        imageButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
        });
        add(imageButton);
    }
}
