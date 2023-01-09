package org.flashcards.commands;

import org.flashcards.repositories.TxtCardRepo;

public class ChgTxtTitle implements Command{
    private final ComHistory history;
    private final TxtCardRepo txtCardRepo;

    private final String title;

    public ChgTxtTitle(ComHistory history, TxtCardRepo txtCardRepo, String title) {
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
