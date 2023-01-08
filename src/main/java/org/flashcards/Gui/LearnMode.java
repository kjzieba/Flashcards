package org.flashcards.Gui;

import org.flashcards.Gui.Components.ButtonComponents;
import org.flashcards.Gui.Components.CardComponent;
import org.flashcards.Gui.Components.FlashCardComponent;

import javax.swing.*;
import java.awt.*;

public class LearnMode extends JPanel {
    private final Initializer initializer;
    private JButton cardView = new CardComponent().cardButtonComponent(318, 135, 323, 175, "dupa");
    private JButton cardReverseView = new CardComponent().cardReverseButtonComponent(318, 135, 323, 175, "chuj");
    private JButton flagButton = new FlashCardComponent().flagButtonComponent(655,135);
    private JButton redFlagButton = new FlashCardComponent().redFlagButtonComponent(655,135);
    public LearnMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getCardView();
        getCardViewReverse();
        getBackButton();
        getFlagButtonComponent();
        getStillLearningButtonComponent();
        getKnowButtonComponent();
        getCustomPointBoard(2,32);
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13,12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        add(backButton);
    }

    private void getCardView() {
        cardView.addActionListener(e -> {
            cardView.setVisible(false);
            cardReverseView.setVisible(true);
        });
        add(cardView);
    }

    private void getCardViewReverse() {
        cardReverseView.addActionListener(e -> {
            cardReverseView.setVisible(false);
            cardView.setVisible(true);
        });
        add(cardReverseView);
    }

    private void getFlagButtonComponent() {
        redFlagButton.setVisible(false);
        flagButton.addActionListener(e -> {
            flagButton.setVisible(false);
            redFlagButton.setVisible(true);
        });
        redFlagButton.addActionListener(e1 -> {
            redFlagButton.setVisible(false);
            flagButton.setVisible(true);
        });
        add(redFlagButton);
        add(flagButton);
    }

    private void getStillLearningButtonComponent(){
        JButton learningButton = new FlashCardComponent().learningButtonComponent(262,335, "Still learning");
        learningButton.addActionListener(e -> {

        });
        add(learningButton);
    }

    private void getKnowButtonComponent(){
        JButton learningButton = new FlashCardComponent().learningButtonComponent(548,335, "Know");
        learningButton.addActionListener(e -> {

        });
        add(learningButton);
    }

    private void getCustomPointBoard(int knowPoints, int stillLearningPoints) {
        JLabel know = new JLabel("Know               " + knowPoints);
        JLabel stillLearning = new JLabel("Still learning   " + stillLearningPoints);
        know.setFont(new Font("Arbutus", Font.PLAIN, 15));
        know.setForeground(Color.white);
        know.setBounds(36, 427, 210, 120);
        stillLearning.setFont(new Font("Arbutus", Font.PLAIN, 15));
        stillLearning.setForeground(Color.white);
        stillLearning.setBounds(36, 462, 210, 120);
        add(know);
        add(stillLearning);
    }
}
