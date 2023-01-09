package org.flashcards.commands;

import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.repositories.AllCards;

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
            TxtFlashcardCollection txtCardRepo = new TxtFlashcardCollection("", new ArrayList<>(), id);
            addedID.add(id);
            Command saveTxtRepo = new SaveTxtRepo(comHistory, txtCardRepo, allCards, id);
            saveTxtRepo.execute();
            comHistory.push(this);
    }
}
