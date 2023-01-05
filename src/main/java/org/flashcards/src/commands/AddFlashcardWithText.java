package org.flashcards.src.commands;

import org.flashcards.src.Flashcard;
import org.flashcards.src.repositories.FlashcardRepository;
import org.flashcards.src.creators.FlashcardWithTextCreator;

public class AddFlashcardWithText implements Command{

    FlashcardRepository flashcardRepository;
    String answer;
    String question;

    FlashcardWithTextCreator flashcardWithTextCreator;

    public AddFlashcardWithText(FlashcardRepository flashcardRepository, String answer, String question) {
        this.flashcardRepository = flashcardRepository;
        this.answer = answer;
        this.question = question;
    }

    @Override
    public void execute() {
        Flashcard flashcard = flashcardWithTextCreator.createFlashcard(answer,question);
        flashcardRepository.addToRepo(flashcard);
    }

    @Override
    public void undo() {

    }
}
