package org.flashcards.states;

public class FlaggedState implements State{

    @Override
    public void action() {
        System.out.println("I'm a flagged flashcard!");
    }
}
