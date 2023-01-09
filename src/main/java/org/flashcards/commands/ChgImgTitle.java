package org.flashcards.commands;


import org.flashcards.collection.ImgFlashcardCollection;

public class ChgImgTitle implements Command{
    private final ComHistory history;
    private final ImgFlashcardCollection imgFlashcardRepository;

    private final String title;

    public ChgImgTitle(ComHistory history, ImgFlashcardCollection imgFlashcardRepository, String title) {
        this.history = history;
        this.imgFlashcardRepository = imgFlashcardRepository;
        this.title = title;
    }

    @Override
    public void execute() {
        imgFlashcardRepository.setTitle(title);
        history.push(this);
    }
}
