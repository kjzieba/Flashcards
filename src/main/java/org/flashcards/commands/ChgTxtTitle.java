package org.flashcards.commands;

import org.flashcards.collection.TxtFlashcardCollection;

public class ChgTxtTitle implements Command{
    private final ComHistory history;
    private final TxtFlashcardCollection txtCardRepo;

    private final String title;

    public ChgTxtTitle(ComHistory history, TxtFlashcardCollection txtCardRepo, String title) {
        this.history = history;
        this.txtCardRepo = txtCardRepo;
        this.title = title;
    }

    @Override
    public void execute() {
        txtCardRepo.setTitle(title);
        history.push(this);
    }
}
