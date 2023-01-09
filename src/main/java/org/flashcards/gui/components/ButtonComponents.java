package org.flashcards.gui.components;

import org.flashcards.gui.GUInitializer;

import javax.swing.*;
import java.awt.*;

public class ButtonComponents {

    public JButton addButtonComponent(int x, int y) {
        JButton addButton = new JButton();
        addButton.setIcon(new ImageIcon("src/main/resources/img/addButton.png"));
        addButton.setBounds(x, y, 40, 40);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        return addButton;
    }

    public JButton backButtonComponent(int x, int y) {
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/main/resources/img/backButton.png"));
        backButton.setBounds(x, y, 30, 30);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        return backButton;
    }

    public JButton bigButtonComponent(String text, int x, int y) {
        JButton bigButton = new JButton(text);
        bigButton.setBackground(GUInitializer.buttonColor);
        bigButton.setForeground(Color.white);
        bigButton.setOpaque(true);
        bigButton.setBorderPainted(false);
        bigButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        bigButton.setBounds(x, y, 210, 65);
        return bigButton;
    }

    public JButton cardTitleButtonComponent(String text, int x, int y) {
        JButton cardTitle = new JButton(text);
        cardTitle.setBackground(GUInitializer.buttonColor);
        cardTitle.setForeground(Color.white);
        cardTitle.setOpaque(true);
        cardTitle.setFont(new Font("Arbutus", Font.PLAIN, 16));
        cardTitle.setBounds(x, y, 210, 80);
        return cardTitle;
    }
}
