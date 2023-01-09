package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.TxtCard;
import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TestMode extends JPanel {

    private final Initializer initializer;
    public TestMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
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

    private void getDefinitionTextArea(String text) {
        JTextArea definitionTextArea = new JTextArea();
        definitionTextArea.setText(text);
        definitionTextArea.setBackground(new java.awt.Color(67, 69, 74));
        definitionTextArea.setForeground(Color.white);
        definitionTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        definitionTextArea.setBounds(496, 192, 210, 85);
        definitionTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        definitionTextArea.setBorder(BorderFactory.createCompoundBorder(
                definitionTextArea.getBorder(),
                BorderFactory.createEmptyBorder(10, 3, 10, 0)));
        add(definitionTextArea);
    }

}
