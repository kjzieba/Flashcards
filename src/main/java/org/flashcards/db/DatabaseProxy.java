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
        System.out.println(memory);
        for (FlashcardCollectionInterface list : memory) {
            if (list.getId().equals(id)) {
                return list;
            } else {
                //return db.getFlashcardList(id, type);
            }
        }
        return null;
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
    public String toString() {
        return "DatabaseProxy{" +
                "memory=" + memory +
                '}';
    }
}
