package org.flashcards.commands;


import org.flashcards.collection.ImgFlashcardCollection;

public class DelImgCard implements Command{
    private final ComHistory history;
    private final ImgFlashcardCollection imgCardRepo;
    private final Long cardId;

    public DelImgCard(ComHistory history, ImgFlashcardCollection imgCardRepo, Long cardId) {
        this.history = history;
        this.imgCardRepo = imgCardRepo;
        this.cardId = cardId;
    }

    @Override
    public void execute() {
//        imgCardRepo.remove();
        history.push(this);
    }
}
