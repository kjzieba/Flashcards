package org.flashcards;

import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.commands.*;
import org.flashcards.creators.ImgCardCreator;
import org.flashcards.creators.TxtCardCreator;
import org.flashcards.repositories.AllCards;

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

    private ComHistory comHistory = new ComHistory();

    private AllCards allCards = new AllCards();

    private ArrayList<Long> addedId = new ArrayList<>();

    private ArrayList<FlashcardCollectionInterface> deletedRepos = new ArrayList<>();

    private ArrayList<Long> deletedId = new ArrayList<>();

    private ArrayList<Long> editedId = new ArrayList<>();

    private final TxtCardCreator txtCardCreator= new TxtCardCreator();

    private final ImgCardCreator imgCardCreator= new ImgCardCreator();


    private Long idRepo = 1L;

    private Long idCards = 1L;

    private int reposNumber = 0;

    boolean end = false;

    String title;
    ArrayList<Integer> optionHistory = new ArrayList<>();

    ArrayList<FlashcardCollectionInterface> reposHistory = new ArrayList<>();

public TxtCard createEmptyTxtCard(){
    TxtCard txtCard = txtCardCreator.createFlashcard(idCards, "","");
    idCards = idCards + 1;
    return txtCard;
}
    public void addTxtRepo(){
        Command command = new AddTxtRepo(comHistory, allCards,addedId, idRepo);
        command.execute();
        this.title=title;
        reposNumber += 1;
        optionHistory.add(1);
    }

    public void addImgRepo(String title){
        Command command = new AddImgRepo(comHistory, allCards, title, addedId, idCards);
        command.execute();
        this.title = title;
        reposNumber += 1;
        optionHistory.add(1);
    }

    public void addTxtCard(TxtCard card){
        Command command = new AddTxtCard(idRepo, comHistory,allCards, card);
        command.execute();
        reposNumber += 1;
        optionHistory.add(1);
    }

    public void editRepo(){
        if (allCards.query(idRepo).getClass() == TxtFlashcardCollection.class) {
            editedId.add(idRepo);
            Command editTextRepo = new EditTextRepo((TxtFlashcardCollection) allCards.query(idRepo), comHistory, reposHistory);
            editTextRepo.execute();
            optionHistory.add(2);
        } else if (allCards.query(idRepo).getClass() == ImgFlashcardCollection.class) {
            editedId.add(idRepo);
//            Command editImgRepo = new EditImgRepo((ImgFlashcardCollection) allCards.query(idRepo), comHistory, reposHistory);
//            editImgRepo.execute();
            optionHistory.add(2);
        } else {
            System.out.println("You entered a wrong id");
        }
    }
    public void deleteRepo(){
        Command delRepo = new DelRepo(comHistory, idRepo, allCards, deletedRepos, deletedId);
        delRepo.execute();
        reposNumber -= 1;
        optionHistory.add(3);
    }

    public void undo(){
        int option = optionHistory.remove(optionHistory.size() - 1);
        System.out.println(option);
        if (option == 1) {
            allCards.deleteFromAll(addedId.remove(addedId.size() - 1));
            comHistory.pop();
            reposNumber -= 1;
        } else if (option == 2) {
            allCards.deleteFromAll(editedId.get(editedId.size()-1));
            allCards.addToAll(editedId.remove(editedId.size()-1),reposHistory.remove(reposHistory.size()-1));
            comHistory.pop();
        } else if (option == 3) {
            allCards.addToAll(deletedId.remove(deletedId.size() - 1), deletedRepos.remove(deletedRepos.size() - 1));
            comHistory.pop();
            reposNumber += 1;
        }
    }
    public void print(){
        allCards.print();
    }

    public void printOne(){
        System.out.println(allCards.query(idRepo));
    }

    public ComHistory getComHistory() {
        return comHistory;
    }

    public void setComHistory(ComHistory comHistory) {
        this.comHistory = comHistory;
    }

    public AllCards getAllCards() {
        return allCards;
    }

    public void setAllCards(AllCards allCards) {
        this.allCards = allCards;
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

    public Long getIdCards() {
        return idCards;
    }

    public void setIdCards(Long idCards) {
        this.idCards = idCards;
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

    public ArrayList<Integer> getOptionHistory() {
        return optionHistory;
    }

    public void setOptionHistory(ArrayList<Integer> optionHistory) {
        this.optionHistory = optionHistory;
    }

    public ArrayList<FlashcardCollectionInterface> getReposHistory() {
        return reposHistory;
    }

    public void setReposHistory(ArrayList<FlashcardCollectionInterface> reposHistory) {
        this.reposHistory = reposHistory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
