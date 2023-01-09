package org.flashcards.gui;

import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;

public class Result extends JPanel {
    private final Initializer initializer;

    public Result(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getCustomTitle();
        getPointsText(1,3);
        getBackMenuButton();
        getTryAgainButton();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Result");
        title.setFont(new Font("Arbutus", Font.PLAIN, 30));
        title.setForeground(Color.white);
        title.setBounds(400, 120, 700, 50);
        add(title);
    }

    private void getPointsText(int correctPoints, int incorrectPoints) {
        JLabel correctLabel = new JLabel("Correct       " + correctPoints);
        JLabel incorrectLabel = new JLabel("Incorrect    " + incorrectPoints);
        correctLabel.setFont(new Font("Arbutus", Font.PLAIN, 20));
        correctLabel.setForeground(Color.white);
        correctLabel.setBounds(286, 231, 210, 120);
        incorrectLabel.setFont(new Font("Arbutus", Font.PLAIN, 20));
        incorrectLabel.setForeground(Color.white);
        incorrectLabel.setBounds(286, 284, 210, 120);
        add(correctLabel);
        add(incorrectLabel);
    }

    private void getTryAgainButton() {
        JButton tryAgainButton = new ButtonComponents().smallButtonComponent("Try again", 757, 406);
        tryAgainButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.TestMode);
        });
        add(tryAgainButton);
    }

    private void getBackMenuButton() {
        JButton backMenuButton = new ButtonComponents().smallButtonComponent("Back to menu", 757, 456);
        backMenuButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        add(backMenuButton);
    }




}
