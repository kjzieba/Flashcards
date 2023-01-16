package org.flashcards.commands;


import org.flashcards.TxtCard;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

public class DelTxtCard implements Command {
    private final FlashcardTxtHistory history;
    private final DatabaseProxy dbProxy;
    private final Long id;
    private final TxtCard txtCard;


    public DelTxtCard(FlashcardTxtHistory history, Long id, TxtCard txtCard) {
        this.history = history;
        this.dbProxy = DatabaseProxy.getInstance();
        this.id = id;
        this.txtCard = txtCard;
    }

    @Override
    public void execute() {
        TxtFlashcardCollection txtCardRepo = (TxtFlashcardCollection) dbProxy.getFlashcardList(id, "T");
        txtCardRepo.remove(txtCard);
        history.push(txtCard.saveToMemento());
    }
}
