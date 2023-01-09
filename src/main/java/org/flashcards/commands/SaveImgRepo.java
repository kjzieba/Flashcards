package org.flashcards.commands;

import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.repositories.AllCards;

public class SaveImgRepo implements Command{
    private final ComHistory history;
    private final ImgFlashcardCollection imgFlashcardRepository;
    private final AllCards allCards;
    private final Long id;

    public SaveImgRepo(ComHistory history, ImgFlashcardCollection imgFlashcardRepository, AllCards allCards, Long id) {
        this.history = history;
        this.imgFlashcardRepository = imgFlashcardRepository;
        this.allCards = allCards;
        this.id = id;
    }

    @Override
    public void execute() {
        allCards.addToAll(id, imgFlashcardRepository);
        history.push(this);
    }
}
