package org.flashcards.Gui;

import org.flashcards.Gui.Components.*;

import javax.swing.*;
import java.awt.*;

public class FlashCardsMode extends JPanel {

    private final Initializer initializer;
    private final CardInit cardInit = new CardInit();
    public FlashCardsMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getCardView();
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
        cardInit.setCardView(new CardView(CardFactory.getRectangle()));
        System.out.println("dupa");
    }

    private void getLeftArrow() {
        JButton leftArrow = new FlashCardComponent().leftArrowButtonComponent(262,314);
        leftArrow.addActionListener(e -> {

        });
        add(leftArrow);
    }

    private void getRightArrow() {
        JButton rightArrow = new FlashCardComponent().rightArrowButtonComponent(631,314);
        rightArrow.addActionListener(e -> {

        });
        add(rightArrow);
    }

    private void getFlagButtonComponent() {
        JButton flagButton = new FlashCardComponent().flagButtonComponent(697,102);
        flagButton.addActionListener(e -> {
            flagButton.setVisible(false);
            JButton redFlagButton = new FlashCardComponent().redFlagButtonComponent(697,102);
            add(redFlagButton);
            redFlagButton.addActionListener(e1 -> {
                redFlagButton.setVisible(false);
                flagButton.setVisible(true);
            });
        });
        add(flagButton);
    }
}
