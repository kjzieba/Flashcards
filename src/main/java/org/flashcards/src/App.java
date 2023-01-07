package org.flashcards.src;

import org.flashcards.src.commands.*;
import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.ImgCardRepo;
import org.flashcards.src.repositories.TxtCardRepo;

import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);

    ComHistory comHistory = new ComHistory();

    AllCards allCards = new AllCards();

    int reposNumber = 0;

    boolean end = false;

    public void run() {
        while (!end) {
            System.out.println("Choose an option: S - go to cards, P - print all repos, L - leave app");
            String commandString = scanner.nextLine();
            switch (commandString) {
                case "S" -> {
                    boolean end2 = false;
                    while (!end2) {
                        if (reposNumber == 0) {
                            Command command = new AddRepo(comHistory, allCards);
                            command.execute();
                            reposNumber += 1;
                        } else {
                            System.out.println("Choose an option: A - add repo, E - edit repo, D - delete repo, R - return");
                            String commandString2 = scanner.nextLine();
                            switch (commandString2) {
                                case "A" -> {
                                    Command command = new AddRepo(comHistory, allCards);
                                    command.execute();
                                    reposNumber += 1;
                                }
                                case "E" -> {
                                    System.out.println("Enter an Id:");
                                    String idString = scanner.nextLine();
                                    Long id = Long.parseLong(idString);
                                    if (allCards.query(id).getClass() == TxtCardRepo.class) {
                                        Command editTextRepo = new EditTextRepo((TxtCardRepo) allCards.query(id), comHistory, allCards);
                                        editTextRepo.execute();
                                    } else if (allCards.query(id).getClass() == ImgCardRepo.class) {
                                        Command editImgRepo = new EditImgRepo((ImgCardRepo) allCards.query(id), comHistory, allCards);
                                        editImgRepo.execute();
                                    } else {
                                        System.out.println("You entered a wrong id");
                                    }
                                }
                                case "D" -> {
                                    System.out.println("Enter an Id:");
                                    String idString = scanner.nextLine();
                                    Long id = Long.parseLong(idString);
                                    Command delRepo = new DelRepo(comHistory, id, allCards);
                                    delRepo.execute();
                                    reposNumber -= 1;
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
