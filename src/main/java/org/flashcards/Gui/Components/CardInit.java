package org.flashcards.Gui.Components;

import javax.swing.*;
import java.awt.*;

public class CardInit extends JButton {
    private CardView cardView;

    public CardInit() {
        setPreferredSize(new Dimension(512,277));
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cardView != null) {
            cardView.draw(g);
        }
    }
}
