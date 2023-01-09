package org.flashcards.commands;

import org.flashcards.repositories.AllCards;
import org.flashcards.repositories.TxtCardRepo;

import java.util.ArrayList;

public class AddTxtRepo implements Command{
    private final ComHistory comHistory;
    private final AllCards allCards;

    private final ArrayList<Long> addedID;
    private final Long id;

    public AddTxtRepo(ComHistory comHistory, AllCards allCards, ArrayList<Long> addedID, Long id) {
        this.comHistory = comHistory;
        this.allCards = allCards;
        this.addedID = addedID;
        this.id = id;
    }

    @Override
    public void execute() {
            TxtCardRepo txtCardRepo = new TxtCardRepo();
            addedID.add(id);
            Command saveTxtRepo = new SaveTxtRepo(comHistory, txtCardRepo, allCards, id);
            saveTxtRepo.execute();
            comHistory.push(this);
    }
}
