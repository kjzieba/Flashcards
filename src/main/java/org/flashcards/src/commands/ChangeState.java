package org.flashcards.src.commands;

import org.flashcards.src.Flashcard;
import org.flashcards.src.states.State;

public class ChangeState implements Command{

    Flashcard flashcard;

    State state;

    public ChangeState(Flashcard flashcard, State state) {
        this.flashcard = flashcard;
        this.state = state;
    }

    @Override
    public void execute() {
        flashcard.setFlashcardState(state);
    }

    @Override
    public void undo() {

    }
}
