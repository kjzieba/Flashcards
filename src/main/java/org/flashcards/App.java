package org.flashcards;

import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.commands.*;
import org.flashcards.creators.ImgCardCreator;
import org.flashcards.creators.TxtCardCreator;
import org.flashcards.db.DatabaseProxy;
import org.flashcards.enums.States;

import java.util.ArrayList;

public class App {
    private static volatile App instance;

    public static App getInstance() {
        if (instance == null) {
            synchronized (App.class) {
                if (instance == null) {
                    instance = new App();
                }
            }
        }
        return instance;
    }

    private App() {
        Long num = DatabaseProxy.getInstance().getAllLists().keySet().stream().max(Long::compareTo).orElse(1L);
        this.idRepo = num + 1;
    }


    private final DatabaseProxy dbProxy = DatabaseProxy.getInstance();

    private ArrayList<Long> addedId = new ArrayList<>();

    private ArrayList<FlashcardCollectionInterface> deletedRepos = new ArrayList<>();

    private ArrayList<Long> deletedId = new ArrayList<>();

    private ArrayList<Long> editedId = new ArrayList<>();

    private final TxtCardCreator txtCardCreator = new TxtCardCreator();

    private final ImgCardCreator imgCardCreator = new ImgCardCreator();


    private Long idRepo;

    private Long currentRepo;

    private Long idTxtCards = 1L;

    private Long idImgCards = 1L;

    private int reposNumber = 0;

    boolean end = false;


    ArrayList<FlashcardCollectionInterface> reposHistory = new ArrayList<>();

    public TxtCard createEmptyTxtCard() {
        TxtCard txtCard = txtCardCreator.createFlashcard(idTxtCards, "", "");
        idTxtCards = idTxtCards + 1;
        return txtCard;
    }

    public ImgCard createEmptyImgCard() {
        ImgCard imgCard = imgCardCreator.createFlashcard(idImgCards, "", "src/main/resources/img/Empty.png");
        idImgCards = idImgCards + 1;
        return imgCard;
    }

    public void addTxtRepo() {
        Command command = new AddTxtRepo(addedId, idRepo);
        command.execute();
        reposNumber += 1;
    }

    public void addImgRepo() {
        Command command = new AddImgRepo(addedId, idRepo);
        command.execute();
        reposNumber += 1;
    }

    public void addTxtCard(TxtCard card) {
        Command command = new AddTxtCard(currentRepo, card);
        command.execute();
    }

    public void addImgCard(ImgCard card) {
        Command command = new AddImgCard(currentRepo, card);
        command.execute();
    }

    public void changeTxtTitle(String title) {
        Command command = new ChgTxtTitle(currentRepo, title);
        command.execute();
    }

    public void changeImgTitle(String title) {
        Command command = new ChgImgTitle(currentRepo, title);
        command.execute();
    }

    public void changeAnswer(Card card, String answer) {
        Command command = new ChgAns(card, answer);
        command.execute();
    }

    public void changeQuestion(TxtCard card, String question) {
        Command command = new ChgQue(card, question);
        command.execute();
    }

    public void changeImage(ImgCard card, String filePath) {
        Command command = new ChgImg(card, filePath);
        command.execute();
    }

    public void saveEditedTxtRepo(TxtFlashcardCollection txtFlashcardCollection){
        Command command = new SaveTxtRepo(txtFlashcardCollection, currentRepo);
        command.execute();
    }

    public void saveEditedImgRepo(ImgFlashcardCollection imgFlashcardCollection){
        Command command = new SaveImgRepo(imgFlashcardCollection, currentRepo);
        command.execute();
    }
    public void deleteRepo(String type) {
        Command delRepo = new DelRepo(currentRepo, deletedRepos, deletedId, type);
        delRepo.execute();
        reposNumber -= 1;
    }

    public void changeStateToFlagged(TxtCard txtCard){
        Command command = new ChgState(txtCard,States.FLAGGED);
        command.execute();
    }

    public void changeStateToNotFlagged(TxtCard txtCard){
        Command command = new ChgState(txtCard, States.NOTFLAGGED);
        command.execute();
    }

    public void saveTxtToMemento(FlashcardTxtHistory flashcardTxtHistory, TxtCard txtCard){
        Command command = new DelTxtCard(flashcardTxtHistory, idRepo, txtCard);
        command.execute();
    }

    public void saveImgToMemento(FlashcardImgHistory flashcardImgHistory, ImgCard imgCard){
        Command command = new DelImgCard(flashcardImgHistory, idRepo, imgCard);
        command.execute();
    }

    public TxtCard restoreTxtFromMemento(FlashcardTxtHistory flashcardTxtHistory){
        MementoTxtCard mementoTxtCard = flashcardTxtHistory.pop();
        return new TxtCard(mementoTxtCard.getId(), mementoTxtCard.getTextQuestion(), mementoTxtCard.getAnswer());
    }

    public ImgCard restoreImgFromMemento(FlashcardImgHistory flashcardImgHistory){
        MementoImgCard mementoImgCard = flashcardImgHistory.pop();
        return new ImgCard(mementoImgCard.getId(), mementoImgCard.getAnswer(), mementoImgCard.getImage());
    }


    public DatabaseProxy getAllCards() {
        return dbProxy;
    }

    public ArrayList<Long> getAddedId() {
        return addedId;
    }

    public void setAddedId(ArrayList<Long> addedId) {
        this.addedId = addedId;
    }

    public ArrayList<FlashcardCollectionInterface> getDeletedRepos() {
        return deletedRepos;
    }

    public void setDeletedRepos(ArrayList<FlashcardCollectionInterface> deletedRepos) {
        this.deletedRepos = deletedRepos;
    }

    public ArrayList<Long> getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(ArrayList<Long> deletedId) {
        this.deletedId = deletedId;
    }

    public ArrayList<Long> getEditedId() {
        return editedId;
    }

    public void setEditedId(ArrayList<Long> editedId) {
        this.editedId = editedId;
    }

    public static void setInstance(App instance) {
        App.instance = instance;
    }


    public Long getIdRepo() {
        return idRepo;
    }

    public void setIdRepo(Long idRepo) {
        this.idRepo = idRepo;
    }

    public Long getIdTxtCards() {
        return idTxtCards;
    }

    public void setIdTxtCards(Long idTxtCards) {
        this.idTxtCards = idTxtCards;
    }

    public Long getIdImgCards() {
        return idImgCards;
    }

    public void setIdImgCards(Long idImgCards) {
        this.idImgCards = idImgCards;
    }

    public int getReposNumber() {
        return reposNumber;
    }

    public void setReposNumber(int reposNumber) {
        this.reposNumber = reposNumber;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }


    public ArrayList<FlashcardCollectionInterface> getReposHistory() {
        return reposHistory;
    }

    public void setReposHistory(ArrayList<FlashcardCollectionInterface> reposHistory) {
        this.reposHistory = reposHistory;
    }

    public Long getCurrentRepo() {
        return currentRepo;
    }

    public void setCurrentRepo(Long currentRepo) {
        this.currentRepo = currentRepo;
    }
}
