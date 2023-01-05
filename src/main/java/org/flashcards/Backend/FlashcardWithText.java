package org.flashcards.Backend;

import org.flashcards.Backend.enums.FlashcardStates;

public class FlashcardWithText extends Flashcard {

    protected String textQuestion;

    public FlashcardWithText(String textQuestion, String answer, FlashcardStates state) {
        super(answer, state);
        this.textQuestion = textQuestion;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }
}
