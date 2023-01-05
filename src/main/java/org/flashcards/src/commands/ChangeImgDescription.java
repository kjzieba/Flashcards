package org.flashcards.src.commands;

import org.flashcards.src.Flashcard;
import org.flashcards.src.FlashcardWithImage;

public class ChangeImgDescription implements Command{

    FlashcardWithImage flashcard;

    String newDescription;

    public ChangeImgDescription(FlashcardWithImage flashcard, String newDescription) {
        this.flashcard = flashcard;
        this.newDescription = newDescription;
    }

    @Override
    public void execute() {
        flashcard.setImageDescription(newDescription);
    }

    @Override
    public void undo() {

    }
}
