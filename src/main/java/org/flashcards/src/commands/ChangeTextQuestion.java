package org.flashcards.src.commands;

import org.flashcards.src.FlashcardWithText;

public class ChangeTextQuestion implements Command{

    FlashcardWithText flashcard;

    String question;

    public ChangeTextQuestion(FlashcardWithText flashcard, String question) {
        this.flashcard = flashcard;
        this.question = question;
    }

    @Override
    public void execute() {
        flashcard.setTextQuestion(question);
    }

    @Override
    public void undo() {

    }
}
