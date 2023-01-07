package org.flashcards.src.commands;

import org.flashcards.src.repositories.AllCards;

public class DelRepo implements Command{
    private final ComHistory history;
    private final Long id;
    private final AllCards allCards;

    public DelRepo(ComHistory history, Long id, AllCards allCards) {
        this.history = history;
        this.id = id;
        this.allCards = allCards;
    }

    @Override
    public void execute() {
        allCards.deleteFromAll(id);
        history.push(this);
    }
}
