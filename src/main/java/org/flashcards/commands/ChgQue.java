package org.flashcards.commands;

import org.flashcards.TxtCard;

public class ChgQue implements Command {
    private final ComHistory history;
    private final TxtCard flashcard;
    private final String question;

    public ChgQue(ComHistory history, TxtCard flashcard, String question) {
        this.history = history;
        this.flashcard = flashcard;
        this.question = question;
    }

    @Override
    public void execute() {
        flashcard.setTextQuestion(question);
        history.push(this);
    }

}
