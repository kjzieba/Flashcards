package org.flashcards.src.commands;

import org.flashcards.src.repositories.TxtCardRepo;
import org.flashcards.src.repositories.AllCards;

public class AddRepo implements Command {

    private final String title;
    private final ComHistory history;
    private final AllCards allCards;


    public AddRepo(ComHistory history, AllCards allCards, TxtCardRepo txtCardRepo, String title) {
        this.history = history;
        this.allCards = allCards;
        this.title = title;
    }

    @Override
    public void execute() {
        TxtCardRepo txtCardRepo = new TxtCardRepo();
        history.push(this);
    }

}
