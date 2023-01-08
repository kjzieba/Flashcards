package org.flashcards.Gui.Components;

import javax.swing.*;
import java.awt.*;

public class FlashCardComponent {
    public JButton rightArrowButtonComponent(int x, int y) {
        JButton rightArrow = new JButton();
        rightArrow.setIcon(new ImageIcon("src/main/resources/img/rightArrow.png"));
        rightArrow.setBounds(x, y, 30, 30);
        rightArrow.setBorderPainted(false);
        rightArrow.setContentAreaFilled(false);
        rightArrow.setFocusPainted(false);
        return rightArrow;
    }

    public JButton leftArrowButtonComponent(int x, int y) {
        JButton leftArrow = new JButton();
        leftArrow.setIcon(new ImageIcon("src/main/resources/img/leftArrow.png"));
        leftArrow.setBounds(x, y, 30, 30);
        leftArrow.setBorderPainted(false);
        leftArrow.setContentAreaFilled(false);
        leftArrow.setFocusPainted(false);
        return leftArrow;
    }

    public JButton flagButtonComponent(int x, int y) {
        JButton flagButton = new JButton();
        flagButton.setIcon(new ImageIcon("src/main/resources/img/flag.png"));
        flagButton.setBounds(x, y, 30, 30);
        flagButton.setBorderPainted(false);
        flagButton.setContentAreaFilled(false);
        flagButton.setFocusPainted(false);
        return flagButton;
    }

    public JButton redFlagButtonComponent(int x, int y) {
        JButton redFlagButton = new JButton();
        redFlagButton.setIcon(new ImageIcon("src/main/resources/img/redFlag.png"));
        redFlagButton.setBounds(x, y, 30, 30);
        redFlagButton.setBorderPainted(false);
        redFlagButton.setContentAreaFilled(false);
        redFlagButton.setFocusPainted(false);
        return redFlagButton;
    }
}
