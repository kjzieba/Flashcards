package org.flashcards.src.commands;

import org.flashcards.src.Flashcard;

public class ChangeAnswer implements Command{

    Flashcard flashcard;
    String answer;

    public ChangeAnswer(Flashcard flashcard, String answer) {
        this.flashcard = flashcard;
        this.answer = answer;
    }

    @Override
    public void execute() {
        flashcard.setAnswer(answer);
    }

    @Override
    public void undo() {

    }
}
