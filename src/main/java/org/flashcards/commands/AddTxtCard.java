package org.flashcards.commands;

import org.flashcards.TxtCard;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

public class AddTxtCard implements Command {

    private final Long id;
    private final DatabaseProxy dbProxy;

    private final TxtCard txtCard;


    public AddTxtCard(Long id, TxtCard txtCard) {
        this.id = id;
        this.dbProxy = DatabaseProxy.getInstance();
        this.txtCard = txtCard;
    }

    @Override
    public void execute() {
        TxtFlashcardCollection txtCardRepo = (TxtFlashcardCollection) dbProxy.getFlashcardList(id, "T");
        txtCardRepo.add(txtCard);
    }

}
