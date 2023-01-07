package org.flashcards.src.commands;

import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.TxtCardRepo;

import java.util.Scanner;

public class EditTextRepo implements Command {

    Scanner scanner = new Scanner(System.in);
    private final TxtCardRepo txtCardRepo;

    private final ComHistory comHistory;

    private final AllCards allCards;

    boolean end = false;

    public EditTextRepo(TxtCardRepo txtCardRepo, ComHistory comHistory, AllCards allCards) {
        this.txtCardRepo = txtCardRepo;
        this.comHistory = comHistory;
        this.allCards = allCards;
    }

    @Override
    public void execute() {
        while (!end) {
            System.out.println("Choose an option: A - add flashcard, E - edit flashcard, D - delete flashcard, R - return");
            String function = scanner.nextLine();
            switch (function) {
                case "T" -> {
                    String title = scanner.nextLine();
                    Command changeTitle = new ChgTxtTitle(comHistory, txtCardRepo, title);
                    changeTitle.execute();
                }
                case "A"->{
                    System.out.println("Enter an Id");
                    String cardId = scanner.nextLine();
                    Long id = Long.parseLong(cardId);
                    if(txtCardRepo.flashcards.contains(txtCardRepo.queryFromRepo(id))){
                        System.out.println("Id is already taken");
                    }
                    else{
                        System.out.println("Enter an question");
                        String question = scanner.nextLine();
                        System.out.println("Enter an answer");
                        String answer = scanner.nextLine();
                        Command addTxtCard = new AddTxtCard(id, comHistory, txtCardRepo, answer, question);
                        addTxtCard.execute();
                    }
                }
                case "D"->{
                    System.out.println("Enter an Id");
                    String cardId = scanner.nextLine();
                    Long id = Long.parseLong(cardId);
                    Command delTxtCard = new DelTxtCard(comHistory, txtCardRepo, id);
                    delTxtCard.execute();

                }
                case "E" -> {
                    String cardId = scanner.nextLine();
                    Long id = Long.parseLong(cardId);
                    Command editTxtCard = new EditTxtCard(txtCardRepo,comHistory,id);
                    editTxtCard.execute();
                }
                case "R" -> {
                    comHistory.push(this);
                    end = true;
                }
            }
        }

    }

    public void changeTitle(TxtCardRepo txtCardRepo, String title) {
        txtCardRepo.setTitle(title);
    }
}
