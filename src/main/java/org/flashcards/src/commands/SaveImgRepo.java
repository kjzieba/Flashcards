package org.flashcards.src.commands;

import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.ImgCardRepo;

public class SaveImgRepo implements Command{
    private final ComHistory history;
    private final ImgCardRepo imgFlashcardRepository;
    private final AllCards allCards;

    public SaveImgRepo(ComHistory history, ImgCardRepo imgFlashcardRepository, AllCards allCards) {
        this.history = history;
        this.imgFlashcardRepository = imgFlashcardRepository;
        this.allCards = allCards;
    }

    @Override
    public void execute() {
        allCards.addToAll(imgFlashcardRepository);
        history.push(this);
    }
}
