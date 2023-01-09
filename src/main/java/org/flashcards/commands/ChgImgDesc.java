package org.flashcards.commands;

import org.flashcards.ImgCard;

public class ChgImgDesc implements Command{
    private final ComHistory history;
    private final ImgCard flashcard;

    private String newDescription;

    public ChgImgDesc(ComHistory history, ImgCard flashcard, String newDescription) {
        this.history = history;
        this.flashcard = flashcard;
        this.newDescription = newDescription;
    }

    @Override
    public void execute() {
        flashcard.setImageDescription(newDescription);
        history.push(this);
    }

}
