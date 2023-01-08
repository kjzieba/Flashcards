package org.flashcards.Gui;

import org.flashcards.src.GuiApp;

import javax.swing.*;
import java.awt.*;

public class AddImgRepoTitle extends JPanel{
    private final Initializer initializer;

    private Edit edit;

    public AddImgRepoTitle(Initializer initializer,Edit edit) {
        this.initializer = initializer;
        this.edit = edit;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getCustomTitle();
        getBackButton();
        getTitleField();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Enter a title for new repository");
        title.setFont(new Font("Arbutus", Font.PLAIN, 30));
        title.setForeground(Color.white);
        title.setBounds(180, 120, 700, 50);
        add(title);
    }

    private void getBackButton() {
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/main/resources/img/backButton.png"));
        backButton.setBounds(13, 12, 30, 30);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Menu);
        });
        add(backButton);
    }

    private void getTitleField() {
        JTextField title = new JTextField();
        title.setFont(new Font("Arbutus", Font.PLAIN, 16));
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setBounds(375, 239, 210, 65);
        title.addActionListener(e -> {
            GuiApp.getInstance().getApp().setTitle(title.getText());
            initializer.update(GUInitializer.Panel.Edit);
            edit.getNameRepository();
            title.setText("");
            GuiApp.getInstance().getApp().addTxtRepo(GuiApp.getInstance().getApp().getTitle());
            GuiApp.getInstance().getApp().print();
        });
        add(title);
    }
}
