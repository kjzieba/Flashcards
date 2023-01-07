package org.flashcards.src.commands;

import org.flashcards.src.TxtCard;
import org.flashcards.src.repositories.TxtCardRepo;

public class DelTxtCard implements Command{
    private final ComHistory history;
    private final TxtCardRepo txtCardRepo;
    private final Long cardId;

    public DelTxtCard(ComHistory history, TxtCardRepo txtCardRepo, Long cardId) {
        this.history = history;
        this.txtCardRepo = txtCardRepo;
        this.cardId = cardId;
    }

    @Override
    public void execute() {
        txtCardRepo.deleteFromRepo(cardId);
        history.push(this);
    }
}
