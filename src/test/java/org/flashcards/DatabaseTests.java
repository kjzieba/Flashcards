package org.flashcards;

import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.DatabaseProxy;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class DatabaseTests {
    private static DatabaseProxy db;
    private static TxtFlashcardCollection txtList;

    @BeforeAll
    static void setup() {
        db = DatabaseProxy.getInstance();
        ArrayList<TxtCard> txtCards = new ArrayList<>();
        txtCards.add(new TxtCard(1L, "question", "answer"));
        txtList = new TxtFlashcardCollection("title", txtCards, 1L);
    }

    @Test
    void shouldReturnTxtListFromMemory_whenListIsPresentInMemory() {
        db.addFlashcardList(txtList);

        TxtFlashcardCollection returnList = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");

        Assertions.assertEquals(txtList.toString(), returnList.toString());
    }

    @Test
    void shouldReturnNull_whenListIsNotInMemoryAndDB() {
        TxtFlashcardCollection returnList = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");

        Assertions.assertNull(returnList);
    }

//    @Test
//    void shouldReturnTxtListFromDB_whenListIsNotInMemoryButIsPresentInDB() {
//        db.addFlashcardList(txtList);
//        db.saveList(1L);
//
//        TxtFlashcardCollection returnList = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");
//
//        Assertions.assertNull(returnList);
//    }


}
