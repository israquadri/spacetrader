package src;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Item {

    private int sellPrice;
    private int buyPrice;
    private String name;
    private int quantity;
    //private int basePrice = 10;
    private Random varRand = new Random();
    private int variance = varRand.nextInt(8);
    private Image image;
    private ImageView imageView;
    private boolean isStolen;

    public Item(double tax, int merchantLevel, int technologyLevel, String name,
                int quantity, int basePrice) {
        sellPrice = (int) (basePrice * (1 + tax + .01 * technologyLevel));
        sellPrice = (int) (varRand.nextInt(sellPrice) + .5 * sellPrice);
        sellPrice = (int) (sellPrice - (.02 * merchantLevel * sellPrice));
        buyPrice = (int) ((sellPrice) * (1.10));
        this.name = name.substring(0, name.indexOf("<"));
        this.image = new Image(name.substring((name.indexOf("<") + 1), name.lastIndexOf(">")));
        this.quantity = quantity;
        this.isStolen = false;
    }
    //constructor for items without image
    public Item(double tax, int merchantLevel, int technologyLevel, String name,
                int quantity, int basePrice, boolean trader) {
        sellPrice = (int) (basePrice * (1 + tax + .01 * technologyLevel));
        sellPrice = (int) (varRand.nextInt(sellPrice) + .5 * sellPrice);
        sellPrice = (int) (sellPrice - (.02 * merchantLevel * sellPrice));
        buyPrice = (int) ((sellPrice) * (.75));
        this.name = name.substring(0, name.indexOf("<"));
        this.image = new Image(name.substring((name.indexOf("<") + 1), name.lastIndexOf(">")));
        this.quantity = quantity;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int newPrice) {
        buyPrice = newPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView, Image img) {
        this.imageView.setImage(img);
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    public double getVariance() {
        return variance;
    }

    public void setName(String s) {

    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setQuantity(int num) {
        this.quantity = num;
    }

    public void setStolen(boolean stolen) {
        isStolen = stolen;
    }

    public boolean isStolen() {
        return isStolen;
    }

    public String toString() {
        return this.getName();
    }
}
