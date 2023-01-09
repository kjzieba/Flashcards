package org.flashcards.commands;

import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

import java.util.ArrayList;

public class AddTxtRepo implements Command{
    private final ComHistory comHistory;
    private final DatabaseProxy dbProxy;

    private final ArrayList<Long> addedID;
    private final Long id;

    public AddTxtRepo(ComHistory comHistory, ArrayList<Long> addedID, Long id) {
        this.comHistory = comHistory;
        this.dbProxy = DatabaseProxy.getInstance();
        this.addedID = addedID;
        this.id = id;
    }

    @Override
    public void execute() {
            TxtFlashcardCollection txtCardRepo = new TxtFlashcardCollection("", new ArrayList<>(), id);
            addedID.add(id);
            Command saveTxtRepo = new SaveTxtRepo(comHistory, txtCardRepo, id);
            saveTxtRepo.execute();
            comHistory.push(this);
    }
}
