package org.flashcards.src.commands;

import org.flashcards.src.Flashcard;
import org.flashcards.src.repositories.FlashcardRepository;
import org.flashcards.src.creators.FlashcardWithImageCreator;

public class AddFlashcardWithImage implements Command{

    FlashcardRepository flashcardRepository;

    String answer;

    String image;

    String description;

    FlashcardWithImageCreator flashcardWithImageCreator;

    public AddFlashcardWithImage(FlashcardRepository flashcardRepository, String answer, String image, String description) {
        this.flashcardRepository = flashcardRepository;
        this.answer = answer;
        this.image = image;
        this.description = description;
    }

    @Override
    public void execute() {
        Flashcard flashcard = flashcardWithImageCreator.createFlashcard(answer, image, description);
        flashcardRepository.addToRepo(flashcard);
    }

    @Override
    public void undo() {

    }
}
