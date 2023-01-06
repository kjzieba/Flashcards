package org.flashcards.src.commands;

import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.TxtCardRepo;

public class SaveTxtRepo implements Command{
    private final ComHistory history;

    private final TxtCardRepo txtCardRepo;
    private final AllCards allCards;

    public SaveTxtRepo(ComHistory history, TxtCardRepo txtCardRepo, AllCards allCards) {
        this.history = history;
        this.txtCardRepo = txtCardRepo;
        this.allCards = allCards;
    }

    @Override
    public void execute() {
        allCards.addToAll(txtCardRepo);
        history.push(this);
    }
}
