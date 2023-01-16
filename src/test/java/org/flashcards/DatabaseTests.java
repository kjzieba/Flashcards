package org.flashcards;

import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.db.Database;
import org.flashcards.db.DatabaseProxy;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class DatabaseTests {
    private static DatabaseProxy db;
    private static TxtFlashcardCollection txtList;
    private static ImgFlashcardCollection imgList;

    @BeforeAll
    static void setup() {
        db = DatabaseProxy.getInstance();
        ArrayList<TxtCard> txtCards = new ArrayList<>();
        txtCards.add(new TxtCard(1L, "question", "answer"));
        txtList = new TxtFlashcardCollection("title", txtCards, 1L);
        ArrayList<ImgCard> imgCards = new ArrayList<>();
        byte[] bytes = new byte[1];
        imgCards.add(new ImgCard(1L, "answer", bytes));
        imgList = new ImgFlashcardCollection("title", imgCards, 1L);
    }

    @AfterAll
    static void afterAll() throws IOException {
        Files.delete(Path.of("db.txt"));
    }

    @AfterEach
    void afterEach() {
        db.deleteFlashcardList(1L);
    }

    @Test
    void shouldReturnTxtListFromMemory_whenListIsPresentInMemory() {
        db.addFlashcardList(txtList);

        TxtFlashcardCollection returnList = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");

        Assertions.assertEquals(txtList.toString(), returnList.toString());
    }

    @Test
    void shouldReturnImgListFromMemory_whenListIsPresentInMemory() {
        db.addFlashcardList(imgList);

        ImgFlashcardCollection returnList = (ImgFlashcardCollection) db.getFlashcardList(1L, "I");

        Assertions.assertEquals(imgList.toString(), returnList.toString());
    }

    @Test
    void shouldReturnNull_whenTxtListIsNotInMemoryAndDB() {
        TxtFlashcardCollection returnList = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");

        Assertions.assertNull(returnList);
    }

    @Test
    void shouldReturnNull_whenImgListIsNotInMemoryAndDB() {
        ImgFlashcardCollection returnList = (ImgFlashcardCollection) db.getFlashcardList(1L, "I");

        Assertions.assertNull(returnList);
    }

    @Test
    void shouldReturnTxtListFromDB_whenListIsNotInMemoryButIsPresentInDB() throws IOException {
        db.addFlashcardList(txtList);
        db.saveList(1L);
        File file = new File("temp.txt");
        file.createNewFile();
        Files.copy(Path.of("db.txt"), Path.of("temp.txt"), REPLACE_EXISTING);
        db.deleteFlashcardList(1L);
        Files.delete(Path.of("db.txt"));
        file.renameTo(new File("db.txt"));


        TxtFlashcardCollection returnList = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");

        Assertions.assertEquals(txtList.toString(), returnList.toString());
    }

    @Test
    void shouldReturnImgListFromDB_whenListIsNotInMemoryButIsPresentInDB() throws IOException {
        db.addFlashcardList(imgList);
        db.saveList(1L);
        File file = new File("temp.txt");
        file.createNewFile();
        Files.copy(Path.of("db.txt"), Path.of("temp.txt"), REPLACE_EXISTING);
        db.deleteFlashcardList(1L);
        Files.delete(Path.of("db.txt"));
        file.renameTo(new File("db.txt"));


        ImgFlashcardCollection returnList = (ImgFlashcardCollection) db.getFlashcardList(1L, "I");

        Assertions.assertEquals(imgList.toString(), returnList.toString());
    }

    @Test
    void shouldReturnSameInstance_whenGettingDatabaseProxyInstance() {
        DatabaseProxy databaseProxy = DatabaseProxy.getInstance();

        Assertions.assertSame(db, databaseProxy);
    }

    @Test
    void shouldReturnSameInstance_whenGettingDatabaseInstance() {
        Database database1 = Database.getInstance();
        Database database2 = Database.getInstance();

        Assertions.assertSame(database1, database2);
    }

    @Test
    void shouldSaveToDB_whenSavingTxtListFromMemory() throws FileNotFoundException {
        db.addFlashcardList(txtList);
        db.saveList(1L);
        Scanner sc = new Scanner(new File("db.txt"));
        String saved = "";
        if (sc.hasNextLine()) {
            saved = sc.nextLine();
        }

        sc.close();

        Assertions.assertEquals(txtList.toString(), saved);
    }

    @Test
    void shouldSaveToDB_whenSavingImgListFromMemory() throws FileNotFoundException {
        db.addFlashcardList(imgList);
        db.saveList(1L);
        Scanner sc = new Scanner(new File("db.txt"));
        String saved = "";
        if (sc.hasNextLine()) {
            saved = sc.nextLine();
        }

        sc.close();

        Assertions.assertEquals(imgList.toString(), saved);
    }

    @Test
    void shouldAddListToMemory_whenAddingTxtList() {
        db.addFlashcardList(txtList);

        TxtFlashcardCollection saved = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");

        Assertions.assertEquals(txtList, saved);
    }

    @Test
    void shouldAddListToMemoryNotToDB_whenAddingTxtList() {
        db.addFlashcardList(txtList);

        int size = db.getAllLists().size();

        Assertions.assertEquals(0, size);
    }

    @Test
    void shouldAddListToMemory_whenAddingImgList() {
        db.addFlashcardList(imgList);

        ImgFlashcardCollection saved = (ImgFlashcardCollection) db.getFlashcardList(1L, "I");

        Assertions.assertEquals(imgList, saved);
    }

    @Test
    void shouldAddListToMemoryNotToDB_whenAddingImgList() {
        db.addFlashcardList(imgList);

        int size = db.getAllLists().size();

        Assertions.assertEquals(0, size);
    }

    @Test
    void shouldRemoveFromMemory_whenDeletingTxtList() {
        db.addFlashcardList(txtList);
        db.deleteFlashcardList(1L);

        TxtFlashcardCollection returnList = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");

        Assertions.assertNull(returnList);
    }

    @Test
    void shouldRemoveFromDB_whenDeletingTxtList() {
        db.addFlashcardList(txtList);
        db.saveList(1L);
        db.deleteFlashcardList(1L);

        TxtFlashcardCollection returnList = (TxtFlashcardCollection) db.getFlashcardList(1L, "T");

        Assertions.assertNull(returnList);
    }

    @Test
    void shouldRemoveFromMemory_whenDeletingImgList() {
        db.addFlashcardList(imgList);
        db.deleteFlashcardList(1L);

        ImgFlashcardCollection returnList = (ImgFlashcardCollection) db.getFlashcardList(1L, "I");

        Assertions.assertNull(returnList);
    }

    @Test
    void shouldRemoveFromDB_whenDeletingImgList() {
        db.addFlashcardList(imgList);
        db.saveList(1L);
        db.deleteFlashcardList(1L);

        ImgFlashcardCollection returnList = (ImgFlashcardCollection) db.getFlashcardList(1L, "I");

        Assertions.assertNull(returnList);
    }

    @Test
    void shouldReturnMap_whenGettingAllListsFromDB() {
        db.addFlashcardList(txtList);
        db.saveList(1L);
        Map<Long, String> returnMap = db.getAllLists();
        Map<Long, String> map = new HashMap<>();
        map.put(1L, "title");

        Assertions.assertEquals(map, returnMap);
    }

    @Test
    void shouldReturnEmptyMap_whenGettingAllListsFromDB() {
        Map<Long, String> returnMap = db.getAllLists();
        Map<Long, String> map = new HashMap<>();

        Assertions.assertEquals(map, returnMap);
    }

    @Test
    void shouldReturnTxtType_whenGettingTypeByID() {
        db.addFlashcardList(txtList);
        db.getTypeByID(1L);

        String type = db.getTypeByID(1L);

        Assertions.assertEquals("T", type);
    }

    @Test
    void shouldReturnImgType_whenGettingTypeByID() {
        db.addFlashcardList(imgList);
        db.getTypeByID(1L);

        String type = db.getTypeByID(1L);

        Assertions.assertEquals("I", type);
    }
}
