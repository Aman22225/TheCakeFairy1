package com.aman.thecakefairy;

public class Cakedata {
    private String itemName,itemDescription,ItemPrice;
    private  int itemImage;

    public Cakedata(String itemName, String itemDescription, String itemPrice, int itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        ItemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public int getItemImage() {
        return itemImage;
    }
}
