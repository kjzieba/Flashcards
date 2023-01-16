package org.flashcards.commands;

import org.flashcards.collection.ImgFlashcardCollection;

import java.util.ArrayList;

public class AddImgRepo implements Command{
    private final Long id;

    public AddImgRepo(Long id) {
        this.id = id;
    }

    @Override
    public void execute() {
        ImgFlashcardCollection imgCardRepo = new ImgFlashcardCollection("", new ArrayList<>(), id);
        Command saveImgRepo = new SaveImgRepo(imgCardRepo, id);
        saveImgRepo.execute();
    }

}
