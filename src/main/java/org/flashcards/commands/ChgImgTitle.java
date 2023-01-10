package org.flashcards.commands;


import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

public class ChgImgTitle implements Command{
    private final Long id;

    private final DatabaseProxy dbProxy;
    private final String title;

    public ChgImgTitle( Long id, String title) {
        this.id = id;
        this.dbProxy = DatabaseProxy.getInstance();
        this.title = title;
    }

    @Override
    public void execute() {
        ImgFlashcardCollection imgCardRepo = (ImgFlashcardCollection) dbProxy.getFlashcardList(id, "T");
        imgCardRepo.setTitle(title);
    }
}
