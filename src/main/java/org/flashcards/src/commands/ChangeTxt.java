package org.flashcards.src.commands;

import org.flashcards.src.TxtCard;

public class ChangeTxt implements Command {
    private final ComHistory history;
    private final TxtCard flashcard;
    private final String question;

    public ChangeTxt(ComHistory history, TxtCard flashcard, String question) {
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
