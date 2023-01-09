package org.flashcards.commands;

import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.db.DatabaseProxy;

import java.util.ArrayList;

public class DelRepo implements Command {
    private final ComHistory history;
    private final Long id;
    private final DatabaseProxy dbProxy;

    private final ArrayList<FlashcardCollectionInterface> deletedRepos;

    private final ArrayList<Long> deletedId;

    private final String type;

    public DelRepo(ComHistory history, Long id, ArrayList<FlashcardCollectionInterface> deletedRepos, ArrayList<Long> deletedId, String type) {
        this.history = history;
        this.id = id;
        this.dbProxy = DatabaseProxy.getInstance();
        this.deletedRepos = deletedRepos;
        this.deletedId = deletedId;
        this.type = type;
    }

    @Override
    public void execute() {
        deletedId.add(id);
        deletedRepos.add(dbProxy.getFlashcardList(id,type));
        dbProxy.deleteFlashcardList(id);
        history.push(this);
    }
}
