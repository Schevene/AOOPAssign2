package Models;

import javafx.scene.image.Image;

public class Product{
    /**
     * Initialize the instance variables for the class
     */
    private String title, author, artist, format;
    private double price;
    private int unit;
    private Image image;
    private static final String[] availFormats = {"hardcover", "issue", "trade", "omnibus"};

    /**
     * Constructor for Product
     * @param title
     * @param author
     * @param artist
     * @param format
     * @param price
     * @param unit
     * @param image
     */
    public Product(String title, String author, String artist, String format,
                   double price, int unit, Image image) {
        setTitle(title);
        setAuthor(author);
        setArtist(artist);
        setFormat(format);
        setPrice(price);
        setUnit(unit);
        setImage(image);
    }

    /**
     * Get method for title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set method for title that checks if it is empty
     * @param title
     */
    public void setTitle(String title) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Please enter a title.");
        } else this.title = title;
    }

    /**
     * Get method for author
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     *Set method for author that checks if it is empty
     * @param author
     */
    public void setAuthor(String author) {
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Please enter a writer.");
        } else this.author = author;
    }

    /**
     * Get method for artist
     * @return
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Set method for artist that checks if it is empty
     * @param artist
     */
    public void setArtist(String artist) {
        if (artist.isEmpty()) {
            throw new IllegalArgumentException("Please enter an artist.");
        } else this.artist = artist;
    }

    /**
     * Get method for format
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * Set method for format that checks if it is empty and then if it is a valid format
     * that exists within the store
     * @param format
     */
    public void setFormat(String format) {
        if (format.isEmpty()) {
            throw new IllegalArgumentException("Please enter a format.");
        } else {
            for (String validFormat : availFormats) {
                if (validFormat.equalsIgnoreCase(format)) {
                    this.format = format;
                    return;
                }
            }
            throw new IllegalArgumentException("Please enter comic format.");
        }
    }

    /**
     * Get method for price
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set method for price that checks if it is less than or equal to zero
     * @param price
     */
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Please enter a price greater than zero.");
        } else this.price = price;
    }

    /**
     * Get method for unit
     * @return
     */
    public int getUnit() {
        return unit;
    }

    /**
     * Set method for unit that checks if it is
     * @param unit
     */
    public void setUnit(int unit) {
        if(unit <= 0)
        {
            throw new IllegalArgumentException("Please set a unit greater than 0.");
        }
        this.unit = unit;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int sellOneUnit(int unit) {
        if (unit < 1) {
            throw new IllegalArgumentException("Unfortunately that item is out of stock.");
        } else {
            return (unit - 1);
        }
    }


    @Override
    public String toString() {
        return this.title + "  " + this.unit + " units for " + this.price;
    }
}
