package larry.gui;

public class Launcher {
    public static void main(String[] args) {
        System.setProperty("prism.order", "sw");
        System.setProperty("javafx.platform", "gtk");
        Main.main(args);
    }
}
