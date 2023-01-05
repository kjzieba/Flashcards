package org.flashcards.src;

import org.flashcards.src.states.State;

public class FlashcardWithImage extends Flashcard {

    protected byte[] imageQuestion;
    protected String imageDescription;

    public FlashcardWithImage(byte[] imageQuestion, String imageDescription, String answer, State flashcardState) {
        super(answer, flashcardState);
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

    }
}
