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
    public List<String> getCategories()
    {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(inventory.keySet());
        return list;
    }

    /**
     * A method to add a product to the LinkedList of products,
     * relating it to a category
     * @param product
     */
    public void addProductsToInventory(String category, Product product)
    {
        if(!inventory.containsKey(category))
        {
            addCategory(category);
        }
        inventory.get(category).add(product);
    }

    /**
     * A method to return a LinkedLIst of all products
     * @return
     */
    public Collection getProducts()
    {
        return inventory.values();
    }

    public LinkedList<LinkedList<Product>> getUniqueCategory(String chosenCategory)
    {
        LinkedList<LinkedList<Product>> productsOfCategory = new LinkedList<>();
        for (String category: inventory.keySet())
        {
            if(chosenCategory == category)
            {
                productsOfCategory.add(inventory.get(category));
            }
        }
        return productsOfCategory;
    }
}
