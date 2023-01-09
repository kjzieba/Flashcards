package org.flashcards.gui;

import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;

public class QuestionsAmount extends JPanel {

    private final Initializer initializer;
    public static int amount;
    JLabel questionsAmountTitle = new JLabel();
    public QuestionsAmount(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getQuestionsAmountTitle();
        getPlusIcon();
        getMinusIcon();
        getStartButton();
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13,12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        add(backButton);
    }

    private void getQuestionsAmountTitle() {
        questionsAmountTitle.setText(amount + " questions");
        questionsAmountTitle.setFont(new Font("Arbutus", Font.PLAIN, 17));
        questionsAmountTitle.setForeground(Color.white);
        questionsAmountTitle.setBounds(423, 190, 700, 50);
        add(questionsAmountTitle);
    }

    private void getPlusIcon() {
        JButton plusIcon = new JButton();
        plusIcon.setIcon(new ImageIcon("src/main/resources/img/plusIcon.png"));
        plusIcon.setBounds(575, 190, 45, 45);
        plusIcon.setBorderPainted(false);
        plusIcon.setContentAreaFilled(false);
        plusIcon.setFocusPainted(false);
        plusIcon.addActionListener(e -> {
            amount++;
            questionsAmountTitle.setText(amount + " questions");
        });
        add(plusIcon);
    }

    private void getMinusIcon() {
        JButton minusIcon = new JButton();
        minusIcon.setIcon(new ImageIcon("src/main/resources/img/minusIcon.png"));
        minusIcon.setBounds(358, 190, 45, 45);
        minusIcon.setBorderPainted(false);
        minusIcon.setContentAreaFilled(false);
        minusIcon.setFocusPainted(false);
        minusIcon.addActionListener(e -> {
            if (amount > 0) {
                amount--;
                questionsAmountTitle.setText(amount + " questions");
            }
        });
        add(minusIcon);
    }

    private void getStartButton() {
        JButton startButton = new ButtonComponents().smallButtonComponent("Start test", 406, 290);
        startButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.TestMode);
            TestMode.questionNumber.setText(TestMode.actualWord + "/" + QuestionsAmount.amount);
        });
        add(startButton);
    }




}

