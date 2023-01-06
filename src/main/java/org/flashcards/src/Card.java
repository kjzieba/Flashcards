package org.flashcards.src;

import org.flashcards.src.states.NormalState;
import org.flashcards.src.states.State;

public abstract class Card implements State {

    protected Long id;
    protected String answer;
    protected State flashcardState;


    protected Card(Long id, String answer){
        this.id = id;
        this.answer = answer;
        this.flashcardState = new NormalState();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
