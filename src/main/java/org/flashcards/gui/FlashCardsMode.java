package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.TxtCard;
import org.flashcards.collection.Iterator;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.collection.TxtFlashcardIterator;
import org.flashcards.gui.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class FlashCardsMode extends JPanel {

    private final Initializer initializer;
    private JButton cardView;
    private JButton cardReverseView;
    private JButton flagButton = new FlashCardComponent().flagButtonComponent(655, 135);
    private JButton redFlagButton = new FlashCardComponent().redFlagButtonComponent(655, 135);

    private Iterator it;

    public FlashCardsMode(Initializer initializer) {
        this.initializer = initializer;
        cardView = new CardComponent().cardButtonComponent(318, 135, 323, 175, "");
        cardReverseView = new CardComponent().cardReverseButtonComponent(318, 135, 323, 175, "");

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
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
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
        JButton leftArrow = new FlashCardComponent().leftArrowButtonComponent(318, 329);
        leftArrow.addActionListener(e -> {
            prevCard();
        });
        add(leftArrow);
    }

    private void getRightArrow() {
        JButton rightArrow = new FlashCardComponent().rightArrowButtonComponent(611, 329);
        rightArrow.addActionListener(e -> {
            nextCard();
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

    public void setCard() {
        Long id = App.getInstance().getCurrentRepo();
        TxtFlashcardCollection list = (TxtFlashcardCollection) App.getInstance().getAllCards().getFlashcardList(id, "T");
        it = new TxtFlashcardIterator(list);
        TxtCard card;
        if (!it.isDoneRight()) {
            card = (TxtCard) it.next();
            card.action(cardView);
            card.action(cardReverseView);
            cardView.setText(card.getTextQuestion());
            cardReverseView.setText(card.getAnswer());
        }
    }

    public void nextCard() {
        if (!it.isDoneRight()) {
            TxtCard card = (TxtCard) it.next();
            card.action(cardView);
            card.action(cardReverseView);
            cardView.setText(card.getTextQuestion());
            cardReverseView.setText(card.getAnswer());
        }
    }

    public void prevCard() {
        if (!it.isDoneLeft()) {
            TxtCard card = (TxtCard) it.prev();
            card.action(cardView);
            card.action(cardReverseView);
            cardView.setText(card.getTextQuestion());
            cardReverseView.setText(card.getAnswer());
        }
    }
}
