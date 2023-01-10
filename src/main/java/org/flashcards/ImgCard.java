package org.flashcards;

import java.util.Arrays;

public class ImgCard extends Card {

    protected byte[] imageQuestion;

    public ImgCard(Long id, String answer, byte[] imageQuestion) {
        super(id, answer);
        this.imageQuestion = imageQuestion;
    }


    public byte[] getImageQuestion() {
        return imageQuestion;
    }

    public void setImageQuestion(byte[] imageQuestion) {
        this.imageQuestion = imageQuestion;
    }


    @Override
    public void action() {
        this.flashcardState.action();
    }

    @Override
    public String toString() {
        return id + "|" + Arrays.toString(imageQuestion) + "|"+ answer + "|" + flashcardState;
    }
}
