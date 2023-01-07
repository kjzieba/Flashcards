package org.flashcards.src.commands;

import org.flashcards.src.repositories.ImgCardRepo;
import org.flashcards.src.repositories.TxtCardRepo;
import org.flashcards.src.repositories.AllCards;

import java.util.Scanner;

public class AddRepo implements Command {

    private final ComHistory comHistory;
    private final AllCards allCards;
    Scanner scanner = new Scanner(System.in);

    public AddRepo(ComHistory comHistory, AllCards allCards) {
        this.comHistory = comHistory;
        this.allCards = allCards;
    }

    @Override
    public void execute() {
        System.out.println("Add Text Repo press 1 or Add Image Repo press 2:");
        String repoString = scanner.nextLine();
        int repo = Integer.parseInt(repoString);
        if (repo == 1) {
            TxtCardRepo txtCardRepo = new TxtCardRepo();
            System.out.println("Enter an Id:");
            String idString = scanner.nextLine();
            Long id = Long.parseLong(idString);
            System.out.println("Enter a title:");
            String title = scanner.nextLine();
            txtCardRepo.setTitle(title);
            SaveTxtRepo saveTxtRepo = new SaveTxtRepo(comHistory, txtCardRepo, allCards, id);
            saveTxtRepo.execute();
            comHistory.push(this);
        } else if (repo == 2) {
            ImgCardRepo imgCardRepo = new ImgCardRepo();
            System.out.println("Enter an Id:");
            String idString = scanner.nextLine();
            Long id = Long.parseLong(idString);
            System.out.println("Enter a title:");
            String title = scanner.nextLine();
            imgCardRepo.setTitle(title);
            SaveImgRepo saveImgRepo = new SaveImgRepo(comHistory, imgCardRepo, allCards, id);
            saveImgRepo.execute();
            comHistory.push(this);
        } else {
            System.out.println("You have pressed wrong button");
            comHistory.push(this);
        }
    }

}
