package org.flashcards.commands;

import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

public class SaveImgRepo implements Command{
    private final ComHistory history;
    private final ImgFlashcardCollection imgFlashcardRepository;
    private final DatabaseProxy dbProxy;
    private final Long id;

    public SaveImgRepo(ComHistory history, ImgFlashcardCollection imgFlashcardRepository, Long id) {
        this.history = history;
        this.imgFlashcardRepository = imgFlashcardRepository;
        this.dbProxy = DatabaseProxy.getInstance();
        this.id = id;
    }

    @Override
    public void execute() {
        dbProxy.addFlashcardList(imgFlashcardRepository);
        history.push(this);
    }
}
