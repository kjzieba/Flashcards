package org.flashcards.src.commands;

import org.flashcards.src.Card;
import org.flashcards.src.enums.States;
import org.flashcards.src.states.FlaggedState;
import org.flashcards.src.states.NormalState;

public class ChgState implements Command {
    private final ComHistory history;
    private final Card card;
    private final States state;

    public ChgState(ComHistory history, Card card, States state) {
        this.history = history;
        this.card = card;
        this.state = state;
    }

    @Override
    public void execute() {
        if(state == States.NOTFLAGGED){
            card.setFlashcardState(new NormalState());
            history.push(this);
        }
        else
        card.setFlashcardState(new FlaggedState());
        history.push(this);
    }

}
