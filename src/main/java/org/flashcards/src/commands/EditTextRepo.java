package org.flashcards.src.commands;

import org.flashcards.src.Card;
import org.flashcards.src.TxtCard;
import org.flashcards.src.enums.States;
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
            System.out.println("Choose an option");
            String function = scanner.nextLine();
            switch (function) {
                case "Change Title" -> {
                    String title = scanner.nextLine();
                    Command changeTitle = new ChgTxtTitle(comHistory, txtCardRepo, title);
                    changeTitle.execute();
                }
                case "Change Answer" -> {
                    String idString = scanner.nextLine();
                    Long id = Long.parseLong(String.valueOf(idString));
                    Card card = txtCardRepo.queryFromRepo(id);
                    String answer = scanner.nextLine();
                    Command changeAnswer = new ChgAns(comHistory, card, answer);
                    changeAnswer.execute();
                }
                case "Change State" -> {
                    String idString = scanner.nextLine();
                    Long id = Long.parseLong(String.valueOf(idString));
                    Card card = txtCardRepo.queryFromRepo(id);
                    String stateString = scanner.nextLine();
                    States state = States.valueOf(stateString);
                    Command changeState = new ChgState(comHistory, card, state);
                    changeState.execute();
                }
                case "Change Text" -> {
                    String idString = scanner.nextLine();
                    Long id = Long.parseLong(String.valueOf(idString));
                    TxtCard flashcard = txtCardRepo.queryFromRepo(id);
                    String question = scanner.nextLine();
                    Command changeQuestion = new ChangeTxt(comHistory, flashcard, question);
                    changeQuestion.execute();
                }
                case "Save" -> {
                    allCards.addToAll(txtCardRepo);
                }
            }
        }

    }

    public void changeTitle(TxtCardRepo txtCardRepo, String title) {
        txtCardRepo.setTitle(title);
    }
}
