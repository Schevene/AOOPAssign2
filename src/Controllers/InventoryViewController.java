package Controllers;

import Models.Inventory;

import Models.Product;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class InventoryViewController implements Initializable {
    @FXML
    private ImageView imageView;

    @FXML
    private Button sellButton;

    @FXML
    private ListView<Product> productListView;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

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

    @FXML
    private ToggleGroup sortGroup;

    //initialize the TreeMap and categories
    Inventory inventory = new Inventory();
    String cat1 = "Superhero";
    String cat2 = "Indie";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //add the categories to the inventory to ready the keys
        inventory.addCategory(cat1);
        inventory.addCategory(cat2);

        //add products to the inventory
        inventory.addProductsToInventory(cat1, new Product("Ms. Marvel", "author", "artist",
                "hardcover", 34.99, 33, new Image("Images/MM24.jpg")));
        inventory.addProductsToInventory(cat2, new Product("Paper Girls", "author", "artist", "trade",
                53.00, 234, new Image("Images/MM24.jpg")));
        inventory.addProductsToInventory(cat1, new Product("X-Men Red", "author", "artist", "issue",
                67.00, 54, new Image("Images/MM24.jpg")));

        //Populate ListView with all products, sorted, first item selected
        for(Product product: inventory.getProducts())
        {
            productListView.getItems().add(product);
        }

        //Automatically select first product
        productListView.getSelectionModel().select(0);

        //set imageView to first selected product
        updateViewWithSelected();

        //Populate choiceBox with all categories, chosen category should update the category total label
        categoryChoiceBox.getItems().addAll(inventory.getCategories());

        //Set radio buttons to a toggle group
        sortGroup = new ToggleGroup();
        priceDescRButton.setToggleGroup(sortGroup);
        priceAscRButton.setToggleGroup(sortGroup);
        nameAscRButton.setToggleGroup(sortGroup);
        nameDescRButton.setToggleGroup(sortGroup);

        //Attach methods to radio buttons to sort the ListView

        //Call method to set the total Inventory Label to total inventory
        totalInvLabel.setText(getTotalInventory() + " ");

        //set the total category total to nothing until something is updated
        totalCatLabel.setText(" ");

        //Change listener to choice box to listen for a change and activate
        //method to update the total category label
        categoryChoiceBox.setValue("Please select Category");
        categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateCatLabel(categoryChoiceBox.getSelectionModel().getSelectedItem()));

        //call method to sell one item of currently selected product when the button is pressed
        //sellButton

    }

    /**
     * Method to update to the product selected in the ListView
     * in the View and use it to update image shown to match
     */
    public void updateViewWithSelected()
    {
        Product product = productListView.getSelectionModel().getSelectedItem();
        imageView.setImage(product.getImage());
    }

    /**
     * Method to update the total inventory label with the current
     * total inventory
     * @return
     */
    public double getTotalInventory()
    {
        double sum = inventory.getProducts().stream()
                .mapToDouble(product -> product.getPrice() * product.getUnit())
                .sum();
        return sum;
    }

    public void updateCatLabel(String chosenCategory)
    {
        LinkedList<Product> productOfCategory = inventory.getUniqueCategory(chosenCategory);
        double catSum = productOfCategory.stream()
                .mapToDouble(product -> product.getPrice() * product.getUnit())
                .sum();
        totalCatLabel.setText(catSum + " ");
    }
}
