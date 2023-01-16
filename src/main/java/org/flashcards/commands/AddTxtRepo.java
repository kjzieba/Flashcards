package org.flashcards.commands;

import org.flashcards.collection.TxtFlashcardCollection;

import java.util.ArrayList;

public class AddTxtRepo implements Command{
    private final Long id;

    public AddTxtRepo(Long id) {
        this.id = id;
    }

    @Override
    public void execute() {
            TxtFlashcardCollection txtCardRepo = new TxtFlashcardCollection("", new ArrayList<>(), id);
            Command saveTxtRepo = new SaveTxtRepo(txtCardRepo, id);
            saveTxtRepo.execute();
    }
}
