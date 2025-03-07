package Models;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        String cat1 = "Superhero";
        String cat2 = "Indie";

        inventory.addCategory(cat1);
        inventory.addCategory(cat2);

        JFXPanel jfxPanel = new JFXPanel();

        inventory.addProductsToInventory(cat1, new Product("title", "author", "artist", "hardcover",
                34.99, 33, new Image("Images/MM24.jpg")));
        inventory.addProductsToInventory(cat2, new Product("asda", "author", "artist", "trade",
                53.00, 234, new Image("images/MM24.jpg")));
        inventory.addProductsToInventory(cat1, new Product("gasfda", "author", "artist", "issue",
                67.00, 54, new Image("images/MM24.jpg")));

        for (String category : inventory.getCategories()) {
            System.out.printf("%s ", category);
        }

        LinkedList list = inventory.getUniqueCategory(cat1);
        System.out.println(list);
    }
}
//        /**
//         * Initialize all products in the inventory
//         */
//        Product P1 = new Product("title", "author", "artist", "hardcover",
//                34.99, 33, new Image("Images/MM24.jpg"));
//        Product P2 = new Product("title","author", "artist", "trade",
//                53.00, 234, new Image("images/MM24.jpg"));
//        Product P3 = new Product("title3", "author", "artist", "trade",
//                45.00, 321, new Image("images/MM24.jpg"));
//        Product P4 = new Product("title76","author", "artist", "hardcover",
//                57.00, 23, new Image("images/MM24.jpg"));
//        Product P5 = new Product("asdald", "author", "artist", "hardcover",
//                59.00, 98, new Image("images/MM24.jpg"));
//        Product P6 = new Product("fasd", "author", "artist", "hardcover",
//                67.00, 45, new Image("images/MM24.jpg"));
//
//        /**
//         * Create a LinkedList and store the products of the inventory
//         */
//        LinkedList<Product> superheroComics = new LinkedList<>();
//        LinkedList<Product> indieComics = new LinkedList<>();
//        LinkedList<Product> kidsComics = new LinkedList<>();
//        LinkedList<Product> horrorComics = new LinkedList<>();
//
//        /**
//         * Populate the LinkedLists
//         */
//        superheroComics.add(P1);
//        indieComics.add(P2);
//        superheroComics.add(P3);
//        horrorComics.add(P4);
//        kidsComics.add(P5);
//        horrorComics.add(P6);
//
//        /**
//         * Create a TreeMap to organize the LinkedList by category
//         */
//        TreeMap<String, LinkedList> treeMap = new TreeMap<>();
//        treeMap.put("Superhero",superheroComics);
//        treeMap.put("Indie",indieComics);
//        treeMap.put("Kids",kidsComics);
//        treeMap.put("Horror",horrorComics);
//
//        System.out.println(treeMap);
  //  }
//}
