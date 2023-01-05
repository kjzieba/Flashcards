package org.flashcards.Gui;

import org.flashcards.Gui.Components.ButtonComponents;

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
        JButton backButton = new ButtonComponents().backButtonComponent(13,12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.GetStarted);
        });
        add(backButton);
    }

    private void getAddButton() {
        JButton addButton = new ButtonComponents().addButtonComponent(833,6);
        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
        });
        add(addButton);
    }

}
