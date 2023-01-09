package org.flashcards.commands;

import org.flashcards.ImgCard;
import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.repositories.AllCards;

import java.util.ArrayList;

public class AddImgRepo implements Command{
    private final ComHistory comHistory;
    private final AllCards allCards;

    private final String title;
    private final ArrayList<Long> addedID;

    private final Long id;

    public AddImgRepo(ComHistory comHistory, AllCards allCards, String title, ArrayList<Long> addedID, Long id) {
        this.comHistory = comHistory;
        this.allCards = allCards;
        this.title = title;
        this.addedID = addedID;
        this.id = id;
    }

    @Override
    public void execute() {
        ImgFlashcardCollection imgCardRepo = new ImgFlashcardCollection(title, new ArrayList<>(), id);
        addedID.add(id);
        Command saveImgRepo = new SaveImgRepo(comHistory, imgCardRepo, allCards, id);
        saveImgRepo.execute();
        comHistory.push(this);
    }
}
