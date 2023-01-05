package org.flashcards.Gui;

import javax.swing.*;
import java.awt.*;

public class Edit extends JPanel {

    private final Initializer initializer;
    public Edit(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getBackButton();
        getSaveButton();
        getNameRepository();
    }

    private void getBackButton() {
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/main/resources/img/backButton.png"));
        backButton.setBounds(13, 12, 30, 30);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
        });
        add(backButton);
    }

    private void getSaveButton() {
        JButton addTextButton = new JButton("Save");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(601, 47, 210, 65);

        addTextButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        add(addTextButton);
    }

    private void getNameRepository() {
        JButton addTextButton = new JButton();
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(149, 47, 210, 65);

        add(addTextButton);
    }
}
