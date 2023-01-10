package org.flashcards.commands;

import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.DatabaseProxy;

public class EditTextRepo implements Command {
    private final ComHistory comHistory;

    private final DatabaseProxy dbProxy;

    private final Long id;

    private final String function;

    boolean end = false;

    public EditTextRepo(ComHistory comHistory, DatabaseProxy dbProxy, Long id, String function) {
        this.comHistory = comHistory;
        this.function = function;
        this.dbProxy = DatabaseProxy.getInstance();
        ;
        this.id = id;
    }

    @Override
    public void execute() {
        switch (function) {
            case "T":
                FlashcardCollectionInterface flashcardCollectionInterface = dbProxy.getFlashcardList(id, "T");
                TxtFlashcardCollection txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;

        }
    }
}