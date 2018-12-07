package Models;

import java.util.*;

public class Inventory {
    private TreeMap<String, LinkedList> inventory;

    public Inventory()
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
            inventory.get(category).add(product);
        }
        else
            inventory.get(category).add(product);
    }

    /**
     * A method to return a LinkedLIst of all products
     * @return
     */
    public LinkedList<Product> getProducts()
    {
        LinkedList<Product> products = new LinkedList<>();
        for(String category: inventory.keySet())
        {
            products.addAll(inventory.get(category));
        }
        return products;
    }

    /**
     * A method to return a list of every product in a category
     * @param chosenCategory
     * @return
     */
    public LinkedList<Product> getUniqueCategory(String chosenCategory)
    {
       return inventory.get(chosenCategory);
    }
}