package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;


public class Menu extends JPanel {
    private final Initializer initializer;
    private JScrollPane scrollPane = new JScrollPane();
    private JPanel contentMenu = new JPanel(new GridLayout(0, 3));

    private Map<Long, String> cards;

    public Menu(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        cards = App.getInstance().getAllCards().getAllLists();
        if (cards.isEmpty()) {
            getCustomTitle();
        } else {
            getCardBoardPane();
        }
        getAddButton();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Add your first repository");
        title.setFont(new Font("Arbutus", Font.PLAIN, 40));
        title.setForeground(Color.white);
        title.setBounds(160, 220, 700, 50);
        add(title);
    }

    private void getAddButton() {
        JButton addButton = new ButtonComponents().addButtonComponent(833, 6);
        addButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
        });
        add(addButton);
    }

    private void getCardBoardPane() {
        cards = App.getInstance().getAllCards().getAllLists();
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem edit = new JMenuItem("Edit");
        getAddDescription();

        for (var entry : cards.entrySet()) {
            JButton cardBoardButton = new ButtonComponents().cardTitleButtonComponent(entry.getValue(), 126, 110);
            cardBoardButton.setBorder(BorderFactory.createCompoundBorder(
                    cardBoardButton.getBorder(),
                    BorderFactory.createEmptyBorder(35, 0, 35, 0)));
            contentMenu.add(cardBoardButton);
            cardBoardButton.addActionListener(e -> {
                App.getInstance().setCurrentRepo(entry.getKey());
                initializer.update(GUInitializer.Panel.ChooseMode);
            });
            cardBoardButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent releasedEvent) {
                    if (SwingUtilities.isRightMouseButton(releasedEvent) && releasedEvent.getClickCount() == 1) {
                        popupMenu.add(edit);
                        popupMenu.add(delete);
                        popupMenu.show(cardBoardButton, releasedEvent.getX(), releasedEvent.getY());
                    }
                }

                @Override
                public void mouseReleased(MouseEvent releasedEvent) {
                    App.getInstance().setCurrentRepo(entry.getKey());
                    edit.addActionListener(event -> initializer.update(GUInitializer.Panel.EditTxtRepo));
                    delete.addActionListener(event -> {
                        App.getInstance().deleteRepo("T");
                        initializer.update(GUInitializer.Panel.Menu);
                    });
                }
            });

            contentMenu.revalidate();
        }
        scrollPane.getViewport().setBackground(GUInitializer.backgroundColor);
        scrollPane.setBounds(126, 110, 717, 403);
        scrollPane.setAutoscrolls(true);
        scrollPane.createVerticalScrollBar();
        scrollPane.getVerticalScrollBar().setUI(null);
        scrollPane.setViewportView(contentMenu);
        add(scrollPane);
    }

    public void checkCards() {
        contentMenu.removeAll();
        contentMenu.repaint();
        contentMenu.revalidate();
        removeAll();
        repaint();
        revalidate();
        cards = App.getInstance().getAllCards().getAllLists();
        if (cards.isEmpty()) {
            getCustomTitle();
        } else {
            getCardBoardPane();
        }
        getAddButton();
    }

    public void getAddDescription() {
        JLabel addNewCard = new JLabel("Add new repo");
        addNewCard.setFont(new Font("Arbutus", Font.PLAIN, 13));
        addNewCard.setForeground(Color.white);
        addNewCard.setBounds(710, 7, 210, 40);
        add(addNewCard);
    }
}
