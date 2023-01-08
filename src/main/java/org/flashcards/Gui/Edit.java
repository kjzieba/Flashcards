
package org.flashcards.Gui;

import org.flashcards.src.App;
import org.flashcards.src.repositories.CardsRepo;
import org.flashcards.src.repositories.TxtCardRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Edit extends JPanel {
    private final Initializer initializer;

    private final JTextField nameTextField = new JTextField("Enter a title");

    public Edit(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getBackButton();
        getSaveButton();
        getAddCardButton();
        getNameRepository();
    }

    private void getBackButton() {
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/main/resources/img/backButton.png"));
        backButton.setBounds(13, 12, 30, 30);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
            App.getInstance().deleteRepo();
        });
        add(backButton);
    }

    private void getSaveButton() {
        JButton addTextButton = new JButton("Save");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(618, 47, 210, 65);
        addTextButton.addActionListener(e -> {
            App.getInstance().setId(App.getInstance().getId() + 1);
            initializer.update(GUInitializer.Panel.ChooseMode);
            nameTextField.setText("Enter a title");
            System.out.println(App.getInstance().getAllCards());
        });
        add(addTextButton);
    }


    private void getAddCardButton() {
        JButton addTextButton = new JButton("Add Flashcard");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(375, 47, 210, 65);
        addTextButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.AddTxtCard);
        });
        add(addTextButton);
    }

    private void getNameRepository() {
        nameTextField.setFont(new Font("Arbutus", Font.PLAIN, 16));
        nameTextField.setBackground(new java.awt.Color(67, 69, 74));
        nameTextField.setForeground(Color.white);
        nameTextField.setBounds(149, 47, 210, 65);
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                CardsRepo cardsRepo = App.getInstance().getAllCards().query(App.getInstance().getId());
                TxtCardRepo txtCardRepo = (TxtCardRepo) cardsRepo;
                txtCardRepo.setTitle(nameTextField.getText());
            }
        });
        add(nameTextField);
    }

}