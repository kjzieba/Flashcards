package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.TxtCard;
import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.Iterator;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.collection.TxtFlashcardIterator;
import org.flashcards.gui.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class FlashCardsMode extends JPanel {

    private final Initializer initializer;
    private final JButton cardView;
    private final JButton cardReverseView;
    private final JButton cardImg;
    private final JButton flagButton = new FlashCardComponent().flagButtonComponent(655, 135);
    private final JButton redFlagButton = new FlashCardComponent().redFlagButtonComponent(655, 135);

    private TxtCard txtCard = App.getInstance().createEmptyTxtCard();
    private TxtFlashcardCollection txtFlashcardCollection = new TxtFlashcardCollection("", new ArrayList<>(), 0L);
    private Iterator it;

    public FlashCardsMode(Initializer initializer) {
        this.initializer = initializer;
        cardView = new CardComponent().cardButtonComponent(318, 135, 323, 175, "");
        cardReverseView = new CardComponent().cardReverseButtonComponent(318, 135, 323, 175, "");
        cardImg = new CardComponent().cardButtonImgComponent(318, 135, 323, 175, "src/main/resources/img/panini.jpg");

        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getCardView();
        getCardViewReverse();
        getBackButton();
        getLeftArrow();
        getRightArrow();

    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
            App.getInstance().deleteRepo("T");
            App.getInstance().saveEditedTxtRepo(txtFlashcardCollection);
            App.getInstance().getAllCards().saveList(App.getInstance().getCurrentRepo());
            remove(redFlagButton);
            remove(flagButton);
        });
        add(backButton);
    }

    private void getCardView() {
//        cardView.addActionListener(e -> {
//            cardView.setVisible(false);
//            cardReverseView.setVisible(true);
//        });
//        add(cardView);
        cardImg.addActionListener(e -> {
            cardImg.setVisible(false);
            cardReverseView.setVisible(true);
        });
        add(cardImg);
    }

    private void getCardViewReverse() {
        cardReverseView.addActionListener(e -> {
            cardReverseView.setVisible(false);
            cardView.setVisible(true);
            cardImg.setVisible(true);
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

    public void setRepo() {
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "T");
        txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;
    }

    public void getFlagButtonComponent() {
        if (Objects.equals(txtCard.getFlashcardState().toString(), "normal")) {
            redFlagButton.setVisible(false);
            flagButton.setVisible(true);
            flagButton.addActionListener(e -> {
                flagButton.setVisible(false);
                redFlagButton.setVisible(true);
                App.getInstance().changeStateToFlagged(txtCard);
                txtCard.action(cardReverseView);
                txtCard.action(cardView);
            });
            redFlagButton.addActionListener(e1 -> {
                redFlagButton.setVisible(false);
                flagButton.setVisible(true);
                App.getInstance().changeStateToNotFlagged(txtCard);
                txtCard.action(cardReverseView);
                txtCard.action(cardView);
            });
            add(flagButton);
            add(redFlagButton);
        } else {
            flagButton.setVisible(false);
            redFlagButton.setVisible(true);
            redFlagButton.addActionListener(e2 -> {
                flagButton.setVisible(true);
                redFlagButton.setVisible(false);
                App.getInstance().changeStateToNotFlagged(txtCard);
                txtCard.action(cardView);
                txtCard.action(cardReverseView);
            });
            flagButton.addActionListener(e3 -> {
                flagButton.setVisible(false);
                redFlagButton.setVisible(true);
                App.getInstance().changeStateToFlagged(txtCard);
                txtCard.action(cardView);
                txtCard.action(cardReverseView);
            });
            add(flagButton);
            add(redFlagButton);
        }
    }

    public void setCard() {
        Long id = App.getInstance().getCurrentRepo();
        TxtFlashcardCollection list = (TxtFlashcardCollection) App.getInstance().getAllCards().getFlashcardList(id, "T");
        it = new TxtFlashcardIterator(list);
        TxtCard card;
        if (!it.isDoneRight()) {
            card = (TxtCard) it.next();
            txtCard = card;
            getFlagButtonComponent();
            card.action(cardView);
            card.action(cardReverseView);
            cardView.setText(card.getTextQuestion());
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardView.setVisible(true);
        }
    }

    public void nextCard() {
        if (!it.isDoneRight()) {
            TxtCard card = (TxtCard) it.next();
            card.action(cardReverseView);
            card.action(cardView);
            txtCard = card;
            getFlagButtonComponent();
            cardView.setText(card.getTextQuestion());
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardView.setVisible(true);
        }
    }

    public void prevCard() {
        if (!it.isDoneLeft()) {
            TxtCard card = (TxtCard) it.prev();
            card.action(cardReverseView);
            card.action(cardView);
            txtCard = card;
            getFlagButtonComponent();
            cardView.setText(card.getTextQuestion());
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardView.setVisible(true);
        }
    }
}
