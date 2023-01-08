package org.flashcards.Gui.Components;

import java.awt.*;

public class CardFactory {
    private static final Rectangle rectangle = new CardComponent(Color.WHITE);
    private static final Rectangle smallRectangle = new SmallCardComponent(new Color(67, 69, 74));

    public static Rectangle getRectangle() {
        return rectangle;
    }

    public static Rectangle getSmallRectangle() {
        return smallRectangle;
    }
}
