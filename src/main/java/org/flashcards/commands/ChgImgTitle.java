package org.flashcards.commands;

import org.flashcards.repositories.ImgCardRepo;

public class ChgImgTitle implements Command{
    private final ComHistory history;
    private final ImgCardRepo imgFlashcardRepository;

    private final String title;

    public ChgImgTitle(ComHistory history, ImgCardRepo imgFlashcardRepository, String title) {
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
