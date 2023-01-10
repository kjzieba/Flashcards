package org.flashcards.commands;

import org.flashcards.TxtCard;

public class ChgQue implements Command {
    private final TxtCard flashcard;
    private final String question;

    public ChgQue(TxtCard flashcard, String question) {
        this.flashcard = flashcard;
        this.question = question;
    }

    @Override
    public void execute() {
        flashcard.setTextQuestion(question);
    }

}
