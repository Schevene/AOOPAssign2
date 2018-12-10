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

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InventoryViewController implements Initializable {
    @FXML
    private ImageView imageView;

    @FXML
    private Button sellButton;

    @FXML
    private ListView<Product> productListView;

    @FXML
    private ComboBox<String> categoryComboBox;

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
    String cat3 = "Horror";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //add the categories to the inventory to ready the keys
        inventory.addCategory(cat1);
        inventory.addCategory(cat2);
        inventory.addCategory(cat3);

        //add products to the inventory
        inventory.addProductsToInventory(cat1, new Product("Ms. Marvel #24", "Wilson", "Alphona",
                "issue", 4.99, 13, new Image("Images/MM24.jpg")));
        inventory.addProductsToInventory(cat2, new Product("Paper Girls", "Vaughan", "Chiang",
                "trade", 11.99, 14,
                new Image("Images/pp1.jpg")));
        inventory.addProductsToInventory(cat1, new Product("X-Men Red #25", "Taylor", "Charest",
                "issue",4.99, 4, new Image("Images/sx.jpg")));
        inventory.addProductsToInventory(cat3, new Product("Animosity Year One","Bennett",
                "de Latorre", "hardcover", 36.22, 19, new Image("Images/an.jpg")));
        inventory.addProductsToInventory(cat1, new Product("Mighty Thor", "Aaron", "Dauterman",
                "omnibus", 39.99, 3, new Image("Images/Thor.jpg")));
        inventory.addProductsToInventory(cat1, new Product("America", "Rivera", "Quinones",
                "trade", 13.99, 3, new Image("Images/America.jpg")));
        inventory.addProductsToInventory(cat2, new Product("The Backstagers", "Tynion IV", "Sygh",
                "hardcover", 39.99, 5, new Image("Images/bs.jpg")));
        inventory.addProductsToInventory(cat2, new Product("Spell on Wheels", "Leth", "Levens",
                "trade", 19.99, 17, new Image("Images/sw.jpg")));
        inventory.addProductsToInventory(cat3, new Product("Carnage Vol 1", "Conway", "Del Mundo",
                "trade", 18.99, 2, new Image("Images/carn.jpg")));

        //Populate ListView with all products, sorted, first item selected
        for(Product product: inventory.getProducts())
        {
            productListView.getItems().add(product);
        }

        //Automatically select first product
        productListView.getSelectionModel().select(0);

        //set imageView to first selected product
        updateViewWithSelected();

        //Add prompt text to the combo box
        categoryComboBox.setPromptText("Please Select Category");

        //Populate comboBox with all categories, chosen category should update the category total label
        categoryComboBox.getItems().addAll(inventory.getCategories());

        //Call method to set the total Inventory Label to total inventory
        totalInvLabel.setText(String.format("$%.2f", getTotalInventory()));

        //set the total category total to nothing until something is updated
        totalCatLabel.setText(" ");

        //Set radio buttons to a toggle group
        sortGroup = new ToggleGroup();
        priceDescRButton.setToggleGroup(sortGroup);
        priceAscRButton.setToggleGroup(sortGroup);
        nameAscRButton.setToggleGroup(sortGroup);
        nameDescRButton.setToggleGroup(sortGroup);


        //Add an event listener to the product list to update the image when a different product is selected
        productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                updateViewWithSelected());

        //Change listener to combo box to listen for a change and activate
        //methods to update the total category label and update the viewList by the category selected
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        updateListByCat(categoryComboBox.getSelectionModel().getSelectedItem()));
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        updateCatLabel(categoryComboBox.getSelectionModel().getSelectedItem()));

        //call method to sell one item of currently selected product when the button is pressed
        sellButton.setOnAction(actionEvent -> sellButtonPressed());

        //Attach methods to radio buttons to sort the ListView
        nameDescRButton.setOnAction(event -> sortAlphaDesc());
        nameAscRButton.setOnAction(event -> sortAlphaAsc());
        priceAscRButton.setOnAction(event -> sortPriceAsc());
        priceDescRButton.setOnAction(event -> sortPriceDesc());
    }

    /**
     * Method to update to the product selected in the ListView
     * in the View and use it to update image shown to match
     */
    public void updateViewWithSelected()
    {
        if(productListView.getSelectionModel().getSelectedItem() != null)
        {
            Product product = productListView.getSelectionModel().getSelectedItem();
            imageView.setImage(product.getImage());
        }
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

    /**
     * Method to update the category total label text
     * when a category is selected from the combo box
     * @param chosenCategory
     */
    public void updateCatLabel(String chosenCategory)
    {
        LinkedList<Product> productsOfCategory = inventory.getUniqueCategory(chosenCategory);
        double catSum = productsOfCategory.stream()
                .mapToDouble(product -> product.getPrice() * product.getUnit())
                .sum();
        totalCatLabel.setText(String.format("$%.2f",catSum));
    }

    /**
     * method to update the product listing when a category is selected from the combo box
     * @param chosenCategory
     */
    public void updateListByCat(String chosenCategory)
    {
        productListView.getItems().clear();


        for(Product product : inventory.getUniqueCategory(chosenCategory))
        {
            productListView.getItems().add(product);
        }

        productListView.getSelectionModel().select(0);
        updateViewWithSelected();

        if(nameAscRButton.isSelected())
        {
            sortAlphaAsc();
        }

        if(nameDescRButton.isSelected())
        {
            sortAlphaDesc();
        }

        if(priceAscRButton.isSelected())
        {
            sortPriceAsc();
        }

        if(priceDescRButton.isSelected())
        {
            sortPriceDesc();
        }
    }

    /**
     * Method to sell a unit and update all related fields to reflect this change to stock
     */
    public void sellButtonPressed()
    {
        Product product = productListView.getSelectionModel().getSelectedItem();
        product.setUnit(product.sellOneUnit(product.getUnit()));

        productListView.refresh();
        updateViewWithSelected();
        totalInvLabel.setText(String.format("%.2f", getTotalInventory()));
        if(categoryComboBox.getSelectionModel().getSelectedItem() != null) {
            updateCatLabel(categoryComboBox.getSelectionModel().getSelectedItem());
        }
        else
            totalCatLabel.setText(" ");
    }

    /**
     * Method to sort Alphabetically A to Z
     */
    public void sortAlphaAsc()
    {
       List<Product> productList;
       productList = productListView.getItems().stream().
               sorted((a,b) -> a.getTitle().compareToIgnoreCase(b.getTitle()))
               .collect(Collectors.toList());
       productListView.getItems().clear();
       productListView.setItems(FXCollections.observableArrayList(productList));
    }

    /**
     * Method to sort Alphabetically Z to A
     */
    public void sortAlphaDesc()
    {
        List<Product> productList;
        productList = productListView.getItems().stream().
                sorted((a,b) -> b.getTitle().compareToIgnoreCase(a.getTitle()))
                .collect(Collectors.toList());
        productListView.getItems().clear();
        productListView.setItems(FXCollections.observableArrayList(productList));
    }

    /**
     * Method to sort price low to high
     */
    public void sortPriceAsc()
    {
        List<Product> productList;
        productList = productListView.getItems().stream().
                sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
        productListView.getItems().clear();
        productListView.setItems(FXCollections.observableArrayList(productList));
    }

    /**
     * Method to sort price high to low
     */
    public void sortPriceDesc()
    {
        List<Product> productList;
        productList = productListView.getItems().stream().
                sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
        productListView.getItems().clear();
        productListView.setItems(FXCollections.observableArrayList(productList));
    }
}
