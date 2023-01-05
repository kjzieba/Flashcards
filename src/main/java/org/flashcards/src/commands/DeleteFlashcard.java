package org.flashcards.src.commands;

import org.flashcards.src.Flashcard;
import org.flashcards.src.repositories.FlashcardRepository;

public class DeleteFlashcard implements Command{

    FlashcardRepository flashcardRepository;
    Flashcard flashcard;

    public DeleteFlashcard(FlashcardRepository flashcardRepository, Flashcard flashcard) {
        this.flashcardRepository = flashcardRepository;
        this.flashcard = flashcard;
    }

    @Override
    public void execute() {
        flashcardRepository.deleteFromRepo(flashcard);
    }

    @Override
    public void undo() {

    }
}
