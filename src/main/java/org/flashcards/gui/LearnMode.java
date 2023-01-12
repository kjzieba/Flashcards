package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.TxtCard;
import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.Iterator;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.collection.TxtFlashcardIterator;
import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.gui.components.CardComponent;
import org.flashcards.gui.components.FlashCardComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class LearnMode extends JPanel {
    private final Initializer initializer;
    private JButton cardView;
    private JButton cardReverseView;
    private JButton flagButton = new FlashCardComponent().flagButtonComponent(655,135);
    private JButton redFlagButton = new FlashCardComponent().redFlagButtonComponent(655,135);
    private int knowPoints = 0;
    private int stillLearningPoints = 0;
    private JLabel know = new JLabel();
    private JLabel stillLearning = new JLabel();
    private TxtCard txtCard = App.getInstance().createEmptyTxtCard();
    private TxtFlashcardCollection txtFlashcardCollection = new TxtFlashcardCollection("", new ArrayList<>(), 0L);
    private Iterator it;

    public LearnMode(Initializer initializer) {
        this.initializer = initializer;
        cardView = new CardComponent().cardButtonComponent(318, 135, 323, 175, "");
        cardReverseView = new CardComponent().cardReverseButtonComponent(318, 135, 323, 175, "");

        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getCardView();
        getCardViewReverse();
        getBackButton();
        getFlagButtonComponent();
        getStillLearningButtonComponent();
        getKnowButtonComponent();
        getCustomPointBoard();
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

    private void getStillLearningButtonComponent(){
        JButton learningButton = new FlashCardComponent().learningButtonComponent(262,335, "Still learning");
        learningButton.addActionListener(e -> {
            stillLearningThat();
        });
        add(learningButton);
    }

    private void getKnowButtonComponent(){
        JButton learningButton = new FlashCardComponent().learningButtonComponent(548,335, "Know");
        learningButton.addActionListener(e -> {
            knowThat();
        });
        add(learningButton);
    }

    public void getCustomPointBoard() {
        know.setText("Know               " + knowPoints);
        stillLearning.setText("Still learning   " + stillLearningPoints);
        know.setFont(new Font("Arbutus", Font.PLAIN, 15));
        know.setForeground(Color.white);
        know.setBounds(36, 427, 210, 120);
        stillLearning.setFont(new Font("Arbutus", Font.PLAIN, 15));
        stillLearning.setForeground(Color.white);
        stillLearning.setBounds(36, 462, 210, 120);
        add(know);
        add(stillLearning);
    }

    public void setRepo() {
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "T");
        txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;
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

    private void knowThat() {
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
            knowPoints++;
            know.setText("Know               " + knowPoints);
            stillLearning.setText("Still learning   " + stillLearningPoints);
        }
        else {
            knowPoints++;
            know.setText("Know               " + knowPoints);
            stillLearning.setText("Still learning   " + stillLearningPoints);
            GUInitializer.flag = false;
            initializer.update(GUInitializer.Panel.Result);
        }
    }

    private void stillLearningThat() {
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
            stillLearningPoints++;
            know.setText("Know               " + knowPoints);
            stillLearning.setText("Still learning   " + stillLearningPoints);
        }
        else {
            stillLearningPoints++;
            know.setText("Know               " + knowPoints);
            stillLearning.setText("Still learning   " + stillLearningPoints);
            GUInitializer.flag = false;
            initializer.update(GUInitializer.Panel.Result);
        }
    }

    public int getKnowWords() {
        return knowPoints;
    }

    public int getStillLearningWords() {
        return stillLearningPoints;
    }

    public void setKnowWords(int knowPoints) {
        this.knowPoints = knowPoints;
    }

    public void setStillLearningWords(int stillLearningPoints) {
        this.stillLearningPoints = stillLearningPoints;
    }

}
