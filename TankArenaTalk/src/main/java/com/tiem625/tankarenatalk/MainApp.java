package com.tiem625.tankarenatalk;

import com.tiem625.tankarenatalk.constants.GUIScenes;
import com.tiem625.tankarenatalk.controllers.MainSceneController;
import com.tiem625.tankarenatalk.utils.GUIScene;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TANK ARENA DIALOGUE MAKER 3000(tm)");
        GUIScene<MainSceneController> initScene = 
                GUIScene.initScene(GUIScenes.MAIN_MENU);
        stage.setScene(initScene.getScene());
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
