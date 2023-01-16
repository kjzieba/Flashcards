package org.flashcards.creators;

import org.flashcards.ImgCard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImgCardCreator implements CardCreator {

    @Override
    public ImgCard createFlashcard(Long id, String... args) {
        return new ImgCard(id, args[0], imageToBytesArray(args[1]));
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
