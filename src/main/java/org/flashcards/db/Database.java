package org.flashcards.db;

import org.flashcards.collection.FlashcardCollectionInterface;

import java.io.*;
import java.util.Scanner;

public class Database implements DatabaseInterface {
    private static Database instance;
    private final File db;

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
    public FlashcardCollectionInterface getFlashcardList(Long id, String type) {
        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNextLine()){
                String str = sc.nextLine();
                String[] strings = str.split(" ");
                if (strings[0].equals(type)) {
                    if(strings[1].equals(id.toString())){
                        if (type.equals("T")){
//                            return new TxtFlashcardCollection();
                        }
                        else if (type.equals("I")){
//                            return new TxtFlashcardCollection();
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteFlashcardList(Long id) {

    }
}
