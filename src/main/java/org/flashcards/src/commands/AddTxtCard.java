package org.flashcards.src.commands;

import org.flashcards.src.TxtCard;
import org.flashcards.src.repositories.TxtCardRepo;
import org.flashcards.src.creators.TxtCardCreator;

public class AddTxtCard implements Command{

    private final Long id;
    private final ComHistory history;
    private final TxtCardRepo txtCardRepo;
    private final String answer;
    private final String question;
    TxtCardCreator textCreator;

    public AddTxtCard(Long id, ComHistory history, TxtCardRepo txtCardRepo, String answer, String question) {
        this.id = id;
        this.history = history;
        this.txtCardRepo = txtCardRepo;
        this.answer = answer;
        this.question = question;
    }

    @Override
    public void execute() {
        TxtCard flashcard = textCreator.createFlashcard(id, answer,question);
        txtCardRepo.addToRepo(flashcard);
        history.push(this);
    }

}
