package org.flashcards.commands;

import org.flashcards.ImgCard;
import org.flashcards.TxtCard;
import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.creators.ImgCardCreator;
import org.flashcards.db.DatabaseProxy;

public class AddImgCard implements Command{
    private final Long id;
    private final DatabaseProxy dbProxy;

    private final ImgCard imgCard;


    public AddImgCard(Long id, ImgCard imgCard) {
        this.id = id;
        this.dbProxy = DatabaseProxy.getInstance();
        this.imgCard = imgCard;
    }

    @Override
    public void execute() {
        ImgFlashcardCollection imgCardRepo = (ImgFlashcardCollection) dbProxy.getFlashcardList(id, "I");
        imgCardRepo.add(imgCard);
    }
}
