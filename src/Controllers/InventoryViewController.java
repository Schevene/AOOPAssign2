package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryViewController implements Initializable {
    @FXML
    private ImageView imageView;

    @FXML
    private Button sellButton;

    @FXML
    private ListView<?> productListView;

    @FXML
    private ChoiceBox<?> categoryChoiceBox;

    @FXML
    private RadioButton priceDescRButton;

    @FXML
    private RadioButton priceAscRButton;

    @FXML
    private RadioButton nameAscRButton;

    @FXML
    private RadioButton nameDescRButton;

    @FXML
    private Label totalInvLabel;

    @FXML
    private Label totalCatLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
