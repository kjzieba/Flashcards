package org.flashcards.Gui.Components;

import org.flashcards.Gui.GUInitializer;

import javax.swing.*;
import java.awt.*;

public class CardComponent {

    public JButton cardButtonComponent(int x, int y, int width, int height, String term) {
        JButton cardButton = new JButton(term);
        cardButton.setBounds(x, y, width, height);
        cardButton.setFont(new Font("Arbutus", Font.PLAIN, 20));
        cardButton.setBackground(GUInitializer.buttonColor);
        cardButton.setForeground(Color.white);
        cardButton.setOpaque(true);
        cardButton.setBorderPainted(false);
        cardButton.setFocusPainted(false);
        return cardButton;
    }

    public JButton cardReverseButtonComponent(int x, int y, int width, int height, String definition) {
        JButton cardReverseButton = new JButton(definition);
        cardReverseButton.setBounds(x, y, width, height);
        cardReverseButton.setFont(new Font("Arbutus", Font.PLAIN, 20));
        cardReverseButton.setBackground(Color.black);
        cardReverseButton.setForeground(Color.white);
        cardReverseButton.setOpaque(true);
        cardReverseButton.setBorderPainted(false);
        cardReverseButton.setFocusPainted(false);
        return cardReverseButton;
    }

}
