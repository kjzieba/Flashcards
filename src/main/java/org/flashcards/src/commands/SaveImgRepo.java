package org.flashcards.src.commands;

import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.ImgCardRepo;

public class SaveImgRepo implements Command{
    private final ComHistory history;
    private final ImgCardRepo imgFlashcardRepository;
    private final AllCards allCards;

    private final Long id;

    public SaveImgRepo(ComHistory history, ImgCardRepo imgFlashcardRepository, AllCards allCards, Long id) {
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
