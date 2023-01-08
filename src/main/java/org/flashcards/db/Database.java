package org.flashcards.db;

import org.flashcards.collection.FlashcardCollectionInterface;

import java.io.*;
import java.util.Scanner;

public class Database implements DatabaseInterface {
    private static Database instance;
    private File db;

    private Database() {
        try {
            db = new File("db.txt");
            db.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    @Override
    public void addFlashcardList(FlashcardCollectionInterface list) {
        try {
            FileWriter fw = new FileWriter("db.txt", true);
            fw.write(list.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FlashcardCollectionInterface getFlashcardList() {
        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNextLine()){
                String str = sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteFlashcardList() {

    }
}
