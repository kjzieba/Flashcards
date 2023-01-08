package org.flashcards.src;

import java.util.Arrays;

public class ImgCard extends Card {

    protected byte[] imageQuestion;
    protected String imageDescription;

    public ImgCard(Long id, String answer, byte[] imageQuestion, String imageDescription) {
        super(id, answer);
        this.imageQuestion = imageQuestion;
        this.imageDescription = imageDescription;
    }


    public byte[] getImageQuestion() {
        return imageQuestion;
    }

    public void setImageQuestion(byte[] imageQuestion) {
        this.imageQuestion = imageQuestion;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    @Override
    public void action() {
        this.flashcardState.action();
    }

    @Override
    public String toString() {
        return "ImgCard{" +
                ", id=" + id +
                "imageQuestion=" + Arrays.toString(imageQuestion) +
                ", imageDescription='" + imageDescription + '\'' +
                ", answer='" + answer + '\'' +
                ", flashcardState=" + flashcardState +
                '}';
    }
}
