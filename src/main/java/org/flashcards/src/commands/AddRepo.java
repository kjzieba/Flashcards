package org.flashcards.src.commands;

import org.flashcards.src.repositories.ImgCardRepo;
import org.flashcards.src.repositories.TxtCardRepo;
import org.flashcards.src.repositories.AllCards;

import java.util.ArrayList;
import java.util.Scanner;

public class AddRepo implements Command {

    private final ComHistory comHistory;
    private final AllCards allCards;

    private final ArrayList<Long> addedID;

    private final Long id;
    Scanner scanner = new Scanner(System.in);


    public AddRepo(ComHistory comHistory, AllCards allCards, ArrayList<Long> addedID, Long id) {
        this.comHistory = comHistory;
        this.allCards = allCards;
        this.addedID = addedID;
        this.id = id;
    }

    @Override
    public void execute() {
        System.out.println("Add Text Repo press 1 or Add Image Repo press 2:");
        String repoString = scanner.nextLine();
        int repo = Integer.parseInt(repoString);
        if (repo == 1) {
            TxtCardRepo txtCardRepo = new TxtCardRepo();
            addedID.add(id);
            System.out.println("Enter a title:");
            String title = scanner.nextLine();
            txtCardRepo.setTitle(title);
            Command saveTxtRepo = new SaveTxtRepo(comHistory, txtCardRepo, allCards, id);
            saveTxtRepo.execute();
            comHistory.push(this);
        } else if (repo == 2) {
            ImgCardRepo imgCardRepo = new ImgCardRepo();
            addedID.add(id);
            System.out.println("Enter a title:");
            String title = scanner.nextLine();
            imgCardRepo.setTitle(title);
            Command saveImgRepo = new SaveImgRepo(comHistory, imgCardRepo, allCards, id);
            saveImgRepo.execute();
            comHistory.push(this);
        } else {
            System.out.println("You have pressed wrong button");
            comHistory.push(this);
        }
    }

}
