package org.flashcards.src.creators;

import org.flashcards.src.Flashcard;
import org.flashcards.src.FlashcardWithImage;
import org.flashcards.src.states.State;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FlashcardWithImageCreator implements FlashcardCreator {

    @Override
    public Flashcard createFlashcard(State flashcardState, String... args) {
        return new FlashcardWithImage(imageToBytesArray(args[0]), args[1], args[2], flashcardState);
    }

    public byte[] imageToBytesArray(String image) {
        File imgPath = new File(image);
        try {
            return Files.readAllBytes(imgPath.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
