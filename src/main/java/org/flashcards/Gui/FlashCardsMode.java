package org.flashcards.Gui;

import javax.swing.*;
import java.awt.*;

public class FlashCardsMode extends JPanel {

    private final Initializer initializer;
    public FlashCardsMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getBackButton();
    }

    private void getBackButton() {
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/main/resources/img/backButton.png"));
        backButton.setBounds(13, 12, 30, 30);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        add(backButton);
    }
}
