package Models;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

import java.util.*;

public class Inventory {
    TreeMap<String, LinkedList> inventory;
    LinkedList<Product> products;

    public void Inventory()
    {
        inventory = new TreeMap<>();
    }


    /**
     * A method to add a key
     * @param category
     */
    public void addCategory(String category)
    {
        if(!inventory.containsKey(category))
        {
            inventory.put(category, new LinkedList<Product>());
        }
    }

    /**
     * A method to return a list of the categories
     * @return
     */
    public Set<String> getCategories()
    {
        return inventory.keySet();
    }

    /**
     * @param product
     * add a product to the LinkedList of products
     */
    public void addProductsToInventory(String category, Product product)
    {
        if(!inventory.containsKey(category))
        {
            addCategory(category);
        }
        inventory.get(category).add(product);
    }

    public LinkedList<Product> getProducts(Product product)
    {
        return products;
    }
}
