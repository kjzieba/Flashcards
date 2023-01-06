package org.flashcards.src.commands;

import org.flashcards.src.TxtCard;
import org.flashcards.src.repositories.TxtCardRepo;

public class DelTxtCard implements Command{
    private final ComHistory history;
    private final TxtCardRepo txtCardRepo;
    private final TxtCard flashcard;

    public DelTxtCard(ComHistory history, TxtCardRepo txtCardRepo, TxtCard flashcard) {
        this.history = history;
        this.txtCardRepo = txtCardRepo;
        this.flashcard = flashcard;
    }

    @Override
    public void execute() {
        txtCardRepo.deleteFromRepo(flashcard);
        history.push(this);
    }
}
