package org.flashcards.src.creators;

import org.flashcards.src.Flashcard;
import org.flashcards.src.FlashcardWithImage;
import org.flashcards.src.states.State;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FlashcardWithImageCreator implements FlashcardCreator {

    @Override
    public Flashcard createFlashcard(String... args) {
        return new FlashcardWithImage(args[0], imageToBytesArray(args[2]), args[3]);
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
