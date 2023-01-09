package org.flashcards;

import org.flashcards.commands.*;
import org.flashcards.repositories.AllCards;
import org.flashcards.repositories.CardsRepo;
import org.flashcards.repositories.ImgCardRepo;
import org.flashcards.repositories.TxtCardRepo;

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

    ComHistory comHistory = new ComHistory();

    AllCards allCards = new AllCards();

    ArrayList<Long> addedId = new ArrayList<>();

    ArrayList<CardsRepo> deletedRepos = new ArrayList<>();

    ArrayList<Long> deletedId = new ArrayList<>();

    ArrayList<Long> editedId = new ArrayList<>();

    public TxtCard txtCard = new TxtCard(0L, "","");

    private Long id = Long.parseLong("1");

    private int reposNumber = 0;

    boolean end = false;

    String title;
    ArrayList<Integer> optionHistory = new ArrayList<>();

    ArrayList<CardsRepo> reposHistory = new ArrayList<>();


    public void addTxtRepo(){
        Command command = new AddTxtRepo(comHistory, allCards,addedId, id);
        command.execute();
        reposNumber += 1;
        optionHistory.add(1);
    }

    public void addImgRepo(String title){
        Command command = new AddImgRepo(comHistory, allCards, title, addedId, id);
        command.execute();
        this.title = title;
        reposNumber += 1;
        optionHistory.add(1);
    }

    public void addTxtCard(){
        Command command = new AddTxtCard(id, comHistory,allCards,txtCard);
        command.execute();
        this.title = title;
        reposNumber += 1;
        optionHistory.add(1);
    }

    public void editRepo(){
        if (allCards.query(id).getClass() == TxtCardRepo.class) {
            editedId.add(id);
            Command editTextRepo = new EditTextRepo((TxtCardRepo) allCards.query(id), comHistory, reposHistory);
            editTextRepo.execute();
            optionHistory.add(2);
        } else if (allCards.query(id).getClass() == ImgCardRepo.class) {
            editedId.add(id);
            Command editImgRepo = new EditImgRepo((ImgCardRepo) allCards.query(id), comHistory, reposHistory);
            editImgRepo.execute();
            optionHistory.add(2);
        } else {
            System.out.println("You entered a wrong id");
        }
    }
    public void deleteRepo(){
        Command delRepo = new DelRepo(comHistory, id, allCards, deletedRepos, deletedId);
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
        System.out.println(allCards.query(id));
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

    public ArrayList<CardsRepo> getDeletedRepos() {
        return deletedRepos;
    }

    public void setDeletedRepos(ArrayList<CardsRepo> deletedRepos) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ArrayList<CardsRepo> getReposHistory() {
        return reposHistory;
    }

    public void setReposHistory(ArrayList<CardsRepo> reposHistory) {
        this.reposHistory = reposHistory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TxtCard getTxtCard() {
        return txtCard;
    }

    public void setTxtCard(TxtCard txtCard) {
        this.txtCard = txtCard;
    }
}
