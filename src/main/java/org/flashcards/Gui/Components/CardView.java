package org.flashcards.Gui.Components;

import java.awt.*;

public class CardView {

    private final Rectangle rectangle;

    public CardView(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void draw(Graphics g) {
        rectangle.draw(g);
    }
}
