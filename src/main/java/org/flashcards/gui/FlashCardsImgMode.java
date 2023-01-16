package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.ImgCard;
import org.flashcards.collection.*;
import org.flashcards.gui.components.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FlashCardsImgMode extends JPanel {

    private final Initializer initializer;
    private final JButton cardReverseView;
    private final JButton cardImgView;
    private final JButton flagButton = new FlashCardComponent().flagButtonComponent(655, 135);
    private final JButton redFlagButton = new FlashCardComponent().redFlagButtonComponent(655, 135);

    private ImgCard imgCard = App.getInstance().createEmptyImgCard();
    private ImgFlashcardCollection imgFlashcardCollection = new ImgFlashcardCollection("", new ArrayList<>(), 0L);
    private Iterator it;

    public FlashCardsImgMode(Initializer initializer) {
        this.initializer = initializer;
        cardReverseView = new CardComponent().cardReverseButtonComponent(318, 135, 323, 175, "");
        cardImgView = new CardComponent().cardButtonImgComponent(318, 135, 323, 175, "");

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

    public void setRepoImg() {
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "T");
        imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;
    }

    public void getFlagButtonComponent() {
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

    public void setCardImg() {
        Long id = App.getInstance().getCurrentRepo();
        ImgFlashcardCollection list = (ImgFlashcardCollection) App.getInstance().getAllCards().getFlashcardList(id, "T");
        it = new ImgFlashcardIterator(list);
        ImgCard card;
        if (!it.isDoneRight()) {
            card = (ImgCard) it.next();
            imgCard = card;
            getFlagButtonComponent();
            card.action(cardImgView);
            card.action(cardReverseView);
            cardImgView.setIcon(ByteArrayToImage(card.getImageQuestion()));
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardImgView.setVisible(true);
        }
    }

    public void nextCard() {
        if (!it.isDoneRight()) {
            ImgCard card = (ImgCard) it.next();
            card.action(cardReverseView);
            card.action(cardImgView);
            imgCard = card;
            getFlagButtonComponent();
            cardImgView.setIcon(ByteArrayToImage(card.getImageQuestion()));
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardImgView.setVisible(true);
        }
    }

    public void prevCard() {
        if (!it.isDoneLeft()) {
            ImgCard card = (ImgCard) it.prev();
            card.action(cardReverseView);
            card.action(cardImgView);
            imgCard = card;
            getFlagButtonComponent();
            cardImgView.setIcon((ByteArrayToImage(card.getImageQuestion())));
            cardReverseView.setText(card.getAnswer());
            cardReverseView.setVisible(false);
            cardImgView.setVisible(true);
        }
    }

    public static ImageIcon ByteArrayToImage(byte[] bytes){
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
            ImageIcon icon = new ImageIcon(img);
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(323, 175,  Image.SCALE_DEFAULT);
            icon = new ImageIcon(newimg);
            return icon;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

