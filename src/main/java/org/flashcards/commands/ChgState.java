package org.flashcards.commands;

import org.flashcards.Card;
import org.flashcards.enums.States;
import org.flashcards.states.FlaggedState;
import org.flashcards.states.NormalState;

public class ChgState implements Command {
    private final Card card;
    private final States state;

    public ChgState(Card card, States state) {
        this.card = card;
        this.state = state;
    }

    @Override
    public void execute() {
        if(state == States.NOTFLAGGED){
            card.setFlashcardState(new NormalState());
        }
        else{
            card.setFlashcardState(new FlaggedState());
        }
    }

}
