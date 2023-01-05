package org.flashcards.Backend.creators;

import org.flashcards.Backend.Flashcard;
import org.flashcards.Backend.FlashcardWithImage;
import org.flashcards.Backend.enums.FlashcardStates;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FlashcardWithImageCreator implements FlashcardCreator {

    @Override
    public Flashcard createFlashcard(FlashcardStates state, String... args) {
        return new FlashcardWithImage(imageToBytesArray(args[0]), args[1], args[2], state);
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
