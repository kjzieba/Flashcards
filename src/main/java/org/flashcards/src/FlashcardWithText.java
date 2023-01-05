package org.flashcards.src;

import org.flashcards.src.states.State;

public class FlashcardWithText extends Flashcard {

    protected String textQuestion;

    public FlashcardWithText(String answer, String textQuestion) {
        super(answer);
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
        this.flashcardState.action();
    }
}
