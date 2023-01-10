package org.flashcards.commands;

import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

import java.util.ArrayList;

public class AddImgRepo implements Command{
    private final ComHistory comHistory;

    private final ArrayList<Long> addedID;
    private final Long id;

    public AddImgRepo(ComHistory comHistory, ArrayList<Long> addedID, Long id) {
        this.comHistory = comHistory;
        this.addedID = addedID;
        this.id = id;
    }

    @Override
    public void execute() {
        ImgFlashcardCollection imgCardRepo = new ImgFlashcardCollection("", new ArrayList<>(), id);
        addedID.add(id);
        Command saveImgRepo = new SaveImgRepo(comHistory, imgCardRepo, id);
        saveImgRepo.execute();
        comHistory.push(this);
    }

}
