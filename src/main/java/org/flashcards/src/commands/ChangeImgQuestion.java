package org.flashcards.src.commands;

import org.flashcards.src.FlashcardWithImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ChangeImgQuestion implements Command{

    FlashcardWithImage flashcard;

    String newImage;

    public ChangeImgQuestion(FlashcardWithImage flashcard, String newImage) {
        this.flashcard = flashcard;
        this.newImage = newImage;
    }

    @Override
    public void execute() {
        flashcard.setImageQuestion(imageToBytesArray(newImage));
    }

    @Override
    public void undo() {

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
