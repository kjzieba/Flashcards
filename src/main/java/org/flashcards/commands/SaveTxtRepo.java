package org.flashcards.commands;

import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

public class SaveTxtRepo implements Command {
    private final TxtFlashcardCollection txtCardRepo;
    private final DatabaseProxy dbProxy;

    private final Long id;

    public SaveTxtRepo(TxtFlashcardCollection txtCardRepo, Long id) {
        this.txtCardRepo = txtCardRepo;
        this.dbProxy = DatabaseProxy.getInstance();
        this.id = id;
    }

    @Override
    public void execute() {
        dbProxy.addFlashcardList(txtCardRepo);
    }
}
