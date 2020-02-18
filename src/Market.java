package src;
import java.util.ArrayList;

public class Market {
    private String name;
    private ArrayList<Item> items;
    private double tax;

    public Market(String name, ArrayList<Item> items, double tax) {
        this.name = name;
        this.items = items;
        this.tax = tax;
    }

    public void sellGoods(Region region, Item item, SpaceShip spaceShip, Player player) {
        spaceShip.getInventory().remove(item);
        player.setCredits(player.getCredits() + (int)item.getSellPrice());
        spaceShip.inventorySize--;
    }

    public void buyGoods(Region region, Item item, SpaceShip spaceShip, Player player) {
        spaceShip.getInventory().add(item);
        player.setCredits(player.getCredits() - (int)item.getBuyPrice());
        spaceShip.inventorySize++;
    }

}
