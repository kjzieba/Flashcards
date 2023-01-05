package org.flashcards.src.commands;

import org.flashcards.src.repositories.FlashcardRepository;
import org.flashcards.src.repositories.AllFlashcards;

public class AddRepository implements Command {

    AllFlashcards allFlashcards;
    FlashcardRepository flashcardRepository;

    public AddRepository(AllFlashcards allFlashcards, FlashcardRepository flashcardRepository) {
        this.allFlashcards = allFlashcards;
        this.flashcardRepository = flashcardRepository;
    }

    @Override
    public void execute() {
        allFlashcards.addToAll(flashcardRepository);
    }

    @Override
    public void undo() {

    }
}
