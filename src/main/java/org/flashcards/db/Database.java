package org.flashcards.db;

import org.flashcards.ImgCard;
import org.flashcards.TxtCard;
import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.states.FlaggedState;

import java.io.*;
import java.util.*;

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
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] strings = str.split(" ");
                if (strings[0].equals(type)) {
                    if (strings[1].equals(id.toString())) {
                        if (type.equals("T")) {
                            String title = strings[2];
                            String[] cards = strings[3].replace("[", "").replace("]", "").split(",");

                            ArrayList<TxtCard> list = new ArrayList<>();

                            for (var card : cards) {
                                String[] sides = card.split("\\|");
                                TxtCard c = new TxtCard(Long.parseLong(sides[0]), sides[1], sides[2]);
                                if (sides[3].equals("flagged")) {
                                    c.setFlashcardState(new FlaggedState());
                                }
                                list.add(c);
                            }
                            sc.close();
                            return new TxtFlashcardCollection(title, list, id);
                        } else if (type.equals("I")) {
                            String title = strings[2];
                            String[] cards = strings[3].replace("[", "").replace("]", "").split(",");

                            ArrayList<ImgCard> list = new ArrayList<>();

                            for (var card : cards) {
                                String[] sides = card.split("\\|");

                                String[] imgParts = sides[1].split("\\.");

                                byte[] img = new byte[imgParts.length];
                                for (int i = 0; i < imgParts.length; i++) {
                                    img[i] = (byte) Integer.parseInt(imgParts[i]);
                                }
                                ImgCard c = new ImgCard(Long.parseLong(sides[0]), sides[2], img);
                                if (sides[3].equals("flagged")) {
                                    c.setFlashcardState(new FlaggedState());
                                }
                                list.add(c);
                            }
                            sc.close();
                            return new ImgFlashcardCollection(title, list, id);
                        }
                    }
                }

            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteFlashcardList(Long id) {
        File tmp;
        try {
            tmp = new File("tmp.txt");
            tmp.createNewFile();
            Scanner sc = new Scanner(db);
            FileWriter fw = new FileWriter("tmp.txt");
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] strings = str.split(" ");
                if (strings[1].equals(id.toString())) {
                    continue;
                }
                fw.write(str + "\n");
            }
            fw.close();
            sc.close();

            db.delete();
            tmp.renameTo(new File("db.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Long, String> getAllLists() {
        Map<Long, String> map = new HashMap<>();
        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] strings = str.split(" ");
                map.put(Long.parseLong(strings[1]), strings[2]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
