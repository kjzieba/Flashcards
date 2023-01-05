package org.flashcards.Backend;

import org.flashcards.Backend.enums.FlashcardStates;

public abstract class Flashcard {

    protected String answer;

    protected FlashcardStates state;

    protected Flashcard(String answer, FlashcardStates state){
        this.answer = answer;
        this.state = state;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
