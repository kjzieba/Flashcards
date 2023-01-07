package org.flashcards.src;

import org.flashcards.src.commands.*;
import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.CardsRepo;
import org.flashcards.src.repositories.ImgCardRepo;
import org.flashcards.src.repositories.TxtCardRepo;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);

    ComHistory comHistory = new ComHistory();

    AllCards allCards = new AllCards();

    ArrayList<Long> addedId = new ArrayList<>();

    ArrayList<CardsRepo> deletedRepos = new ArrayList<>();

    ArrayList<Long> deletedId = new ArrayList<>();

    ArrayList<Long> editedId = new ArrayList<>();

    private Long id = Long.parseLong("1");
    private int reposNumber = 0;

    boolean end = false;

    ArrayList<Integer> optionHistory = new ArrayList<>();

    ArrayList<CardsRepo> reposHistory = new ArrayList<>();

    public void run() {
        while (!end) {
            System.out.println("Choose an option: S - go to cards, P - print all repos, L - leave app");
            String commandString = scanner.nextLine();
            switch (commandString) {
                case "S" -> {
                    boolean end2 = false;
                    while (!end2) {
                        if (reposNumber == 0) {
                            Command command = new AddRepo(comHistory, allCards, addedId, id);
                            command.execute();
                            id += 1;
                            reposNumber += 1;
                            optionHistory.add(1);
                        } else {
                            System.out.println("Choose an option: A - add repo, E - edit repo, D - delete repo, P - print repos, R - return, U - Undo");
                            String commandString2 = scanner.nextLine();
                            switch (commandString2) {
                                case "A" -> {
                                    Command command = new AddRepo(comHistory, allCards, addedId, id);
                                    command.execute();
                                    id += 1;
                                    reposNumber += 1;
                                    optionHistory.add(1);
                                }
                                case "E" -> {
                                    System.out.println("Enter an Id:");
                                    String idString = scanner.nextLine();
                                    Long id = Long.parseLong(idString);
                                    if (allCards.query(id).getClass() == TxtCardRepo.class) {
                                        editedId.add(id);
                                        Command editTextRepo = new EditTextRepo((TxtCardRepo) allCards.query(id), comHistory, reposHistory);
                                        editTextRepo.execute();
                                        System.out.println(reposHistory);
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
                                case "D" -> {
                                    System.out.println("Enter an Id:");
                                    String idString = scanner.nextLine();
                                    Long id = Long.parseLong(idString);
                                    Command delRepo = new DelRepo(comHistory, id, allCards, deletedRepos, deletedId);
                                    delRepo.execute();
                                    reposNumber -= 1;
                                    optionHistory.add(3);
                                }
                                case "U" -> {
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
                                case "P" -> {
                                    allCards.print();
                                }
                                case "R" -> {
                                    end2 = true;
                                }
                            }
                        }
                    }
                }
                case "P" -> {
                    allCards.print();
                }
                case "L" -> {
                    System.out.println("Bye");
                    end = true;
                }
            }
        }
    }
}
