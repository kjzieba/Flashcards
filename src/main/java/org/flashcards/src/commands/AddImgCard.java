package org.flashcards.src.commands;

import org.flashcards.src.ImgCard;
import org.flashcards.src.creators.ImgCardCreator;
import org.flashcards.src.repositories.ImgCardRepo;

public class AddImgCard implements Command{
    private final Long id;
    private final ComHistory history;
    private final ImgCardRepo imgCardRepo;
    private final String answer;
    private final String image;
    private final String description;

    ImgCardCreator imageCreator;

    public AddImgCard(Long id, ComHistory history, ImgCardRepo imgCardRepo, String answer, String image, String description) {
        this.id = id;
        this.history = history;
        this.imgCardRepo = imgCardRepo;
        this.answer = answer;
        this.image = image;
        this.description = description;
    }

    @Override
    public void execute() {
        ImgCard flashcard = imageCreator.createFlashcard(id, answer, image, description);
        imgCardRepo.addToRepo(flashcard);
        history.push(this);
    }
}
