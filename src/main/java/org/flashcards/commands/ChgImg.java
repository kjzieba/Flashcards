package org.flashcards.commands;

import org.flashcards.ImgCard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ChgImg implements Command {
    private final ImgCard flashcard;
    private final String newImage;

    public ChgImg(ImgCard flashcard, String newImage) {
        this.flashcard = flashcard;
        this.newImage = newImage;
    }

    @Override
    public void execute() {
        flashcard.setImageQuestion(imageToBytesArray(newImage));
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
