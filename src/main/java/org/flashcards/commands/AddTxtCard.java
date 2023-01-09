package org.flashcards.commands;

import org.flashcards.TxtCard;
import org.flashcards.repositories.AllCards;
import org.flashcards.creators.TxtCardCreator;
import org.flashcards.repositories.TxtCardRepo;

public class AddTxtCard implements Command{

    private final Long id;
    private final ComHistory history;
    private final AllCards allCards;
    private final TxtCard txtCard;
    TxtCardCreator textCreator = new TxtCardCreator();

    public AddTxtCard(Long id, ComHistory history, AllCards allCards, TxtCard txtCard) {
        this.id = id;
        this.history = history;
        this.allCards = allCards;
        this.txtCard = txtCard;
    }

    @Override
    public void execute() {
        TxtCard flashcard = textCreator.createFlashcard(id, txtCard.getAnswer(),txtCard.getTextQuestion());
        TxtCardRepo txtCardRepo = (TxtCardRepo) allCards.query(id);
        txtCardRepo.addToRepo(flashcard);
        history.push(this);
    }

}
