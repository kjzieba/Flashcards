package org.flashcards.src.commands;

import org.flashcards.src.repositories.CardsRepo;
import org.flashcards.src.repositories.TxtCardRepo;

import java.util.ArrayList;
import java.util.Scanner;

public class EditTextRepo implements Command {

    Scanner scanner = new Scanner(System.in);
    private final TxtCardRepo txtCardRepo;

    private final ComHistory comHistory;

    private final ArrayList<CardsRepo> cardsRepo;

    private Long id = Long.parseLong("1");

    boolean end = false;

    public EditTextRepo(TxtCardRepo txtCardRepo, ComHistory comHistory, ArrayList<CardsRepo> cardsRepo) {
        this.txtCardRepo = txtCardRepo;
        this.comHistory = comHistory;
        this.cardsRepo = cardsRepo;
    }

    @Override
    public void execute() {
        try {
            cardsRepo.add((CardsRepo) txtCardRepo.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        while (!end) {
            System.out.println("Choose an option: A - add flashcard, E - edit flashcard, D - delete flashcard, P - print repo, U - undo R - return");
            String function = scanner.nextLine();
            switch (function) {
                case "T" -> {
                    String title = scanner.nextLine();
                    Command changeTitle = new ChgTxtTitle(comHistory, txtCardRepo, title);
                    changeTitle.execute();
                }
                case "A"->{
                    if(txtCardRepo.flashcards.contains(txtCardRepo.queryFromRepo(id))){
                        System.out.println("Id is already taken");
                    }
                    else{
                        System.out.println("Enter an question");
                        String question = scanner.nextLine();
                        System.out.println("Enter an answer");
                        String answer = scanner.nextLine();
                        id += 1;
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
                    System.out.println("Enter an Id");
                    String cardId = scanner.nextLine();
                    Long id = Long.parseLong(cardId);
                    Command editTxtCard = new EditTxtCard(txtCardRepo,comHistory,id);
                    editTxtCard.execute();
                }
                case "P" -> {
                    System.out.println(txtCardRepo);
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
