package org.flashcards.gui;

import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;

import static org.flashcards.gui.GUInitializer.flag;

public class Result extends JPanel {
    private final Initializer initializer;
    private JLabel correctLabel = new JLabel();
    private JLabel incorrectLabel = new JLabel();

    public Result(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getCustomTitle();
        getPointsText();
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

    private void getPointsText() {
        correctLabel.setFont(new Font("Arbutus", Font.PLAIN, 20));
        correctLabel.setForeground(Color.white);
        correctLabel.setBounds(286, 231, 310, 120);
        incorrectLabel.setFont(new Font("Arbutus", Font.PLAIN, 20));
        incorrectLabel.setForeground(Color.white);
        incorrectLabel.setBounds(286, 284, 310, 120);
        add(correctLabel);
        add(incorrectLabel);
    }

    private void getTryAgainButton() {
        JButton tryAgainButton = new ButtonComponents().smallButtonComponent("Try again", 757, 406);
        tryAgainButton.addActionListener(e -> {
            if(flag) {
                initializer.update(GUInitializer.Panel.TestMode);
            } else if (!flag) {
                initializer.update(GUInitializer.Panel.LearnMode);

            }
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

    public void setScore(String First, String Second, int right, int wrong){
        correctLabel.setText(First + "        " + right);
        incorrectLabel.setText(Second + "    " + wrong);
    }
}
