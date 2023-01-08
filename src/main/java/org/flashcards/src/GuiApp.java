package org.flashcards.src;

public class GuiApp {

    private static volatile GuiApp instance;
    private App app = new App();

    public static GuiApp getInstance() {
        if (instance == null) {
            synchronized (GuiApp.class) {
                if (instance == null) {
                    instance = new GuiApp();
                }
            }
        }
        return instance;
    }

    public App getApp() {
        return app;
    }
}
