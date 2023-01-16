package org.flashcards.commands;

import org.flashcards.db.DatabaseProxy;

public class DelRepo implements Command {
    private final Long id;
    private final DatabaseProxy dbProxy;
    private final String type;

    public DelRepo(Long id, String type) {
        this.id = id;
        this.dbProxy = DatabaseProxy.getInstance();
        this.type = type;
    }

    @Override
    public void execute() {
        dbProxy.getFlashcardList(id,type);
        dbProxy.deleteFlashcardList(id);
    }
}
