package org.flashcards.Backend;

import org.flashcards.Backend.enums.FlashcardStates;

public class FlashcardWithImage extends Flashcard {

    protected byte[] imageQuestion;
    protected String imageDescription;
    public FlashcardWithImage(byte[] imageQuestion, String imageDescription, String answer, FlashcardStates state) {
        super(answer, state);
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
}
