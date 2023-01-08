package org.flashcards.src.commands;

import org.flashcards.src.repositories.ImgCardRepo;

public class DelImgCard implements Command{
    private final ComHistory history;
    private final ImgCardRepo imgCardRepo;
    private final Long cardId;

    public DelImgCard(ComHistory history, ImgCardRepo imgCardRepo, Long cardId) {
        this.history = history;
        this.imgCardRepo = imgCardRepo;
        this.cardId = cardId;
    }

    @Override
    public void execute() {
        imgCardRepo.deleteFromRepo(cardId);
        history.push(this);
    }
}
