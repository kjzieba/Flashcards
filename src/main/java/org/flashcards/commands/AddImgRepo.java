package org.flashcards.commands;

import org.flashcards.collection.ImgFlashcardCollection;

import java.util.ArrayList;

public class AddImgRepo implements Command {
    private final ArrayList<Long> addedID;
    private final Long id;

    public AddImgRepo(ArrayList<Long> addedID, Long id) {
        this.addedID = addedID;
        this.id = id;
    }

    @Override
    public void execute() {
        ImgFlashcardCollection imgCardRepo = new ImgFlashcardCollection("", new ArrayList<>(), id);
        addedID.add(id);
        Command saveImgRepo = new SaveImgRepo(imgCardRepo, id);
        saveImgRepo.execute();
    }

}
