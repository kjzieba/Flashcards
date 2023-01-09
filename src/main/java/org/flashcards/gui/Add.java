package org.flashcards.gui;

import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.App;

import javax.swing.*;
import java.awt.*;

public class Add extends JPanel {

    private final Initializer initializer;

    public Add(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
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
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
        backButton.addActionListener(e -> {
                initializer.update(GUInitializer.Panel.Menu);
        });
        add(backButton);
    }

    private void getSoundButton() {
        JButton soundButton = new ButtonComponents().bigButtonComponent("Sound", 375, 239);
        soundButton.setBorderPainted(true);
        soundButton.addActionListener(e -> {
        });
        add(soundButton);
    }

    private void getTextButton() {
        JButton addTextButton = new ButtonComponents().bigButtonComponent("Text", 132, 239);
        addTextButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
            App.getInstance().addTxtRepo();
        });
        add(addTextButton);
    }

    private void getImageButton() {
        JButton addButton = new ButtonComponents().bigButtonComponent("Image", 618, 239);
        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.EditImg);
            App.getInstance().addImgRepo("img");
        });
        add(addButton);
    }

}