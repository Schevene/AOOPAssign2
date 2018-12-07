package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launch extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //instantiate the loader
        FXMLLoader loader = new FXMLLoader();
        //load the fxml file
        //getClass gets to root director of class
        loader.setLocation(getClass().getResource("InventoryView.fxml"));
        Parent parent = loader.load();
        //initialize the scene
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Comic Store Inventory");
        //loads scene on to window
        primaryStage.setScene(scene);
        //shows the stage
        primaryStage.show();
    }
}
