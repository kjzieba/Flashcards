package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;



public class Menu extends JPanel {
    private final Initializer initializer;
    JScrollPane scrollPane = new JScrollPane();
    JPanel contentMenu = new JPanel(new GridLayout(0,3));

    public Menu(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
//        getCustomTitle();
        getAddButton();
        getCardBoardPane();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Add your first repository");
        title.setFont(new Font("Arbutus", Font.PLAIN, 40));
        title.setForeground(Color.white);
        title.setBounds(160, 220, 700, 50);
        add(title);
    }

    private void getAddButton() {
        JButton addButton = new ButtonComponents().addButtonComponent(833,6);
        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
        });
        add(addButton);
    }

    private void getCardBoardPane() {
        JButton cardBoardButton = new ButtonComponents().cardTitleButtonComponent("chuj",126,110);
        scrollPane.getViewport().setBackground(GUInitializer.backgroundColor);
        scrollPane.setBounds(126, 110, 717, 403);
        scrollPane.setAutoscrolls(true);
        scrollPane.createVerticalScrollBar();
        contentMenu.add(cardBoardButton);
        scrollPane.setViewportView(contentMenu);
        cardBoardButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
        });
        add(cardBoardButton);
        add(scrollPane);

    }

}
