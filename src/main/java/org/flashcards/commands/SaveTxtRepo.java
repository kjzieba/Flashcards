package org.flashcards.commands;

import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.repositories.AllCards;

public class SaveTxtRepo implements Command{
    private final ComHistory history;

    private final TxtFlashcardCollection txtCardRepo;
    private final AllCards allCards;

    private final Long id;

    public SaveTxtRepo(ComHistory history, TxtFlashcardCollection txtCardRepo, AllCards allCards, Long id) {
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
