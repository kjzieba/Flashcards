package org.flashcards.commands;

import org.flashcards.repositories.AllCards;
import org.flashcards.repositories.CardsRepo;

import java.util.ArrayList;

public class DelRepo implements Command {
    private final ComHistory history;
    private final Long id;
    private final AllCards allCards;

    private final ArrayList<CardsRepo> deletedRepos;

    private final ArrayList<Long> deletedId;

    public DelRepo(ComHistory history, Long id, AllCards allCards, ArrayList<CardsRepo> deletedRepos, ArrayList<Long> deletedId) {
        this.history = history;
        this.id = id;
        this.allCards = allCards;
        this.deletedRepos = deletedRepos;
        this.deletedId = deletedId;
    }

    @Override
    public void execute() {
        deletedId.add(id);
        deletedRepos.add(allCards.deleteFromAll(id));
        history.push(this);
    }
}
