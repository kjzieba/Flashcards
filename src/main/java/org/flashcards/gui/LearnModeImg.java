package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.ImgCard;
import org.flashcards.collection.*;
import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.gui.components.CardComponent;
import org.flashcards.gui.components.FlashCardComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static org.flashcards.gui.FlashCardsImgMode.ByteArrayToImage;

public class LearnModeImg extends JPanel {
    private final Initializer initializer;
    private final JButton cardImgView;
    private final JButton cardReverseView;
    private final JButton flagButton = new FlashCardComponent().flagButtonComponent(655,135);
    private final JButton redFlagButton = new FlashCardComponent().redFlagButtonComponent(655,135);
    public static final JLabel knowImg = new JLabel();
    public static final JLabel stillLearningImg = new JLabel();
    private ImgCard imgCard = App.getInstance().createEmptyImgCard();
    private ImgFlashcardCollection imgFlashcardCollection = new ImgFlashcardCollection("", new ArrayList<>(), 0L);
    private Iterator it;

    public LearnModeImg(Initializer initializer) {
        this.initializer = initializer;
        cardReverseView = new CardComponent().cardReverseButtonComponent(318, 135, 323, 175, "");
        cardImgView = new CardComponent().cardButtonImgComponent(318, 135, 323, 175, "");

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
            App.getInstance().saveEditedImgRepo(imgFlashcardCollection);
            App.getInstance().getAllCards().saveList(App.getInstance().getCurrentRepo());
            remove(redFlagButton);
            remove(flagButton);
        });
        add(backButton);
    }

    private void getCardView() {
        cardImgView.addActionListener(e -> {
            cardImgView.setVisible(false);
            cardReverseView.setVisible(true);
        });
        add(cardImgView);
    }

    private void getCardViewReverse() {
        cardReverseView.addActionListener(e -> {
            cardReverseView.setVisible(false);
            cardImgView.setVisible(true);
        });
        add(cardReverseView);
    }

    private void getFlagButtonComponent() {
        if (Objects.equals(imgCard.getFlashcardState().toString(), "normal")) {
            redFlagButton.setVisible(false);
            flagButton.setVisible(true);
            flagButton.addActionListener(e -> {
                flagButton.setVisible(false);
                redFlagButton.setVisible(true);
                App.getInstance().changeStateToFlaggedImg(imgCard);
                imgCard.action(cardReverseView);
                imgCard.action(cardImgView);
            });
            redFlagButton.addActionListener(e1 -> {
                redFlagButton.setVisible(false);
                flagButton.setVisible(true);
                App.getInstance().changeStateToNotFlaggedImg(imgCard);
                imgCard.action(cardReverseView);
                imgCard.action(cardImgView);
            });
            add(flagButton);
            add(redFlagButton);
        } else {
            flagButton.setVisible(false);
            redFlagButton.setVisible(true);
            redFlagButton.addActionListener(e2 -> {
                flagButton.setVisible(true);
                redFlagButton.setVisible(false);
                App.getInstance().changeStateToNotFlaggedImg(imgCard);
                imgCard.action(cardImgView);
                imgCard.action(cardReverseView);
            });
            flagButton.addActionListener(e3 -> {
                flagButton.setVisible(false);
                redFlagButton.setVisible(true);
                App.getInstance().changeStateToFlaggedImg(imgCard);
                imgCard.action(cardImgView);
                imgCard.action(cardReverseView);
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
        knowImg.setText("Know               " + LearnMode.knowPoints);
        stillLearningImg.setText("Still learning   " + LearnMode.stillLearningPoints);
        knowImg.setFont(new Font("Arbutus", Font.PLAIN, 15));
        knowImg.setForeground(Color.white);
        knowImg.setBounds(36, 427, 210, 120);
        stillLearningImg.setFont(new Font("Arbutus", Font.PLAIN, 15));
        stillLearningImg.setForeground(Color.white);
        stillLearningImg.setBounds(36, 462, 210, 120);
        add(knowImg);
        add(stillLearningImg);
    }

    public void setRepoImg() {
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "T");
        imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;
    }

    public void setCardImg() {
        Long id = App.getInstance().getCurrentRepo();
        ImgFlashcardCollection list = (ImgFlashcardCollection) App.getInstance().getAllCards().getFlashcardList(id, "I");
        it = new ImgFlashcardIterator(list);
        ImgCard card;
        if (!it.isDoneRight()) {
            card = (ImgCard) it.next();
            imgCard = card;
            getFlagButtonComponent();
            card.action(cardImgView);
            card.action(cardReverseView);
            cardImgView.setIcon((ByteArrayToImage(card.getImageQuestion())));
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardImgView.setVisible(true);
        }
    }

    private void knowThat() {
        if (!it.isDoneRight()) {
            ImgCard card = (ImgCard) it.next();
            card.action(cardReverseView);
            card.action(cardImgView);
            imgCard = card;
            getFlagButtonComponent();
            cardImgView.setIcon((ByteArrayToImage(card.getImageQuestion())));
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardImgView.setVisible(true);
            LearnMode.knowPoints++;
            knowImg.setText("Know               " + LearnMode.knowPoints);
            stillLearningImg.setText("Still learning   " + LearnMode.stillLearningPoints);
        }
        else {
            LearnMode.knowPoints++;
            knowImg.setText("Know               " + LearnMode.knowPoints);
            stillLearningImg.setText("Still learning   " + LearnMode.stillLearningPoints);
            GUInitializer.flag = false;
            initializer.update(GUInitializer.Panel.Result);
        }
    }

    private void stillLearningThat() {
        if (!it.isDoneRight()) {
            ImgCard card = (ImgCard) it.next();
            card.action(cardReverseView);
            card.action(cardImgView);
            imgCard = card;
            getFlagButtonComponent();
            cardImgView.setIcon((ByteArrayToImage(card.getImageQuestion())));
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardImgView.setVisible(true);
            LearnMode.stillLearningPoints++;
            knowImg.setText("Know               " + LearnMode.knowPoints);
            stillLearningImg.setText("Still learning   " + LearnMode.stillLearningPoints);
        }
        else {
            LearnMode.stillLearningPoints++;
            knowImg.setText("Know               " + LearnMode.knowPoints);
            stillLearningImg.setText("Still learning   " + LearnMode.stillLearningPoints);
            GUInitializer.flag = false;
            initializer.update(GUInitializer.Panel.Result);
        }
    }


}
