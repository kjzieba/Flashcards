package org.flashcards.commands;

import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

public class ChgTxtTitle implements Command{
    private final Long id;

    private final DatabaseProxy dbProxy;
    private final String title;

    public ChgTxtTitle(Long id, String title) {
        this.id = id;
        this.dbProxy = DatabaseProxy.getInstance();
        this.title = title;
    }

    @Override
    public void execute() {
        TxtFlashcardCollection txtCardRepo = (TxtFlashcardCollection) dbProxy.getFlashcardList(id, "T");
        System.out.println(txtCardRepo);
        txtCardRepo.setTitle(title);
    }
}
