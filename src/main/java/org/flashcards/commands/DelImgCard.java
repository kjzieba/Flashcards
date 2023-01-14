package org.flashcards.commands;

import org.flashcards.ImgCard;
import org.flashcards.collection.ImgFlashcardCollection;

import org.flashcards.db.DatabaseProxy;

public class DelImgCard implements Command {
    private final FlashcardImgHistory history;
    private final DatabaseProxy dbProxy;
    private final Long id;

    private final ImgCard imgCard;

    public DelImgCard(FlashcardImgHistory history, Long id, ImgCard imgCard) {
        this.history = history;
        this.dbProxy = DatabaseProxy.getInstance();
        this.id = id;
        this.imgCard = imgCard;
    }

    @Override
    public void execute() {
        ImgFlashcardCollection imgCardRepo = (ImgFlashcardCollection) dbProxy.getFlashcardList(id, "I");
        imgCardRepo.remove(imgCard);
        history.push(imgCard.saveToMemento());
    }
}
