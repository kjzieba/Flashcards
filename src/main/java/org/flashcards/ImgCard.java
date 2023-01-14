package org.flashcards;

import javax.swing.*;
import java.util.Arrays;

public class ImgCard extends Card {

    protected byte[] imageQuestion;

    public ImgCard(Long id, String answer, byte[] imageQuestion) {
        super(id, answer);
        this.imageQuestion = imageQuestion;
    }

    public MementoImgCard saveToMemento()
    {
        System.out.println("Saving time to Memento");
        return new MementoImgCard(id, imageQuestion, answer);
    }

    public byte[] getImageQuestion() {
        return imageQuestion;
    }

    public void setImageQuestion(byte[] imageQuestion) {
        this.imageQuestion = imageQuestion;
    }


    @Override
    public void action(JButton button) {
        this.flashcardState.action(button);
    }

    @Override
    public String toString() {
        return id + "|" + Arrays.toString(imageQuestion) + "|"+ answer + "|" + flashcardState;
    }
}
