package Models;

import javafx.scene.image.Image;

public class Product{
    private String title, category, author, artist, format;
    private double price;
    private int unit;
    private Image image;
    private static final String[] availFormats = {"hardcover", "issue", "trade", "omnibus"};


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Please enter a title.");
        } else this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Please enter a writer.");
        } else this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if (artist.isEmpty()) {
            throw new IllegalArgumentException("Please enter an artist.");
        } else this.artist = artist;
    }

    public String getFormat() {
        return format;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Please enter a price greater than zero.");
        } else this.price = price;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
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
        return this.title + " has " + this.unit + " units in stock for " + this.price + "each.\n";
    }
}
