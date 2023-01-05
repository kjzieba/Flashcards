package org.flashcards.src;

import org.flashcards.src.states.NormalState;
import org.flashcards.src.states.State;

public abstract class Flashcard implements State {

    protected String answer;
    protected State flashcardState;


    protected Flashcard(String answer){
        this.answer = answer;
        this.flashcardState = new NormalState();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public State getFlashcardState() {
        return flashcardState;
    }

    public void setFlashcardState(State flashcardState) {
        this.flashcardState = flashcardState;
    }

    @Override
    public void action() {
        this.flashcardState.action();
    }

}
