package org.flashcards.src.commands;

import org.flashcards.src.ImgCard;
import org.flashcards.src.repositories.ImgCardRepo;

public class DelImgCard implements Command{
    private final ComHistory history;
    private final ImgCardRepo imgCardRepo;
    private final ImgCard flashcard;

    public DelImgCard(ComHistory history, ImgCardRepo imgCardRepo, ImgCard flashcard) {
        this.history = history;
        this.imgCardRepo = imgCardRepo;
        this.flashcard = flashcard;
    }

    @Override
    public void execute() {
        imgCardRepo.deleteFromRepo(flashcard);
        history.push(this);
    }
}
