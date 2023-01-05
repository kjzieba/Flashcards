package org.flashcards.src;

import org.flashcards.src.states.State;

public class FlashcardWithText extends Flashcard {

    protected String textQuestion;

    public FlashcardWithText(String textQuestion, String answer, State flashcardState) {
        super(answer, flashcardState);
        this.textQuestion = textQuestion;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    @Override
    public void action() {

    }
}
