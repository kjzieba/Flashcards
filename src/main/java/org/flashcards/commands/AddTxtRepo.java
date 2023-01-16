package org.flashcards.commands;

import org.flashcards.collection.TxtFlashcardCollection;

import java.util.ArrayList;

public class AddTxtRepo implements Command {
    private final ArrayList<Long> addedID;
    private final Long id;

    public AddTxtRepo(ArrayList<Long> addedID, Long id) {
        this.addedID = addedID;
        this.id = id;
    }

    @Override
    public void execute() {
        TxtFlashcardCollection txtCardRepo = new TxtFlashcardCollection("", new ArrayList<>(), id);
        addedID.add(id);
        Command saveTxtRepo = new SaveTxtRepo(txtCardRepo, id);
        saveTxtRepo.execute();
    }
}
