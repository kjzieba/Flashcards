package org.flashcards.commands;

import org.flashcards.repositories.AllCards;
import org.flashcards.repositories.TxtCardRepo;

public class SaveTxtRepo implements Command{
    private final ComHistory history;

    private final TxtCardRepo txtCardRepo;
    private final AllCards allCards;

    private final Long id;

    public SaveTxtRepo(ComHistory history, TxtCardRepo txtCardRepo, AllCards allCards, Long id) {
        this.history = history;
        this.txtCardRepo = txtCardRepo;
        this.allCards = allCards;
        this.id = id;
    }

    @Override
    public void execute() {
        allCards.addToAll(id, txtCardRepo);
        history.push(this);
    }
}
