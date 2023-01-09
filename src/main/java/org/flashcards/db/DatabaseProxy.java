package org.flashcards.db;

import org.flashcards.collection.FlashcardCollectionInterface;

import java.util.ArrayList;

public class DatabaseProxy implements DatabaseInterface {
    private static DatabaseProxy instance;
    private final Database db;
    private ArrayList<FlashcardCollectionInterface> memory;

    private DatabaseProxy() {
        this.db = Database.getInstance();
    }

    public static DatabaseProxy getInstance() {
        if (instance == null) {
            instance = new DatabaseProxy();
        }
        return instance;
    }

    @Override
    public void addFlashcardList(FlashcardCollectionInterface list) {
        db.addFlashcardList(list);
        memory.add(list);
    }

    @Override
    public FlashcardCollectionInterface getFlashcardList(Long id, String type) {
        for (FlashcardCollectionInterface list : memory) {
            if (list.getId().equals(id)) {
                return list;
            } else {
                return db.getFlashcardList(id, type);
            }
        }
        return null;
    }

    @Override
    public void deleteFlashcardList(Long id) {
        db.deleteFlashcardList(id);
        memory.removeIf(list -> list.getId().equals(id));
    }
}
