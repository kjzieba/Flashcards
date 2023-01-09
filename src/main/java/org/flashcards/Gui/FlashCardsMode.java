package org.flashcards.Gui;

import org.flashcards.Gui.Components.*;

import javax.swing.*;
import java.awt.*;

public class FlashCardsMode extends JPanel {

    private final Initializer initializer;
    private JButton cardView = new CardComponent().cardButtonComponent(318, 135, 323, 175, "dupa");
    private JButton cardReverseView = new CardComponent().cardReverseButtonComponent(318, 135, 323, 175, "chuj");
    private JButton flagButton = new FlashCardComponent().flagButtonComponent(655,135);
    private JButton redFlagButton = new FlashCardComponent().redFlagButtonComponent(655,135);
    public FlashCardsMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getCardView();
        getCardViewReverse();
        getBackButton();
        getLeftArrow();
        getRightArrow();
        getFlagButtonComponent();
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


    private void getLeftArrow() {
        JButton leftArrow = new FlashCardComponent().leftArrowButtonComponent(318,329);
        leftArrow.addActionListener(e -> {

        });
        add(leftArrow);
    }

    private void getRightArrow() {
        JButton rightArrow = new FlashCardComponent().rightArrowButtonComponent(611,329);
        rightArrow.addActionListener(e -> {

        });
        add(rightArrow);
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
}
