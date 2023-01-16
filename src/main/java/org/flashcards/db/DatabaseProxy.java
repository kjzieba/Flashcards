package org.flashcards.db;

import org.flashcards.collection.FlashcardCollectionInterface;

import java.util.ArrayList;
import java.util.Map;

public class DatabaseProxy implements DatabaseInterface {
    private static DatabaseProxy instance;
    private final Database db;
    private final ArrayList<FlashcardCollectionInterface> memory;

    private DatabaseProxy() {
        this.db = Database.getInstance();
        memory = new ArrayList<>();
    }

    public static DatabaseProxy getInstance() {
        if (instance == null) {
            instance = new DatabaseProxy();
        }
        return instance;
    }

    public void saveList(Long id) {
        for (FlashcardCollectionInterface list : memory) {
            if (list.getId().equals(id)) {
                db.addFlashcardList(list);
            }
        }
    }

    @Override
    public void addFlashcardList(FlashcardCollectionInterface list) {
        memory.add(list);
    }

    @Override
    public FlashcardCollectionInterface getFlashcardList(Long id, String type) {
        for (FlashcardCollectionInterface list : memory) {
            if (list.getId().equals(id)) {
                return list;
            }
        }
        FlashcardCollectionInterface list = db.getFlashcardList(id, type);
        if (list != null) {
            memory.add(list);
        }

        return list;
    }

    @Override
    public void deleteFlashcardList(Long id) {
        db.deleteFlashcardList(id);
        memory.removeIf(list -> list.getId().equals(id));
    }

    @Override
    public Map<Long, String> getAllLists() {
        return db.getAllLists();
    }

    @Override
    public String getTypeByID(Long id) {
        for (FlashcardCollectionInterface list : memory) {
            if (list.getId().equals(id)) {
                return list.getType();
            }
        }
        return db.getTypeByID(id);
    }

    @Override
    public String toString() {
        return "DatabaseProxy{" +
                "memory=" + memory +
                '}';
    }
}
