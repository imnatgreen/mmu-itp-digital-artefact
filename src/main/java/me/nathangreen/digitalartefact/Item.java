package me.nathangreen.digitalartefact;

import org.javamoney.moneta.format.CurrencyStyle;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryFormats;
import java.util.Locale;
import java.util.UUID;

public class Item {

    private final String Id;
    private MonetaryAmount price;
    private String name;
    private int stock;
    private int rating;

    public Item(String Id, double price, String name, int stock, int rating) {
        this.Id = Id;
        this.setPrice(price);
        this.setName(name);
        this.setStock(stock);
        this.setRating(rating);
    }

    public Item(double price, String name, int stock, int rating) {
        this(UUID.randomUUID().toString(), price, name, stock, rating);
    }

    public String getId() {
        return Id;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        return MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.of(Locale.UK)
                .set(CurrencyStyle.SYMBOL)
                .build()).format(this.price);
    }

    public void setPrice(MonetaryAmount price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = Monetary.getDefaultAmountFactory().setNumber(price).setCurrency("GBP").create();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock > 0) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("Cannot set stock below 0.");
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
