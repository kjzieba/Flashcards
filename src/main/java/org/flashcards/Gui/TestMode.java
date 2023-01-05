package org.flashcards.Gui;

import org.flashcards.Gui.Components.ButtonComponents;

import javax.swing.*;
import java.awt.*;

public class TestMode extends JPanel {

    private final Initializer initializer;
    public TestMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getBackButton();
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13,12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        add(backButton);
    }
}
