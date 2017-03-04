import javafx.application.Application;
import javafx.stage.Stage;
import ui.MainMenu;

import javax.swing.*;

/**
 * Created by mathias on 28/02/17.
 */
public class Startup extends Application {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setSize(1000, 1000);
        mainMenu.setVisible(true);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Application.launch();
    }

    public void start(Stage primaryStage) throws Exception {

    }
}
