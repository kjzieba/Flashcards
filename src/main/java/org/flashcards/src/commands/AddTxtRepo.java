package org.flashcards.src.commands;

import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.TxtCardRepo;

import java.util.ArrayList;

public class AddTxtRepo implements Command{
    private final ComHistory comHistory;
    private final AllCards allCards;
    private final String title;
    private final ArrayList<Long> addedID;
    private final Long id;

    public AddTxtRepo(ComHistory comHistory, AllCards allCards, String title, ArrayList<Long> addedID, Long id) {
        this.comHistory = comHistory;
        this.allCards = allCards;
        this.title = title;
        this.addedID = addedID;
        this.id = id;
    }

    @Override
    public void execute() {
            TxtCardRepo txtCardRepo = new TxtCardRepo();
            txtCardRepo.setTitle(title);
            addedID.add(id);
            Command saveTxtRepo = new SaveTxtRepo(comHistory, txtCardRepo, allCards, id);
            saveTxtRepo.execute();
            comHistory.push(this);
    }
}
