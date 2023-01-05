package org.flashcards.Gui;

import javax.swing.*;
import java.awt.*;

public class ChooseMode extends JPanel {

    private final Initializer initializer;
    ChooseMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getBackButton();
        getFlashCardsButton();
        getTestButton();
        getLearnButton();
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

    private void getFlashCardsButton() {
        JButton addTextButton = new JButton("flashcards");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(149, 47, 210, 65);

        addTextButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.FlashCardsMode);
        });
        add(addTextButton);
    }

    private void getLearnButton() {
        JButton addButton = new JButton("learn");
        addButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addButton.setBounds(392, 47, 210, 65);

        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.LearnMode);
        });
        add(addButton);
    }

    private void getTestButton() {
        JButton addButton = new JButton("test");
        addButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addButton.setBounds(635, 47, 210, 65);

        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.TestMode);
        });
        add(addButton);
    }
}
