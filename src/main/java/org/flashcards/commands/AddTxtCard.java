package org.flashcards.commands;

import org.flashcards.TxtCard;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.repositories.AllCards;

public class AddTxtCard implements Command{

    private final Long id;
    private final ComHistory history;
    private final AllCards allCards;

    private final TxtCard txtCard;



    public AddTxtCard(Long id, ComHistory history, AllCards allCards, TxtCard txtCard) {
        this.id = id;
        this.history = history;
        this.allCards = allCards;
        this.txtCard = txtCard;
    }

    @Override
    public void execute() {
        TxtFlashcardCollection txtCardRepo = (TxtFlashcardCollection) allCards.query(id);
        txtCardRepo.add(txtCard);
        history.push(this);
    }

}
